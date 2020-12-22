package com.example.urlShortner.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;


@Entity
@Table(name="UrlEntry")
public class UrlEntry implements Serializable {


    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp lastAccessTime;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created_At;

    private int accessCount =0;

    public UrlEntry(){}
    public UrlEntry(String id, String originalUrl, Timestamp lastAccessTime, Timestamp created_At, int accessCount) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.lastAccessTime = lastAccessTime;

        this.created_At = created_At;
        this.accessCount = accessCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Timestamp getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Timestamp lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Timestamp getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Timestamp created_At) {
        this.created_At = created_At;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", lastAccessTime=" + lastAccessTime +
                ", created_At=" + created_At +
                ", accessCount=" + accessCount +
                '}';
    }
}
