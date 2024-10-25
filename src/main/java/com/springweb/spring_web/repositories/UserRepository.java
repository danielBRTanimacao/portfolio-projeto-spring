package com.springweb.spring_web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springweb.spring_web.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
