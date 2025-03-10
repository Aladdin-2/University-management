package com.aladdin.universitymanagement.model.dto.user;

import com.aladdin.universitymanagement.dao.entitys.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDto {
    private String username;
    private String password;
    private Role role;
}
