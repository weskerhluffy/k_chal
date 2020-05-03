package k.test.endpoint

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import k.endpoint.GameEndpoint
import spock.lang.Specification

import lombok.extern.slf4j.Slf4j;
@Slf4j
@WebMvcTest(GameEndpoint.class)
class GameEndpointTest extends Specification {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GameEndpointTest.class)
	@Autowired
	private MockMvc mvc

	// XXX: https://www.baeldung.com/spring-spock-testing
	// XXX: https://livebook.manning.com/book/spring-in-action-fifth-edition/chapter-4/164
	// XXX: https://spring.io/guides/gs/testing-web/
	// XXX: https://allegro.tech/2018/04/Spring-WebMvcTest-with-Spock.html
	def "when get is performed then the response has status 200 and content is 'pong'"() {
		expect: "Status is 200 and the response is 'Hello world!'"
		mvc.perform(get("/game/ping"))
				.andExpect(status().isOk())
				.andReturn()
				.response
				.contentAsString == "pong"
	}	
	def "wat"() {
		when:
		log.debug("rrright before ")
		then:
		log.debug("rrresult list ")
		true
	}
	
}
