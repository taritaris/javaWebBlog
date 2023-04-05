package com.taritari.blog.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * 雪花算法工具类
 */
public class SnowflakeUtils {
    private static final Snowflake snowflake = IdUtil.getSnowflake(1, 1); // 默认使用 workerId = 1, datacenterId = 1

    /**
     * 生成 long 类型的 ID
     */
    public static synchronized long generateId() {
        return snowflake.nextId();
    }

    /**
     * 生成 String 类型的 ID
     */
    public static synchronized String generateIdStr() {
        return snowflake.nextIdStr();
    }
}
