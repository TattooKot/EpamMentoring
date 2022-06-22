package repo;

import model.DBFile;
import utils.DBUtils;
import utils.Requests;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class FileRepoImpl implements FileRepo {
    private final DBUtils dbUtils = new DBUtils();
    private final String bucketName = "files-Task7/s3";

    @Override
    public Optional<DBFile> saveFile(File file) throws Exception {
        ResultSet lastFile = getLastFile();

        int newId = lastFile.getInt("id") + 1;
        String destination = bucketName + "/" + newId + "_" + file.getName();

        saveFileToDB(file, destination);
        ResultSet createdFile = getLastFile();

        if (createdFile.getString("name").equals(file.getName())) {
            Optional<DBFile> dbFile = createDBFileFromResultSet(createdFile);

            if (!saveFileToBucket(file, destination)) throw new UnsupportedOperationException("File cannot be save");

            return dbFile;
        }
        return Optional.empty();
    }

    @Override
    public Optional<DBFile> getFile(Integer id) {
        try (CallableStatement fileById = dbUtils.getStatement(
                Requests.GET_FILE_BY_ID.toString())) {
            fileById.setInt(1, id);
            ResultSet resultSet = fileById.executeQuery();

            if (resultSet.next()) {
                DBFile result = new DBFile();
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setPath(resultSet.getString("path"));
                result.setExp(resultSet.getDate("exp"));

                if (result.getExp().before(new java.util.Date()))
                    throw new UnsupportedOperationException("File is expired");

                return Optional.of(result);
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }

        return Optional.empty();
    }

    private void saveFileToDB(File file, String destination) throws SQLException {
        CallableStatement createStatement = dbUtils.getStatement(Requests.CREATE_NEW_FILE.toString());
        createStatement.setString(1, file.getName());
        createStatement.setString(2, destination);
        createStatement.setDate(3, Date.valueOf(LocalDate.now()));
        createStatement.execute();
    }

    private ResultSet getLastFile() throws SQLException {
        CallableStatement lastFileStatement = dbUtils.getStatement(Requests.GET_LAST_FILE.toString());
        ResultSet resultSet = lastFileStatement.executeQuery();
        if (!resultSet.next()) throw new SQLException("Last file cannot be read");
        return resultSet;

    }

    private Optional<DBFile> createDBFileFromResultSet(ResultSet resultSet) throws SQLException {
        return Optional.of(new DBFile(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("path"),
                resultSet.getDate("exp")));
    }

    private boolean saveFileToBucket(File file, String destination) throws IOException {
        Files.createFile(Path.of(destination));
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {
            byte[] bytes = in.readAllBytes();
            out.write(bytes);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
