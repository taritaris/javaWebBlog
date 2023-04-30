package com.taritari.blog.service.admin;

import com.taritari.blog.emum.UrlEnum;
import com.taritari.blog.entity.dto.AdminArticleDto;
import com.taritari.blog.utils.HttpClientUtil;

/**
 * @author taritari
 * @date 2023-04-30 15:27
 * @description
 */
public class ArticleService {
    /**
     * 查询全部文章
     * */
    public String getAllArticle(AdminArticleDto adminArticleDto){
        String s = HttpClientUtil.get(UrlEnum.ADMIN + "/article/articles?page="+adminArticleDto.getPage());
        return s ;
    }
    /**
     * 通过id进行文章删除
     * */
    public String deleteById(int id){
        String s = HttpClientUtil.get(UrlEnum.ADMIN+"/article/deleteArticleById?id="+id);
        return s;
    }
}
