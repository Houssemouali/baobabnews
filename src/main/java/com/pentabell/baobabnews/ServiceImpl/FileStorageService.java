package com.pentabell.baobabnews.ServiceImpl;

import com.pentabell.baobabnews.Repositories.FileStorageRepository;

<<<<<<< HEAD
import java.io.File;
=======
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD
@Service
=======

>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
public class FileStorageService implements FileStorageRepository {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("filestorage");

    @Override
    public void store(MultipartFile file) {
        try {
<<<<<<< HEAD
            File uploadPath=Paths.get(this.rootLocation.toString()).toFile();
            if(!uploadPath.exists()){
                uploadPath.mkdirs();
            }
=======
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
<<<<<<< HEAD
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
=======
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error! -> message = " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());

    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    @Override
    public Stream<Path> loadFiles() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
<<<<<<< HEAD
        }
        catch (IOException e) {
=======
        } catch (IOException e) {
>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
            throw new RuntimeException("\"Failed to read stored file");
        }
    }
}
