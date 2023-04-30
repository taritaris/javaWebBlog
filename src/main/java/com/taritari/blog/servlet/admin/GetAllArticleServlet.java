package com.taritari.blog.servlet.admin;

import com.alibaba.fastjson.JSONObject;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogUser;
import com.taritari.blog.entity.dto.AdminArticleDto;
import com.taritari.blog.service.admin.ArticleService;
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
 * @date 2023-04-30 15:24
 * @description
 */
@WebServlet("/admin/article/articles")
public class GetAllArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        EntityUtil entityUtil = new EntityUtil();
        ArticleService articleService = new ArticleService();
        AdminArticleDto adminArticleDto = entityUtil.parseRequestToEntity(request, AdminArticleDto.class);
        String s = articleService.getAllArticle(adminArticleDto);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK, JSONObject.parse(s)));
    }
}
