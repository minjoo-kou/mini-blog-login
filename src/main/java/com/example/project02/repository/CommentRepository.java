package com.example.project02.repository;

import com.example.project02.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findAllByArticleId(Long articleId);
//    List<Article> findAllByOrderByIdDesc();

}