package com.taritari.blog.dao;

import com.taritari.blog.entity.BlogUser;
import com.taritari.blog.utils.CurdUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-05 13:10
 * @description
 */
public class UserDao {
    public BlogUser login(String username, String password) {
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_user WHERE username=? AND password=?";
        Object[] params ={username, password};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, params);
        if (resultList.size() == 1) {
            BlogUser blogUser = new BlogUser();
            Map<String, Object> resultMap = resultList.get(0);
            blogUser.setId(Integer.parseInt(resultMap.get("id").toString()));
            blogUser.setUsername((String) resultMap.get("username"));
            blogUser.setPassword((String) resultMap.get("password"));
            blogUser.setBirthday((Date) resultMap.get("birthday"));
            blogUser.setMessage((String) resultMap.get("message"));
            blogUser.setTitleImgPath((String) resultMap.get("title_img_path"));
            return blogUser;
        } else {
            return null;
        }
    }
    public int insert(BlogUser blogUser) {
        CurdUtil curdUtil = new CurdUtil();
        String sql = "INSERT INTO blog_user (number,username, password, birthday, message, title_img_path, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {blogUser.getNumber(),blogUser.getUsername(), blogUser.getPassword(), blogUser.getBirthday(), blogUser.getMessage(), blogUser.getTitleImgPath(), blogUser.getEmail()};
        return curdUtil.execute(sql, params);
    }
}
