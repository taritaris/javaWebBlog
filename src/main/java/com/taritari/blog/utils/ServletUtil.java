package com.taritari.blog.utils;

import com.alibaba.fastjson.JSON;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.utils.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtil {
    public static void sendJson(HttpServletResponse response, ResultEnum resultEnum, Object data) throws IOException {
        Result result = Result.buildResult(resultEnum, data);
        sendJson(response, result);
    }

    public static void sendJson(HttpServletResponse response, Result result) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = JSON.toJSONString(result);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}

