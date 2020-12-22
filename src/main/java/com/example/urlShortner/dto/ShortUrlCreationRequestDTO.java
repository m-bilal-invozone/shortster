package com.example.urlShortner.dto;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;


public class ShortUrlCreationRequestDTO implements Serializable {
    private String originalUrl;
    private String shortCode;

    public ShortUrlCreationRequestDTO(){}

    public ShortUrlCreationRequestDTO(String originalUrl, String shortCode) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Boolean isValid(){
        if(originalUrl == null){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShortUrlCreationRequestDTO{" +
                "originalUrl='" + originalUrl + '\'' +
                ", shortCode='" + shortCode + '\'' +
                '}';
    }
}
