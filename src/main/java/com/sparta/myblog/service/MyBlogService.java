package com.sparta.myblog.service;

import com.sparta.myblog.domain.MyBlog;
import com.sparta.myblog.domain.MyBlogRepository;
import com.sparta.myblog.domain.MyBlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyBlogService {
    private final MyBlogRepository myBlogRepository;

    public List<MyBlog> sortMyBlogAtTheDateToDESC() {
        return myBlogRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
    }//생성 시간 기준 정렬

    public Optional<MyBlog> findId(Long id) {
        return myBlogRepository.findById(id);
    }
    //db에 id 찾기
    public MyBlog saveMyBlogInDb(MyBlogRequestDto requestDto) {
        return myBlogRepository.save(new MyBlog(requestDto));
    }
    //db에 저장하기
    public MyBlog getTheMyBlogInIndex(Long id) {
        return this.findId(id).orElseGet(
                () -> {
                    System.err.println("해당 아이디를 찾을 수 없습니다.");
                    return null;
                }
        );
    }//id 있는지 확인

    public void delete(Long id) {
        myBlogRepository.deleteById(id);
    }
    //db에 데이터 삭제
    @Transactional
    public Long update(Long id, MyBlogRequestDto RequestDto){
        MyBlog myBlog= this.findTheCaseID(id);
        myBlog.update(RequestDto);
        return this.ResponsePrintingData(myBlog);
    }//db 내용 수정

    public MyBlog findTheCaseID(Long id) {
        return myBlogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("정보가 존재하지 않습니다.")
        );
    }

    private Long ResponsePrintingData(MyBlog myBlog) {
        return myBlog.getId();
    }
}
