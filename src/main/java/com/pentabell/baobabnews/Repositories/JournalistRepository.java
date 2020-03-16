package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Journaliste;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Repository
public interface JournalistRepository extends JpaRepository<Journaliste, Long> {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

<<<<<<< HEAD

    @Query(value="select id_user from Journaliste as j " +
=======
    @Query(value = "select id_user from Journaliste as j " +
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
            "INNER JOIN user_roles as ur ON ur.user_id = j.id_user" +
            " where j.username =:journalist_name", nativeQuery = true)
    Long authenticatedUser(String journalist_name);
    //void storeFile(MultipartFile cvfile, MultipartFile portefoliofile) throws IOException;
<<<<<<< HEAD
//
    @Query(value="select * from Journaliste as j " +
            "INNER JOIN users as ur ON ur.id_user = j.id_user" +
            " where j.status =:journalist_status",nativeQuery = true)
    List<Journaliste> getAllByStatus(String journalist_status);
=======

    @Query(value = "select * from Journaliste as j " +
            "INNER JOIN users as ur ON ur.id_user = j.id_user" +
            " where j.status =:journalist_status", nativeQuery = true)
    List<Journaliste> getAllByStatus(String journalist_status);


>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
}
