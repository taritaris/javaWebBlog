package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.entity.BlogUser;
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
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-09 22:58
 * @description
 */
@WebServlet("/comment/getComment")
public class getCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BlogCommentService blogCommentService = new BlogCommentService();
        EntityUtil entityUtil = new EntityUtil();
        BlogComment blogComment = entityUtil.parseRequestToEntity(request, BlogComment.class);
        List<Map<BlogComment, List<BlogComment>>> comment = blogCommentService.getComment(blogComment.getNumber());
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,comment));
    }
}
