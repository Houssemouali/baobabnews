package com.pentabell.baobabnews.ServiceImpl;

import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public abstract class ArticleService implements ArticleRepository {
    @Qualifier("articleRepository")
    @Autowired
    private ArticleRepository articlerepo;

    public void addArticle(Article ar){
        articlerepo.save(ar);
    }
}
