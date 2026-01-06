package com.jules.financeapp.service;

import com.jules.financeapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    List<User> getAllUsers();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User changePassword(Long userId, String newPassword);

    User toggleUserStatus(Long userId, boolean enabled);

    User getCurrentUser();
}