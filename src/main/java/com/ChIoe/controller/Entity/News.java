package com.ChIoe.controller.Entity;

import lombok.Data;

import java.util.Date;
@Data
public class News {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private String openerName;
    //private String time;//格式化处理
}
