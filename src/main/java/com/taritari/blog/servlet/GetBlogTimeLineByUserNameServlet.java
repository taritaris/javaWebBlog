package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.entity.BlogTimeLine;
import com.taritari.blog.entity.vo.TimeLineDatasVo;
import com.taritari.blog.service.BlogArticleService;
import com.taritari.blog.service.BlogTimeService;
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
 * @date 2023-04-15 16:34
 * @description
 */
@WebServlet("/timeLine/getBlogTimeLineByUserName")
public class GetBlogTimeLineByUserNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        String userName = request.getParameter("userName");
        BlogTimeService blogTimeService = new BlogTimeService();
        List<TimeLineDatasVo> blogTimeLineByUserName = blogTimeService.getBlogTimeLineByUserName(userName);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,blogTimeLineByUserName));
    }
}
