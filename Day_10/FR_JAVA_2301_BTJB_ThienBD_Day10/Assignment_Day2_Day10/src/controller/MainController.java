package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Candidate;
import repository.candidate.CandidateRepository;
import repository.candidate.CandidateRepositoryImpl;
import service.InputCandidateService;
import utils.DisplayViewForUser;
import utils.InputDataImportByUser;

public class MainController {
	static DisplayViewForUser displayViewForUser = new DisplayViewForUser();
	static CandidateRepository repository = new CandidateRepositoryImpl();
	static InputCandidateService inputService = new InputCandidateService();
	

	public static void main(String[] args) {
		start();
	}

	/**
	 * Start.
	 * 
	 * Create date : Feb 15, 2023 10:48:24 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 */
	public static void start() {
		Scanner sc = new Scanner(System.in);
		boolean cont = true;
		do {
			displayViewForUser.displayMenuFromDataList(
					"Nhap thong tin cua ung vien moi :,"
					+ "Cap nhap thong tin cua ung vien da co trong he thong :,"
					+ "Xem thong tin cua ung vien da co trong he thong :");
			
//			+ "Xoa thong tin cua ung vien da co trong he thong :,"
			
			int key = sc.nextInt();
			
			switch (key) {
			case 1:
				create(sc);
				break;
			case 2:
				update(sc);
				break;
			case 3:
				viewCandidate(sc);
				break;
			case 4:
//				delete(sc);
				System.out.println("Tạm biệt");
				cont = false;
				break;
			default:
				System.out.println("Tạm biệt");
				cont = false;
				break;
			}
		} while (cont);
		sc.close();
	}

	/**
	 * Creates the.
	 * 
	 * Create date : Feb 15, 2023 10:48:31 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param sc the sc
	 */
	public static void create(Scanner sc) {
		displayViewForUser.displayMenuFromDataList("Experience:,Fresher:,Intern:");
		int key = sc.nextInt();

		switch (key) {
		case 1:
			repository.themMoi(inputService.inputCandidate(inputService.inputCandidate(sc), sc, 0), sc);
			break;
		case 2:
			repository.themMoi(inputService.inputCandidate(inputService.inputCandidate(sc), sc, 1), sc);
			break;
		case 3:
			repository.themMoi(inputService.inputCandidate(inputService.inputCandidate(sc), sc, 2), sc);
			break;
		default:
			break;
		}
	}

	/**
	 * Update.
	 * 
	 * Create date : Feb 15, 2023 10:48:48 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param sc the sc
	 */
	private static void update(Scanner sc) {
		System.out.print("Nhap ID cua ung vien ma ban muon update: ");
		String candidateId = sc.next();
		sc.nextLine();
		Candidate candidate = repository.findByid(candidateId);
		if (candidate == null) {
			System.out.println("CandidateId not exixts");
		}
		
		Map<String, String> result = InputDataImportByUser.getDataChangeFromUser(sc, displayViewForUser, candidate);
		
		if (result.isEmpty()) {
			System.out.println("Nothing change!!!");
			return;
		}
		
		repository.updateCandidate(candidate, result);

	}

	/**
	 * Delete.
	 * 
	 * Create date : Feb 15, 2023 10:48:57 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param sc the sc
	 */
	public static void delete(Scanner sc) {
		displayViewForUser.displayMenuFromDataList("Xoa thong tin cua ung vien da co trong he thong.,"
				+ "Xoa thong tin cua ung vien da co ceftificated trong he thong.");
		int key = sc.nextInt();

		switch (key) {
		case 1:
			System.out.println("Moi ban nhap id ung vien can xoa: ");
			String candidateID = sc.next();
			sc.nextLine();
			repository.deleteWithId(candidateID);
			break;
		case 2:
//			repository.deleteDupliCefId();
			repository.deleCefId();
			viewShort();
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * View candidate.
	 * 
	 * Create date : Feb 15, 2023 10:49:04 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param sc the sc
	 */
	private static void viewCandidate(Scanner sc) {
		displayViewForUser.displayMenuFromDataList("Xem tat ca thong tin cua candidate trong database.,"
				+ "Xem thong tin fullName cua tat ca candidate trong database.,"
				+ "Xem thong tin theo loai");
		int key = sc.nextInt();

		switch (key) {
		case 1:
			viewShort();
			break;
		case 2:
			System.out.println(repository.getFullName().toString());
			break;
		case 3:
			try {
				sortCandidate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * Sort candidate.
	 * 
	 * Create date : Feb 15, 2023 10:49:10 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @throws SQLException the SQL exception
	 */
	public static void sortCandidate() throws SQLException {
		List<Candidate> candidates = repository.sortCandidate();
		for (Candidate candidate : candidates) {
			System.out.println(candidate.toString());
		}
	}
	
	/**
	 * View short.
	 * 
	 * Create date : Feb 15, 2023 10:49:14 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 */
	public static void viewShort() {
		for (Candidate candidate : repository.findAll()) {
			System.out.println(candidate.toString());
		}
	}
}
