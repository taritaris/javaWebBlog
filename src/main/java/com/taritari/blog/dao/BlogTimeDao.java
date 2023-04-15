package com.taritari.blog.dao;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.entity.BlogTimeLine;
import com.taritari.blog.entity.dto.TimeLineDto;
import com.taritari.blog.utils.CurdUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-15 16:28
 * @description
 */
public class BlogTimeDao {
    public List<BlogTimeLine> getBlogTimeLineByUserName(String userName){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_timeline WHERE userName = ?";
        Object[] userNames = {userName};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, userNames);
        return getBlogTimeLines(resultList);
    }
    public int insertTimeLineByNumber(TimeLineDto timeLineDto){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "INSERT into blog_timeline VALUES(NULL,?,?,?);";
        Object[] params = {DateUtil.now(),timeLineDto.getContent(),timeLineDto.getUserName()};
        return curdUtil.execute(sql, params);
    }
    private List<BlogTimeLine> getBlogTimeLines(List<Map<String, Object>> resultList) {
        List<BlogTimeLine> blogTimeLines = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                BlogTimeLine blogComment = new BlogTimeLine();
                blogComment.setId(Convert.toInt(resultMap.get("id")));
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                blogComment.setTime(sdf2.format(resultMap.get("time")));
                blogComment.setContent(resultMap.get("content").toString());
                blogComment.setUserName(resultMap.get("userName").toString());
                blogTimeLines.add(blogComment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogTimeLines;
    }
}
