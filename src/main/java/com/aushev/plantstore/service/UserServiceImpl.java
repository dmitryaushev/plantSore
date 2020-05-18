package com.aushev.plantstore.service;

import com.aushev.plantstore.exception.PasswordMatchException;
import com.aushev.plantstore.exception.UserNotExistException;
import com.aushev.plantstore.model.Role;
import com.aushev.plantstore.model.User;
import com.aushev.plantstore.repository.RoleRepository;
import com.aushev.plantstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotExistException(String.format("User with id %s not found", id)));
    }

    @Override
    public User findUser(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new UserNotExistException(String.format("User with email %s not exist", email)));
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {

        if (Objects.isNull(user.getRole())) {
            user.setRole(roleRepository.findByRole("ROLE_USER"));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> getAllUserRoles() {
        return roleRepository.findAll();
    }

    @Override
    public User userExist(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {

        if (!encoder.matches(oldPassword, user.getPassword())) {
            throw new PasswordMatchException("Wrong password");
        }
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }
}
