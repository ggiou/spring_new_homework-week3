package com.sparta.myblog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class MyBlog extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    public MyBlog(MyBlogRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.user_name = postRequestDto.getUser_name();
        this.content = postRequestDto.getContent();
        this.password = postRequestDto.getPassword();
    }

    public void update(MyBlogRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.user_name = postRequestDto.getUser_name();
        this.content = postRequestDto.getContent();
        this.password = postRequestDto.getPassword();
    }
}
