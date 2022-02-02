package com.example.jwt.web.docs;

import com.example.jwt.domain.Order;
import com.example.jwt.domain.User;
import com.example.jwt.service.dto.IPageUser;
import com.example.jwt.service.dto.PageDTO;
import com.example.jwt.service.dto.UserDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserResourceDocs {

  @Operation(
      summary = "회원가입 API",
      description =
          "ex = {\n"
              + "  \"email\": \"test1@naver.com\",\n"
              + "  \"gender\": \"M\",\n"
              + "  \"name\": \"test가낟다\",\n"
              + "  \"nickName\": \"test\",\n"
              + "  \"password\": \"test1123Sx\",\n"
              + "  \"phoneNumber\": \"01000000000\"\n"
              + "}")
  User joinUser(UserDTO userDTO);

  @Operation(summary = "단일 회원 상세 정보 조회 기능 API")
  @ApiImplicitParam(name = "id" , value = "유저아이디")
  User showUser(Long id);

  @Operation(summary = "단일 회원의 주문 목록 조회 API (no paging)")
  @ApiImplicitParam(name = "userId" , value = "유저아이디")
  List<Order> showOrderListByUser(Long userId);

  @Operation(
      summary = "여러 회원 조회 목록 조회 API",
      description =
          "1. 페이지 네이션으로 일정 단위를 조회한다." + "2. 이름, 이메일을 이용하여서 검색 기능이 필요" + "3. 각 회원의 마지막 주문 정보")
  Page<IPageUser> showUsers(PageDTO dto);
}
