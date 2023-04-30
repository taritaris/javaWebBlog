package com.taritari.blog.service.admin;

import com.taritari.blog.emum.UrlEnum;
import com.taritari.blog.entity.BlogUser;
import com.taritari.blog.utils.HttpClientUtil;


/**
 * @author taritari
 * @date 2023-04-20 16:33
 * @description
 */
public class UserService {
    /**
     * 获取全部用户
     * */
    public String getAll(int page,String username){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/user/users?page="+page+"&username="+username);
        return s ;
    }
    /**
     * 通过姓名查询姓名列表
     * */
    public String searchUserName(String username){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/user/selectUserNameByUserName?username="+username);
        return s ;
    }
    /**
     * 通过id删除用户
     * */
    public String deleteById(int id){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/user/deleteUserById?id="+id);
        return s ;
    }
    /**
     * 修改用户数据
     * */
    public String modifyUser(BlogUser user){
        String s = HttpClientUtil.get(UrlEnum.ADMIN +"/user/modify?id="+user.getId()+"&username="+user.getUsername()+"&email="+user.getEmail()+"&birthday="+user.getBirthday());
        return s;
    }

}
