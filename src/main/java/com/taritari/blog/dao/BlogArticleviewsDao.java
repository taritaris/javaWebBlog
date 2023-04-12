package com.taritari.blog.dao;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.taritari.blog.entity.BlogArticleviews;
import com.taritari.blog.utils.CurdUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-11 20:12
 * @description
 */
public class BlogArticleviewsDao {
    public int addViews(String articleNumber){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "UPDATE blog_articleviews set numberOfViews = numberOfViews+1 WHERE articleNumber = ?";
        Object[] params = {articleNumber};
        return curdUtil.execute(sql, params);
    }
    public int getViewsByNumber(String number) {
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT numberOfViews as views FROM blog_articleviews WHERE articleNumber = ? ";
        Object[] userNames = {number};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, userNames);
        int views = 0;
        try {
            for (Map<String, Object> resultMap : resultList) {
                views = Convert.toInt(resultMap.get("views"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return views;
    }
    public List<BlogArticleviews> getViewsRankTopTen(String userName) {
        CurdUtil curdUtil = new CurdUtil();
        StringBuffer sql = new StringBuffer("SELECT * FROM blog_articleviews WHERE articleNumber IN (SELECT numbers FROM blog_article ");
        if (ObjectUtil.isNotEmpty(userName)){
            sql.append("WHERE author = ?");
        }
        sql.append(")ORDER BY numberOfViews DESC LIMIT 10");
        Object[] name = {userName};
        List<Map<String, Object>> resultList = null;
        if (ObjectUtil.isNotEmpty(userName)){
            resultList = curdUtil.queryForList(sql+"", name);
        }else {
            resultList = curdUtil.queryForList(sql+"",null);
        }
        List<BlogArticleviews> blogArticleviews = new ArrayList<>();

        try {
            for (Map<String, Object> resultMap : resultList) {
                BlogArticleviews blogArticleview = new BlogArticleviews();
                blogArticleview.setId(Convert.toInt(resultMap.get("id")));
                blogArticleview.setArticleNumber(resultMap.get("articleNumber").toString());
                blogArticleview.setNumberOfViews(resultMap.get("numberOfViews").toString());
                blogArticleviews.add(blogArticleview);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogArticleviews;
    }
}
