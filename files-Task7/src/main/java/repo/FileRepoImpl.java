package repo;

import model.DBFile;
import utils.DBUtils;
import utils.Requests;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class FileRepoImpl implements FileRepo {
    private final DBUtils dbUtils = new DBUtils();

    @Override
    public Optional<DBFile> saveFile(File file) {
        try (CallableStatement createStatement = dbUtils.getStatement(
                Requests.CREATE_NEW_FILE.toString());
             CallableStatement lastFileStatement = dbUtils.getStatement(
                     Requests.GET_LAST_FILE.toString())) {
            createStatement.setString(1, file.getName());
            createStatement.setString(2, file.getPath());
            createStatement.setDate(3, Date.valueOf(LocalDate.now()));
            createStatement.execute();

            ResultSet createdFile = lastFileStatement.executeQuery();
            createdFile.next();

            if (createdFile.getString("name").equals(file.getName())) {
                return Optional.of(new DBFile(
                        createdFile.getInt("id"),
                        createdFile.getString("name"),
                        createdFile.getString("path"),
                        createdFile.getDate("exp")));
            }
        } catch (SQLException e) {
           throw new UnsupportedOperationException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DBFile> getFile(Integer id) {
        try (CallableStatement fileById = dbUtils.getStatement(
                Requests.GET_FILE_BY_ID.toString())) {
            fileById.setInt(1, id);
            ResultSet resultSet = fileById.executeQuery();

            if(resultSet.next()){
                DBFile result = new DBFile();
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setPath(resultSet.getString("path"));
                result.setExp(resultSet.getDate("exp"));

                return Optional.of(result);
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }

        return Optional.empty();
    }
}
