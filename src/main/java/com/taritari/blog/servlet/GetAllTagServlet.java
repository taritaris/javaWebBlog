package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogTag;
import com.taritari.blog.entity.BlogUser;
import com.taritari.blog.service.BlogTagService;
import com.taritari.blog.service.BlogUserService;
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
 * @date 2023-04-12 21:49
 * @description
 */
@WebServlet("/tag/geAll")
public class GetAllTagServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        BlogTagService blogTagService = new BlogTagService();
        List<BlogTag> allTag = blogTagService.getAllTag();
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,allTag));
    }
}
