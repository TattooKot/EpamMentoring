import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "files-Task7")
public class Task7 {

	public static void main(String[] args) {
		SpringApplication.run(Task7.class, args);
	}

}
