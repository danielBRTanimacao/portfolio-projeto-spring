package com.springweb.spring_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springweb.spring_web.entities.User;
import com.springweb.spring_web.repositories.UserRepository;
import com.springweb.spring_web.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Método findAll
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Método findById com tratamento de exceção
    public User findById(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return user.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao buscar usuário com ID: " + id);
        }
    }

    // Método insert
    public User insert(User user) {
        return userRepository.save(user);
    }

    // Método delete com tratamento de exceção
    public void delete(Long id) {
        try {
            findById(id); // Verifica se o usuário existe antes de deletar
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao tentar excluir usuário com ID: " + id);
        }
    }

    // Método update com tratamento de exceção
    public User update(Long id, User userDetails) {
        try {
            User user = findById(id); // Verifica se o usuário existe

            // Atualiza os dados
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPhone(userDetails.getPhone());
            user.setPassword(userDetails.getPassword());

            return userRepository.save(user); // Salva o usuário atualizado
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao atualizar usuário com ID: " + id);
        }
    }
}
