package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.LanguageRepository;
import com.pentabell.baobabnews.model.Language;
import com.pentabell.baobabnews.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    LanguageRepository languageRepository;

    @GetMapping(path = "/")
    public Iterable<Language> getLanguage() {
        return languageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getTutorialById(@PathVariable("id") long id) {
        Optional<Language> articleData = languageRepository.findById(id);

        if (articleData.isPresent()) {
            return new ResponseEntity<>(articleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
