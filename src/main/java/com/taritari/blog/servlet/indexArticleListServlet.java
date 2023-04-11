package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.dto.ArticleDto;
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
import java.util.List;

/**
 * @author taritari
 * @date 2023-04-09 15:45
 * @description
 */
@WebServlet("/article/indexArticleList")
public class indexArticleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        String userName = request.getParameter("userName");
        int size = Integer.parseInt(request.getParameter("size"));
        BlogArticleService blogArticleService = new BlogArticleService();
        List<ArticleDto> articleDtos = blogArticleService.indexArticleList(userName,size);
        Result<List<ArticleDto>> listResult = Result.buildResult(ResultEnum.OK, articleDtos);
        ServletUtil.sendJson(response, listResult);
    }
}
