package com.pentabell.baobabnews.Repositories;

import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD
import java.nio.file.Path;
import org.springframework.core.io.Resource;
=======

import java.nio.file.Path;

import org.springframework.core.io.Resource;

>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
import java.util.stream.Stream;

public interface FileStorageRepository {
    public void store(MultipartFile file);
<<<<<<< HEAD
    public Resource loadFile(String filename);
    public void deleteAll();
    public void init();
=======

    public Resource loadFile(String filename);

    public void deleteAll();

    public void init();

>>>>>>> 921aaff7a4eb24b3a81ecdfe1e893c00126a3327
    public Stream<Path> loadFiles();
}
