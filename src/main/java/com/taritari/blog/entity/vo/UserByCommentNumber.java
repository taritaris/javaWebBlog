package com.taritari.blog.entity.vo;

import lombok.Data;

/**
 * @author taritari
 * @date 2023-04-11 18:48
 * @description
 */
@Data
public class UserByCommentNumber {
    private int id;
    private String userName;
    private int Count;
    private String imgSrc;
}
