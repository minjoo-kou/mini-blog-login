package com.example.project02;

import com.example.project02.model.Article;
import com.example.project02.repository.ArticleRepository;
import com.example.project02.dto.ArticleRequestDto;
import com.example.project02.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class Project02Application {

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Project02Application.class, args);
    }
}