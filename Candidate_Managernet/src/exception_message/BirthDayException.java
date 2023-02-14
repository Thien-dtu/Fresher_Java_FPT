package exception_message;


public class BirthDayException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Hàm log ra lỗi check Ngày Sinh
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	public BirthDayException(String message) {
		super(message);
		System.err.println(message);
	}
}
