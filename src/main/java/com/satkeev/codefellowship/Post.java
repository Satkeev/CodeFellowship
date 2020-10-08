package com.satkeev.codefellowship;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    private String createdAt;
    @ManyToOne
    private ApplicationUser postedBy;

    public long getId() {
        return this.id;
    }

    public String getBody() {
        return this.body;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public ApplicationUser getPostedBy() {
        return this.postedBy;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public void setPostedBy(ApplicationUser postedBy) {
        this.postedBy = postedBy;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    public void setId(long id) {
        this.id = id;
    }
}