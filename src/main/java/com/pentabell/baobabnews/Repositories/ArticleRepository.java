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

import java.util.ArrayList;
import java.util.List;

@Repository
//@Transactional(propagation = Propagation.MANDATORY)
public interface ArticleRepository extends JpaRepository<Article, Long> {


    //    @Query(value= "insert into")
//    public Article AddCategoryToArticle(Long articleId, Long categoryId);
<<<<<<< HEAD
    @Query(value="SELECT * "+
            " from article as a "+
            "INNER JOIN journaliste as j ON a.journalist_id = j.id_user"
            +" where j.id_user =:journalist_id", nativeQuery = true)
    List<Article> findArticlesByAuthor (@Param("journalist_id") Long JournalistId);

    @Query(value="SELECT * "+
=======
    @Query(value = "SELECT * " +
            " from article as a " +
            "INNER JOIN journaliste as j ON a.journalist_id = j.id_user " +
            "INNER JOIN users as ur ON ur.id_user = j.id_user"
            + " where j.id_user =:journalist_id", nativeQuery = true)
    List<Article> findArticlesByAuthor(@Param("journalist_id") Long JournalistId);


    @Query(value = "SELECT * " +
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
            "FROM article as a " +
            ", category as c " +
            ", content_details as cd " +
            ", article_categories as aca " +
<<<<<<< HEAD
            ", article_content as aco "+
=======
            ", article_content as aco " +
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
            "where a.id_article= aca.article_id " +
            "AND a.id_article = aco.article_id " +
            "AND c.id = aca.category_id " +
            "AND cd.id = aco.content_id " +
            "and c.name =:category_name " +
<<<<<<< HEAD
            "ORDER BY date_created desc ",nativeQuery = true)
    List<Article>findArticlesByCategory(String category_name);
=======
            "ORDER BY date_created desc ", nativeQuery = true)
    List<Article> findArticlesByCategory(String category_name);

    @Query(value = "SELECT * " +
            "FROM article as a " +
            "WHERE a.date_created IN" +
            "(SELECT MAX(date_created) FROM article)", nativeQuery = true)
    List<Article> lastArticle();

    @Query(value = "SELECT * " +
            "from article as a " +
            ", category as c " +
            ", content_details as cd " +
            ", article_categories as ac " +
            ", article_content as aco " +
            "where a.id_article = ac.article_id " +
            "AND a.id_article= ac.article_id " +
            "AND c.id = ac.category_id " +
            "AND a.id_article = aco.article_id " +
            "AND cd.id = aco.content_id " +
            "AND a.status ='en cours' " +
            "and c.name =:category " +
            "ORDER BY date_created DESC"
            , nativeQuery = true)
    List<Article> firstArticleByDate(String category);

    @Query(value = "SELECT * " +
            "from article as a " +
            ", journaliste as j " +
            ", users as u " +
            "where a.journalist_id = j.id_user " +
            "AND j.id_user = u.id_user " +
            "AND u.username =:username " +
            "ORDER BY date_created DESC"
            , nativeQuery = true)
    List<Article> MyArticles(String username);

    @Query(value = "SELECT * " +
            "from article as a " +
            ", journaliste as j " +
            ", users as u " +
            "where a.journalist_id = j.id_user " +
            "AND j.id_user = u.id_user " +
            "AND u.username =:username " +
            "AND a.status ='valid' " +
            "ORDER BY date_created DESC"
            , nativeQuery = true)
    List<Article> MyValidArticles(String username);


    @Query(value = "select * from Article as a " +
            " where a.status =:article_status", nativeQuery = true)
    List<Article> getAllByStatus(String article_status);


>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
}
