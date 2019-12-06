package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Internaute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternauteRepository extends JpaRepository<Internaute,Long>  {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
