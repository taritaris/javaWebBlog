package com.taritari.blog.servlet;

import com.taritari.blog.utils.ImageVerificationUtil;
import com.taritari.blog.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author taritari
 * @date 2023-04-07 19:02
 * @description
 */
@WebServlet("/login/ImageVerify")
public class ImageVerifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         /*
             1.生成验证码
             2.把验证码上的文本存在session中
             3.把验证码图片发送给客户端
             */
        ImageVerificationUtil imageVerificationUtil = new ImageVerificationUtil();     //用我们的验证码类，生成验证码类对象
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        String username = req.getParameter("username");
        Jedis pool = jedisPoolUtil.getPool();
        BufferedImage image = imageVerificationUtil.getImage();  //获取验证码
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        pool.set(username, imageVerificationUtil.getText()); //将验证码的文本存在session中
        imageVerificationUtil.output(image, resp.getOutputStream());//将验证码图片响应给客户端
    }
}
