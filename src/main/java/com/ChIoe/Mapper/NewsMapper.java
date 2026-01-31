package com.ChIoe.Mapper;

import com.ChIoe.controller.Entity.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface NewsMapper {
    @Select({"select * from news"})
    public List<News> list();

    @Insert({"insert into news(title,content,createTime,openerName) values(#{title},#{content},#{createTime},#{openerName})"})
    public void add(News news);

    @Select({"select * from news where id = #{id}"})
    public News getById(@Param("id") Integer id);

    @Update({"update news set title = #{title},content = #{content},openerName=#{openerName} where id = #{id}"})
    public void update(News news);

    @Delete({"delete from news where id = #{id}"})
    public void delete(Integer id);
}
