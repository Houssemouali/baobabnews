package com.pentabell.baobabnews.Repositories;

import com.pentabell.baobabnews.model.Journaliste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public interface JournalistRepository extends JpaRepository<Journaliste,Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    //void storeFile(MultipartFile cvfile, MultipartFile portefoliofile) throws IOException;
}
