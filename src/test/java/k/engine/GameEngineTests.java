package k.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameEngineTests {
	@Autowired
	GameEngine gameEngine;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testValidMovesWithEmptyInitialState() {
		String initialState = "_________";
		log.info("the game engine is " + gameEngine);
		assertThat(gameEngine.isValidMove(initialState, 1, GameEngine.PLAYER_ONE_SYMBOL))
				.isEqualTo("_" + GameEngine.PLAYER_ONE_SYMBOL + "_______");
		assertThat(gameEngine.isValidMove(initialState, 1, GameEngine.EMPTY_SPACE_SYMBOL)).isNull();
		assertThat(gameEngine.isValidMove(initialState, 9, GameEngine.PLAYER_ONE_SYMBOL)).isNull();
		assertThat(gameEngine.isValidMove(initialState, 90000, GameEngine.PLAYER_ONE_SYMBOL)).isNull();
		assertThat(gameEngine.isValidMove(initialState, 90000, new Character('F'))).isNull();
	}

	@Test
	public void testValidMovesWithNonEmptyInitialState() {
		String initialState = "_OOOXXXXO";
		log.info("the game engine is " + gameEngine);
		assertThat(gameEngine.isValidMove(initialState, 0, GameEngine.PLAYER_ONE_SYMBOL))
				.isEqualTo(GameEngine.PLAYER_ONE_SYMBOL + "OOOXXXXO");
		assertThat(gameEngine.isValidMove(initialState, 2, GameEngine.PLAYER_ONE_SYMBOL)).isNull();
		assertThat(gameEngine.isValidMove(initialState, 1, GameEngine.EMPTY_SPACE_SYMBOL)).isNull();
		assertThat(gameEngine.isValidMove(initialState, 9, GameEngine.PLAYER_ONE_SYMBOL)).isNull();
		assertThat(gameEngine.isValidMove(initialState, 90000, GameEngine.PLAYER_ONE_SYMBOL)).isNull();
		assertThat(gameEngine.isValidMove(initialState, 90000, new Character('F'))).isNull();
	}
}
