package com.sergeyvoytov.CodeFellowship.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;

    @ManyToOne
    ApplicationUser applicationUser;

    String body;


    String timestamp;



    public long getId() {
        return Id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public String getBody() {
        return body;
    }

    public String getTimestamp() {
        return timestamp;
    }


    public Post(ApplicationUser applicationUser, String body) {
        this.applicationUser = applicationUser;
        this.body = body;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
    }

    public Post() {
    }
}
