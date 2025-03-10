package com.aladdin.universitymanagement.controllers;

import com.aladdin.universitymanagement.dao.entitys.Role;
import com.aladdin.universitymanagement.dao.entitys.User;
import com.aladdin.universitymanagement.dao.repositorys.RoleRepository;
import com.aladdin.universitymanagement.model.dto.user.ResponseUserDto;
import com.aladdin.universitymanagement.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "com.aladdin/university-site/user")
@RequiredArgsConstructor
public class UserController {

    private final RoleRepository roleRepository;
    private final UserServiceImpl userServiceImpl;

    @PostMapping(path = "new")
    public ResponseUserDto addUser(@RequestBody User user) {
        return userServiceImpl.addUser(user);
    }

    @PutMapping(path = "update")
    public ResponseUserDto updateUser(@RequestBody User user) {
        return userServiceImpl.updateUser(user);
    }

    @GetMapping(path = "sort/{type}")
    public List<ResponseUserDto> sortUser(@PathVariable(name = "type") String sortType) {
        return userServiceImpl.sortUser(sortType);
    }

    @GetMapping(path = "get")
    public List<ResponseUserDto> getUser(@RequestParam(name = "id", required = false) Long userId,
                                         @RequestParam(name = "name", required = false) String userName) {
        return userServiceImpl.getUser(userId, userName);
    }

    @GetMapping(path = "get-all")
    public List<ResponseUserDto> getUsers() {
        return userServiceImpl.getUsers();
    }

    @DeleteMapping(path = "delete-by/{id}")
    public void deleteUserById(@PathVariable(name = "id") Long userId) {
        userServiceImpl.deleteUserById(userId);
    }

    @DeleteMapping(path = "delete")
    public void deleteAllUsers() {
        userServiceImpl.deleteAllUsers();
    }

    @PostMapping(path = "add-role")
    public Role addRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }
}
