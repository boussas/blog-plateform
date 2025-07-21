package com.boussas.blog.services;

import com.boussas.blog.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
