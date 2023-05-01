package com.taritari.blog.servlet.admin;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.service.admin.TagService;
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
 * @date 2023-05-01 14:48
 * @description
 */
@WebServlet("/admin/tag/updateTag")
public class UpdateTagServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        TagService tagService = new TagService();
        String tagName = request.getParameter("tagName");
        String updateName = request.getParameter("updateName");
        String result = tagService.updateTag(tagName,updateName);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,result));
    }
}
