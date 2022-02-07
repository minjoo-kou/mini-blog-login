package com.example.project02.controller;

import com.example.project02.dto.CommentUpdateRequestDto;
import com.example.project02.model.Comment;
import com.example.project02.dto.CommentRequestDto;
import com.example.project02.repository.CommentRepository;
import com.example.project02.security.UserDetailsImpl;
import com.example.project02.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@RestController

public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    //여기 id는 게시글 id
    @GetMapping("/api/comments/{articleId}")
    public List<Comment> showComments(@PathVariable Long articleId){
        return commentRepository.findAllByArticleId(articleId);
    }

    @PostMapping("/api/comments")
    public Comment creatComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String commentWriter = userDetails.getUser().getUsername();
        Comment comment = new Comment(requestDto, commentWriter);
        return commentRepository.save(comment);
    }

    @DeleteMapping("/api/comments/{commentId}")
    public Long deleteComment(@PathVariable Long commentId){
        commentRepository.deleteById(commentId);
        return commentId;
    }

    @PutMapping("/api/comments/{commentId}")
    public Long updatecomment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto){
        return commentService.updatecomment(commentId, requestDto);
    }


}
