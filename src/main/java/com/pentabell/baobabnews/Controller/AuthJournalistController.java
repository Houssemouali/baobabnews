package com.pentabell.baobabnews.Controller;

import ch.qos.logback.core.CoreConstants;
import com.pentabell.baobabnews.Repositories.ArticleRepository;
import com.pentabell.baobabnews.Repositories.JournalistRepository;
import com.pentabell.baobabnews.Repositories.RoleRepository;
import com.pentabell.baobabnews.Security.JwtProvider;
import com.pentabell.baobabnews.Security.response.JwtResponse;
import com.pentabell.baobabnews.ServiceImpl.FileStorageService;
import com.pentabell.baobabnews.ServiceImpl.FilesJournalistService;
import com.pentabell.baobabnews.ServiceImpl.MailService;
import com.pentabell.baobabnews.model.*;
import com.pentabell.baobabnews.model.Requests.LoginForm;
import com.pentabell.baobabnews.model.Requests.LoginJournalistForm;
import com.pentabell.baobabnews.model.Requests.SignUpForm;
import com.pentabell.baobabnews.model.Requests.SignUpJournalistForm;
import com.pentabell.baobabnews.model.Response.ResponseMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;
<<<<<<< HEAD
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
=======
import java.util.*;
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/journalist")
public class AuthJournalistController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JournalistRepository journalistRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FileStorageService filestorage;
//    @Autowired
//    FilesJournalistService filesJournalistService;


    private Authentication authentication;
    @Autowired
    ArticleRepository arepo;

    private String nameUser;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private MailService notificationService;


    private static final Logger logger = LoggerFactory.getLogger(AuthJournalistController.class);

    private Users userss;
    private static String upload_dir = "C:/springfileupload/";

    //@PreAuthorize("hasRole('Journalist')")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateInternaute(@Valid @RequestBody LoginJournalistForm loginRequest) {

        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        nameUser = authentication.getName();
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ArrayList<String> sRo = new ArrayList<String>();
        sRo.add("ROLE_Journalist");
        ArrayList<String> lstAuth = new ArrayList<String>();
        lstAuth.add(userDetails.getAuthorities().toString().replace("[ROLE_Journalist]", "ROLE_Journalist"));
        System.out.println("the auth list contain =" + lstAuth);
        if (lstAuth.containsAll(sRo)) {
            System.out.println("User has name: " + nameUser + " id=> " + authentication.getAuthorities());
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
        } else
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("ACCESS DENIED");
    }

    //,@RequestParam("file") MultipartFile cvfile,@RequestParam("file") MultipartFile portefoliofile
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpJournalistForm signUpRequest,@RequestParam("file") MultipartFile cvFile,@RequestParam("file") MultipartFile portefolioFile) {
    //    public ResponseEntity<?> registerUser(@Valid @RequestPart SignUpJournalistForm signUpRequest) throws IOException, SQLException {
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
//        if(!file.isEmpty()){
//            try {
//                byte[]bytes=file.getBytes();
//                // Creating the directory to store file
//                String rootPath = System.getProperty("catalina.home");
//                File dir = new File(rootPath + File.separator + "tmpFiles");
//                if (!dir.exists())
//                    dir.mkdirs();
//                // Create the file on server
//                File serverFile = new File(dir.getAbsolutePath()
//                        + File.separator + file.getOriginalFilename());
//                BufferedOutputStream stream = new BufferedOutputStream(
//                        new FileOutputStream(serverFile));
//                logger.info("Server File Location="
//                        + serverFile.getAbsolutePath());
//                //return new ResponseEntity<>(new ResponseMessage("You successfully uploaded file"));
//                stream.write(bytes);
//                stream.close();
//            } catch (Exception e) {
//                logger.error( "You failed to upload "  + " => " +e.getMessage());
//            }
//            }else {
//            System.out.println("You failed to upload because the file was empty." );
//        }
//        filestorage.store(file);
//        byte[]content=file.getBytes();
//        Blob blob =new SerialBlob(content);
//        InputStream issFile=file.getInputStream();

        Journaliste user = new Journaliste(signUpRequest.getEmail(),
<<<<<<< HEAD
                        signUpRequest.getUsername(),
                        encoder.encode(signUpRequest.getPassword()),
                        signUpRequest.getNumtel(),
=======
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getNumtel(),
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
                signUpRequest.getNationality(),
                signUpRequest.getDatenaiss(),
                signUpRequest.getExperience(),
                signUpRequest.getActualEntreprise(),
                signUpRequest.getMotivationtext(),
                signUpRequest.getName(),
                signUpRequest.getSurname(),
                signUpRequest.getCv(),
                //file,
                signUpRequest.getPortefolio(),
                signUpRequest.getStatus());
            // file storage work

        //signUpRequest.setCv();

//        ArrayList<String> fileNames = null;
//       MultipartFile CVfile=signUpRequest.getCv();
//        if (null != CVfile && CVfile.getSize() > 0)
//        {
//
//
//                String fileName = CVfile.getOriginalFilename();
//                fileNames.add(fileName);
//
//                File imageFile = new File(servletRequest.getServletContext().getRealPath("/image"), fileName);
//                try
//                {
//                    CVfile.transferTo(imageFile);
//                } catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }

//        for (MultipartFile file: signUpRequest.getCv()){
//            if(file.isEmpty()){
//                model.put("message","please select a file to upload");
//            }
//        }
        //signUpRequest.getEmail();
        //filesJournalistService.storeFile(signUpRequest.getCv(),signUpRequest.getPortefolio());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        //signUpRequest.setRole("journalist");
        strRoles.forEach(role -> {
            switch (role) {
//                case "admin":
//                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
//                    roles.add(adminRole);
//
//                    break;
//                case "journalist":
//                    Role jRole = roleRepository.findByName(RoleName.ROLE_Journalist)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Journalist Role not found."));
//                    roles.add(jRole);
//
//                    break;
//                case "moderateur":
//                    Role mRole = roleRepository.findByName(RoleName.ROLE_Moderateur)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Moderator Role not find."));
//                    roles.add(mRole);
//
//                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_Journalist)
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

    @RequestMapping("send-mail")
    public String send() {

<<<<<<< HEAD
        /*
         * Creating a User with the help of User class that we have declared and setting
         * Email address of the sender.
         */
        userss.setEmail("houssem.ouali@gmail.com");  //Receiver's email address

        System.out.println("emaiiiiiiil = "+ userss.getEmail());/*
         * Here we will call sendEmail() for Sending mail to the sender.
         */
        try {
            notificationService.sendEmail(userss);
            System.out.println(userss.getEmail());
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
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
//    @PreAuthorize("hasRole('ROLE_Journalist')")
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


    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public Iterable<Journaliste> getAllData() {
        Iterable<Journaliste> arts = journalistRepository.findAll();

        /*for (Article art : arts){
            if (art.getAuthor() != null){
                long id = art.getAuthor().getIdUser();
                long count = 0;
                for (Article a : arts){
                    if (a.getAuthor().getIdUser() == id)
                        count++;
                }
                art.getAuthor().setPostsNumber(count);
            }
        }*/

        return arts;
    }
    //Modify the status of a journalist
    @PutMapping(value="/changeStatus/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Journaliste>ValidateJournalistStatus(@PathVariable(value="id")Long id)
    {
        return new ResponseEntity<>(this.updateJournalistStatus(id),HttpStatus.ACCEPTED);
    }

        public Journaliste updateJournalistStatus(long id) {

        if (journalistRepository.findById(id).isPresent()){
            Journaliste existingJournalist = journalistRepository.findById(id).get();
            existingJournalist.setStatus("valid");


            Journaliste updatedJournalist = journalistRepository.save(existingJournalist);

            return new Journaliste(updatedJournalist.getNom(),
                    updatedJournalist.getStatus()
            );
        }else{
            return null;
        }
    }

}
=======
}
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
