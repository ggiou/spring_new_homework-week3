package com.sparta.myblog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBlogRepository extends JpaRepository<MyBlog, Long> {
}