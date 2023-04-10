package com.taritari.blog.service;

import cn.hutool.core.util.ObjectUtil;
import com.taritari.blog.dao.UserDao;
import com.taritari.blog.emum.ResultEnum;
import com.taritari.blog.entity.BlogUser;
import com.taritari.blog.entity.dto.TokenDto;
import com.taritari.blog.utils.*;
import redis.clients.jedis.Jedis;

import java.util.Base64;

/**
 * @author taritari
 * @date 2023-04-05 13:04
 * @description
 */
public class BlogUserService {
    MD5Utils md5Utils = new MD5Utils();
    JwtUtils jwtUtils = new JwtUtils();
    UserDao userDao = new UserDao();

    public Result login(String username, String password, String verify) throws Exception {
        String password2MD5 = md5Utils.string2MD5(password);
        JedisPoolUtil jedisPoolUtil = new JedisPoolUtil();
        Jedis pool = jedisPoolUtil.getPool();
        String name = pool.get(username);
        BlogUser blogUser = userDao.login(username, password2MD5);
        if (ObjectUtil.isEmpty(blogUser)) {
            return Result.buildResult(ResultEnum.NOT_FOUND);
        }
        if (!name.equals(verify)){
            return Result.buildResult(ResultEnum.NOT_FOUND);
        }
        String token = jwtUtils.generateToken(Base64.getUrlEncoder().withoutPadding().encodeToString(RSAUtil.encrypt(blogUser.getUsername().getBytes())));
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token);
        return Result.buildResult(ResultEnum.OK,tokenDto);
    }
    /**
     * 注册
     * @param blogUser 用户类
     * @return 结果对象
     * @throws Exception 异常
     */
    public Result<Object> register(BlogUser blogUser){
        blogUser.setNumber(SnowflakeUtils.generateIdStr());
        String md52Password = md5Utils.string2MD5(blogUser.getPassword());
        blogUser.setPassword(md52Password);
        int insert = userDao.insert(blogUser);
        if (insert>0){
            return Result.buildResult(ResultEnum.OK);
        }
        return Result.buildResult(ResultEnum.BAD_REQUEST);
    }
    /**
     * 通过用户名获取用户信息
     * @param userName 用户名
     * */
    public BlogUser getUserInfoByUserName(String userName){
        BlogUser userInfoByUserName = userDao.getUserInfoByUserName(userName);
        return userInfoByUserName;
    }
}
