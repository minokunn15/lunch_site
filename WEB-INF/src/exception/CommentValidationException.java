package exception;

/**
 * @author 01014554
 * コメント投稿のときのバリデーションチェック
 */
public class CommentValidationException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CommentValidationException() {
	}

	public CommentValidationException(String message) {
		super(message);
	}
}
