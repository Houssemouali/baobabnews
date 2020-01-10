package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Security.JwtProvider;
import com.pentabell.baobabnews.Security.response.JwtResponse;
import com.pentabell.baobabnews.model.Internaute;
import com.pentabell.baobabnews.model.Requests.LoginForm;
import com.pentabell.baobabnews.model.Requests.LoginModeratorForm;
import org.springframework.beans.factory.annotation.Autowired;
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

    //@PreAuthorize("hasRole('Moderateur')")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateModerator(@Valid @RequestBody LoginModeratorForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserName = authentication.getName();
//            System.out.println(currentUserName);
//        }
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has name: " + userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }
}