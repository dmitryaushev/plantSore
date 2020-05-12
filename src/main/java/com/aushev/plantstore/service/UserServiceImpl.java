package com.aushev.plantstore.service;

import com.aushev.plantstore.exception.UserAlreadyExistsException;
import com.aushev.plantstore.exception.UserNotExistException;
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

        if (emailExist(user.getEmail())) {
            throw new UserAlreadyExistsException(String.format("User with email %s already exist", user.getEmail()));
        }

        user.setRole(roleRepository.findByRole("ROLE_USER"));
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {

        UUID id = user.getId();
        User checkUser = null;
        try {
            checkUser = findUser(user.getEmail());
        } catch (UserNotExistException e) {
        }

        if (Objects.nonNull(checkUser) && !checkUser.getId().equals(id)) {
            throw new UserAlreadyExistsException(String.format("User with email %s already exist", user.getEmail()));
        }

        userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    private boolean emailExist(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }
}
