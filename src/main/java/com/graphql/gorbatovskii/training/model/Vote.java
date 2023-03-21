package com.graphql.gorbatovskii.training.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("votes")
public class Vote {

    @Id
    private String id;

    private LocalDateTime createdAt;
    private String userId;
    private String linkId;

    public Vote() {
    }

    public Vote(LocalDateTime createdAt, String userId, String linkId) {
        this(null, createdAt, userId, linkId);
    }

    public Vote(String id, LocalDateTime createdAt, String userId, String linkId) {
        this.id = id;
        this.createdAt = createdAt;
        this.userId = userId;
        this.linkId = linkId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @Override
    public String toString() {
        return "Vote{" +
            "id='" + id + '\'' +
            ", createdAt=" + createdAt +
            ", userId='" + userId + '\'' +
            ", linkId='" + linkId + '\'' +
            '}';
    }
}
