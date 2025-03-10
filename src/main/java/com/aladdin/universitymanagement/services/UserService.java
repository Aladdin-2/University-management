package com.aladdin.universitymanagement.services;

import com.aladdin.universitymanagement.dao.entitys.User;
import com.aladdin.universitymanagement.model.dto.user.ResponseUserDto;

import java.util.List;

public interface UserService {

    ResponseUserDto addUser(User user);

    ResponseUserDto updateUser(User user);

    List<ResponseUserDto> sortUser(String sortType);

    List<ResponseUserDto> getUser(Long userId, String userName);

    List<ResponseUserDto> getUsers();

    void deleteUserById(Long userId);

    void deleteAllUsers();
}
