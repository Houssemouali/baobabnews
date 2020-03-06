package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.Repositories.ArticleSearchRepository;
import com.pentabell.baobabnews.Repositories.CategoryRepository;
import com.pentabell.baobabnews.Security.JwtAuthTokenFilter;
import com.pentabell.baobabnews.Security.JwtProvider;
import com.pentabell.baobabnews.ServiceImpl.ArticleService;
import com.pentabell.baobabnews.ServiceImpl.CategoryService;
import com.pentabell.baobabnews.model.*;
import com.pentabell.baobabnews.model.Requests.ArticleForm;
import com.pentabell.baobabnews.model.Requests.ContentDetailsForm;
import com.sipios.springsearch.anotation.SearchSpec;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateQuery;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

import org.hibernate.Session;

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

    public static final String uploadingDir = System.getProperty("user.dir") + "/uploadingDir/";

    @Autowired
    private AuthJournalistController journalistDetails;


    //    public Category getCatbyId(Long catID){
//        return categoryList.stream().filter(
//                (category) -> category.getId()==catID
//        ).findFirst().get();
//    }
//, @RequestParam("files") MultipartFile files
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

//        articleService.uploadFile(files);
//        String filename=files.getOriginalFilename();
        Article articlef = new Article(article.getDate(),article.getStatus());
        Set<String> strCategory = article.getCategory();
        Set<Category> categories = new HashSet<>();
        //Language strlang = article.getLanguageArticle();
        Journaliste strauthor = article.getAuthor();
        //ContentDetails contenus=article.getContentDetails();
        HashSet<ContentDetails>setContent=article.getContentDetails();
        HashSet<Tag> strtag = article.getTags();
        HashSet<Country> strcountry = article.getCountries();
        //HashSet<Category>strCate=article.getCategory();
        long id_article=articlef.getIdArticle();
//        Language lang=new Language();
//        Journaliste author=new Journaliste();
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
        //(after database changing) articlef.setLanguageArticle(strlang);
        articlef.setTags(strtag);
        articlef.setCountries(strcountry);
        articlef.setContentDetails(setContent);
        //arepo.save(articlef);
        System.out.println("---------"+id_article);
        return new ResponseEntity<>(arepo.save(articlef), HttpStatus.CREATED);
        //return arepo.save(article);
        //return cra.save(category);
    }
