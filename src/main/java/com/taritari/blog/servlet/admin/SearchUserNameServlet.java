package com.taritari.blog.servlet.admin;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.service.admin.UserService;
import com.taritari.blog.utils.Result;
import com.taritari.blog.utils.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author taritari
 * @date 2023-04-29 14:42
 * @description
 */
@WebServlet("/admin/user/searchUserName")
public class SearchUserNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        UserService userService = new UserService();
        String username = request.getParameter("username");
        String result = userService.searchUserName(username);
        JSONObject jsonObject = JSON.parseObject(result);
        Object data = jsonObject.get("data");
        List<String> list = (List<String>) data;
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,list.stream().distinct().collect(Collectors.toList())));
    }
}
