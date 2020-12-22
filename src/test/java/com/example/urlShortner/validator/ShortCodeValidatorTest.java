package com.example.urlShortner.validator;

import com.example.urlShortner.validator.ShortCodeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShortCodeValidatorTest {

    private final String VALID_SHORT_CODE = "AAAAAA";
    private final String SHORTER_SHORT_CODE = "AAA";
    private final String LONGER_SHORT_CODE = "AAAAAAAA";
    private final String INVALID_CHAR_SHORT_CODE = "AAAA__";

    @Test
    void isShortCodeValidTestWithShorterLength(){
        Assertions.assertFalse(ShortCodeValidator.isShortCodeValid(SHORTER_SHORT_CODE));
    }

    @Test
    void isShortCodeValidTestWithLongerLength(){
        Assertions.assertFalse(ShortCodeValidator.isShortCodeValid(LONGER_SHORT_CODE));
    }

    @Test
    void isShortCodeValidTestWithInvalidChars(){
        Assertions.assertFalse(ShortCodeValidator.isShortCodeValid(INVALID_CHAR_SHORT_CODE));
    }

    @Test
    void isShortCodeValidTestWithValidData(){
        Assertions.assertTrue(ShortCodeValidator.isShortCodeValid(VALID_SHORT_CODE));
    }
}
