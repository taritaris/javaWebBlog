package com.taritari.blog.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author taritari
 * @date 2023-04-05 13:46
 * @description
 */
public class EntityUtil {
    public <T> T parseRequestToEntity(HttpServletRequest request, Class<T> entityClass) throws IOException {
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        T entity = JSON.parseObject(requestBody, entityClass);
        return entity;
    }
}
