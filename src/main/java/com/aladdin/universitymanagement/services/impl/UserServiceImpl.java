package com.aladdin.universitymanagement.services.impl;

import com.aladdin.universitymanagement.config.mapper.UserMapper;
import com.aladdin.universitymanagement.dao.entitys.User;
import com.aladdin.universitymanagement.dao.repositorys.RoleRepository;
import com.aladdin.universitymanagement.dao.repositorys.UserRepository;
import com.aladdin.universitymanagement.exception.ResourceNotFoundException;
import com.aladdin.universitymanagement.model.dto.user.ResponseUserDto;
import com.aladdin.universitymanagement.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final HttpServletRequest request;


    @Override
    public ResponseUserDto addUser(@NotNull User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public ResponseUserDto updateUser(@NotNull User user) {
        User newUser = userRepository.findById(user.getId())
                .orElse(new User());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        return userMapper.toDto(newUser);
    }

    @Override
    public List<ResponseUserDto> sortUser(String sortType) {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("User DB is empty!");
        }
        if (sortType != null) {
            switch (sortType) {
                case "ascending" -> users = users.stream().sorted(Comparator.comparing(User::getUsername)).toList();
                case "descending" ->
                        users = users.stream().sorted(Comparator.comparing(User::getUsername).reversed()).toList();
            }
        }
        return userMapper.toDto(users);
    }

    @Override
    public List<ResponseUserDto> getUser(Long userId, String userName) {
        List<User> users = new ArrayList<>();
        if (userId != null) {
            users.add(userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found this id:")));
        }
        if (userName != null) {
            users.add(userRepository.findByUsername(userName)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found this name:")));
        }

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Not found user!");
        }
        return userMapper.toDto(users);
    }

    @Override
    public List<ResponseUserDto> getUsers() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getRoles().clear();
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
        userRepository.resetAutoIncrement();
    }


}
