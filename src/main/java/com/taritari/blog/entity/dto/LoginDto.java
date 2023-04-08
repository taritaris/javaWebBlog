package com.taritari.blog.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    public String username;
    public String password;
    public String verify;
}
