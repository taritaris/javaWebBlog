package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.dto.LoginDto;
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
 * @date 2023-04-04 20:01
 * @description
 */
@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BlogUserService blogUserService = new BlogUserService();
        EntityUtil entityUtil = new EntityUtil();
        LoginDto loginDto = entityUtil.parseRequestToEntity(request, LoginDto.class);
        Result result;
        try {
            result = blogUserService.login(loginDto.username, loginDto.password,loginDto.verify);
            System.out.println(result);
        } catch (Exception e) {
            result = Result.buildResult(ResultEnum.ERROR, e.getMessage());
        }
        ServletUtil.sendJson(response, result);
    }


}



