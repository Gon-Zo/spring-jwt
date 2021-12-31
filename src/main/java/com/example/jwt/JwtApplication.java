package com.example.jwt;

import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class JwtApplication {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    @PostConstruct
    public void setUpData(){
        userService.initUserData();
    }

}
