package com.taritari.blog.service;

import com.taritari.blog.dao.BlogArticleDao;
import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.entity.dto.ArticleDto;

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
    /**
     * 主页面的文章列表
     * */
    public List<ArticleDto> indexArticleList(){
        List<ArticleDto> articleDtos = blogArticleDao.selectArticle();
        return articleDtos;
    }
    /**
     * 通过numbers获取单个文章
     * */
    public ArticleDto getArticleByNumber(String number){
        ArticleDto articleByNumbers = blogArticleDao.getArticleByNumbers(number);
        return articleByNumbers;
    }
}
