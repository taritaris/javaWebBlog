package com.taritari.blog.service;

import com.taritari.blog.dao.BlogBgDao;
import com.taritari.blog.entity.BlogBg;

import java.util.List;

/**
 * @author taritari
 * @date 2023-04-10 15:20
 * @description
 */
public class BlogBgService {
    private final BlogBgDao blogBgDao = new BlogBgDao();
    public List<BlogBg> getAllSrc(){
        List<BlogBg> allSrc = blogBgDao.getAllSrc();
        return allSrc;
    }
}
