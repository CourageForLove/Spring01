package com.ChIoe.controller;

import com.ChIoe.Repository.NewsRepository;
import com.ChIoe.JPAEntity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController//方法返回值自动转换为JSON/XML,返回给浏览器
@RequestMapping("/JPA")
public class TestJPAController {
    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/list")
    public List<News> list(){
        return this.newsRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public News getById(@PathVariable("id") Integer id){
        Optional<News> optionalNews = this.newsRepository.findById(id);
        return optionalNews.get();
    }
/*
* @RequestBody 是一个 Spring MVC 注解，
* 用于将 HTTP 请求体（body）中的 JSON/XML 数据绑定到 Java 对象。
* */
    @PostMapping("/add")
    public void add(@RequestBody News news){
    this.newsRepository.save(news);
    }
    @PutMapping("/update")
    public  void update(@RequestBody News news){
        this.newsRepository.save(news);//根据id执行修改或者添加操作
    }
    @DeleteMapping("/delete")
    public void  delete(@PathVariable("id") Integer id){
        this.newsRepository.deleteById(id);
    }
}
