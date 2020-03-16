package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Category;
import com.pentabell.baobabnews.model.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
//@Transactional(propagation = Propagation.MANDATORY)
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(CategoryName categoryName);

    Optional<Category> getByName(String name);
}
