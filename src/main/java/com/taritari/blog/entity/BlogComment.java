package com.taritari.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author taritari
 * @date 2023-04-09 21:55
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogComment {
    private int id;
    private String number;
    private String articleNumber;
    private String userNumber;
    private String createTime;
    private String parentNumber;
    private String comment;
    private String parentUser;
    private String imgSrc;
    private List<BlogComment> replies;

}
