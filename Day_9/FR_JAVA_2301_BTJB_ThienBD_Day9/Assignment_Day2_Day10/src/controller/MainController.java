package controller;

import java.util.Scanner;

import model.Candidate;
import repository.candidate.CandidateRepository;
import repository.candidate.CandidateRepositoryImpl;
import service.InputCandidateService;
import service.UpdateCandidateService;
import utils.DisplayViewForUser;

public class MainController {
	static DisplayViewForUser displayViewForUser = new DisplayViewForUser();
	static CandidateRepository repository = new CandidateRepositoryImpl();
	static InputCandidateService inputService = new InputCandidateService();
	static UpdateCandidateService updateService = new UpdateCandidateService();
	
//	private static final Logger logger = 

	public static void main(String[] args) {
		start();
	}

	public static void start() {
//		Logger logger = LogFactory
		Scanner sc = new Scanner(System.in);
//		repository.checkConnection();
//		logger.("Time Begin {}", LocalDate.now());
		boolean cont = true;
		do {
			displayViewForUser.displayMenuFromDataList(
					"Nhap thong tin cua ung vien moi :,"
					+ "Cap nhap thong tin cua ung vien da co trong he thong :,"
					+ "Xoa thong tin cua ung vien da co trong he thong :,"
					+ "Xem thong tin cua ung vien da co trong he thong :");
			int key = sc.nextInt();
			
			switch (key) {
			case 1:
				create(sc);
				break;
			case 2:
				update(sc);
				break;
			case 3:
				delete();
				break;
			case 4:
				viewCandidate();
				break;
			default:
				System.out.println("Tạm biệt");
				cont = false;
				break;
			}
		} while (cont);
		sc.close();
	}

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

	private static void update(Scanner sc) {
		System.out.print("Nhap ID cua ung vien ma ban muon update: ");
		String candidateId = sc.next();
		sc.nextLine();
		Candidate candidate = repository.findByid(candidateId);
		if (candidate == null) {
			System.out.println("CandidateId not exixts");
		} else {
			System.out.println(candidate.toString());
		}
		if (candidate != null) {
//			System.out.println(candidate.toString());
			repository.updateWithId(updateService.updateCandidate(updateService.updateCandidate(candidate, sc), sc, candidate.getCandidateType()));
		}
	}

	public static void delete() {
		Scanner sc = new Scanner(System.in);
		displayViewForUser.displayMenuFromDataList("Xoa thong tin cua ung vien da co trong he thong.,"
				+ "Xoa thong tin cua ung vien da co ceftificated trong he thong.");
		int key = sc.nextInt();

		switch (key) {
		case 1:
			Scanner scanner = new Scanner(System.in);
			System.out.println("Moi ban nhap id ung vien can xoa: ");
			String candidateID = sc.nextLine();
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
	
	private static void viewCandidate() {
		Scanner sc = new Scanner(System.in);
		displayViewForUser.displayMenuFromDataList("Xem tat ca thong tin cua candidate trong database.,"
				+ "Xem thong tin fullName cua tat ca candidate trong database.");
		int key = sc.nextInt();

		switch (key) {
		case 1:
			viewShort();
			break;
		case 2:
			System.out.println(repository.getFullName().toString());
			break;
		default:
			break;
		}
		
	}
	
	public static void viewShort() {
		for (Candidate candidate : repository.findAll()) {
			System.out.println(candidate.toString());
		}
	}
}
