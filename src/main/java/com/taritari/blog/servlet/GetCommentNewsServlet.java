package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogBg;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.entity.vo.NewCommentVo;
import com.taritari.blog.service.BlogBgService;
import com.taritari.blog.service.BlogCommentService;
import com.taritari.blog.utils.Result;
import com.taritari.blog.utils.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author taritari
 * @date 2023-04-11 11:00
 * @description
 */
@WebServlet("/comment/getCommentNews")
public class GetCommentNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        String userName = request.getParameter("userName");
        BlogCommentService blogCommentService = new BlogCommentService();
        List<NewCommentVo> commentNews = blogCommentService.getCommentNews(userName);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,commentNews));
    }
}
