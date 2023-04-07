package com.taritari.blog.utils;

import cn.hutool.core.convert.Convert;
import com.taritari.blog.config.YmlConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;


/**
 * @author taritari
 * @date 2023-04-07 19:14
 * @description
 */
public class JedisPoolUtil {
    public Jedis getPool() {
        YmlConfig ymlConfig = new YmlConfig();
        Map<String, Object> redis = ymlConfig.getApplication("redis");
        String host = redis.get("host").toString();
        int port = Convert.toInt(redis.get("port"));
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(30);
        //最大连接空闲数
        config.setMaxIdle(2);
        //连接redis服务
        JedisPool pool = new JedisPool(config, host, port);
        Jedis jedis = null;
        try  {
            jedis = pool.getResource();
            return jedis;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();  //关闭连接
            }
        }
        return null;
    }
}
