package com.example.project02.model;


import com.example.project02.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import  javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter

public class Article extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

//    @Column(nullable = false)
//    private String writer;

//    @Column(nullable = false)
//    private String date;
    //timestamp 값 불러와서 잘 만져보면 될 듯

    @Column(nullable = false)
    private String context;

    @CreatedDate
    private String createdDate;

    @LastModifiedDate
    private String lastModifiedDate;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String writerName;

//    public Article(String title, String context) {
//        this.title = title;
//        this.context = context;
//    }

    public Article(ArticleRequestDto requestDto, Long userId, String writerName) {
        this.title = requestDto.getTitle();
        this.userId = userId;
        this.writerName = writerName;
        // date는 고민 필요
        //this.date = requestDto.getDate();
        this.context = requestDto.getContext();
    }

    public void update(ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.context = requestDto.getContext();
    }
}