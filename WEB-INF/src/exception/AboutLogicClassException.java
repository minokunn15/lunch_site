package exception;

public class AboutLogicClassException extends Exception {
	/**
	 *ロジッククラスの名前間違い
	 */
	private static final long serialVersionUID = 1L;

	public AboutLogicClassException(String message) {
		super(message);
	}
}
