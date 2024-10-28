package com.springweb.spring_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springweb.spring_web.entities.User;
import com.springweb.spring_web.repositories.UserRepository;
import com.springweb.spring_web.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id)));
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User u = existingUser.get();
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setPhone(user.getPhone());
            u.setPassword(user.getPassword());
            return userRepository.save(u);
        }
        throw new RuntimeException("User não encontrado id: " + id);
    }

}
