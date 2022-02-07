package com.example.project02.controller;

import com.example.project02.model.Article;
import com.example.project02.repository.ArticleRepository;
import com.example.project02.dto.ArticleRequestDto;
import com.example.project02.security.UserDetailsImpl;
import com.example.project02.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController

public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> getArticles(ArticleRequestDto requestDto) {
        return articleRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/api/articles/{id}")
    public List<Article> showArticles(@PathVariable String id){
        long longId = Long.parseLong(id);
        return articleRepository.findAllById(Collections.singleton(longId));
    }

    @GetMapping("/api/articles/own")
    public List<Article> getOwnArticles(@AuthenticationPrincipal UserDetailsImpl userDetails){
        //현재 로그인 중인 회원의 고유 ID
        Long userId = userDetails.getUser().getId();
        return articleRepository.findAllByUserId(userId);
    }

    @PostMapping("/api/articles")
    public Article createArticle(@RequestBody ArticleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        //현재 로그인 되어있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();
        String writerName = userDetails.getUser().getUsername();

        Article article = new Article(requestDto, userId, writerName);

        // JPA를 이용하여 DB에 저장하고, 그 결과를 반환합니다.
        return articleRepository.save(article);
    }

    @PutMapping("/api/articles/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
        return articleService.update(id, requestDto);
    }

    @DeleteMapping("/api/articles/{id}")
    public Long deleteCourse(@PathVariable Long id){
        articleRepository.deleteById(id);
        return id;
    }

}
