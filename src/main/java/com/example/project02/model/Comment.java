package com.example.project02.model;


import com.example.project02.dto.CommentRequestDto;
import com.example.project02.dto.CommentUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import  javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter

public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String context;

    @CreatedDate
    private String createdDate;

    @LastModifiedDate
    private String lastModifiedDate;

    @Column(nullable = false)
    private String commentWriter;

    @Column(nullable = false)
    private Long articleId;

    public Comment(CommentRequestDto requestDto, String commentWriter) {
        this.articleId = requestDto.getArticleId();
        this.commentWriter = commentWriter;
        this.context = requestDto.getContext();
    }

    public void updatecomment(CommentUpdateRequestDto requestDto) {
        this.context = requestDto.getContext();
    }
}