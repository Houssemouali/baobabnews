package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.Repositories.ArticleSearchRepository;
import com.pentabell.baobabnews.Repositories.CategoryRepository;
import com.pentabell.baobabnews.Security.JwtAuthTokenFilter;
import com.pentabell.baobabnews.Security.JwtProvider;
import com.pentabell.baobabnews.Security.UserPrinciple;
import com.pentabell.baobabnews.ServiceImpl.ArticleService;
import com.pentabell.baobabnews.ServiceImpl.CategoryService;
import com.pentabell.baobabnews.model.*;
import com.pentabell.baobabnews.model.Requests.ArticleForm;
import com.pentabell.baobabnews.model.Response.ResponseMessage;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    ArticleSearchRepository ArtRepo;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtProvider tokenProvider;

    private JwtAuthTokenFilter jwtAuthFilter;


    @Autowired
    private AuthJournalistController journalistDetails;

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
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserName = authentication.getName();
//            System.out.println(currentUserName);
//        }
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String AuthorUsername=userDetails.getUsername().toString();
        //System.out.println("User has name: " + AuthorUsername);


//        String token=tokenProvider.generateJwtToken();
//        String jwt=tokenProvider.getUserNameFromJwtToken()
//        String username=tokenProvider.getUserNameFromJwtToken(jwt);
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

    //Search Article
    //example of link in json http://localhost:8080/api/article/articleFind?search=(titre:'test lundi')
    @GetMapping("/articleFind")
    public ResponseEntity<List<Article>> searchForCars(@SearchSpec Specification<Article> articleSpecification){
        return new ResponseEntity<>(ArtRepo.findAll(Specification.where(articleSpecification)),HttpStatus.FOUND);
    }

    @GetMapping(value="/articleBy/{journalistId}")
    public List<Article> getMyArticle(@PathVariable Long journalistId){
        //return arepo.findArticlesByAuthor(journalistId);
        return arepo.findArticlesByAuthor(journalistId);
    }
}