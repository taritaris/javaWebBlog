package com.taritari.blog.servlet.admin;

import cn.hutool.core.convert.Convert;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.service.admin.ArticleService;
import com.taritari.blog.service.admin.UserService;
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
 * @date 2023-04-30 16:49
 * @description
 */
@WebServlet("/admin/article/deleteArticleById")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        ArticleService articleService = new ArticleService();
        int id = Convert.toInt(request.getParameter("id"));
        String result = articleService.deleteById(id);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,result));
    }
}
