package com.example.jwt.repository;

import com.example.jwt.domain.User;
import com.example.jwt.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private Long DEFAULT_ID = 0L;

    private final String DEFAULT_EMAIL = "test@naver.com";

    private final String DEFAULT_PASSWORD = "1234567890";

    private final String DEFAULT_NAME = "가나다";

    private final String DEFAULT_NICKNAME = "testetttaa";

    private final String DEFAULT_PHONE_NUMBER = "010000000000";

    private final Gender DEFAULT_GENDER = Gender.M;

    @BeforeEach
    void init() {

        User entity = User.builder()
                .email(DEFAULT_EMAIL)
                .password(DEFAULT_PASSWORD)
                .name(DEFAULT_NAME)
                .nickName(DEFAULT_NICKNAME)
                .phoneNumber(DEFAULT_PHONE_NUMBER)
                .gender(DEFAULT_GENDER)
                .build();

        userRepository.save(entity);

        DEFAULT_ID = entity.getId();

    }

    @Test
    @DisplayName("유저 저장 로직")
    void saveUser() throws Exception {

        String email = "test@naver.com";

        String password = "12345";

        String name = "user";

        String nickName = "tester";

        String phoneNumber = "0100000000";

        Gender gender = Gender.M;

        User entity = User.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .build();

        userRepository.save(entity);

        // user pk value null checked
        Assertions.assertNotNull(entity.getId());

        // user email value checked
        Assertions.assertEquals(email, entity.getEmail());

        // 비밀번호 체크
        Assertions.assertEquals(password, entity.getPassword());

        // 이름 체크
        Assertions.assertEquals(name, entity.getName());

        // 닉네임 체크
        Assertions.assertEquals(nickName, entity.getNickName());

        // 전화번호 체크
        Assertions.assertEquals(phoneNumber, entity.getPhoneNumber());

        // 성별 체크
        Assertions.assertEquals(gender, entity.getGender());

    }

    @Test
    @DisplayName("단일 회원 정보")
    void findById() throws Exception {

        User user = userRepository.findById(DEFAULT_ID).orElseThrow();

        Assertions.assertEquals(DEFAULT_ID, user.getId());

        // user email value checked
        Assertions.assertEquals(DEFAULT_EMAIL, user.getEmail());

        // 비밀번호 체크
        Assertions.assertEquals(DEFAULT_PASSWORD, user.getPassword());

        // 이름 체크
        Assertions.assertEquals(DEFAULT_NAME, user.getName());

        // 닉네임 체크
        Assertions.assertEquals(DEFAULT_NICKNAME, user.getNickName());

        // 전화번호 체크
        Assertions.assertEquals(DEFAULT_PHONE_NUMBER, user.getPhoneNumber());

        // 성별 체크
        Assertions.assertEquals(DEFAULT_GENDER, user.getGender());

    }

}
