package com.anonyplanet.mapper;


import com.anonyplanet.pojo.Works;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author 谭应有
 */
public interface WorksMapper {
    @Insert("insert into works(works,image,thumbnail,description,longitude,latitude,time) values(#{works},#{image},#{thumbnail},#{description},#{longitude},#{latitude},#{time})")
    void insertWorks(Works works);

    @Select("select * from works where works=#{works}")
    Works selectUrlById(String workID);
}
