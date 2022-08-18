package com.sparta.myblog.controller;

import com.sparta.myblog.domain.MyBlog;
import com.sparta.myblog.domain.MyBlogRequestDto;
import com.sparta.myblog.service.MyBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MyBlogController {
    private final MyBlogService myBlogService;

    @GetMapping("/api/posts")
    public List<MyBlog> getAllMyBlog(){
        return myBlogService.sortMyBlogAtTheDateToDESC();
    }
    //전체 메모 리스트 불러오기
    @GetMapping("/api/posts/{id}")
    public Optional<MyBlog> getMyBlog(@PathVariable Long id){
        return myBlogService.findId(id);
    }
    //id 메모 리스트 불러오기

    @PostMapping("/api/posts")
    public MyBlog createPost(@RequestBody MyBlogRequestDto requestDto){
        return myBlogService.saveMyBlogInDb(requestDto);
    }
    //메모 작성하기

    @PostMapping("/api/posts/{id}/pw")
    public boolean checkPW(@PathVariable Long id, @RequestBody MyBlogRequestDto requestDto){
        String pw_in_DB= myBlogService.getTheMyBlogInIndex(id).getPassword();
        String is_inputed_PW= this.getIs_inputed_pw(requestDto);

        return pw_in_DB.equals(is_inputed_PW);
    }
    private String getIs_inputed_pw(MyBlogRequestDto requestDto) {
        String is_inputed_PW= "";
        try{
            is_inputed_PW = requestDto.getPassword();
        }catch (NoSuchElementException e){
            System.err.println("비밀번호가 일치하지 않습니다.");
        }
        return is_inputed_PW;
    } //비밀번호 확인


    @PutMapping("/api/posts/{id}")
    public Long updateDoc(@PathVariable Long id, @RequestBody MyBlogRequestDto myBlogRequestDto){
        return myBlogService.update(id, myBlogRequestDto);
    }//수정

    @DeleteMapping("/api/posts/{id}")
    public String deleteDoc(@PathVariable Long id){
        myBlogService.delete(id);
        return id + ": 메세지가 성공적으로 지워졌습니다.";
    }//삭제

}