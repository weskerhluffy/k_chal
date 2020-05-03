package k.engine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import k.engine.exception.GameEngineException;
import k.engine.exception.InvalidStateException;
import k.engine.exception.InvalidSymbolException;

// TODO: Methods should be static, there is no state saved.
@Component
public class GameEngine {
	public static final Integer COLUMN_LEN = 3;
	public static final Integer ROW_LEN = 3;
	public static final Integer STATE_LEN = (COLUMN_LEN * ROW_LEN);
	public static final Character EMPTY_SPACE_SYMBOL = '_';
	public static final Character PLAYER_ONE_SYMBOL = 'X';
	public static final Character PLAYER_TWO_SYMBOL = 'O';
	public static final String VALID_SYMBOLS_REGEX = "[" + PLAYER_ONE_SYMBOL + PLAYER_TWO_SYMBOL + EMPTY_SPACE_SYMBOL
			+ "]+";

	private static final String ROW_1_WIN_REGEX = "WWW......";
	private static final String ROW_2_WIN_REGEX = "...WWW...";
	private static final String ROW_3_WIN_REGEX = "......WWW";
	private static final String COLUMN_1_WIN_REGEX = "W..W..W..";
	private static final String COLUMN_2_WIN_REGEX = ".W..W..W.";
	private static final String COLUMN_3_WIN_REGEX = "..W..W..W";
	private static final String DIAG_1_WIN_REGEX = "W...W...W";
	private static final String DIAG_2_WIN_REGEX = "..W.W.W..";
	// XXX: https://stackoverflow.com/questions/6520382/what-is-the-shortest-way-to-initialize-list-of-strings-in-java
	private static final List<String> WIN_REGEXS = List.of(ROW_1_WIN_REGEX, ROW_2_WIN_REGEX, ROW_3_WIN_REGEX,
			COLUMN_1_WIN_REGEX, COLUMN_2_WIN_REGEX, COLUMN_3_WIN_REGEX, DIAG_1_WIN_REGEX, DIAG_2_WIN_REGEX);

	private Boolean isValidState(String state) {
		Boolean isValid = false;

		isValid = state != null && state.length() == STATE_LEN && state.matches(VALID_SYMBOLS_REGEX);
		// TODO: Make sure even the initial state is valid in terms of symbol
		// ocurrences/positions.
		return isValid;
	}

	private Boolean isValidPlayerSymbol(Character symbol) {
		return symbol.toString().matches("[" + PLAYER_ONE_SYMBOL + PLAYER_TWO_SYMBOL + "]");
	}

	private Character getOppositeSymbol(Character symbol) throws InvalidSymbolException {
		if (!isValidPlayerSymbol(symbol)) {
			throw new InvalidSymbolException(symbol);
		}
		return symbol == PLAYER_ONE_SYMBOL ? PLAYER_TWO_SYMBOL : PLAYER_ONE_SYMBOL;
	}

	/**
	 * 
	 * @param initialState
	 * @param movePosition
	 * @param lasPlayerSymbol
	 * @return The new state if the move was valid, else null.
	 * @throws GameEngineException
	 */
	public String isValidMove(String initialState, Integer movePosition, Character lasPlayerSymbol)
			throws GameEngineException {
		String newState = null;
		Character playerSymbol;
		if (!isValidPlayerSymbol(lasPlayerSymbol)) {
			throw new InvalidSymbolException(lasPlayerSymbol);
		}
		playerSymbol = getOppositeSymbol(lasPlayerSymbol);
		if (!isValidState(initialState)) {
			throw new InvalidStateException(initialState);
		}
		if (movePosition > STATE_LEN) {
			throw new GameEngineException("Index " + movePosition + " is out of range for a move");
		}
		if (initialState.charAt(movePosition) != EMPTY_SPACE_SYMBOL) {
			throw new GameEngineException("Index " + movePosition + " is not empty");
		}
		newState = initialState.substring(0, movePosition) + playerSymbol + initialState.substring(movePosition + 1);
		return newState;
	}

	private Boolean isPlayerWinner(String state, Character playerSymbol) {
		Boolean isWinner = false;

		if (isValidState(state) && isValidPlayerSymbol(playerSymbol)) {
			/**
			 * Check if the state matches any pattern of those that represent a winned game.
			 */
			for (String winRegex : WIN_REGEXS.stream().map(wr -> wr.replaceAll("W", playerSymbol.toString()))
					.collect(Collectors.toList())) {

				if (state.matches(winRegex)) {
					isWinner = true;
					break;
				}
			}
		}
		return isWinner;
	}

	/**
	 * 
	 * @param state
	 * @return The symbol of the player who won, if no one has won, null.
	 */
	public Character isWon(String state) {
		Character symbolWin = null;
		if (isPlayerWinner(state, PLAYER_ONE_SYMBOL)) {
			symbolWin = PLAYER_ONE_SYMBOL;
		} else {
			if (isPlayerWinner(state, PLAYER_TWO_SYMBOL)) {
				symbolWin = PLAYER_TWO_SYMBOL;
			}
		}
		return symbolWin;
	}
}
