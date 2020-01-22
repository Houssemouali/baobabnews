package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Article;
import com.pentabell.baobabnews.model.Category;
import com.pentabell.baobabnews.model.Journaliste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
//@Transactional(propagation = Propagation.MANDATORY)
public interface ArticleRepository extends CrudRepository<Article,Long> {


//    @Query(value= "insert into")
//    public Article AddCategoryToArticle(Long articleId, Long categoryId);
    @Query(value="SELECT * "+
            " from article as a "+
            "INNER JOIN journaliste as j ON a.journalist_id = j.id_user"
            +" where j.id_user =:journalist_id", nativeQuery = true)
    List<Article> findArticlesByAuthor (@Param("journalist_id") Long JournalistId);
}
