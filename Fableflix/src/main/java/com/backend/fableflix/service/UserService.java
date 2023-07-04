package com.backend.fableflix.service;
import com.backend.fableflix.model.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    // Other methods...
}