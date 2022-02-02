package com.example.jwt.service.dto;

import com.example.jwt.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface IPageUser {

  Long getId();

  String getEmail();

  String getName();

  String getNickName();

  String getPhoneNumber();

  Gender getGender();

  @Value("#{target.orderList.size() > 0 ? target.orderList.get(0) : null }")
  ITopOrder getLastOrder();

  interface ITopOrder {
    String getNumber();

    String getName();

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "Asia/Seoul")
    Date getOrderDate();
  }
}
