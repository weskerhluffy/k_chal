package k.engine.exception;

public class InvalidStateException extends GameEngineException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2725045021836214958L;

	public InvalidStateException(String s) {
		super("State'" + s + "' is not valid");
	}

	public InvalidStateException(String s, String msg) {
		super("State'" + s + "' is not valid" + ":" + msg);
	}

}
