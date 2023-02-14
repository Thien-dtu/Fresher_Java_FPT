package main.java.com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import main.java.com.entities.Candidate;
import main.java.com.entities.Degree;
import main.java.com.error.BirthdaySmall1900Exception;
import main.java.com.error.EmailNotCorrectTypeException;

public class InputDataImportByUser {
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

	private static String inputDayByScanner(Scanner scanner, String string) {
		boolean flag = true;
		while (true) {
			System.out.printf("\nMời nhập %s %s: ", string, flag ? "" : "lần nữa");
			flag = false;
			try {
				String resultString = scanner.nextLine();
				LocalDate localDate = LocalDate.parse(resultString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				df.setLenient(false);
				df.parse(resultString);
				if (string.equalsIgnoreCase("birthday")) {
					CustomerValid.checkBirthdayBefore1900(localDate);
					return (localDate).toString();
				}
				return (localDate).toString();
			} catch (BirthdaySmall1900Exception e) {
				System.err.println(e.getMessage());
			} catch (ParseException e) {
				System.err.println("ngày định dang sai kiểu năm tháng ngày!!! Ví dụ 02/02/1990 (ngày/tháng/năm)");
			}
		}
	}

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
					throw new EmailNotCorrectTypeException(errorline);
				}

				if (!resultString.matches(regex) && !regex.isEmpty()) {
					System.err.printf("%s sai format chuẩn!!! ví dụ: %s", string, errorline);
					continue;
				}
				return resultString;
			} catch (EmailNotCorrectTypeException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			}

		}
	}

	public static Set<Degree> inputDegeesByUser(DisplayViewForUser displayViewForUser, Scanner scanner,
			String getProperties) {
		Set<Degree> list = new HashSet<>();
		while (true) {
			displayViewForUser.displayMenuFromDataList("Thêm bằng cấp kèm theo,Đã đủ và lưu");
			switch (scanner.nextLine()) {
			case "1":
				if (list.add(new Degree(inputDataByUserDoScanner(scanner, getProperties)))) {
					System.out.println("Thêm mới bằng cấp thành công!!!");
					break;
				}
				System.err.println("Id bằng cấp trùng lập");
				break;
			default:
				return list;
			}
		}
	}

	public static Map<String, String> getDataChangeFromUser(Scanner scanner, DisplayViewForUser displayViewForUser,
			Candidate candidateEdit) {
		Map<String, String> resultMap = new HashMap<>();
		System.out.println(candidateEdit.toString());
		System.out.println("Bạn muốn sửa trường nào");
		String menuString = candidateEdit.getProString().concat(",Quit");
		while (true) {
			displayViewForUser.displayMenuFromDataList(menuString);
			String choice = scanner.nextLine();
			if (CustomerValid.validChoice(choice, menuString)) {
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
