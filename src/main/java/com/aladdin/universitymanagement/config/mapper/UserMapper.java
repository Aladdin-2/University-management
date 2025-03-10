package com.aladdin.universitymanagement.config.mapper;

import com.aladdin.universitymanagement.dao.entitys.User;
import com.aladdin.universitymanagement.model.dto.user.ResponseUserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {


    public ResponseUserDto toDto(User user) {
        return new ResponseUserDto(
                user.getUsername(),
                user.getPassword()
        );
    }

    public List<ResponseUserDto> toDto(List<User> users) {
        return users.stream().map(this::toDto).toList();
    }

}
