package com.taritari.blog.dao;

import cn.hutool.core.date.DateUtil;
import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.entity.dto.ArticleDto;
import com.taritari.blog.utils.CurdUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-05 14:56
 * @description
 */
public class BlogArticleDao {
    public List<BlogArticle> selectAll() {
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_article WHERE is_delete=0";
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, null);
        return getBlogArticles(resultList);
    }
    public List<BlogArticle> selectArticlesByAuthor(String author) {
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_article WHERE author = ? AND is_delete = 0";
        Object[] authors = {author};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, authors);
        return getBlogArticles(resultList);
    }
    public List<ArticleDto> selectArticle() {
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT numbers,author,title,content,createTime,numbers,imgSrc,tag FROM blog_article ba JOIN blog_tag bt ON ba.tagId = bt.id WHERE is_delete = 0;";
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql,null);
        List<ArticleDto> articleDtos = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                ArticleDto articleDto = new ArticleDto();
                articleDto.setAuthor(resultMap.get("author").toString());
                articleDto.setTitle(resultMap.get("title").toString());
                articleDto.setContent(resultMap.get("content").toString());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                articleDto.setCreateTime(sdf2.format(resultMap.get("createTime")));
                articleDto.setNumbers(resultMap.get("numbers").toString());
                articleDto.setImgSrc(resultMap.get("imgSrc").toString());
                articleDto.setTag(resultMap.get("tag").toString());
                articleDtos.add(articleDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDtos;
    }
    private List<BlogArticle> getBlogArticles(List<Map<String, Object>> resultList) {
        List<BlogArticle> blogArticles = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                BlogArticle blogArticle = new BlogArticle();
                blogArticle.setId(Integer.parseInt(resultMap.get("id").toString()));
                blogArticle.setAuthor(resultMap.get("author").toString());
                blogArticle.setTitle(resultMap.get("title").toString());
                blogArticle.setContent(resultMap.get("content").toString());
                blogArticle.setCreateTime((Date) resultMap.get("createTime"));
                blogArticle.setModifyTime((Date) resultMap.get("modifyTime"));
                blogArticle.setNumbers(resultMap.get("numbers").toString());
                blogArticle.setImgSrc(resultMap.get("imgSrc").toString());
                blogArticle.setTagId(Integer.parseInt(resultMap.get("tagId").toString()));
                blogArticles.add(blogArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogArticles;
    }
    public ArticleDto getArticleByNumbers(String numbers){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT numbers,author,title,content,createTime,numbers,imgSrc,tag FROM blog_article ba JOIN blog_tag bt ON ba.tagId = bt.id WHERE is_delete = 0 AND numbers = ?";
        Object[] number = {numbers};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql,number);
        List<ArticleDto> articleDtos = new ArrayList<>();
        ArticleDto articleDto = new ArticleDto();
        try {
            for (Map<String, Object> resultMap : resultList) {
                articleDto.setAuthor(resultMap.get("author").toString());
                articleDto.setTitle(resultMap.get("title").toString());
                articleDto.setContent(resultMap.get("content").toString());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                articleDto.setCreateTime(sdf2.format(resultMap.get("createTime")));
                articleDto.setNumbers(resultMap.get("numbers").toString());
                articleDto.setImgSrc(resultMap.get("imgSrc").toString());
                articleDto.setTag(resultMap.get("tag").toString());
                articleDtos.add(articleDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDto;
    }
}
