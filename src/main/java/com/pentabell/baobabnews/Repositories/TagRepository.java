package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    //public List<Tag> getTag();
//    boolean delete(Long id);
}
