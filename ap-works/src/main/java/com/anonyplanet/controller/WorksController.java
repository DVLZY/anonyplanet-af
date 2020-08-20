package com.anonyplanet.controller;

import com.anonyplanet.pojo.Works;
import com.anonyplanet.service.WorksService;
import com.anonyplanet.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

/**
 * @author 谭应有
 */
@RestController
@RequestMapping("/works")
public class WorksController {
    @Autowired
    private WorksService worksService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object getWorks(
            @RequestParam("longitude") Double longitude, 
            @RequestParam("latitude") Double latitude
    ) {
        try {
            Point point = new Point(longitude, latitude);
            return worksService.getWorks(point);
        } catch (Exception e) {
            e.printStackTrace();
            return "作品获取失败";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public SysResult addWorks(@RequestParam("image") String image,
                              @RequestParam("thumbnail") String thumbnail,
                              @RequestParam("description") String description,
                              @RequestParam("longitude") Double longitude,
                              @RequestParam("latitude") Double latitude) {
        try {
            Works works = new Works();
            Point point = new Point(longitude, latitude);
            works.setImage(image);
            works.setThumbnail(thumbnail);
            works.setDescription(description);
            works.setLongitude(longitude);
            works.setLatitude(latitude);
            worksService.addWorks(point, works);
            return SysResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "新增失败", null);
        }
    }
}
