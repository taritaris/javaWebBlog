package com.taritari.blog.dao;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.entity.vo.NewCommentVo;
import com.taritari.blog.utils.CurdUtil;
import com.taritari.blog.utils.SnowflakeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
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
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    public List<NewCommentVo> getCommentNews(String userName){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT id,userNumbers,`comment` FROM blog_comment  WHERE articleNumbers in (SELECT numbers FROM blog_article where author = ?) AND userNumbers !=? ORDER BY createTime desc LIMIT 5;";
        Object[] userNames = {userName,userName};
        List<NewCommentVo> newCommentVos = new ArrayList<>();
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, userNames);
        try {
            for (Map<String, Object> resultMap : resultList) {
                NewCommentVo newCommentVo = new NewCommentVo();
                newCommentVo.setId(Convert.toInt(resultMap.get("id")));
                newCommentVo.setUserName(resultMap.get("userNumbers").toString());
                newCommentVo.setContent(resultMap.get("comment").toString());
                newCommentVos.add(newCommentVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCommentVos;
    }
    public int getCommentCountByNumber(String number){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT count(*) as count FROM blog_comment WHERE articleNumbers =?";
        Object[] userNames = {number};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, userNames);
        int count = 0;
        try {
            for (Map<String, Object> resultMap : resultList) {
                count = Convert.toInt(resultMap.get("count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    /**
     * 用户评论
     * */
    public int commentArticle(BlogComment blogComment){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "INSERT INTO blog_comment VALUES(NULL,?,?,?,?,?,?,?)";
        List<Object> data = new ArrayList<>();
        blogComment.setNumber(SnowflakeUtils.generateIdStr());
        blogComment.setCreateTime((DateUtil.now()));
        data.add(blogComment.getNumber());
        data.add(blogComment.getArticleNumber());
        data.add(blogComment.getUserNumber());
        data.add(blogComment.getCreateTime());
        if (ObjectUtil.isNotEmpty(blogComment.getParentNumber())){
            data.add(blogComment.getParentNumber());
        }else {
            data.add(null);
        }
        data.add(blogComment.getComment());
        if (ObjectUtil.isNotEmpty(blogComment.getParentUser())){
            data.add(blogComment.getParentUser());
        }else {
            data.add(null);
        }
        System.out.println(data);
        Object[] array = data.toArray();
        return curdUtil.execute(sql, array);
    }

}
