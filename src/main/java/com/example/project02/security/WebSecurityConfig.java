package com.example.project02.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
// h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
// 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        http.csrf().disable();
//                .ignoringAntMatchers("/user/**");
        http.headers().frameOptions().disable();

        http.authorizeRequests()
// image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
// css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
// 회원 관리 처리 API 전부를 login 없이 허용
                .antMatchers("/user/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/articles").permitAll()
                .antMatchers("/api/articles/{id}").permitAll()
                .antMatchers("/api/articles").permitAll()
                .antMatchers("/detail/{id}").permitAll()

// 그 외 어떤 요청이든 '인증'
//                .anyRequest().authenticated()
                .and()
                // 로그인 기능
                .formLogin()
                //로그인 뷰 제공 (Get / user/login)
                .loginPage("/user/login")
                // 로그인처리(post, user/login)
                .loginProcessingUrl("/user/login")
                // 로그인 처리 후 성공 시 URL
                .defaultSuccessUrl("/")
                // 로그인 처리 후 실패 시 URL
//                .failureUrl("/user/login?error")
                .permitAll()
                .and()
                // 로그아웃 기능
                .logout()
                .logoutUrl("/user/logout")
                .permitAll();
    }
}