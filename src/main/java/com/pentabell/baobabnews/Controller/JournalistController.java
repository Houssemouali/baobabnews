package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.Repositories.JournalistRepository;
import com.pentabell.baobabnews.model.Article;
import com.pentabell.baobabnews.model.Journaliste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/journalist")
public class JournalistController {
    @Autowired
    JournalistRepository jr;
    @Autowired
    ArticleRepository arepo;

    private String nameUser;
//    @PostMapping(path="/")
//    @ResponseBody
//    public Journaliste addJournalist(@RequestBody @Valid Journaliste journaliste){
//        return jr.save(journaliste);
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addParty(@RequestBody Journaliste journaliste) {
        return new ResponseEntity<>(jr.save(journaliste), HttpStatus.CREATED);
    }

    //    @GetMapping(path="/",produces= MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin("*")
//    public Iterable<Journaliste> getAllData() {
//        return jr.findAll();
//
//    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Journaliste>> getParties() {
        return new ResponseEntity<>(jr.findAll(), HttpStatus.FOUND);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Journaliste> getEmployeeById(@PathVariable(value = "id") Long journalistId)
            throws ResourceNotFoundException {
        Journaliste employee = jr.findById(journalistId)
                .orElseThrow(() -> new ResourceNotFoundException("Journalist not found for this id :: " + journalistId));
        return ResponseEntity.ok().body(employee);
    }

    public Long getMyIdFromSession(String name) {
        //, LoginJournalistForm loginRequest
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//        } else {
//            String username = principal.toString();
//        }
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        //String hdsk = authentication.getName();
        Long author_id = jr.authenticatedUser(nameUser);
        System.out.println("user name" + nameUser + "id" + author_id);
        return author_id;
        //return authentication().getName().compareTo(jr.authenticatedUser());
    }

    @GetMapping(value = "/articleBy/{journalistId}")
    public ResponseEntity<List<Article>> getMyArticle(@PathVariable Long journalistId) {
        //, LoginJournalistForm loginRequest
//        //return arepo.findArticlesByAuthor(journalistId);
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
////        String hdsk = authentication.getName();
        Long idss = getMyIdFromSession(nameUser);
        System.out.println("user id==" + idss);
//
//
//        Journaliste author = jr.getOne(jr.authenticatedUser(hdsk));
//         Long j_id = jr.authenticatedUser(hdsk);
//        System.out.println("id of user" + j_id);
//getmyarticle -
        return new ResponseEntity<>(arepo.findArticlesByAuthor(idss), HttpStatus.FOUND);
    }


    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Journaliste> getAllData() {
        Iterable<Journaliste> arts = jr.findAll();

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

    @GetMapping(path = "/PendingJournalist", produces = MediaType.APPLICATION_JSON_VALUE)
//@PreAuthorize("hasAuthority('Moderateur') or hasAuthority('ROLE_AMIN')")
    public Iterable<Journaliste> getPendingJournalist(String status) {
        status = "en cours";
        Iterable<Journaliste> Pendingj = jr.getAllByStatus(status);
        return Pendingj;
    }


    @PutMapping(value = "/changeStatus/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Journaliste> ValidateJournalistStatus(@PathVariable(value = "idUser") Long idUser) {
        return new ResponseEntity<>(this.updateJournalistStatus(idUser), HttpStatus.ACCEPTED);
    }

    public Journaliste updateJournalistStatus(long idUser) {

        if (jr.findById(idUser).isPresent()) {
            Journaliste existingJournalist = jr.findById(idUser).get();
            existingJournalist.setStatus("valid");


            Journaliste updatedJournalist = jr.save(existingJournalist);

            return new Journaliste(updatedJournalist.getName(),
                    updatedJournalist.getStatus()
            );
        } else {
            return null;
        }
    }


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }


    @PutMapping("/j/{name}")
    public ResponseEntity<Journaliste> updateJ(@PathVariable("id") long id, @RequestBody Journaliste journaliste) {
        System.out.println("Update RDV with ID = " + id + "...");
        Optional<Journaliste> journalisteData = jr.findById(id);
        if (journalisteData.isPresent()) {
            Journaliste _journaliste = journalisteData.get();
            _journaliste.setName(journaliste.getName());
            _journaliste.setStatus(journaliste.getStatus());

            return new ResponseEntity<>(jr.save(_journaliste), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
