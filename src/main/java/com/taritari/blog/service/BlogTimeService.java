package com.taritari.blog.service;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.taritari.blog.dao.BlogTimeDao;
import com.taritari.blog.entity.BlogTimeLine;
import com.taritari.blog.entity.dto.TimeLineDto;
import com.taritari.blog.entity.vo.EventVo;
import com.taritari.blog.entity.vo.TimeLineDatasVo;

import java.util.*;

/**
 * @author taritari
 * @date 2023-04-15 16:33
 * @description
 */
public class BlogTimeService {
    private final BlogTimeDao blogTimeDao = new BlogTimeDao();
    public List<TimeLineDatasVo> getBlogTimeLineByUserName(String userName){
        List<BlogTimeLine> blogTimeLineByUserName = blogTimeDao.getBlogTimeLineByUserName(userName);
        for (int i = 0;i<blogTimeLineByUserName.size();i++){
            String time = blogTimeLineByUserName.get(i).getTime();
            Date dateTime= DateUtil.parse(time);
            int year = DateUtil.year(dateTime); // 获取年份
            String month = DateUtil.month(dateTime) + 1 + ""; // 获取月份并加1
            String date = DateUtil.format(DateUtil.parse(time), "M月d日 HH:mm:ss"); // 格式化时间字符串
            date = StrUtil.replace(date, " 0", " "); // 去掉日期中的前导0
            blogTimeLineByUserName.get(i).setYear(year+"");
            blogTimeLineByUserName.get(i).setMonth(month);
            blogTimeLineByUserName.get(i).setData(date);
        }
        List<TimeLineDatasVo> result = new ArrayList<>();
        Map<String, Map<String, List<EventVo>>> dataMap = new HashMap<>();
        for (BlogTimeLine blogTimeLine : blogTimeLineByUserName) {
            String year = blogTimeLine.getYear();
            String month = blogTimeLine.getMonth();
            String key = year + month;
            Map<String, List<EventVo>> monthData = dataMap.computeIfAbsent(key, k -> new HashMap<>());
            List<EventVo> eventList = monthData.computeIfAbsent(month, k -> new ArrayList<>());
            EventVo event = new EventVo();
            event.setCreate_time(blogTimeLine.getTime());
            event.setContent(blogTimeLine.getContent());
            eventList.add(event);
        }
        for (Map.Entry<String, Map<String, List<EventVo>>> yearEntry : dataMap.entrySet()) {
            String year = yearEntry.getKey().substring(0, 4);
            TimeLineDatasVo timeLineDatasVo = new TimeLineDatasVo();
            timeLineDatasVo.setYear(year);
            timeLineDatasVo.setMonth(yearEntry.getValue());
            result.add(timeLineDatasVo);
        }
        System.out.println(result);
        return result;
    }
    public int insertTimeLineByNumber(TimeLineDto timeLineDto){
        int i = blogTimeDao.insertTimeLineByNumber(timeLineDto);
        return i;
    }
}
