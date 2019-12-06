package com.pentabell.baobabnews.ServiceImpl;

import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.Repositories.CategoryRepository;
import com.pentabell.baobabnews.model.Article;
import com.pentabell.baobabnews.model.Category;
import com.pentabell.baobabnews.model.Requests.ArticleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public  class ArticleService  {
//    @Qualifier("articleRepository")
   @Autowired
   private ArticleRepository articlerepo;

   @Autowired
    private CategoryRepository catrep;

//   public Article createArticle(Article article){
//       return articlerepo.save(article);
//   }
//   public void AddCategoryToMenu(Long articleId,Category CategoryId){
//       Article article=articlerepo.findById(articleId).orElseThrow(EntityNotFoundException::new);
//       int x= CategoryId.getId();
//       Long s=Long.valueOf(x);
//       System.out.println("id category"+s);
//       Category category=catrep
//               .findById(s).orElseThrow(EntityNotFoundException::new);
//       article.addCategory(category);
//       articlerepo.save(article);
//   }

//    public void addArticle(Article ar,Category c){
//        articlerepo.save(ar);
//    }

//    @Override
//    public void AddCategoryToArticle(Article a, Set<Category> c) {
//    }


//    public List<Article> getBooksById(Long id )
//    {
//        return checkIfIdIsPresentandReturnAuthor( id ).getBookList();
//    }

    public Article checkIfIdIsPresentandReturnAuthor( Long id )
    {
        if ( !articlerepo.findById( id ).isPresent() )
            throw new ResourceNotFoundException( " Article id = " + id + " not found" );
        else
            return articlerepo.findById( id ).get();
    }


    public Article updateVehicle(long id, Article articleDTO) {

        if (articlerepo.findById(id).isPresent()){
            Article existingArticle = articlerepo.findById(id).get();
            java.sql.Timestamp datea = new java.sql.Timestamp(new java.util.Date().getTime());
            Date actualDate=datea;
            existingArticle.setTitre(articleDTO.getTitre());
            existingArticle.setContent(articleDTO.getContent());
            existingArticle.setLanguageArticle(articleDTO.getLanguageArticle());
            existingArticle.setAuthor(articleDTO.getAuthor());
            existingArticle.setDate(articleDTO.getDate());
            existingArticle.setTags(articleDTO.getTags());

            Article updatedArticle = articlerepo.save(existingArticle);

            return new Article(updatedArticle.getTitre(),
                    updatedArticle.getContent(),
                    updatedArticle.getLanguageArticle(),
            updatedArticle.getAuthor(),
            updatedArticle.getTags(),
                    updatedArticle.getCountries()
            );
        }else{
            return null;
        }
    }


}
