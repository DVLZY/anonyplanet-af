package com.anonyplanet.service;

import com.anonyplanet.mapper.WorksMapper;
import com.anonyplanet.pojo.Works;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author 谭应有
 */
@Service
public class WorksService {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private WorksMapper worksMapper;
    @Resource
    private ObjectMapper mapper;

    private static final String GEO_KEY = "works";

    /**
     * 获取作品
     *
     * @param point 经纬度对象
     * @return 查询到的作品
     */
    public List<Works> getWorks(Point point) throws Exception {

        String user = UUID.randomUUID().toString();
        redisTemplate.opsForGeo().add(GEO_KEY, point, user);

        Distance distance = new Distance(500, RedisGeoCommands.DistanceUnit.KILOMETERS);
        Circle circle = new Circle(point, distance);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeCoordinates().includeDistance().includeCoordinates().sortAscending();

        // 利用经纬度查询指定范围内的所有作品对象
        redisTemplate.opsForZSet().remove(GEO_KEY, user);
        GeoResults<RedisGeoCommands.GeoLocation> results = redisTemplate.opsForGeo().radius(GEO_KEY, circle, args);

        List<Works> worksList = new ArrayList<>();
        // 遍历查询结果
        for (GeoResult<RedisGeoCommands.GeoLocation> result : results) {

            String wjson = mapper.writeValueAsString(result);
            // 提取作品ID
            String workID = wjson.split("\"name\":\"")[1].split("\",\"point\"")[0];

            if (!workID.equals(user)) {
                // 判断MySQL里的对象是否在Redis中有缓存
                if (redisTemplate.hasKey(workID)) {
                    //有缓存时调用缓存
                    String workJSON = (String) redisTemplate.opsForValue().get(workID);
                    Works oneWorks = mapper.readValue(workJSON, Works.class);
                    worksList.add(oneWorks);
                } else {
                    // 无缓存时，将查询到的作品对象存入缓存
                    Works oneWorks = worksMapper.selectUrlById(workID);
                    String workJSON = mapper.writeValueAsString(oneWorks);
                    redisTemplate.opsForValue().set(workJSON, oneWorks, 2, TimeUnit.HOURS);
                    worksList.add(oneWorks);
                }
            }
        }
        return worksList;
    }

    /**
     * 新增作品
     *
     * @param point 地理位置对象
     * @param works 作品对象
     */
    public void addWorks(Point point, Works works) {
        String worksId = UUID.randomUUID().toString();
        // 将作品ID和GEO信息存入Redis
        redisTemplate.opsForGeo().add(GEO_KEY, point, worksId);
        // 将作品ID和所有信息存入MySQL
        works.setWorks(worksId);
        works.setTime(LocalDateTime.now());
        works.setLongitude(point.getX());
        works.setLatitude(point.getY());
        worksMapper.insertWorks(works);
    }


}
