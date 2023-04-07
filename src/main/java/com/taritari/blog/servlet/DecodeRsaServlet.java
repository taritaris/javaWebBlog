package com.taritari.blog.servlet;

import cn.hutool.core.util.ObjectUtil;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.dto.UserTokenDto;
import com.taritari.blog.service.EncryptionAndDecryptionService;
import com.taritari.blog.utils.Result;
import com.taritari.blog.utils.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-05 14:23
 * @description
 */
@WebServlet("/data/decodeRsa")
public class DecodeRsaServlet extends HttpServlet {
    private final EncryptionAndDecryptionService dataService = new EncryptionAndDecryptionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        String data = request.getParameter("data");
        String username = dataService.decodeRsa(data);
        Map<String,String> map = new HashMap<>(8);
        map.put("username",username);
        if (ObjectUtil.isNotEmpty(username)) {
            ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK, map));
        } else {
            ServletUtil.sendJson(response, Result.buildResult(ResultEnum.ERROR, "Failed to decode ras"));
        }
    }
}
