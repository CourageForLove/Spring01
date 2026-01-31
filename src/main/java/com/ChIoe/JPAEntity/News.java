package com.ChIoe.JPAEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
//JPA中的核心注解，主要作用是将一个普通的 Java 类映射到数据库表
@Table(name = "news")
public class News {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
   //与 @GeneratedValue(strategy = GenerationType.AUTO)的区别
    /*IDENTITY：交给数据库处理，简单但有限制
    AUTO：交给 JPA 提供者决策，灵活但复杂*/
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "createTime")
    private Date createTime;
    @Column(name = "openerName")
    private String openerName;
    public News() {}
}
