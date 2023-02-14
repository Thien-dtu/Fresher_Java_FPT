package exception_message;

public class EmailException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Hàm log ra lỗi check Email
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	public EmailException(String message) {
		super(message);
		System.out.println(message);
	}

}
