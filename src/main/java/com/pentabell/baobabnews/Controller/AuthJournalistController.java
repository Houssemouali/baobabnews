package com.pentabell.baobabnews.Controller;

import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.Repositories.JournalistRepository;
import com.pentabell.baobabnews.Repositories.RoleRepository;
import com.pentabell.baobabnews.Security.JwtProvider;
import com.pentabell.baobabnews.Security.response.JwtResponse;
import com.pentabell.baobabnews.ServiceImpl.FilesJournalistService;
import com.pentabell.baobabnews.model.*;
import com.pentabell.baobabnews.model.Requests.LoginForm;
import com.pentabell.baobabnews.model.Requests.LoginJournalistForm;
import com.pentabell.baobabnews.model.Requests.SignUpForm;
import com.pentabell.baobabnews.model.Requests.SignUpJournalistForm;
import com.pentabell.baobabnews.model.Response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/journalist/auth")
public class AuthJournalistController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JournalistRepository journalistRepository;

    @Autowired
    ArticleRepository arepo;
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    RoleRepository roleRepository;

//    @Autowired
//    FilesJournalistService filesJournalistService;

    private Authentication authentication;
    private String nameUser;

    @Autowired
    PasswordEncoder encoder;

    //@PreAuthorize("hasRole('Journalist')")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateInternaute(@Valid @RequestBody LoginJournalistForm loginRequest) {

         authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        nameUser=authentication.getName();
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserName = authentication.getName();
//            System.out.println(currentUserName);
//        }
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has name: " +nameUser + " id=> " + authentication.getAuthorities());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    //,@RequestParam("file") MultipartFile cvfile,@RequestParam("file") MultipartFile portefoliofile
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpJournalistForm signUpRequest,@RequestParam("file") MultipartFile cvFile,@RequestParam("file") MultipartFile portefolioFile) {
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpJournalistForm signUpRequest) {
        if (journalistRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (journalistRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }


//for your segment file fill from client side(in request)
        // Creating user's account
        Journaliste user = new Journaliste(signUpRequest.getEmail(), signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getActualEntreprise(), signUpRequest.getNationality(), signUpRequest.getExperience(),
                signUpRequest.getNom(), signUpRequest.getPrenom(), signUpRequest.getNumtel(), signUpRequest.getDatenaiss(), signUpRequest.getMotivationtext(),
                signUpRequest.getCv(), signUpRequest.getPortefolio());

        //filesJournalistService.storeFile(signUpRequest.getCv(),signUpRequest.getPortefolio());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        //signUpRequest.setRole("journalist");
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
                    roles.add(adminRole);

                    break;
                case "journalist":
                    Role jRole = roleRepository.findByName(RoleName.ROLE_Journalist)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Journalist Role not found."));
                    roles.add(jRole);

                    break;
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
//        Role jRole = roleRepository.findByName(RoleName.ROLE_Journalist)
//                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Journalist Role not found."));
//        roles.add(jRole);
        user.setRoles(roles);
        journalistRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("Journalist registered successfully!"), HttpStatus.OK);
    }




    public Long getMyIdFromSession(String name) {
        //, LoginJournalistForm loginRequest
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//        } else {
//            String username = principal.toString();
//        }
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    //String hdsk = authentication.getName();
        Long author_id =journalistRepository.authenticatedUser(nameUser);
        System.out.println("user name"+nameUser+"id"+author_id);
        return author_id;
        //return authentication().getName().compareTo(journalistRepository.authenticatedUser());
    }

    @GetMapping(value = "/articleBy/{journalistId}")
    public ResponseEntity<List<Article>> getMyArticle(@PathVariable Long journalistId) {
        //, LoginJournalistForm loginRequest
//        //return arepo.findArticlesByAuthor(journalistId);
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
////        String hdsk = authentication.getName();
        Long idss=getMyIdFromSession(nameUser);
        System.out.println("user id=="+idss);
//
//
//        Journaliste author = journalistRepository.getOne(journalistRepository.authenticatedUser(hdsk));
//         Long j_id = journalistRepository.authenticatedUser(hdsk);
//        System.out.println("id of user" + j_id);
//getmyarticle -
        return new ResponseEntity<>(arepo.findArticlesByAuthor(idss),HttpStatus.FOUND);
    }



}
