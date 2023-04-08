package com.taritari.blog.servlet;

import com.alibaba.fastjson.JSON;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.service.BlogArticleService;
import com.taritari.blog.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author taritari
 * @date 2023-04-05 14:53
 * @description
 */
@WebServlet("/article/selectAll")
public class BlogArticleSelectAllServlet extends HttpServlet {

    private final BlogArticleService blogArticleService = new BlogArticleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        List<BlogArticle> blogArticles = blogArticleService.selectAll();
        Result result = Result.buildResult(ResultEnum.OK, blogArticles);
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
}

