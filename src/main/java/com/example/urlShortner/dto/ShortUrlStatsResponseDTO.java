package com.example.urlShortner.dto;


import java.io.Serializable;


public class ShortUrlStatsResponseDTO implements Serializable {
    private String creationDate;
    private String lastAccessed;
    private int accessCount;
    private String orignalUrl;

    public ShortUrlStatsResponseDTO(){}
    public ShortUrlStatsResponseDTO(String creationDate, String lastAccessed, int accessCount, String orignalUrl) {
        this.creationDate = creationDate;
        this.lastAccessed = lastAccessed;
        this.accessCount = accessCount;
        this.orignalUrl = orignalUrl;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(String lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    public String getOrignalUrl() {
        return orignalUrl;
    }

    public void setOrignalUrl(String orignalUrl) {
        this.orignalUrl = orignalUrl;
    }

    @Override
    public String toString() {
        return "ShortUrlStatsResponseDTO{" +
                "creationDate='" + creationDate + '\'' +
                ", lastAccessed='" + lastAccessed + '\'' +
                ", accessCount=" + accessCount +
                ", orignalUrl='" + orignalUrl + '\'' +
                '}';
    }
}
