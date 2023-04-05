package com.taritari.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author taritari
 * @date 2023-04-02 21:01
 * @description 文章类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogArticle {
    private int id;
    private String author;
    private String title;
    private String content;
    private Date createTime;
    private Date modifyTime;
    private char isDelete;
    private String numbers;
}
