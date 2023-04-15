package com.taritari.blog.entity;

import lombok.Data;

/**
 * @author taritari
 * @date 2023-04-15 16:26
 * @description
 */
@Data
public class BlogTimeLine {
    private int id;
    private String time;
    private String content;
    private String userName;
    // 截取年月时间
    private String month;
    private String year;
    private String data;
}
