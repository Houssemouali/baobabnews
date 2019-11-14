package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.CountryRepository;
import com.pentabell.baobabnews.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    CountryRepository cr;


    @GetMapping(path="/",produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Country> getAllData() {
        return cr.findAll();
    }

}
