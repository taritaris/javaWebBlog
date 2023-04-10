package com.taritari.blog.servlet;

import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.service.EncryptionAndDecryptionService;
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
 * @date 2023-04-10 13:34
 * @description
 */

@WebServlet("/encryptionAndDecryption")
public class EncryptionAndDecryptionServlet extends HttpServlet {
    private final EncryptionAndDecryptionService encryptionAndDecryptionService = new EncryptionAndDecryptionService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        String token = request.getParameter("token");
        String userName = encryptionAndDecryptionService.getUserName(token);
        ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK,userName));
    }
}
