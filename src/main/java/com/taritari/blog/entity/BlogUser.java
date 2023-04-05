package com.taritari.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BlogUser {

    private Integer id;
    private String number;
    private String username;
    private String password;
    private Date birthday;
    private String message;
    private String titleImgPath;
}