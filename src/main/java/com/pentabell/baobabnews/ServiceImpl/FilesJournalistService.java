package com.pentabell.baobabnews.ServiceImpl;

import com.pentabell.baobabnews.Repositories.JournalistRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//implements JournalistRepository
@Component
public abstract class FilesJournalistService  {

    private static final String FILE_DIRECTORY = "/var/files";

    public void storeFile(MultipartFile cvfile, MultipartFile portefoliofile) throws IOException {
        Path filePath = Paths.get(FILE_DIRECTORY + "/" + cvfile.getOriginalFilename());
        Path file2path = Paths.get(FILE_DIRECTORY + "/" + cvfile.getOriginalFilename());

        Files.copy(cvfile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(portefoliofile.getInputStream(), file2path, StandardCopyOption.REPLACE_EXISTING);

    }
}