//after database changing
//    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Article> updateVehicle(@PathVariable(value = "id") Long id,
//                                                 @RequestBody Article articleUpdateDTO) {
//        return new ResponseEntity<>(articleService.updateVehicle(id, articleUpdateDTO), HttpStatus.OK);
//    }

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

        Iterable<Article> arts = arepo.findAll();

        /*for (Article art : arts){
            if (art.getAuthor() != null){
                long id = art.getAuthor().getIdUser();
                long count = 0;
                for (Article a : arts){
                    if (a.getAuthor().getIdUser() == id)
                        count++;
                }
                art.getAuthor().setPostsNumber(count);
            }
        }*/

        return arts;
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

    @RequestMapping(value = "/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.checkIfIdIsPresentandReturnAuthor(id);
    }

    @GetMapping("/ArticleBy/{id}")
    public ResponseEntity<Article> getTutorialById(@PathVariable("id") long id) {
        Optional<Article> articleData = arepo.findById(id);

        if (articleData.isPresent()) {
            return new ResponseEntity<>(articleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Search Article
    //example of link in json http://localhost:8080/api/article/articleFind?search=(titre:'test lundi')
    @GetMapping("/articleFind")
    public ResponseEntity<List<Article>> searchForCars(@SearchSpec Specification<Article> articleSpecification) {
        return new ResponseEntity<>(ArtRepo.findAll(Specification.where(articleSpecification)), HttpStatus.FOUND);
    }

    //
//    @GetMapping(value="/articleBy/{journalistId}")
//    public List<Article> getMyArticle(@PathVariable Long journalistId){
//        //return arepo.findArticlesByAuthor(journalistId);
//        return arepo.findArticlesByAuthor(journalistId);
//    }
    @ResponseBody
    @GetMapping(value = "/edit/{idArticle}")
    public Optional<Article> editArticle(@PathVariable("idArticle") Long idArticle) {

        return arepo.findById(idArticle);
    }


    //get by category economie {}
    @GetMapping(path = "/ByCatEco", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAuthority('Moderateur') or hasAuthority('ROLE_AMIN')")
    public ResponseEntity<?> getArtByCatEco(String category_name) {
        category_name = "economie";
        Iterable<Article> EconomyArticle = arepo.findArticlesByCategory(category_name);
        return new ResponseEntity<>(EconomyArticle, HttpStatus.FOUND);
    }

    //get by category lifestyle {}
    @GetMapping(path = "/ByCatlifestyle", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAuthority('Moderateur') or hasAuthority('ROLE_AMIN')")
    public ResponseEntity<?> getArtByCatlifestyle(String category_name) {
        category_name = "lifestyle";
        Iterable<Article> EconomyArticle = arepo.findArticlesByCategory(category_name);
        return new ResponseEntity<>(EconomyArticle, HttpStatus.FOUND);
    }

    //get by category lifestyle {Status valid to add next }
    @GetMapping(path = "/ByCattech", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAuthority('Moderateur') or hasAuthority('ROLE_AMIN')")
    public ResponseEntity<?> getArtByCatTechnologie(String category_name) {
        category_name = "technologie";
        Iterable<Article> TechArticle = arepo.findArticlesByCategory(category_name);
        if (TechArticle != null) {
            return new ResponseEntity<>(TechArticle, HttpStatus.FOUND);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get by category lifestyle {ajout du condition not found}
    @GetMapping(path = "/ByCatEntreprise", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAuthority('Moderateur') or hasAuthority('ROLE_AMIN')")
    public ResponseEntity<?> getArtByCatEntreprise(String category_name) {
        category_name = "entreprise";
        return Optional.ofNullable(arepo.findArticlesByCategory(category_name))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //get last article
    @GetMapping(path = "/LastArticle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLastPostedArticle(String status_art, String category_name) {
        category_name = "entreprise";
        status_art = "en cours";
        //lstLastArticle.add(arepo.firstArticleByDate(category_name,status_art));
        //arepo.firstArticleByDate(category_name,status_art);
        List<Article> lstLastArticle = arepo.lastArticle();
        return new ResponseEntity<>(lstLastArticle, HttpStatus.FOUND);
    }


    //fellow 4 article
    @GetMapping(path = "/Last4Article", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLastfourArticle(String category_name) {
        category_name = "entreprise";
        //lstLastArticle.add(arepo.firstArticleByDate(category_name,status_art));
        //arepo.firstArticleByDate(category_name,status_art);
        List<Article> lstLast4Article = arepo.firstArticleByDate(category_name);
        return new ResponseEntity<>(lstLast4Article.subList(1, 5), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/MyArticle", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> MyArticleAsJournalist(Principal principal, String loggeduser) {
        loggeduser = principal.getName();
        Iterable<Article> mesArticles = arepo.MyArticles(loggeduser);
        if (mesArticles != null) {
            return new ResponseEntity<>(mesArticles, HttpStatus.FOUND);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/MyValidArticle", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> MyValidArticleAsJournalist(Principal principal, String loggeduser) {
        loggeduser = principal.getName();
        Iterable<Article> mesArticles = arepo.MyValidArticles(loggeduser);
        if (mesArticles != null) {
            return new ResponseEntity<>(mesArticles, HttpStatus.FOUND);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get en cours article
    @GetMapping(path = "/pendingarticle", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Article> getPendingArticle(String status) {
        status = "en cours";
        Iterable<Article> PendingArticle = arepo.getAllByStatus(status);
        return PendingArticle;
    }

    @GetMapping(path = "/validarticle", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Article> getValidArticle(String status) {
        status = "valid";
        Iterable<Article> validArticle = arepo.getAllByStatus(status);
        return validArticle;
    }

    //update article status
    @PutMapping(value = "/changeStatus/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> ValidateJournalistStatus(@PathVariable(value = "idArticle") Long idArticle) {
        return new ResponseEntity<>(this.updateArticleStatus(idArticle), HttpStatus.ACCEPTED);
    }

    public Article updateArticleStatus(long idArticle) {

        if (arepo.findById(idArticle).isPresent()) {
            Article existingArticle = arepo.findById(idArticle).get();
            existingArticle.setStatus("valid");


            Article updatedArticle = arepo.save(existingArticle);

            return new Article(updatedArticle.getStatus(),
                    updatedArticle.getContentDetails()
            );
        } else {
            return null;
        }
    }


}