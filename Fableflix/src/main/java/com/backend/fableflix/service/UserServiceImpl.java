package com.backend.fableflix.service;

import com.backend.fableflix.model.User;
import com.backend.fableflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.backend.fableflix.Enums.UserRoles;

import javax.persistence.EntityNotFoundException;
import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final UserRoles DEFAULT_ROLE = UserRoles.USER_ROLE;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (user == null) {
            return null;
        }

        user.setRole(DEFAULT_ROLE);

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(("Email already exists."));
        }

        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRoles loggedInUserRole = UserRoles.USER_ROLE;
        if ( auth != null && auth.getPrincipal() instanceof User ) {
            User loggedInUser = (User) auth.getPrincipal();
            loggedInUserRole = loggedInUser.getRole();
        }

        if ( loggedInUserRole != UserRoles.ADMIN_ROLE ) {
            return null;
        }

        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found :("));
    }
}