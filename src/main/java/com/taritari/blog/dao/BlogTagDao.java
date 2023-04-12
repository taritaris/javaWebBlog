package com.taritari.blog.dao;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.entity.BlogTag;
import com.taritari.blog.utils.CurdUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-12 21:36
 * @description
 */
public class BlogTagDao {
    public List<BlogTag> getAllTag(){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_tag ";
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, null);
        return getBlogTags(resultList);
    }
    public String getTagById(int id){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_tag where tagId = ?";
        Object[] ids = {id};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, ids);
        return getBlogTags(resultList).get(0).getTagName();
    }
    private List<BlogTag> getBlogTags(List<Map<String, Object>> resultList) {
        List<BlogTag> blogTags = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                BlogTag blogTag = new BlogTag();
                blogTag.setId(Convert.toInt(resultMap.get("id")));
                blogTag.setTagId(resultMap.get("tagId").toString());
                blogTag.setTagName(resultMap.get("tagName").toString());
                blogTags.add(blogTag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogTags;
    }
}
