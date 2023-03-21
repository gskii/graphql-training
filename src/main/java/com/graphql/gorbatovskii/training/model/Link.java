package com.graphql.gorbatovskii.training.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "links")
public class Link {

    @Id
    private String id;

    @Version
    private Integer version;

    private String url;
    private String description;
    private String userId;

    public Link() {
    }

    public Link(String url, String description, String userId) {
        this(null, url, description, userId);
    }

    public Link(String id, String url, String description, String userId) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Link{" +
            "id='" + id + '\'' +
            ", version=" + version +
            ", url='" + url + '\'' +
            ", description='" + description + '\'' +
            ", userId='" + userId + '\'' +
            '}';
    }
}
