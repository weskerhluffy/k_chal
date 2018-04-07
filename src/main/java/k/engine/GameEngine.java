package k.engine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

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
	private static final String[] WIN_REGEXS_ARR = { ROW_1_WIN_REGEX, ROW_2_WIN_REGEX, ROW_3_WIN_REGEX,
			COLUMN_1_WIN_REGEX, COLUMN_2_WIN_REGEX, COLUMN_3_WIN_REGEX, DIAG_1_WIN_REGEX, DIAG_2_WIN_REGEX };
	private static final List<String> WIN_REGEXS = Arrays.asList(WIN_REGEXS_ARR);

	private Boolean isValidState(String state) {
		Boolean isValid = false;

		isValid = state != null && state.length() == STATE_LEN && state.matches(VALID_SYMBOLS_REGEX);
		// TODO: Make sure even the initial state is valid in terms of symbol
		// ocurrences/positions.
		return isValid;
	}

	private Boolean isValidSymbol(Character symbol) {
		return isValidPlayerSymbol(symbol) || symbol.toString().matches(EMPTY_SPACE_SYMBOL.toString());
	}

	private Boolean isValidPlayerSymbol(Character symbol) {
		return symbol.toString().matches("[" + PLAYER_ONE_SYMBOL + PLAYER_TWO_SYMBOL + "]");
	}

	public Boolean isValidMove(String initialState, String newState, Character playerSymbol) {
		Boolean isValid = false;
		/**
		 * Ensure that both states are of the expected size and they don't contain
		 * garbage.
		 */
		if (isValidPlayerSymbol(playerSymbol) && isValidState(initialState) && isValidState(newState)
				&& !initialState.equals(newState)) {
			/**
			 * Get indexes of differences in both strings.
			 */
			List<Integer> charDifIndexes = Arrays
					.stream(IntStream.range(0, initialState.length())
							.map(i -> initialState.charAt(i) != newState.charAt(i) ? 1 : 0).toArray())
					.boxed().collect(Collectors.toList());
			/**
			 * The symbol being added is put in an empty position and is the symbol
			 * corresponding to the player.
			 */
			if (charDifIndexes.size() == 1 && initialState.charAt(charDifIndexes.get(0)) == EMPTY_SPACE_SYMBOL
					&& newState.charAt(charDifIndexes.get(0)) == playerSymbol) {
				isValid = true;
			}
		}

		return isValid;
	}

	/**
	 * 
	 * @param initialState
	 * @param movePosition
	 * @param playerSymbol
	 * @return The new state if the move was valid, else null.
	 */
	public String isValidMove(String initialState, Integer movePosition, Character playerSymbol) {
		String newState = null;
		if (isValidPlayerSymbol(playerSymbol) && isValidState(initialState) && movePosition < STATE_LEN
				&& initialState.charAt(movePosition) == EMPTY_SPACE_SYMBOL) {
			newState = initialState.substring(0, movePosition) + playerSymbol
					+ initialState.substring(movePosition + 1);
		}
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
