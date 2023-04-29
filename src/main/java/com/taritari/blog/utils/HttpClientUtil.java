package com.taritari.blog.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
/**
 * @author taritari
 * @date 2023-04-20 16:26
 * @description
 */
public class HttpClientUtil {
    /**
     * GET请求
     *
     * @param url 路径
     * @return 结果
     */
    public static String get(String url) {
        return HttpUtil.get(url);
    }

    /**
     * POST请求
     *
     * @param url  路径
     * @param json 参数
     * @return 结果
     */
    public static String postJson(String url, Object json) {
        return HttpUtil.post(url, JSONUtil.toJsonStr(json));
    }

    /**
     * 发送HTTP请求(GET/POST)
     *
     * @param method 方法(GET/POST)
     * @param url    路径
     * @param json   参数(POST方法用到)
     * @return 结果
     */
    public static String request(String method, String url, Object json) {
        if ("GET".equalsIgnoreCase(method)) {
            return get(url);
        } else if ("POST".equalsIgnoreCase(method)) {
            return postJson(url, json);
        }
        return null;
    }

}
