package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.JournalistRepository;
import com.pentabell.baobabnews.model.Journaliste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/journalist")
public class JournalistController {
    @Autowired
    JournalistRepository jr ;
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
}
