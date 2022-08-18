package com.sparta.myblog.domain;

import lombok.Getter;

@Getter
public class MyBlogRequestDto {

    private String title;
    private String user_name;
    private String content;
    private String password;
}