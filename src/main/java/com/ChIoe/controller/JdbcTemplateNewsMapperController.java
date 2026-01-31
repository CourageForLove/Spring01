package com.ChIoe.controller;

import com.ChIoe.controller.Entity.News;
import com.ChIoe.JdbcTemplate.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//返回 JSON 数据
public class JdbcTemplateNewsMapperController {
    @Autowired
    private NewsMapper newsMapper;

    @GetMapping("/JDBCList")
    public List<News> list(){
        return this.newsMapper.list();
    }

    @GetMapping("/JDBCGetById/{id}")
    public News GetById(@PathVariable("id") Integer id){
        News news = null;
        try {
            news = this.newsMapper.getById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }
    //@RequestBody 注解的作用是将前端传回来的数据封装成为Java对象
    @PostMapping("JDBCAdd")
    public void add(@RequestBody News news){
        this.newsMapper.add(news);
    }
    @PutMapping("JDBCUpdate")
    public void update(@RequestBody News news){
        this.newsMapper.update(news);
    }
    @DeleteMapping("/JDBCDelete/{id}")
    public void delete(@PathVariable("id") Integer id){
        this.newsMapper.deleteById(id);
    }
    @GetMapping("/batchAdd")
    public void batchAdd(){
        this.newsMapper.batchAdd();
    }
    @GetMapping("/batchUpdate")
    public void batchUpdate(){
        this.newsMapper.batchUpdate();
    }

    @GetMapping("/batchDelete")
    public void batchDelete(){
        this.newsMapper.batchDelete();
    }
}
