package com.jules.financeapp.service;

import com.jules.financeapp.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    public boolean isOwner(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        User currentUser = userService.getCurrentUser();

        return currentUser != null && currentUser.getId().equals(userId);
    }
}