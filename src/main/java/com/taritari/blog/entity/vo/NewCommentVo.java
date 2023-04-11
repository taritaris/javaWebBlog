package com.taritari.blog.entity.vo;

import lombok.Data;

/**
 * @author taritari
 * @date 2023-04-10 23:11
 * @description
 */
@Data
public class NewCommentVo {
    private int id;
    private String userName;
    private String content;
    private String imgSrc;
}
