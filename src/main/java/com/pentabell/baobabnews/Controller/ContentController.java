package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ContentRepository;
import com.pentabell.baobabnews.model.Article;
import com.pentabell.baobabnews.model.ContentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/content")
public class ContentController {
    @Autowired
    ContentRepository contentRepository;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<ContentDetails> getAllData() {
        Iterable<ContentDetails> arts = contentRepository.findAll();
        return arts;
    }
}
