package k.endpoint;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/game", produces = "application/json")
@CrossOrigin(origins = "*")
public class GameEndpoint {

	@GetMapping("/ping")
	public String recentTacos() {
		log.info("CON ping");
		return "pong";
	}
	
	@GetMapping("/sec_ping")
	public String secPing() {
		log.info("sec test");
		return "ok";
	}
}
