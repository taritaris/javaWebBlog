package com.taritari.blog.servlet.admin;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.service.admin.CommentService;
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
 * @date 2023-05-01 17:13
 * @description
 */
@WebServlet("/admin/comment/getChild")
public class GetChildCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        CommentService commentService = new CommentService();
        String number = request.getParameter("number");
        String result = commentService.getChild(number);
        Object parse = JSON.parse(result);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,parse));
    }
}
