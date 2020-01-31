package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {
}
