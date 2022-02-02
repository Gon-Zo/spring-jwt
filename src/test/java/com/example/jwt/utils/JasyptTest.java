package com.example.jwt.utils;

//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JasyptTest {

  @Test
  @DisplayName("jasypt 값 인코딩 테스트")
  void encodeTest() {
    StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();

    String[] array = new String[] {
            "etaskldfklncxmdsafvdsavcxzvxcv"
    };

    jasypt.setPassword("order112!!");

    jasypt.setAlgorithm("PBEWithMD5AndDES");

    for (String encrypt : array) {

      String encryptedText = jasypt.encrypt(encrypt);

      String text = jasypt.decrypt(encryptedText);

      System.out.println("encryptedText:  " + encryptedText);

      System.out.println("text:  " + text);
    }
  }
}
