package com.example.project02.service;


import com.example.project02.dto.CommentUpdateRequestDto;
import com.example.project02.model.Comment;
import com.example.project02.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long updatecomment(Long commentId, CommentUpdateRequestDto requestDto){
        Comment comment1 = commentRepository.findById(commentId).orElseThrow(
                ()->new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        comment1.updatecomment(requestDto);

        return comment1.getId();
    }
}
