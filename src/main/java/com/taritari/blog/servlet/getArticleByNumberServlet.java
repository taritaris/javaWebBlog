package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogUser;
import com.taritari.blog.entity.dto.ArticleDto;
import com.taritari.blog.entity.vo.NumbersVo;
import com.taritari.blog.service.BlogArticleService;
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
 * @date 2023-04-09 17:17
 * @description
 */
@WebServlet("/article/getArticleByNumber")
public class getArticleByNumberServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        EntityUtil entityUtil = new EntityUtil();
        NumbersVo numbersVo = entityUtil.parseRequestToEntity(request, NumbersVo.class);
        BlogArticleService blogArticleService = new BlogArticleService();
        ArticleDto articleDtos = blogArticleService.getArticleByNumber(numbersVo.getNumbers());
        Result<ArticleDto> articleDtoResult = Result.buildResult(ResultEnum.OK, articleDtos);
        ServletUtil.sendJson(response, articleDtoResult);
    }
}
