package com.pentabell.baobabnews.Repositories;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import org.springframework.core.io.Resource;
import java.util.stream.Stream;

public interface FileStorageRepository {
    public void store(MultipartFile file);
    public Resource loadFile(String filename);
    public void deleteAll();
    public void init();
    public Stream<Path> loadFiles();
}
