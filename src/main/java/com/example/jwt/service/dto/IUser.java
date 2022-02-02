package com.example.jwt.service.dto;

import com.example.jwt.enums.Gender;

public interface IUser {

  Long getId();

  String getEmail();

  String getPassword();

  String getName();

  String getNickName();

  String getPhoneNumber();

  Gender getGender();
}
