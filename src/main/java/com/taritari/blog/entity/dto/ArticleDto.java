package com.taritari.blog.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author taritari
 * @date 2023-04-09 15:29
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private String author;
    private String title;
    private String content;
    private String createTime;
    private String numbers;
    private String imgSrc;
    private String tag;
}
