package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ArticleSearchRepository extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article
        > {
}
