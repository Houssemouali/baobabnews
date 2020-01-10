package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Moderateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderateur,Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
