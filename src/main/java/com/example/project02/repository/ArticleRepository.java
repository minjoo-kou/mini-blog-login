package com.example.project02.repository;

import com.example.project02.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();

    List<Article> findAllByUserId(Long userId);
}