package k;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import k.config.SecurityConfig;
import k.dao.GameDAO;
import k.endpoint.GameEndpoint;
import k.engine.GameEngine;

@SpringBootApplication(scanBasePackageClasses = { GameEngine.class, GameDAO.class, GameEndpoint.class,
		SecurityConfig.class })
public class KChalApplication {

	public static void main(String[] args) {
		SpringApplication.run(KChalApplication.class, args);
	}
}
