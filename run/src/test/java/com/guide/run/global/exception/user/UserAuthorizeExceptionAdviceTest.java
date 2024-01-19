package com.guide.run.global.exception.user;

import com.guide.run.global.dto.response.FailResult;
import com.guide.run.global.exception.user.authorize.NotApprovedUserException;
import com.guide.run.global.exception.user.authorize.NotAuthorizationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

//니중에 해당 컨트롤러에 실패 테스트 코드 작성 후 지워야 할 듯하다
//상태코드도 비교하는 코드가 필요할 것 같다 404인지 400인지 ..
@SpringBootTest
class UserAuthorizeExceptionAdviceTest {
    @Autowired
    UserAuthorizeExceptionAdvice userAuthorizeExceptionAdvice;
    //  code: "1100"
    //  msg: "가입이 승인되지 않은 사용자 입니다."
    @Test
    @DisplayName("가입 승인되지 않은 사용자 에러코드 테스트")
    void notApprovedUserException() {
        ResponseEntity<FailResult> failResultResponseEntity = userAuthorizeExceptionAdvice.NotApprovedUserException(new NotApprovedUserException());
        Assertions.assertThat(failResultResponseEntity.getBody().getCode()).isEqualTo("1100");
        Assertions.assertThat(failResultResponseEntity.getBody().getMsg()).isEqualTo("가입이 승인되지 않은 사용자 입니다.");
    }

    //  code: "1101"
    //  msg: "로그인 되지 않은 사용자 입니다."
    @Test
    @DisplayName("로그인 되지 않은 에러코드 테스트")
    void notAuthorizationException() {
        ResponseEntity<FailResult> failResultResponseEntity = userAuthorizeExceptionAdvice.NotAuthorizationException(new NotAuthorizationException());
        Assertions.assertThat(failResultResponseEntity.getBody().getCode()).isEqualTo("1101");
        Assertions.assertThat(failResultResponseEntity.getBody().getMsg()).isEqualTo("로그인 되지 않은 사용자 입니다.");
    }
}