package com.ChIoe.JdbcTemplate;

import com.ChIoe.controller.Entity.News;

import java.util.List;

public interface NewsMapper {
    public List<News> list();
    public News getById(Integer id);
    public void add(News news);
    public void update(News news);
    public void deleteById(Integer id);
    public void batchAdd();//批量添加
    public void batchUpdate();//批量更改
    public void batchDelete();//批量删除
}
