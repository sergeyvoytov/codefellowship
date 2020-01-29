package com.sergeyvoytov.CodeFellowship.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;

    @ManyToOne
    ApplicationUser applicationUser;

    String body;

    public String getTimestamp() {
        return timestamp;
    }

    String timestamp;
//    LocalDate timestamp = LocalDate.now();

    public long getId() {
        return Id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getBody() {
        return body;
    }


    public Post(ApplicationUser applicationUser, String body) {
        this.applicationUser = applicationUser;
        this.body = body;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
    }
    public Post() {
    }
}
