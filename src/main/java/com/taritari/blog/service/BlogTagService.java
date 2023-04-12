package com.taritari.blog.service;

import com.taritari.blog.dao.BlogTagDao;
import com.taritari.blog.entity.BlogTag;

import java.util.List;

/**
 * @author taritari
 * @date 2023-04-12 21:47
 * @description
 */
public class BlogTagService {
    BlogTagDao blogTagDao = new BlogTagDao();
    public List<BlogTag> getAllTag(){
        List<BlogTag> allTag = blogTagDao.getAllTag();
        return allTag;
    }
    public String getTagName(int tagId){
        String tagById = blogTagDao.getTagById(tagId);
        return tagById;
    }
}
