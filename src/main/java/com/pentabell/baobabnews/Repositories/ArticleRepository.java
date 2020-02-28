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

    @Query(value="SELECT * "+
            "FROM article as a " +
            ", category as c " +
            ", content_details as cd " +
            ", article_categories as aca " +
            ", article_content as aco "+
            "where a.id_article= aca.article_id " +
            "AND a.id_article = aco.article_id " +
            "AND c.id = aca.category_id " +
            "AND cd.id = aco.content_id " +
            "and c.name =:category_name " +
            "ORDER BY date_created desc ",nativeQuery = true)
    List<Article>findArticlesByCategory(String category_name);
}
