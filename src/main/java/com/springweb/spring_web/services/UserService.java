package com.springweb.spring_web.services;

import java.util.List;
import java.util.Optional;

import com.springweb.spring_web.entities.User;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User insert(User user);
    void delete(Long id);
    User update(Long id, User user);
}
