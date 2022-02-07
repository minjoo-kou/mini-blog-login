package com.example.project02.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class CommentRequestDto {
    private final Long articleId;
    private final String context;
}
