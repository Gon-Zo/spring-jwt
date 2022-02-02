package com.example.jwt.utils;

import java.util.Random;

public class RandomUtils {

  public static String getRandomValue() {
    int leftLimit = 48;
    int rightLimit = 90;

    int targetStringLength = 12;

    Random random = new Random();

    String generatedString =
        random
            .ints(leftLimit, rightLimit + 1)
            .filter(num -> (num <= 57 || num >= 65))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

    return generatedString;
  }
}
