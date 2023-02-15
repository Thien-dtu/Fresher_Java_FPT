package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import exception.BirthdayException;
import exception.MailException;
import model.Candidate;
import service.ValidateData;


public class InputDataImportByUser {
	
	/**
	 * Input data by user do scanner.
	 * 
	 * Create date : Feb 15, 2023 10:58:08 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param scanner the scanner
	 * @param properties the properties
	 * @return the list
	 */
	public static List<String> inputDataByUserDoScanner(Scanner scanner, String properties) {
		List<String> list = new ArrayList<>();
		String[] strings = properties.split(",");
		for (String string : strings) {
			if (string.toLowerCase().contains("day")) {
				list.add(inputDayByScanner(scanner, string));
				continue;
			}
			if (string.toLowerCase().contains("name")) {
				list.add(inputDataByScanner(scanner, string,
						"^[A-Za-z úùụũủịỉìỉĩâăôđêọòóõỏáàảãạèéẹẽẻưửữựừứốồổộỗếềểễệấầẫẩậặắẳẵằạáàảã.?!@#$%^&*]+$",
						"tên không nên có số!!!"));
				continue;
			}
			if (string.toLowerCase().contains("phone")) {
				list.add(inputDataByScanner(scanner, string, "^0[0-9]{9}$", "số điện thoại nên là 0123456789"));
				continue;
			}
			if (string.toLowerCase().contains("email")) {
				list.add(inputDataByScanner(scanner, string, "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
						"email nên kiểu cumeo154@gmail.com"));
				continue;
			}
			if (string.toLowerCase().contains("expinyear")) {
				list.add(inputDataByScanner(scanner, string, "^[0-9]+$", "ở trường này nên nhập số"));
				continue;
			}
			list.add(inputDataByScanner(scanner, string, "", ""));
		}
		return list;
	}
	
	/**
	 * Input data by scanner.
	 * 
	 * Create date : Feb 15, 2023 10:58:13 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param scanner the scanner
	 * @param string the string
	 * @param regex the regex
	 * @param errorline the errorline
	 * @return the string
	 */
	private static String inputDataByScanner(Scanner scanner, String string, String regex, String errorline) {
		boolean flag = true;
		while (true) {
			try {
				System.out.printf("\nMời nhập %s %s: ", string, flag ? "" : "lần nữa");
				flag = false;
				String resultString = scanner.nextLine().toLowerCase();
				if (resultString.trim().isEmpty())
					continue;

				if (!resultString.matches(regex) && !regex.isEmpty() && string.contains("email")) {
					throw new MailException(errorline);
				}

				if (!resultString.matches(regex) && !regex.isEmpty()) {
					System.err.printf("%s sai format chuẩn!!! ví dụ: %s", string, errorline);
					continue;
				}
				return resultString;
			} catch (MailException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			}

		}
	}
	
	/**
	 * Input day by scanner.
	 * 
	 * Create date : Feb 15, 2023 10:58:18 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param scanner the scanner
	 * @param string the string
	 * @return the string
	 */
	private static String inputDayByScanner(Scanner scanner, String string) {
		ValidateData validateData = new ValidateData();
		boolean flag = true;
		while (true) {
			System.out.printf("\nMời nhập %s %s: ", string, flag ? "" : "lần nữa");
			flag = false;
			try {
				String resultString = scanner.nextLine();
				
				if (resultString.equals("")) {
					throw new BirthdayException("Khong duoc de trong ngay sinh, moi ban nhap vao ngay sinh ");
				}
				if (!validateData.validateDate(resultString)) {
					throw new BirthdayException(
							"Ban da nhap sai dinh dang cua ngay sinh, dinh dang dung la dd/MM/yyyy: ");
				}
				if ((Integer.parseInt(resultString.substring(6, 10)) < 1900)) {
					throw new BirthdayException("Ban khong duoc nhap nam duoi 1900");
				}
				return resultString;
			} catch (BirthdayException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			}
		}
	}
	
	/**
	 * Gets the data change from user.
	 * 
	 * Create date : Feb 15, 2023 10:58:22 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param scanner the scanner
	 * @param displayViewForUser the display view for user
	 * @param candidateEdit the candidate edit
	 * @return the data change from user
	 */
	public static Map<String, String> getDataChangeFromUser(Scanner scanner, DisplayViewForUser displayViewForUser,
			Candidate candidateEdit) {
		Map<String, String> resultMap = new HashMap<>();
		System.out.println(candidateEdit.toString());
		System.out.println("Bạn muốn sửa trường nào");
		String menuString = candidateEdit.getProString().concat(",Quit");
		while (true) {
			displayViewForUser.displayMenuFromDataList(menuString);
			String choice = scanner.nextLine();
			if (ValidateData.validChoice(choice, menuString)) {
				System.out.println("Nhập sai định dạng, xin nhập lại");
				continue;
			}
			choice = DisplayViewForUser.coverterStringChoiceFromNumber(choice, menuString);
			if (choice.equalsIgnoreCase("Quit"))
				break;
			System.out.println("Bạn đã chọn " + choice);
			resultMap.put(choice, inputDataByUserDoScanner(scanner, choice).get(0));
		}
		return resultMap;
	}
}
