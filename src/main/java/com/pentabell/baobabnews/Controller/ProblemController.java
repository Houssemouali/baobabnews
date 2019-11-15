package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ProblemRepository;
import com.pentabell.baobabnews.model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/problem")
public class ProblemController {
    @Autowired
    ProblemRepository pr;

    @PostMapping(path="/")
    @ResponseBody
    public Problem AddProblem(@Valid @RequestBody Problem probleme){
        return pr.save(probleme);
    }

    @GetMapping(path="/",produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Problem> GetAllproblem(){
        return pr.findAll();
    }
}
