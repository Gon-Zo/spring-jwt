package com.example.jwt.domain;

import com.example.jwt.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {


    @Test
    @DisplayName("유저 도메인 테스트")
    void userDTO() throws Exception {

        String email = "test@naver.com";

        String password = "12345";

        String name = "user";

        String nickName = "tester";

        String phoneNumber = "0100000000";

        Gender gender = Gender.M;

        User user = User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .build();

        Assertions.assertEquals(email, user.getEmail());

        Assertions.assertEquals(password, user.getPassword());

        Assertions.assertEquals(name, user.getName());

        Assertions.assertEquals(nickName, user.getNickName());

        Assertions.assertEquals(gender, user.getGender());

        Assertions.assertEquals(phoneNumber, user.getPhoneNumber());

    }

}
