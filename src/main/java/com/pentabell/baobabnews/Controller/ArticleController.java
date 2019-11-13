package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/article")
public class ArticleController {
   @Qualifier("articleRepository")
    @Autowired
    ArticleRepository arepo;

    @PostMapping(path="/")
    @ResponseBody
    public Article addArticle(@RequestBody @Valid Article article){
        return arepo.save(article);

    }

    @GetMapping(path="/",produces=MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Article> getAllData() {
        return arepo.findAll();

    }
}
