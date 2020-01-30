package com.sergeyvoytov.CodeFellowship.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);

}