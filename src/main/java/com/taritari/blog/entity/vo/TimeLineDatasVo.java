package com.taritari.blog.entity.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-15 17:09
 * @description
 */
@Data
public class TimeLineDatasVo {
    private String year;
    private Map<String, List<EventVo>> month;

}

