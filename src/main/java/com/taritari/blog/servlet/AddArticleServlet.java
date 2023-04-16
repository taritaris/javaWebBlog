package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.entity.dto.LoginDto;
import com.taritari.blog.service.BlogArticleService;
import com.taritari.blog.service.BlogUserService;
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
 * @date 2023-04-16 16:09
 * @description
 */
@WebServlet("/article/addArticle")
public class AddArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BlogUserService blogUserService = new BlogUserService();
        EntityUtil entityUtil = new EntityUtil();
        BlogArticle blogArticle = entityUtil.parseRequestToEntity(request, BlogArticle.class);
        BlogArticleService blogArticleService = new BlogArticleService();
        int i = blogArticleService.addArticle(blogArticle);
        if (i!=0){
            ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK));
        }else {
            ServletUtil.sendJson(response, Result.buildResult(ResultEnum.ERROR));
        }
    }
}
