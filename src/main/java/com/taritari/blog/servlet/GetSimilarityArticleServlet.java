package com.taritari.blog.servlet;

import cn.hutool.core.convert.Convert;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.vo.SimilarityArticleVo;
import com.taritari.blog.entity.vo.UserByCommentNumber;
import com.taritari.blog.service.BlogArticleService;
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
 * @date 2023-04-14 14:28
 * @description
 */
@WebServlet("/article/getSimilarityArticle")
public class GetSimilarityArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        int tagId = Convert.toInt(request.getParameter("tagId"));
        BlogArticleService blogArticleService = new BlogArticleService();
        List<SimilarityArticleVo> similarityArticle = blogArticleService.getSimilarityArticle(tagId);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,similarityArticle));
    }
}
