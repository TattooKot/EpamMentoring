package repo;

import model.DBFile;

import java.io.File;
import java.sql.SQLException;
import java.util.Optional;

public interface FileRepo {
    Optional<DBFile> saveFile(File file) throws Exception;
    Optional<DBFile> getFile(Integer id);
}
