package com.taritari.blog.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author taritari
 * @date 2023-04-03 12:54
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String number;
}
