package com.ChIoe.JdbcTemplate;

import com.ChIoe.controller.Entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository//持久层注入IOC
public class NewsMapperImpl implements NewsMapper{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<News> list() {
        return this.jdbcTemplate.query(
                "select * from news",
                new BeanPropertyRowMapper<>(News.class));
        /*
        *  方法说明：
         * 1. this.jdbcTemplate.query() - Spring JdbcTemplate的核心查询方法
         * 2. 第一个参数是SQL语句，执行数据库查询
         * 3. 第二个参数是RowMapper，负责将查询结果映射为Java对象
         * 4. BeanPropertyRowMapper通过Java反射机制，自动匹配数据库列名和JavaBean属性名
         *
         * 注意事项：
         * 1. News类必须有对应的属性和setter方法
         * 2. 数据库列名最好与Java属性名一致（下划线转驼峰）
         * 3. 如果数据库字段与Java属性名不一致，需要自定义RowMapper或使用别名
        * *
        为什么要使用query:因为返回来的是一个集合而不是一个对象
         */
    }
    @Override
    public News getById(Integer id) {
        //为什么使用queryForObject:因为传回来的是一个对象
        return this.jdbcTemplate.queryForObject(
                "select * from news where id = ?",
                new Object[]{id},//参数要求为数组
                new BeanPropertyRowMapper<>(News.class)
                );
    }

    @Override
    public void add(News news) {
        this.jdbcTemplate.update(
                "insert into news(title,content,createTime,openerName) values (?,?,?,?)",
                news.getTitle(),
                news.getContent(),
                news.getCreateTime(),
                news.getOpenerName()
        );
    }

    @Override
    public void update(News news) {
        this.jdbcTemplate.update(
                "update news set title = ?,content = ?,createTime = ?,openerName = ? where id = ?",
                news.getTitle(),
                news.getContent(),
                news.getCreateTime(),
                news.getOpenerName()
        );
    }
    @Override
    public void deleteById(Integer id) {
        this.jdbcTemplate.update("delete * from news where id = ?",
                id);
    }

    @Override
    public void batchAdd() {
        List<Object[]> args = new ArrayList<>();
        args.add(new Object[]{"测1","测11",new Date(),"张三"});
        args.add(new Object[]{"测2","测22",new Date(),"张三"});
        this.jdbcTemplate.batchUpdate(
                "insert into news(title,content,createTime,openerName) values(?,?,?,?)"
                ,args);//批量操作
    }

    @Override
    public void batchUpdate() {
        List<Object[]> args = new ArrayList<>();
        args.add(new Object[]{"测12","测112",new Date(),"李四",53});
        args.add(new Object[]{"测22","测222",new Date(),"李四",54});
        this.jdbcTemplate.batchUpdate(
                "update news set title = ?,content = ?,createtime = ?,opername = ? where id = ?"
                , args);
    }

    @Override
    public void batchDelete() {
        List<Object[]> args = new ArrayList<>();
        args.add(new Object[]{53});
        args.add(new Object[]{54});
        this.jdbcTemplate.batchUpdate(
                "delete from sys_news where id = ?"
                ,args);
    }
}
