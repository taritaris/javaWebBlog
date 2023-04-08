package com.taritari.blog.servlet;

import com.taritari.blog.entity.BlogUser;
import com.taritari.blog.service.BlogUserService;
import com.taritari.blog.utils.Result;
import com.taritari.blog.utils.ServletUtil;
import com.taritari.blog.utils.EntityUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/register")
public class UserRegisterServlet extends HttpServlet {

    private BlogUserService blogUserService;

    public void init() throws ServletException {
        super.init();
        blogUserService = new BlogUserService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        EntityUtil entityUtil = new EntityUtil();
        BlogUser blogUser = entityUtil.parseRequestToEntity(request, BlogUser.class);
        Result result = blogUserService.register(blogUser);
        ServletUtil.sendJson(response, result);
    }
}
