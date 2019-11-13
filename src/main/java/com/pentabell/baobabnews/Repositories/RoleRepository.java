package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Role;
import com.pentabell.baobabnews.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
        Optional<Role> findByName(RoleName roleName);

        }
