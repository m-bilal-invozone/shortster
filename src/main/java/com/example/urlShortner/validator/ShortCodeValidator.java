package com.example.urlShortner.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShortCodeValidator {
    public static Boolean isShortCodeValid(String shortCode){
        Pattern pattern = Pattern.compile("[A-Za-z0-9]{4,6}");
        Matcher matcher = pattern.matcher(shortCode);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
}
