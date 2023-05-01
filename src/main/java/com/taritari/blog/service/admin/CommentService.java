package com.taritari.blog.service.admin;

import com.taritari.blog.emum.UrlEnum;
import com.taritari.blog.entity.BlogComment;
import com.taritari.blog.entity.dto.CommentDto;
import com.taritari.blog.utils.HttpClientUtil;

/**
 * @author taritari
 * @date 2023-05-01 16:34
 * @description
 */
public class CommentService {
    /**
     * 查询全部评论
     * */
    public String getAllComment(CommentDto blogComment){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/article/comments?page="+blogComment.getPage()+"&search="+blogComment.getKeyWord());
        return s ;
    }
    /**
     * 获取子评论
     * */
    public String getChild(String number){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/user/getChildComments?number="+number);
        return s ;
    }
    /**
     * 删除评论
     * */
    public String deleteComment(String number){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/comment/deleteCommentByNumber?number="+number);
        return s ;
    }
    /**
     * 删除子评论
     * */
    public String deleteChildComment(String id){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/comment/deleteChildComment?id="+id);
        return s ;
    }
}
