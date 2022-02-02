package com.example.jwt.repository;

import com.example.jwt.domain.Order;
import com.example.jwt.domain.User;
import com.example.jwt.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles({"local"})
@DataJpaTest
@ExtendWith(SpringExtension.class)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    private String DEFAULT_NAME = "test..";

    @BeforeEach
    void init() {

        String email = "test@naver.com";

        String password = "12345";

        String name = "user";

        String nickName = "tester";

        String phoneNumber = "0100000000";

        Gender gender = Gender.M;

        user = User.builder().email(email).password(password).name(name).nickName(nickName).phoneNumber(phoneNumber).gender(gender).build();

        userRepository.save(user);

    }


    @Test
    @DisplayName("주문 저장 테스트")
    void save_success() throws Exception {

        Order entity = Order.builder().user(user).name(DEFAULT_NAME).build();

        orderRepository.saveAndFlush(entity);

        // 유저 테스트
        Assertions.assertEquals(user, entity.getUser());

        // 이름 체크
        Assertions.assertEquals(entity.getName(), entity.getName());

        // not null 테스트
        Assertions.assertNotNull(entity.getNumber());

        // not null 테스트
        Assertions.assertNotNull(entity.getOrderDate());

    }

    @Test
    @DisplayName("이모자 저장 테스트")
    void save_이모지테스트() throws Exception{

        String EMORJI_NAME = "👺 이모지입니다.";

        Order entity = Order.builder().user(user).name(EMORJI_NAME).build();

        orderRepository.saveAndFlush(entity);

        // 유저 테스트
        Assertions.assertEquals(user, entity.getUser());

        // 이름 체크
        Assertions.assertEquals(entity.getName(), entity.getName());

        // not null 테스트
        Assertions.assertNotNull(entity.getNumber());

        // not null 테스트
        Assertions.assertNotNull(entity.getOrderDate());

    }

}
