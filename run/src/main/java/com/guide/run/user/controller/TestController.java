package com.guide.run.user.controller;


import com.guide.run.global.exception.user.resource.NotExistUserException;
import com.guide.run.user.entity.type.Role;
import com.guide.run.user.entity.user.User;
import com.guide.run.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = {"https://guide-run-qa.netlify.app", "https://guiderun.org",
        "https://guide-run.netlify.app","https://www.guiderun.org", "http://localhost:3000"},
        maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserRepository userRepository;
    //USER 권한 테스트
    @GetMapping("/api/test")
    public String test(){
        return "테스트 성공";
    }
    @PostMapping("/ap")
    public String test34(){
        throw new NotExistUserException("존재하지 않는 사용자입니다");
    }
    //ADMIN 권한 테스트
    @GetMapping("/api/test2")
    public String test2(){
        return "테스트 성공";
    }
    //authentificated 테스트
    @GetMapping("/api/test3")
    public String test3(){
        return "테스트 성공";
    }
    @GetMapping("/user/create")
    public String userCreateTest(){
        User user = User.builder()
                .userId("kakao_1")
                .role(Role.ADMIN)
                .build();
        User user2 = User.builder()
                .userId("kakao_2")
                .role(Role.WAIT)
                .build();
        userRepository.save(user);
        userRepository.save(user2);
        return "생성 완료";
    }

}