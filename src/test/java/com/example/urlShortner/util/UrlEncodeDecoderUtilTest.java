package com.example.urlShortner.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest
public class UrlEncodeDecoderUtilTest {

    @Test
    void generateShortCodeTest(){
        BigInteger num = BigInteger.valueOf(12345678912345L);
        Assertions.assertEquals(UrlEncodeDecoderUtil.generateShortCode(num), "dFv1yJ");
    }
}
