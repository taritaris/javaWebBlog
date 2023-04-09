package com.taritari.blog.service;


import com.taritari.blog.dao.BlogCommentDao;
import com.taritari.blog.entity.BlogComment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-09 22:25
 * @description
 */
public class BlogCommentService {
    private final BlogCommentDao blogCommentDao = new BlogCommentDao();
    public List<BlogComment> getBlogCommentByArticle(String numbers){
        List<BlogComment> blogCommentByArticleNumbers = blogCommentDao.getBlogCommentByArticleNumbers(numbers);
        return blogCommentByArticleNumbers;
    }
    public List<BlogComment> getBlogCommentByParentNumber(String parentNumber){
        List<BlogComment> blogCommentByArticleNumbers = blogCommentDao.getBlogCommentByParentNumber(parentNumber);
        return blogCommentByArticleNumbers;
    }
    public List<Map<BlogComment,List<BlogComment>>> getComment(String numbers){
        List<BlogComment> blogCommentByArticle = getBlogCommentByArticle(numbers);
        List<Map<BlogComment,List<BlogComment>>> list = new ArrayList<>();
        for (int i = 0;i<blogCommentByArticle.size();i++){
            Map<BlogComment,List<BlogComment>> blogCommentListMap = new HashMap<>();
            BlogComment blogComment = blogCommentByArticle.get(i);
            String number = blogComment.getNumber();
            List<BlogComment> blogCommentByParentNumber = getBlogCommentByParentNumber(number);
            List<BlogComment> blogCommentList = new ArrayList<>();
            for (int j = 0;j<blogCommentByParentNumber.size();j++){
                BlogComment blogComments = blogCommentByParentNumber.get(j);
                blogCommentList.add(blogComments);
            }
            blogCommentListMap.put(blogComment,blogCommentList);
            list.add(blogCommentListMap);
        }
        return list;
    }
}
