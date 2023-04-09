package com.taritari.blog.dao;

import cn.hutool.core.util.ObjectUtil;
import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.utils.CurdUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-09 21:57
 * @description
 */
public class BlogCommentDao {
    public List<BlogComment> getBlogCommentByArticleNumbers(String articleNumbers){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_comment WHERE parentNumber is null AND articleNumbers = ? ";
        Object[] articleNumber = {articleNumbers};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, articleNumber);
        return getBlogComments(resultList);
    }
    public List<BlogComment> getBlogCommentByParentNumber(String parentNumber){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_comment WHERE parentNumber is not null AND parentNumber = ?";
        Object[] parentNumbers = {parentNumber};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, parentNumbers);
        return getBlogComments(resultList);
    }
    private List<BlogComment> getBlogComments(List<Map<String, Object>> resultList) {
        List<BlogComment> blogComments = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                BlogComment blogComment = new BlogComment();
                blogComment.setId(Integer.parseInt(resultMap.get("id").toString()));
                blogComment.setNumber(resultMap.get("number").toString());
                blogComment.setUserNumber(resultMap.get("userNumbers").toString());
                blogComment.setArticleNumber(resultMap.get("articleNumbers").toString());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                blogComment.setCreateTime(sdf2.format(resultMap.get("createTime")));
                Object parentNumber = resultMap.get("parentNumber");
                if (ObjectUtil.isNotEmpty(parentNumber)){
                    blogComment.setParentNumber(parentNumber.toString());
                }
                blogComment.setComment(resultMap.get("comment").toString());
                Object parentUser = resultMap.get("parentUser");
                if (ObjectUtil.isNotEmpty(parentUser)){
                    blogComment.setParentUser(parentUser.toString());
                }
                blogComments.add(blogComment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogComments;
    }
}
