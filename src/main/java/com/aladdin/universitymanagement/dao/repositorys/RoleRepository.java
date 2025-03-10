package com.aladdin.universitymanagement.dao.repositorys;

import com.aladdin.universitymanagement.dao.entitys.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
