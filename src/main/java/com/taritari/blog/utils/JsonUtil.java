package com.taritari.blog.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * @author taritari
 * @date 2023-04-20 16:36
 * @description
 */
public class JsonUtil {
    /**
     * JSON字符串转换为实体类
     *
     * @param jsonStr JSON字符串
     * @param clazz   实体类class
     * @param <T>     泛型
     * @return 实体类对象
     */
    public static <T> T jsonToPojo(String jsonStr, Class<T> clazz) {
        return JSONUtil.toBean(jsonStr, clazz);
    }

    /**
     * 实体类转换为JSON字符串
     *
     * @param obj 实体类对象
     * @return JSON字符串
     */
    public static String pojoToJson(Object obj) {
        return JSONUtil.toJsonStr(obj);
    }
    /**
     * JSON数组字符串转换为List
     *
     * @param jsonStr JSON数组字符串
     * @param tr      泛型的TypeReference
     * @param <T>     泛型
     * @return List对象
     */
    public static <T> List<T> jsonToPojoList(String jsonStr, Class<T> clazz) {
        JSONArray jsonArray = JSONUtil.parseArray(jsonStr);
        return JSONUtil.toList(jsonArray, clazz);
    }
}
