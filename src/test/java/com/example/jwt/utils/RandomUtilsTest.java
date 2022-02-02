package com.example.jwt.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

class RandomUtilsTest {

    @Test
    @DisplayName("랜덤값 출력")
    void getRandomValue() {

        int leftLimit = 48;
        int rightLimit = 90;

        int targetStringLength = 12;

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(num -> (num <= 57 || num >= 65))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
    }

}
