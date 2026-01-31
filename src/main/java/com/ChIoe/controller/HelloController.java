package com.ChIoe.controller;

import com.ChIoe.controller.Entity.News;
import com.ChIoe.Mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class HelloController {
    @Autowired
    private NewsMapper newsMapper;
    @RequestMapping("/list")
    public String list(Model model){
        List<News> list = this.newsMapper.list();
        model.addAttribute("list",list);
        return "index";
    }
    //从HTML网页跳到后台，再到add网页
    @GetMapping("add")
    public String add(){
        return "add";
    }

    //方法重载,方法名可以一样，但是映射不能一样
    //从add网页到后台并插入数据到数据库然后回到主页
    @PostMapping("add")
    public String add(News news){
        news.setCreateTime(new Date());
        this.newsMapper.add(news);
        return "redirect:/list";
    }

    //编辑操作
    @GetMapping("get")
    public String add(Integer id, Model model){
        News news = this.newsMapper.getById(id);
        model.addAttribute("news",news);
        return "edit";
    }

    @PostMapping("update")
    public String update(News news){
        /*
        * 自动绑定原理
        当 Spring MVC 看到方法参数是一个 POJO 对象（如 News news），它会：
        创建实例：调用 News 类的无参构造函数创建空对象
        遍历请求参数：获取 HTTP 请求中的所有参数（GET 的 QueryString 或 POST 的 Form Data）
        匹配属性名：根据参数名与对象属性名匹配，自动调用 setter 方法赋值
        类型转换：自动将 String 参数转换为属性对应类型（int, Date 等）*/
        this.newsMapper.update(news);
        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        this.newsMapper.delete(id);
        return "redirect:/list";
    }
}
