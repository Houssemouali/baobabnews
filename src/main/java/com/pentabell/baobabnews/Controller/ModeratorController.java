package com.pentabell.baobabnews.Controller;

import com.google.gson.Gson;
import com.pentabell.baobabnews.Repositories.JournalistRepository;
import com.pentabell.baobabnews.Security.JwtProvider;
import com.pentabell.baobabnews.Security.response.JwtResponse;
import com.pentabell.baobabnews.model.Internaute;
import com.pentabell.baobabnews.model.Journaliste;
import com.pentabell.baobabnews.model.Requests.LoginForm;
import com.pentabell.baobabnews.model.Requests.LoginModeratorForm;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Moderator/")
public class ModeratorController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JournalistRepository journalistRepository;

    //@PreAuthorize("hasRole('Moderateur')")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateModerator(@Valid @RequestBody LoginModeratorForm loginRequest) {
       // if(u.equals("Role_Moderator")) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ArrayList<String>sRo=new ArrayList<String>();
        sRo.add("ROLE_Moderateur");
        ArrayList<String>lstAuth=new ArrayList<String>();
        lstAuth.add(userDetails.getAuthorities().toString().replace("[ROLE_Moderateur]","ROLE_Moderateur"));
        System.out.println("the auth list contain ="+lstAuth);
            if(lstAuth.containsAll(sRo)){
           //System.out.println(userDetails.getAuthorities().toString());
            //Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserName = authentication.getName();
//            System.out.println(currentUserName);
//        //}
            //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
        }
        else
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("ACCESS DENIED");
    }

    @GetMapping(path="/PendingJournalist",produces = MediaType.APPLICATION_JSON_VALUE)
   //@PreAuthorize("hasAuthority('Moderateur') or hasAuthority('ROLE_AMIN')")
    public Iterable<Journaliste>getPendingJournalist(String status){
        status="en cours";
        Iterable<Journaliste> Pendingj=journalistRepository.getAllByStatus(status);
        return Pendingj;
    }
}
