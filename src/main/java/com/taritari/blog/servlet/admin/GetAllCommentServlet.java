package com.taritari.blog.servlet.admin;

import com.alibaba.fastjson.JSONObject;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.entity.BlogTag;
import com.taritari.blog.entity.dto.CommentDto;
import com.taritari.blog.service.admin.CommentService;
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
 * @date 2023-05-01 16:36
 * @description
 */
@WebServlet("/admin/comment/comments")
public class GetAllCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        EntityUtil entityUtil = new EntityUtil();
        CommentService commentService = new CommentService();
        CommentDto commentDto = entityUtil.parseRequestToEntity(request, CommentDto.class);
        String s = commentService.getAllComment(commentDto);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK, JSONObject.parse(s)));
    }
}
