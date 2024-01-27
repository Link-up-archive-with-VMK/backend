package com.guide.run.user.service;

import com.guide.run.user.entity.type.Role;
import com.guide.run.user.entity.User;

import com.guide.run.user.entity.Vi;
import com.guide.run.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void init(){
        userRepository.deleteAll();
    }

    @DisplayName("로그인 시 기존 회원 응답")
    @Test
    void existUserLoginResponse(){
        User user = User.builder()
                .uuid(userService.getUUID())
                .userId("kakao_1")
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String userStatus = userService.getUserStatus("kakao_1");
        Assertions.assertThat(userStatus).isEqualTo(Role.USER.getValue());
    }

    @DisplayName("로그인 시 가입 대기중 회원 응답")
    @Test
    void waitUserLoginResponse(){
        User user = User.builder()
                .uuid(userService.getUUID())
                .userId("kakao_1")
                .role(Role.WAIT)
                .build();
        userRepository.save(user);
        String userStatus = userService.getUserStatus("kakao_1");
        Assertions.assertThat(userStatus).isEqualTo(Role.WAIT.getValue());
    }

    @DisplayName("로그인 시 신규 회원 응답")
    @Test
    void newUserLoginResponse(){
        String userStatus = userService.getUserStatus("kakao_1");
        Assertions.assertThat(userStatus).isEqualTo(Role.NEW.getValue());
    }

    @DisplayName("vi 회원가입")
    @Test
    void viSignup(){
        String id = UUID.randomUUID().toString();
        Vi vi = Vi.builder()
                .runningExp(true)
                .guideName("ljg")
                .uuid(id)
                .userId("kakao_1")
                .name("lj")
                .gender("male")
                .phoneNumber("010-9999-xxxx")
                .age(13)
                .detailRecord("45:23")
                .recordDegree("A")
                .role(Role.WAIT)
                .snsId("XXXXX12345")
                .build();

        Vi newVi = userRepository.save(vi);
        Assertions.assertThat(vi.getUuid()).isEqualTo(newVi.getUuid());
        //Assertions.assertThat(vi).isEqualTo(newVi); //created_at 과 updated_at 때문에 같을 수가 없다.
    }
    

}