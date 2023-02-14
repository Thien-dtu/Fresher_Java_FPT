package until;

import java.util.Scanner;

import exception_message.BirthDayException;
import exception_message.EmailException;
import regex.Regex;

public class CheckRegex {
	static Scanner scanner = new Scanner(System.in);

	/**
	 * Kiểm tra email
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @return
	 */
	public static String checkEmail() {
		String email;
		do {
			try {
				System.out.print("Nhập Email : (Ví dụ : abc@gmail.com) ");
				email = scanner.nextLine();
				if (Regex.formatEmail(email)) {
					break;
				} else {
					throw new EmailException("Sai định dạng.Ví dụ --abc@gmail.com--");
				}
			} catch (EmailException e) {
				System.err.println("Nhập lại");
			}
		} while (true);
		return email;
	}

	/**
	 * Kiểm tra ngày sinh
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @return
	 */
	public static String checkBirthday() {
		String dayOfBirth;
		do {
			try {
				System.out.print("Nhập ngày sinh : (Ví dụ : yyyyMMdd) ");
				dayOfBirth = scanner.nextLine();
				if (Regex.dateFormat(dayOfBirth)) {
					break;
				} else {
					System.out.println("else clause");
					throw new BirthDayException("Sai định dạng.Ví dụ --19970913--");
				}
			} catch (BirthDayException e) {
				System.err.println("Nhập lại");
			}
		} while (true);
		return dayOfBirth;
	}

	/**
	 * Kiểm tra ngày tháng năm 8 số 
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @return
	 */
	public static String checkDate() {
		String dayOfBirth;
		do {
			try {
				System.out.print("Nhập ngày tốt nghiệp : (Ví dụ : yyyyMMdd) ");
				dayOfBirth = scanner.nextLine();
				if (Regex.dateFormat(dayOfBirth)) {
					break;
				} else {
					throw new BirthDayException("Sai định dạng.Ví dụ --19970913--");
				}
			} catch (BirthDayException e) {
				System.err.println("Nhập lại");
			}
		} while (true);
		return dayOfBirth;
	}

}
