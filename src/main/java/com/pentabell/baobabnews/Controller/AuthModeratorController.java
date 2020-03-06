package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.InternauteRepository;
import com.pentabell.baobabnews.Repositories.RoleRepository;
import com.pentabell.baobabnews.Security.JwtProvider;
import com.pentabell.baobabnews.Security.response.JwtResponse;
import com.pentabell.baobabnews.model.Requests.LoginModeratorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/Moderator")
public class AuthModeratorController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    InternauteRepository userRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    RoleRepository roleRepository;


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

        ArrayList<String> sRo = new ArrayList<String>();
        sRo.add("ROLE_Moderateur");
        ArrayList<String> lstAuth = new ArrayList<String>();
        lstAuth.add(userDetails.getAuthorities().toString().replace("[ROLE_Moderateur]", "ROLE_Moderateur"));
        System.out.println("the auth list contain =" + lstAuth);

        if (lstAuth.containsAll(sRo)) {
            System.out.println("User has name: " + userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

        } else
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("ACCESS DENIED");

    }
}
