package k.test.dao

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration


import k.KChalApplication
import k.dao.GameDAO
import spock.lang.Specification

@SpringBootTest
@EnableConfigurationProperties
/*
 @ContextConfiguration(classes=[KChalApplication])
 @WebAppConfiguration
 @ActiveProfiles("default")
 */
class GameDAOTest extends Specification {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GameDAOTest.class)

	@Autowired
	GameDAO gameDAO

	def "find all games"() {
		when:
		log.debug("right before "+gameDAO)
		def games=gameDAO.findAll()
		then:
		log.debug("result list "+games)
		games
	}

	def "find any game"(){
		when:
		log.debug("antes "+gameDAO)
		def game=gameDAO.findAny()
		then:
		log.debug("despues")
		game
	}
}
