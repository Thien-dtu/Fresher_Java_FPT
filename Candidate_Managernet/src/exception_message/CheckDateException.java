package exception_message;

public class CheckDateException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Hàm log ra lỗi check Ngày Tốt Nghiệp
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	public CheckDateException(String message) {
		super(message);
		System.out.println(message);
	}

}
