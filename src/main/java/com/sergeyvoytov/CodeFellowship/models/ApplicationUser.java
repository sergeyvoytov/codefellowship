package com.sergeyvoytov.CodeFellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;


    // matches the property on the other class
    @OneToMany(mappedBy = "applicationUser")
    List<Post> posts;


    String username;
    String password;
    String dateOfBirth;
    String bio;
    String firstName;
    String lastName;

    public ApplicationUser() {
    }

    public ApplicationUser(String username, String password, String dateOfBirth, String bio, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public long getId() {
        return id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}