package com.taritari.blog.servlet.admin;

import com.alibaba.fastjson.JSONObject;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogTag;
import com.taritari.blog.entity.dto.AdminArticleDto;
import com.taritari.blog.service.admin.ArticleService;
import com.taritari.blog.service.admin.TagService;
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
 * @date 2023-05-01 10:28
 * @description
 */
@WebServlet("/admin/tag/tags")
public class GetAllTagServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        EntityUtil entityUtil = new EntityUtil();
        TagService tagService = new TagService();
        BlogTag blogTag = entityUtil.parseRequestToEntity(request, BlogTag.class);
        String s = tagService.getAll(blogTag);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK, JSONObject.parse(s)));
    }
}
