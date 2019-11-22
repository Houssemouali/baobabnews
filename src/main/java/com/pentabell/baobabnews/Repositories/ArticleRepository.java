package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Article;
import com.pentabell.baobabnews.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ArticleRepository extends CrudRepository<Article,Long> {
//    @Query(value= "insert into")
//    public Article AddCategoryToArticle(Long articleId, Long categoryId);
}
