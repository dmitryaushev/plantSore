package com.aushev.plantstore.service;

import com.aushev.plantstore.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User findUser(UUID id);

    User findUser(String email);

    List<User> findAllUser();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(UUID id);
}
