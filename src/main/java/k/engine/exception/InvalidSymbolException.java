package k.engine.exception;

public class InvalidSymbolException extends GameEngineException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725045021836214958L;

	public InvalidSymbolException(Character c) {
		super(c.toString() + " is not a valid character");
	}

	public InvalidSymbolException(Character c, String msg) {
		super(c.toString() + " is not a valid character" + ":" + msg);
	}

}
