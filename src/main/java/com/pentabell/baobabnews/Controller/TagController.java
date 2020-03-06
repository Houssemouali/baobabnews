package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Controller.Utils.ResponseHelper;
import com.pentabell.baobabnews.Repositories.TagRepository;
import com.pentabell.baobabnews.model.Article;
import com.pentabell.baobabnews.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    TagRepository trepo;

    public Tag addTag(Tag tag){
        return trepo.save(tag);
    }

    @PostMapping(path = "/")
    public ResponseEntity createTag(@Valid @RequestBody Tag tag, Errors errors) {
        if (errors.hasFieldErrors()) {
            return ResponseHelper.buildFieldErrorResponse(errors);
        }
        return ResponseEntity.ok(addTag(tag));
    }

    @GetMapping(path = "/")
    public Iterable<Tag> getTag() {

        return trepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTutorialById(@PathVariable("id") long id) {
        Optional<Tag> articleData = trepo.findById(id);

        if (articleData.isPresent()) {
            return new ResponseEntity<>(articleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable("id") long tagId) {
        //HttpHeaders headers = new HttpHeaders();
        Tag tagi = trepo.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("tag not found for this id :: " + tagId));
        trepo.delete(tagi);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
