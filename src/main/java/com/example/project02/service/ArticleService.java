package com.example.project02.service;


import com.example.project02.dto.ArticleRequestDto;
import com.example.project02.model.Article;
import com.example.project02.repository.ArticleRepository;
import com.example.project02.dto.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // 이 클래스에서 꼭 필요한 생성자를 자동으로 생성해줌
@Service
public class ArticleService {
    // final: 서비스에게 꼭 필요한 것임을 명시 (상수 - 한 번 값 부여되면 바뀌지 X)
    private final ArticleRepository articleRepository;

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    // 어떤 애가 업데이트 됐는지 id값을 알려줌
    public Long update(Long id, ArticleRequestDto requestDto) {
        // id 이용해서 course1에 바꿀 애를 넣어줌
        Article article1 = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        // course1에 바꾸는 내용인 파라미터 course를 넣어줌
        // (1) course.java 클래스 안에 만들어준 update 메소드 호출
        // (2) transactional로 인해 자동으로 데이터베이스에 적용이 됨
        article1.update(requestDto);

        //id 돌려줌
        return article1.getId();
    }
}