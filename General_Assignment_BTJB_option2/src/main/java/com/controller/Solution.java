package main.java.com.controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import main.java.com.entities.Candidate;
import main.java.com.entities.Degree;
import main.java.com.entities.Experience;
import main.java.com.entities.Fresher;
import main.java.com.entities.Intern;
import main.java.com.service.CandidateService;
import main.java.com.service.DegreesService;
import main.java.com.utils.DisplayViewForUser;
import main.java.com.utils.InputDataImportByUser;

/**
 * 
 * @author TuanLN6
 */
public class Solution {
	static DisplayViewForUser displayViewForUser = new DisplayViewForUser();
	static CandidateService candidateService = new CandidateService();
	static DegreesService degreesService = new DegreesService();

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Solution.class);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			displayViewForUser.displayMenuFromDataList(
					"Nhập thêm người mới,Sửa đổi người mới,Danh sách,Xem theo String,Xem theo StringBuilder,Thoát");
			switch (scanner.nextLine()) {
			case "1":
				logger.info("user log");
				insertCandidate(scanner);
				break;
			case "2":
				updateCandidate(scanner);
				break;
			case "3":
				displayList(scanner);
				break;
			case "4":
				displayListByString();
				break;
			case "5":
				displayListByStringBuffer();
				break;
			case "6":
				System.out.println("Tạm biệt");
				return;
			default:
				System.out.println("Nhập lại đi!");
				break;
			}

		}
	}

	/**
	 * 
	 * 
	 * @date Feb 10, 2023
	 * @description
	 */
	private static void displayListByStringBuffer() {
		// TODO Auto-generated method stub
		System.out.println(candidateService.findAllByStringBuilder());
	}

	private static void displayListByString() {
		// TODO Auto-generated method stub
		System.out.println(candidateService.findAllByString());
	}

	private static void displayList(Scanner scanner) {
		displayViewForUser.displayMenuFromDataList("Sort theo tên,Sort theo ID,Quit");
		String choice = DisplayViewForUser.coverterStringChoiceFromNumber(scanner.nextLine(),
				" order by fullname, order by candidateID,quit");
		if (choice.equalsIgnoreCase("quit"))
			return;
		candidateService.findAll(choice).forEach(Candidate::showInfo);
	}

	public static void insertCandidate(Scanner scanner) {
		while (true) {
			displayViewForUser.displayMenuFromDataList(
					"Nhập nhân viên lâu năm,Nhập nhân viên học việc,Nhập nhân viên thực tập,Ra menu chính");
			switch (scanner.nextLine()) {
			case "1":
				inputDataCandidate(new Experience(), scanner);
				break;
			case "2":
				inputDataCandidate(new Fresher(), scanner);
				break;
			case "3":
				inputDataCandidate(new Intern(), scanner);
				break;
			default:
				return;
			}

		}
	}

	private static void updateCandidate(Scanner scanner) {
		while (true) {
			displayViewForUser.displayMenuFromDataList("Tìm nhân viên,Ra menu chính");
			if ("1".equals(scanner.nextLine())) {
				searchCandidate(scanner);
				continue;
			}
			return;
		}
	}

	private static void searchCandidate(Scanner scanner) {
		System.out.println("ID search: ");
		Candidate candidate = candidateService.findByScannerLine(scanner.nextLine());

		if (candidate == null) {
			return;
		}

		Map<String, String> result = InputDataImportByUser.getDataChangeFromUser(scanner, displayViewForUser, candidate);

		if (result.isEmpty()) {
			System.out.println("Không thay đổi gì cả!!!");
			return;
		}

		candidateService.updateCandidate(candidate, result);
	}

	private static void inputDataCandidate(Candidate candidate, Scanner scanner) {
		// TODO Auto-generated method stub
		List<String> list = InputDataImportByUser.inputDataByUserDoScanner(scanner, candidate.getProString());
		candidateService.saveCandidateAfterInputData(candidate, list);
		Set<Degree> degrees = InputDataImportByUser.inputDegeesByUser(displayViewForUser, scanner,
				Degree.getProperties);
		degreesService.saveDegrees(degrees, list.get(0));
		System.out.println("Đã nhập được " + Candidate.canidate);
	}
}
