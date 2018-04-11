package k;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import k.dao.GameDAO;
import k.engine.GameEngine;

@SpringBootApplication(scanBasePackageClasses = { GameEngine.class, GameDAO.class })
public class KChalApplication {

	public static void main(String[] args) {
		SpringApplication.run(KChalApplication.class, args);
	}
}
