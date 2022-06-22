package repo;

import model.DBFile;

import java.io.File;
import java.util.Optional;

public interface FileRepo {
    Optional<DBFile> saveFile(File file);
    Optional<DBFile> getFile(Integer id);
}
