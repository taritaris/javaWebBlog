package com.taritari.blog.service;

import com.taritari.blog.dao.BlogArticleDao;
import com.taritari.blog.entity.BlogArticle;

import java.util.List;

/**
 * @author taritari
 * @date 2023-04-05 14:55
 * @description
 */
public class BlogArticleService {
    BlogArticleDao blogArticleDao = new BlogArticleDao();
    EncryptionAndDecryptionService encryptionAndDecryptionService = new EncryptionAndDecryptionService();
    public List<BlogArticle> selectAll(){
        List<BlogArticle> blogArticles = blogArticleDao.selectAll();
        return blogArticles;
    }
    /**
     * 按照作者查询文章
     * @return 文章列表
     * @param token
     * */
    public List<BlogArticle> selectArticleByAuthor(String token){
        String author = encryptionAndDecryptionService.getUserName(token);
        List<BlogArticle> blogArticles = blogArticleDao.selectArticlesByAuthor(author);
        return blogArticles;
    }
}
