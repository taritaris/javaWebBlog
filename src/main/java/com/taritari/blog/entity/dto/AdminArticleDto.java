package com.taritari.blog.entity.dto;

import lombok.Data;

/**
 * @author taritari
 * @date 2023-04-30 15:26
 * @description
 */
@Data
public class AdminArticleDto {
    private Integer id;
    private int page;
    private String search;
    private String startTime;
    private String endTime;
}
