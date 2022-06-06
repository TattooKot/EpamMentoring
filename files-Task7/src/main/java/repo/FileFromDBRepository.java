package repo;

import model.FileFromDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FileFromDBRepository extends JpaRepository<FileFromDB, Integer> {
}