package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.InternauteRepository;
import com.pentabell.baobabnews.model.Internaute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class InternauteController {
    @Autowired
    InternauteRepository ir;

    @PostMapping(path="/")
    @ResponseBody
    public Internaute addInternaute(@RequestBody @Valid Internaute user){
        return ir.save(user);

    }

    @GetMapping(path="/",produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Internaute> getAllData() {
        return ir.findAll();

    }
}
