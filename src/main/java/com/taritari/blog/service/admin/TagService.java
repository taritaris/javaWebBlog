package com.taritari.blog.service.admin;

import com.taritari.blog.emum.UrlEnum;
import com.taritari.blog.entity.BlogTag;
import com.taritari.blog.utils.HttpClientUtil;

/**
 * @author taritari
 * @date 2023-05-01 10:27
 * @description
 */
public class TagService {
    /**
     * 查询全部标签
     * */
    public String getAll(BlogTag tag){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/tag/tags?page="+tag.getPage()+"&tagId="+tag.getTagId());
        return s ;
    }
    /**
     * 删除标签通过姓名
     * */
    public String deleteTagByName(String tagName){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/tag/deleteTagByTagName?tagName="+tagName);
        return s ;
    }
    /**
     * 添加标签
     * */
    public String addTag(String tagName){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/tag/addTag?tagName="+tagName);
        return s ;
    }
    /**
     * 修改标签
     * */
    public String updateTag(String tagName,String updateName){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/tag/modify?tagName="+tagName+"&updateName="+updateName);
        return s ;
    }
}
