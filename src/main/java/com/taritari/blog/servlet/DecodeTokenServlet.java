package com.taritari.blog.servlet;

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

@WebServlet("/data/decodeToken")
public class DecodeTokenServlet extends HttpServlet {
    private final EncryptionAndDecryptionService dataService = new EncryptionAndDecryptionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        String token = request.getParameter("token");
        UserTokenDto userTokenDto = dataService.decode(token);
        if (userTokenDto != null) {
            ServletUtil.sendJson(response, Result.buildResult(ResultEnum.OK, userTokenDto));
        } else {
            ServletUtil.sendJson(response, Result.buildResult(ResultEnum.ERROR, "Failed to decode token"));
        }
    }
}
