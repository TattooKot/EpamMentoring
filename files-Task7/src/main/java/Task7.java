import model.DBFile;
import service.DBFileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Task7 {
	private static final String FILE_FROM = "files-Task7\\test.txt";
	private static final DBFileService fileService = new DBFileService();

	public static void main(String[] args) {
		Path path = Path.of(FILE_FROM);
		File file = path.toFile();
		DBFile dbFile = fileService.getFile(9);
//		DBFile dbFile = fileService.saveFile(file);
		System.out.println(dbFile);
	}

}
