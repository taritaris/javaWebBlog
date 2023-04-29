package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.service.BlogCommentService;
import com.taritari.blog.utils.EntityUtil;
import com.taritari.blog.utils.Result;
import com.taritari.blog.utils.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author taritari
 * @date 2023-04-15 12:10
 * @description
 */
@WebServlet("/comment/addCommentArticle")
public class addCommentArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        EntityUtil entityUtil = new EntityUtil();
        BlogComment blogComment = entityUtil.parseRequestToEntity(request, BlogComment.class);
        BlogCommentService blogCommentService = new BlogCommentService();
        int i = blogCommentService.userCommentArticle(blogComment);
        if (i!=0){
            ServletUtil.sendJson(response,Result.buildResult(ResultEnum.OK));
        }else {
            ServletUtil.sendJson(response,Result.buildResult(ResultEnum.ERROR));
        }

    }
}
