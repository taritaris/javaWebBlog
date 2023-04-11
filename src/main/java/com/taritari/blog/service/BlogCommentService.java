package com.taritari.blog.service;


import com.taritari.blog.dao.BlogCommentDao;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.entity.BlogUser;
import com.taritari.blog.entity.vo.NewCommentVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taritari
 * @date 2023-04-09 22:25
 * @description
 */
public class BlogCommentService {
    private final BlogCommentDao blogCommentDao = new BlogCommentDao();
    public List<BlogComment> getBlogCommentByArticle(String numbers){
        List<BlogComment> blogCommentByArticleNumbers = blogCommentDao.getBlogCommentByArticleNumbers(numbers);
        return blogCommentByArticleNumbers;
    }
    public List<BlogComment> getBlogCommentByParentNumber(String parentNumber){
        List<BlogComment> blogCommentByArticleNumbers = blogCommentDao.getBlogCommentByParentNumber(parentNumber);
        return blogCommentByArticleNumbers;
    }
    public List<BlogComment> getComment(String numbers){
        List<BlogComment> blogComments = new ArrayList<>();
        List<BlogComment> blogCommentByArticle = getBlogCommentByArticle(numbers);

        for (int i = 0;i<blogCommentByArticle.size();i++){
            BlogComment blogComment = blogCommentByArticle.get(i);
            String number = blogComment.getNumber();
            List<BlogComment> blogCommentByParentNumber = getBlogCommentByParentNumber(number);
            List<BlogComment> blogCommentList = new ArrayList<>();
            List<BlogComment> newList = new ArrayList<>();
            for (int j = 0;j<blogCommentByParentNumber.size();j++){
                BlogComment blogCommentChild = blogCommentByParentNumber.get(j);
                newList.add(blogCommentChild);
            }
            blogComment.setReplies(newList);
            blogComments.add(blogComment);
        }

        return blogComments;
    }
    /**
     * 获取最新评论列表
     * @param userName 用户名
     * @return List<NewCommentVo> 评论列表
     * */
    public List<NewCommentVo> getCommentNews(String userName){
        List<NewCommentVo> commentNews = blogCommentDao.getCommentNews(userName);
        BlogUserService blogUserService = new BlogUserService();
        for (int i =0;i<commentNews.size();i++){
            BlogUser userInfoByUserName = blogUserService.getUserInfoByUserName(commentNews.get(i).getUserName());
            commentNews.get(i).setImgSrc(userInfoByUserName.getTitleImgPath());
        }
        return commentNews;
    }
}
