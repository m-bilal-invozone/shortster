package com.example.urlShortner.util;

import java.math.BigInteger;

public class UrlEncodeDecoderUtil {

    public static String generateShortCode(BigInteger n)
    {
        // Map to store 62 possible characters
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuffer shorturl = new StringBuffer();

        // Convert given integer id to a base 62 number
        while (n.compareTo(BigInteger.valueOf(0)) > 0)
        {
            // use above map to store actual character
            // in short url
            shorturl.append(map[n.remainder(BigInteger.valueOf(62L)).intValue()]);
            n = n.divide(BigInteger.valueOf(62));
        }

        // Reverse shortURL to complete base conversion
        return shorturl.reverse().toString().substring(0, 6);
    }

}
