package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.ContentDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContentRepository extends CrudRepository<ContentDetails,Long> {
}
