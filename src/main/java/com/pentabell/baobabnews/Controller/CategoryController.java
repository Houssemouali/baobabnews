package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.CategoryRepository;
import com.pentabell.baobabnews.Repositories.JournalistRepository;
import com.pentabell.baobabnews.model.Category;
import com.pentabell.baobabnews.model.Journaliste;
import com.pentabell.baobabnews.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryRepository cat;
//    @PostMapping(path="/")
//    @ResponseBody
//    public Journaliste addJournalist(@RequestBody @Valid Journaliste journaliste){
//        return jr.save(journaliste);
//    }


    //    @GetMapping(path="/",produces= MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin("*")
//    public Iterable<Journaliste> getAllData() {
//        return jr.findAll();
//
//    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Category>> getParties() {
        return new ResponseEntity<>(cat.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getTutorialById(@PathVariable("id") long id) {
        Optional<Category> articleData = cat.findById(id);

        if (articleData.isPresent()) {
            return new ResponseEntity<>(articleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Category> getTutorialByName(@PathVariable("name") String name) {
        Optional<Category> articleData = cat.getByName(name);
        if (articleData.isPresent()) {
            return new ResponseEntity<>(articleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
