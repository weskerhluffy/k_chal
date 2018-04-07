package k;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import k.engine.GameEngine;

@SpringBootApplication(scanBasePackageClasses = { GameEngine.class })
public class KChalApplication {

	public static void main(String[] args) {
		SpringApplication.run(KChalApplication.class, args);
	}
}
