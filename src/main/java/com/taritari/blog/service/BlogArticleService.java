package com.taritari.blog.service;

import cn.hutool.core.convert.Convert;
import com.taritari.blog.dao.BlogArticleDao;
import com.taritari.blog.dao.BlogArticleviewsDao;
import com.taritari.blog.dao.BlogCommentDao;
import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.entity.BlogArticleviews;
import com.taritari.blog.entity.dto.ArticleDto;
import com.taritari.blog.entity.vo.SimilarityArticleVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taritari
 * @date 2023-04-05 14:55
 * @description
 */
public class BlogArticleService {
    BlogArticleDao blogArticleDao = new BlogArticleDao();
    BlogArticleviewsDao blogArticleviewsDao = new BlogArticleviewsDao();
    BlogCommentDao blogCommentDao = new BlogCommentDao();
    BlogTagService blogTagService = new BlogTagService();

    EncryptionAndDecryptionService encryptionAndDecryptionService = new EncryptionAndDecryptionService();
    public List<ArticleDto> selectAll(String tagId, int size){
        List<BlogArticle> blogArticles = blogArticleDao.selectAll(tagId, size);
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (int i = 0;i<blogArticles.size();i++){
            BlogArticle blogArticle = blogArticles.get(i);
            String numbers = blogArticle.getNumbers();
            int commentCountByNumber = blogCommentDao.getCommentCountByNumber(numbers);
            int viewsByNumber = blogArticleviewsDao.getViewsByNumber(numbers);
            ArticleDto articleDto = new ArticleDto(
                    blogArticle.getAuthor()
                    ,blogArticle.getTitle()
                    ,blogArticle.getContent()
                    ,blogArticle.getCreateTime()
                    ,blogArticle.getNumbers()
                    ,blogArticle.getImgSrc()
                    ,commentCountByNumber
                    ,viewsByNumber
                    ,blogTagService.getTagName(Convert.toInt(blogArticle.getTagId()))
            );
            articleDtos.add(articleDto);
        }
        return articleDtos;
    }
    /**
     * 按照作者查询文章
     * @return 文章列表
     * @param token
     * */
    public List<BlogArticle> selectArticleByAuthor(String token,int size){
        String author = encryptionAndDecryptionService.getUserName(token);
        List<BlogArticle> blogArticles = blogArticleDao.selectArticlesByAuthor(author,size);
        return blogArticles;
    }
    /**
     * 主页面的文章列表
     * */
    public List<ArticleDto> indexArticleList(String userName ,int size){
        List<ArticleDto> articleDtos = blogArticleDao.selectArticle(userName,size);
        for (int i =0;i<articleDtos.size();i++){
            articleDtos.get(i).setViews(blogArticleviewsDao.getViewsByNumber(articleDtos.get(i).getNumbers()));
            articleDtos.get(i).setContent(articleDtos.get(i).getContent().substring(0,20));
            articleDtos.get(i).setTag(blogTagService.getTagName(Convert.toInt(articleDtos.get(i).getTag())));
            articleDtos.get(i).setCommentCount(blogCommentDao.getCommentCountByNumber(articleDtos.get(i).getNumbers()));
        }
        return articleDtos;
    }
    /**
     * 通过numbers获取单个文章,然后文件浏览数+1
     * */
    public ArticleDto getArticleByNumber(String number){
        ArticleDto articleByNumbers = blogArticleDao.getArticleByNumbers(number);
        articleByNumbers.setViews(blogArticleviewsDao.getViewsByNumber(number));
        blogArticleviewsDao.addViews(number);
        return articleByNumbers;
    }
    /**
     * 通过用户获取文章点击排行
     * */
    public List<BlogArticle> getArticleRankTopTen(String userName){
        List<BlogArticleviews> viewsRankTopTen = blogArticleviewsDao.getViewsRankTopTen(userName);
        List<BlogArticle> blogArticles = new ArrayList<>();
        for (int i =0;i<viewsRankTopTen.size();i++){
            BlogArticleviews blogArticleviews = viewsRankTopTen.get(i);
            BlogArticle blogArticleByNumbers = blogArticleDao.getBlogArticleByNumbers(blogArticleviews.getArticleNumber());
            blogArticleByNumbers.setId(i+1);
            blogArticles.add(blogArticleByNumbers);
        }
        return blogArticles;
    }
    /**
     * 获取相识文章
     * */
    public List<SimilarityArticleVo> getSimilarityArticle(int tagId){
        List<SimilarityArticleVo> similarityArticle = blogArticleDao.getSimilarityArticle(tagId);
        for (int i =0;i<similarityArticle.size();i++){
            similarityArticle.get(i).setId(i+1);
        }
        return similarityArticle;
    }
}
