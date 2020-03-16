package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.AdminRepository;
import com.pentabell.baobabnews.Repositories.InternauteRepository;
import com.pentabell.baobabnews.Repositories.RoleRepository;
import com.pentabell.baobabnews.Security.JwtProvider;
import com.pentabell.baobabnews.Security.response.JwtResponse;
import com.pentabell.baobabnews.model.Admin;
import com.pentabell.baobabnews.model.Requests.LoginAdminForm;
import com.pentabell.baobabnews.model.Requests.SignUpAdminForm;
import com.pentabell.baobabnews.model.Response.ResponseMessage;
import com.pentabell.baobabnews.model.Role;
import com.pentabell.baobabnews.model.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/admin")
public class AuthAdminController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginAdminForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        ArrayList<String> sRo = new ArrayList<String>();
        sRo.add("ROLE_ADMIN");
        ArrayList<String> lstAuth = new ArrayList<String>();
        lstAuth.add(userDetails.getAuthorities().toString().replace("[ROLE_ADMIN]", "ROLE_ADMIN"));
        System.out.println("the auth list contain =" + lstAuth);
        if (lstAuth.containsAll(sRo)) {
            System.out.println("User has name: " + userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
        } else
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("ACCESS DENIED");
    }


    /*sign up admin ,add another admin*/

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/signup_admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignUpAdminForm adminFormForm) {
        if (adminRepository.existsByUsername(adminFormForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (adminRepository.existsByEmail(adminFormForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        Admin user = new Admin(adminFormForm.getEmail(),
                adminFormForm.getUsername(),
                encoder.encode(adminFormForm.getPassword()),
                adminFormForm.getNumtel(),
                adminFormForm.getNationality(),
                adminFormForm.getDatenaiss()
        );

        Set<String> strRoles = adminFormForm.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
//                case "admin":
//                    Role mRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Admin Role not find."));
//                    roles.add(mRole);
//
//                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        adminRepository.save(user);
        return new ResponseEntity<>(new ResponseMessage("Admin registered successfully"), HttpStatus.CREATED);
    }
}
