package com.example.jwt.service;

import com.example.jwt.domain.User;
import com.example.jwt.enums.Gender;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.service.dto.UserDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks private UserService userService;

  @Mock private UserRepository userRepository;

  private final Long DEFAULT_ID = 1L;

  private final String DEFAULT_EMAIL = "test@naver.com";

  private final String DEFAULT_PASSWORD = "1234567890";

  private final String DEFAULT_NAME = "가나다";

  private final String DEFAULT_NICKNAME = "testetttaa";

  private final String DEFAULT_PHONE_NUMBER = "010000000000";

  private final Gender DEFAULT_GENDER = Gender.M;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Nested
  @DisplayName("저장 관련 테스트 케이스")
  class Save {

    @Test
    @Transactional
    @DisplayName("유저 회원 가입 테스트 케이스")
    void joinUser() throws Exception {

      // given
      UserDTO userDTO =
          new UserDTO(
              DEFAULT_EMAIL,
              DEFAULT_PASSWORD,
              DEFAULT_NAME,
              DEFAULT_NICKNAME,
              DEFAULT_PHONE_NUMBER,
              DEFAULT_GENDER);

      User user = userDTO.toEntity();

      Mockito.lenient().when(userRepository.save(any(User.class))).thenReturn(user);

      // when
      User serviceUser = userService.joinUser(userDTO);

      // then
      Assertions.assertEquals(serviceUser.getEmail(), user.getEmail());
    }

    @Test
    @Disabled("조회 exception custom 으로 변경 후 제거")
    @DisplayName("단일 회원 상세 정보 조회 NoSuchElementException")
    void getUser_NoSuchElementException() throws Exception {

      UserDTO userDTO =
          new UserDTO(
              DEFAULT_EMAIL,
              DEFAULT_PASSWORD,
              DEFAULT_NAME,
              DEFAULT_NICKNAME,
              DEFAULT_PHONE_NUMBER,
              DEFAULT_GENDER);

      User user = userDTO.toEntity();

      Mockito.lenient().when(userRepository.findById(1L)).thenReturn(Optional.of(user));

      Assertions.assertThrows(NoSuchElementException.class, () -> userService.getUser(1L));
    }
  }

  @Nested
  @DisplayName("조회 관련 테스트 케이스")
  class Select {

    @BeforeEach
    void init() {

      User user =
          new UserDTO(
                  DEFAULT_EMAIL,
                  DEFAULT_PASSWORD,
                  DEFAULT_NAME,
                  DEFAULT_NICKNAME,
                  DEFAULT_PHONE_NUMBER,
                  DEFAULT_GENDER)
              .toEntity();

      userRepository.save(user);
    }

    @Test
    @DisplayName("단일 회원 상세 정보 조회 기능")
    void getUser() throws Exception {

      UserDTO userDTO =
          new UserDTO(
              DEFAULT_EMAIL,
              DEFAULT_PASSWORD,
              DEFAULT_NAME,
              DEFAULT_NICKNAME,
              DEFAULT_PHONE_NUMBER,
              DEFAULT_GENDER);

      User user = userDTO.toEntity();

      Mockito.lenient().when(userRepository.findById(1L)).thenReturn(Optional.of(user));

      User user1 = userService.getUser(1L);

      Assertions.assertEquals(user1.getEmail(), userDTO.getEmail());
    }
  }
}
