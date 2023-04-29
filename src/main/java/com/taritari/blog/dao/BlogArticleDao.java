package com.taritari.blog.dao;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.taritari.blog.entity.BlogArticle;
import com.taritari.blog.entity.dto.ArticleDto;
import com.taritari.blog.entity.vo.SimilarityArticleVo;
import com.taritari.blog.utils.CurdUtil;
import com.taritari.blog.utils.SnowflakeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-05 14:56
 * @description
 */
public class BlogArticleDao {
    public List<BlogArticle> selectAll(String tagId ,int size,String keyWord) {
        CurdUtil curdUtil = new CurdUtil();
        StringBuffer sqlBuf = new StringBuffer("SELECT * FROM blog_article WHERE is_delete=0 ");
        List<Object> data = new ArrayList<>();
        String sql ;
        List<Map<String, Object>> resultList ;
        if (!tagId.equals("0")){
             data.add(tagId);
             sqlBuf.append(" and tagId = ? ");
             if (!keyWord.equals("")){
                 sqlBuf.append(" AND title LIKE '%");
                 sqlBuf.append(keyWord);
                 sqlBuf.append("%' OR content LIKE '%");
                 sqlBuf.append(keyWord);
                 sqlBuf.append("%' ");
             }
        }else {
            if (!keyWord.equals("")){
                sqlBuf.append(" AND title LIKE '%");
                sqlBuf.append(keyWord);
                sqlBuf.append("%' OR content LIKE '%");
                sqlBuf.append(keyWord);
                sqlBuf.append("%' ");
            }
        }
        data.add(size);
        sql = sqlBuf.append(" LIMIT ?,5")+"";
        Object[] array = data.toArray();
        resultList = curdUtil.queryForList(sql, array);

        return getBlogArticles(resultList);
    }
    public List<BlogArticle> selectArticlesByAuthor(String author,int size) {
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_article WHERE author = ? AND is_delete = 0 LIMIT ?,5";
        Object[] authors = {author,size};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, authors);
        return getBlogArticles(resultList);
    }
    public List<ArticleDto> selectArticle(String userName,int size) {
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT numbers,author,title,content,createTime,numbers,imgSrc,ba.tagId FROM blog_article ba JOIN blog_tag bt ON ba.tagId = bt.id WHERE is_delete = 0 AND author = ? LIMIT ?,5";
        Object[] number = {userName,size};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql,number);
        List<ArticleDto> articleDtos = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                ArticleDto articleDto = new ArticleDto();
                articleDto.setAuthor(resultMap.get("author").toString());
                articleDto.setTitle(resultMap.get("title").toString());
                articleDto.setContent(resultMap.get("content").toString());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                articleDto.setCreateTime(sdf2.format(resultMap.get("createTime")));
                articleDto.setNumbers(resultMap.get("numbers").toString());
                articleDto.setImgSrc(resultMap.get("imgSrc").toString());
                articleDto.setTag(resultMap.get("tagId").toString());
                articleDtos.add(articleDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDtos;
    }
    public BlogArticle getBlogArticleByNumbers(String number){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT * FROM blog_article WHERE numbers = ? and is_delete = 0";
        Object[] numbers = {number};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql, numbers);
        List<BlogArticle> blogArticles = getBlogArticles(resultList);
        return blogArticles.get(0);
    }
    private List<BlogArticle> getBlogArticles(List<Map<String, Object>> resultList) {
        List<BlogArticle> blogArticles = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                BlogArticle blogArticle = new BlogArticle();
                blogArticle.setId(Integer.parseInt(resultMap.get("id").toString()));
                blogArticle.setAuthor(resultMap.get("author").toString());
                blogArticle.setTitle(resultMap.get("title").toString());
                blogArticle.setContent(resultMap.get("content").toString());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                blogArticle.setCreateTime(sdf2.format(resultMap.get("createTime")));
                blogArticle.setModifyTime((Date) resultMap.get("modifyTime"));
                blogArticle.setNumbers(resultMap.get("numbers").toString());
                blogArticle.setImgSrc(resultMap.get("imgSrc").toString());
                blogArticle.setTagId(Integer.parseInt(resultMap.get("tagId").toString()));
                blogArticles.add(blogArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogArticles;
    }
    public ArticleDto getArticleByNumbers(String numbers){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT numbers,author,title,content,createTime,numbers,imgSrc,ba.tagId FROM blog_article ba JOIN blog_tag bt ON ba.tagId = bt.id WHERE is_delete = 0 AND numbers = ?";
        Object[] number = {numbers};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql,number);
        List<ArticleDto> articleDtos = new ArrayList<>();
        ArticleDto articleDto = new ArticleDto();
        try {
            for (Map<String, Object> resultMap : resultList) {
                articleDto.setAuthor(resultMap.get("author").toString());
                articleDto.setTitle(resultMap.get("title").toString());
                articleDto.setContent(resultMap.get("content").toString());
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                articleDto.setCreateTime(sdf2.format(resultMap.get("createTime")));
                articleDto.setNumbers(resultMap.get("numbers").toString());
                articleDto.setImgSrc(resultMap.get("imgSrc").toString());
                articleDto.setTag(resultMap.get("tagId").toString());
                articleDtos.add(articleDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleDto;
    }
    public List<SimilarityArticleVo> getSimilarityArticle(int TagId){
        CurdUtil curdUtil = new CurdUtil();
        String sql = "SELECT ba.title,ba.numbers FROM blog_article ba JOIN blog_articleviews bt ON ba.numbers=bt.articleNumber WHERE ba.tagId = ? ORDER BY numberOfViews DESC LIMIT 10;";
        Object[] id = {TagId};
        List<Map<String, Object>> resultList = curdUtil.queryForList(sql,id);
        List<SimilarityArticleVo> articleVos = new ArrayList<>();
        try {
            for (Map<String, Object> resultMap : resultList) {
                SimilarityArticleVo similarityArticleVo = new SimilarityArticleVo();
                similarityArticleVo.setTitle(resultMap.get("title").toString());
                similarityArticleVo.setNumber(resultMap.get("numbers").toString());
                articleVos.add(similarityArticleVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleVos;
    }
    public int addArticle(BlogArticle blogArticle){
        CurdUtil curdUtil = new CurdUtil();
        long numbers = SnowflakeUtils.generateId();
        String sql = "INSERT INTO blog_article VALUES(NULL,?,?,?,?,?,0,?,?,?);";
        Object[] params = {blogArticle.getAuthor(),blogArticle.getTitle(), blogArticle.getContent(),
                DateUtil.now(), DateUtil.now(), numbers, blogArticle.getImgSrc(),blogArticle.getTagId()};
        String viewSql= "INSERT INTO blog_articleviews VALUES(NULL,?,0)";
        Object[] viewParams = {numbers};
        curdUtil.execute(viewSql, viewParams);
        return curdUtil.execute(sql, params);
    }
}
