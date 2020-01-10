package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ModeratorRepository;
import com.pentabell.baobabnews.Repositories.RoleRepository;
import com.pentabell.baobabnews.Security.JwtProvider;
import com.pentabell.baobabnews.Security.response.JwtResponse;
import com.pentabell.baobabnews.model.Internaute;
import com.pentabell.baobabnews.model.Moderateur;
import com.pentabell.baobabnews.model.Requests.LoginAdminForm;
import com.pentabell.baobabnews.model.Requests.LoginForm;
import com.pentabell.baobabnews.model.Requests.SignUpAdminForm;
import com.pentabell.baobabnews.model.Requests.SignUpModeratorForm;
import com.pentabell.baobabnews.model.Response.ResponseMessage;
import com.pentabell.baobabnews.model.Role;
import com.pentabell.baobabnews.model.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/auth")
public class AdminController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ModeratorRepository mRepo;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/signin")
    //@PreAuthorize("hasRole('ADMIN')")
    //@Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginAdminForm loginRequest) {

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
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/signup_moderator")
    public ResponseEntity<?> registerModerator(@Valid @RequestBody SignUpModeratorForm moderatorForm){
        if (mRepo.existsByUsername(moderatorForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (mRepo.existsByEmail(moderatorForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        Moderateur user=new Moderateur(moderatorForm.getEmail(),moderatorForm.getUsername(),encoder.encode(moderatorForm.getPassword()),moderatorForm.getNumtel());

        Set<String> strRoles = moderatorForm.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role->{
            switch (role){
              case "moderateur":
                    Role mRole = roleRepository.findByName(RoleName.ROLE_Moderateur)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Moderator Role not find."));
                    roles.add(mRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        mRepo.save(user);
        return new ResponseEntity<>(new ResponseMessage("Moderator registered successfully"),HttpStatus.CREATED);
    }

    @GetMapping(path="/",produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Moderateur> getAllData() {
        return mRepo.findAll();
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/signup_admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignUpAdminForm adminFormForm){
        if (mRepo.existsByUsername(adminFormForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (mRepo.existsByEmail(adminFormForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        Moderateur user=new Moderateur(adminFormForm.getEmail(),adminFormForm.getUsername(),encoder.encode(adminFormForm.getPassword()));

        Set<String> strRoles = adminFormForm.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role->{
            switch (role){
                case "admin":
                    Role mRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Admin Role not find."));
                    roles.add(mRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        mRepo.save(user);
        return new ResponseEntity<>(new ResponseMessage("Admin registered successfully"),HttpStatus.CREATED);
    }

}
