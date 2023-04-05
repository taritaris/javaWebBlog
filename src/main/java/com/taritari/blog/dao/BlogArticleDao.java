package com.taritari.blog.dao;

import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.utils.CurdUtil;

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

    private List<BlogArticle> getBlogArticles(List<Map<String, Object>> resultList) {
        List<BlogArticle> blogArticles = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                BlogArticle blogArticle = new BlogArticle();
                blogArticle.setId(Integer.parseInt(resultMap.get("id").toString()));
                blogArticle.setAuthor(resultMap.get("author").toString());
                blogArticle.setTitle(resultMap.get("title").toString());
                blogArticle.setContent(resultMap.get("content").toString());
                blogArticle.setCreateTime((Date) resultMap.get("create_time"));
                blogArticle.setModifyTime((Date) resultMap.get("modify_time"));
                blogArticle.setNumbers(resultMap.get("numbers").toString());
                blogArticles.add(blogArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogArticles;
    }
}
