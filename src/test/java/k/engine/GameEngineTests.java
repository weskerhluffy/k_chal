package k.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import k.engine.exception.GameEngineException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameEngineTests {

	// XXX:
	// https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	GameEngine gameEngine;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testValidMovesWithEmptyInitialState() throws GameEngineException {
		String initialState = "_________";
		log.info("the game engine is " + gameEngine);
		assertThat(gameEngine.isValidMove(initialState, 1, GameEngine.PLAYER_ONE_SYMBOL))
				.isEqualTo("_" + GameEngine.PLAYER_TWO_SYMBOL + "_______");
		thrown.expect(GameEngineException.class);
		gameEngine.isValidMove(initialState, 1, GameEngine.EMPTY_SPACE_SYMBOL);
		gameEngine.isValidMove(initialState, 9, GameEngine.PLAYER_ONE_SYMBOL);
		gameEngine.isValidMove(initialState, 90000, GameEngine.PLAYER_ONE_SYMBOL);
		gameEngine.isValidMove(initialState, 90000, new Character('F'));
	}

	@Test
	public void testValidMovesWithNonEmptyInitialState() throws GameEngineException {
		String initialState = "_OOOXXXXO";
		log.info("the game engine is " + gameEngine);
		assertThat(gameEngine.isValidMove(initialState, 0, GameEngine.PLAYER_TWO_SYMBOL))
				.isEqualTo(GameEngine.PLAYER_ONE_SYMBOL + "OOOXXXXO");
		thrown.expect(GameEngineException.class);
		gameEngine.isValidMove(initialState, 2, GameEngine.PLAYER_ONE_SYMBOL);
		gameEngine.isValidMove(initialState, 1, GameEngine.EMPTY_SPACE_SYMBOL);
		gameEngine.isValidMove(initialState, 9, GameEngine.PLAYER_ONE_SYMBOL);
		gameEngine.isValidMove(initialState, 90000, GameEngine.PLAYER_ONE_SYMBOL);
		gameEngine.isValidMove(initialState, 90000, new Character('F'));
	}
}
