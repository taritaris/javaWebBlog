package com.taritari.blog.servlet;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.utils.Result;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/file/upload")
public class FileUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "/img/art/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        // 创建FileItemFactory工厂
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        // 创建文件上传处理器
        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
        try {
            // 解析请求
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            // 遍历文件项
            for (FileItem fileItem : fileItems) {
                // 如果是文本项,跳过
                if (fileItem.isFormField()) continue;
                // 获取文件名
                String fileName = fileItem.getName();
                // 重命名文件
                fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
                // 拼接路径
                String filePath = getServletContext().getRealPath(UPLOAD_DIR) + fileName;
                // 创建文件夹
                File file = new File(filePath);
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                // 保存文件
                fileItem.write(file);
                // 返回文件名
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(Result.buildResult(ResultEnum.OK,fileName)));
                out.flush();
                out.close();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
