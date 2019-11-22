package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.Repositories.CategoryRepository;
import com.pentabell.baobabnews.ServiceImpl.ArticleService;
import com.pentabell.baobabnews.model.Article;
import com.pentabell.baobabnews.model.Category;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping(path="/")
    @ResponseBody
    public Article addArticle(@RequestBody @Valid Article article){
        return arepo.save(article);
        //return cra.save(category);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Article> updateVehicle(@PathVariable(value = "id") Long id,
                                                         @RequestBody Article articleUpdateDTO){
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

    @GetMapping(path="/",produces=MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Article> getAllData() {
        return arepo.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable("id") long articleId) {
        //HttpHeaders headers = new HttpHeaders();
        Article tagi =arepo.findById(articleId).orElseThrow(()->new ResourceNotFoundException("tag not found for this id :: " + articleId));
        arepo.delete(tagi);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }


}
