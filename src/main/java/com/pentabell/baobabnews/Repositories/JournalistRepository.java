package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Journaliste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Repository
public interface JournalistRepository extends JpaRepository<Journaliste,Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


    @Query(value="select id_user from Journaliste as j " +
            "INNER JOIN user_roles as ur ON ur.user_id = j.id_user" +
            " where j.username =:journalist_name",nativeQuery = true)
    Long authenticatedUser(String journalist_name);
    //void storeFile(MultipartFile cvfile, MultipartFile portefoliofile) throws IOException;
//
    @Query(value="select * from Journaliste as j " +
            "INNER JOIN users as ur ON ur.id_user = j.id_user" +
            " where j.status =:journalist_status",nativeQuery = true)
    List<Journaliste> getAllByStatus(String journalist_status);
}
