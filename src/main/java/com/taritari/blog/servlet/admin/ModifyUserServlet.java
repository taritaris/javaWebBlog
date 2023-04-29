package com.taritari.blog.servlet.admin;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogUser;
import com.taritari.blog.service.admin.UserService;
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
 * @date 2023-04-29 21:11
 * @description
 */
@WebServlet("/admin/user/modifyUser")
public class ModifyUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        EntityUtil entityUtil = new EntityUtil();
        UserService userService = new UserService();
        BlogUser blogUser = entityUtil.parseRequestToEntity(request, BlogUser.class);
        String s = userService.modifyUser(blogUser);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,s));
    }
}
