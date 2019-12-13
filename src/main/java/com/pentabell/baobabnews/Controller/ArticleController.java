package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.Repositories.CategoryRepository;
import com.pentabell.baobabnews.ServiceImpl.ArticleService;
import com.pentabell.baobabnews.ServiceImpl.CategoryService;
import com.pentabell.baobabnews.model.*;
import com.pentabell.baobabnews.model.Requests.ArticleForm;
import com.pentabell.baobabnews.model.Response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transaction;
import javax.validation.Valid;
import javax.websocket.Session;
import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    ArticleRepository arepo;
    @Autowired
    CategoryRepository cra;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;


//    public Category getCatbyId(Long catID){
//        return categoryList.stream().filter(
//                (category) -> category.getId()==catID
//        ).findFirst().get();
//    }

    @PostMapping(path = "/AddArticle")
    @ResponseBody
    public ResponseEntity<?> addArticle(@RequestBody @Valid ArticleForm article) {
//       for(Category c:article.getCategories()){
//              Category cc=categoryService.getCatbyId(c.getId());
//                     // cra.getOne(c.getId());
//            cc.getArticles().add(article);
//            System.out.println("testing add = "+c.getId());
//       }
//       return null;
        Article articlef = new Article(article.getTitle(), article.getContent(), article.getDate());
        Set<String> strCategory = article.getCategory();
        Set<Category> categories = new HashSet<>();
        Language strlang = article.getLanguageArticle();
        Journaliste strauthor = article.getAuthor();
        HashSet<Tag> strtag = article.getTags();
        HashSet<Country> strcountry = article.getCountries();
//        Language lang=new Language();
//        Journaliste author=new Journaliste();


        strCategory.forEach(category -> {
            switch (category) {
                case "economie":
                    Category financeCategory = cra.findByName(CategoryName.economie)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: category not found."));
                    categories.add(financeCategory);

                    break;
                case "lifestyle":
                    Category lfCategory = cra.findByName(CategoryName.lifestyle)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: category not found."));
                    categories.add(lfCategory);

                    break;
                case "technologie":
                    Category techCategory = cra.findByName(CategoryName.technologie)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: category not found."));
                    categories.add(techCategory);

                    break;
                default:
                    Category entrepriseCategory = cra.findByName(CategoryName.entreprise)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    categories.add(entrepriseCategory);
            }
        });
        articlef.setCategories(categories);
        articlef.setAuthor(strauthor);
        articlef.setLanguageArticle(strlang);
        articlef.setTags(strtag);
        articlef.setCountries(strcountry);
        //arepo.save(articlef);

        return new ResponseEntity<>(arepo.save(articlef), HttpStatus.CREATED);
        //return arepo.save(article);
        //return cra.save(category);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Article> updateVehicle(@PathVariable(value = "id") Long id,
                                                 @RequestBody Article articleUpdateDTO) {
        return new ResponseEntity<>(articleService.updateVehicle(id, articleUpdateDTO), HttpStatus.OK);
    }

    //    @PutMapping(value="/{articleId}/categories",consumes=MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity addCategoryToMenu(
//            @PathVariable Long ArticleId, @RequestBody Category Categoryarticles) {
//        try {
//            articleService.AddCategoryToMenu(ArticleId, Categoryarticles);
//            return ResponseEntity.noContent().build();
//        } catch (EntityNotFoundException error) {
//            return ResponseEntity.status(400).body(error.getMessage());
//        }
//    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Article> getAllData() {
        return arepo.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable("id") long articleId) {
        //HttpHeaders headers = new HttpHeaders();
        Article tagi = arepo.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("tag not found for this id :: " + articleId));
        arepo.delete(tagi);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @RequestMapping(value="/{id}")
    public Article getArticle(@PathVariable Long id ){
        return articleService.checkIfIdIsPresentandReturnAuthor(id);
    }

}