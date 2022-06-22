package service;

import model.DBFile;
import repo.FileRepo;
import repo.FileRepoImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DBFileService {
    private final FileRepo repo = new FileRepoImpl();

    public DBFile saveFile(File file) throws IOException {
        if(Files.size(Path.of(file.toURI())) > 200000) throw new UnsupportedOperationException();
        return repo.saveFile(file).orElseThrow(UnsupportedOperationException::new);
    }

    public DBFile getFile(Integer id){
        return repo.getFile(id).orElseThrow(UnsupportedOperationException::new);
    }
}
