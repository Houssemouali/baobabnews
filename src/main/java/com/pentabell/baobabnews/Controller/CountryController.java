package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.CountryRepository;
import com.pentabell.baobabnews.model.Article;
import com.pentabell.baobabnews.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    CountryRepository cr;


    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Country> getAllData() {
        return cr.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getTutorialById(@PathVariable("id") long id) {
        Optional<Country> articleData = cr.findById(id);

        if (articleData.isPresent()) {
            return new ResponseEntity<>(articleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
