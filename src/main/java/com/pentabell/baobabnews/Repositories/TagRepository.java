package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
