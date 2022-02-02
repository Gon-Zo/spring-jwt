package com.example.jwt.web;

import com.example.jwt.domain.Order;
import com.example.jwt.domain.User;
import com.example.jwt.service.OrderService;
import com.example.jwt.service.UserService;
import com.example.jwt.service.dto.IPageUser;
import com.example.jwt.service.dto.PageDTO;
import com.example.jwt.service.dto.UserDTO;
import com.example.jwt.web.docs.UserResourceDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource implements UserResourceDocs {

  private final UserService userService;

  private final OrderService orderService;

  @PostMapping("/user")
  public User joinUser(@RequestBody @Valid UserDTO userDTO) {
    return userService.joinUser(userDTO);
  }

  @GetMapping("/user/{id}")
  public User showUser(@PathVariable Long id) {
    return userService.getUser(id);
  }

  @GetMapping("/user/{userId}/orders")
  public List<Order> showOrderListByUser(@PathVariable Long userId) {
    return orderService.getOrderListByUser(userId);
  }

  @GetMapping("/users")
  public Page<IPageUser> showUsers(PageDTO dto) {
    return userService.getUserList(dto);
  }
}
