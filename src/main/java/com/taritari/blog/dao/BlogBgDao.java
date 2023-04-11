package com.taritari.blog.dao;

import com.taritari.blog.entity.BlogBg;
import com.taritari.blog.entity.dto.ArticleDto;
import com.taritari.blog.utils.CurdUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-10 15:17
 * @description
 */
public class BlogBgDao {
    public List<BlogBg> getAllSrc(){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * from blog_bg";
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql,null);
        List<BlogBg> blogBgs = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                BlogBg blogBg = new BlogBg();
                blogBg.setId(Integer.parseInt(resultMap.get("id").toString()));
                blogBg.setImgSrc(resultMap.get("imgSrc").toString());
                blogBgs.add(blogBg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogBgs;
    }
}
