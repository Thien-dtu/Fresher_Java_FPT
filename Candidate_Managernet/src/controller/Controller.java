package controller;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Candidate;
//import models.Certificate;
import service.CandidateService;
import service.CertificateService;
import service.ExperienceService;
import service.FresherService;
import service.InterService;

public class Controller {
	static Scanner scanner = new Scanner(System.in);
	static ExperienceService experienceService = new ExperienceService();
	static FresherService fresherService = new FresherService();
	static InterService interService = new InterService();
	static CertificateService certificateService = new CertificateService();
	static CandidateService candidateService = new CandidateService();

	/**
	 * Menu chính của chương trình
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @throws SQLException
	 */
	public static void displayMainMenu() throws SQLException {
		do {
			System.out.println("1.--Hiển thị tất cả thông tin của ứng viên--\n"
					+ "2.--Thêm mới ứng viên bằng câu lệnh SQL--\n" + "3.--Thêm mới ứng viên bằng CONCUR_UPDATABLE--\n"
					+ "4.--Cập nhật thông tin ứng viên bằng CONCUR_UPDATABLE--\n" + "5.--Sắp xếp danh sách ứng viên--\n"
					+ "6.--Hiển thị tất cả tên của ứng viên--\n" + "7.--Thoát chương trình--\n"
					+ "--Nhấn để chọn chức năng--");

			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				System.err.println("--Số lượng ứng viên được thêm trong lần chạy chương trình hiện tại là "
						+ Candidate.candidateCount + " người--");
				display();
				displayMainMenu();
				return;
			case "2":
				System.err.println("--Thêm mới bằng câu lệnh SQL--");
				caseAddNewSQL();
				return;
			case "3":
				System.err.println("--Thêm mới ứng viên bằng CONCUR_UPDATABLE--");
			caseAddByConcurUpdatable();
				displayMainMenu();
				return;
			case "4":
				System.err.println("--Cập nhật ứng viên bằng CONCUR_UPDATABLE--");
				CandidateService.updateByConcurUpdatable();
				displayMainMenu();
				return;
			case "5":
				System.err.println("--Sắp xếp danh sách ứng viên--");
				sortCandidate();
				displayMainMenu();
				return;
			case "6":
				System.err.println("--Họ và Tên của tất cả ứng viên--");
				displayCandidateName();
				displayMainMenu();
				return;
			case "7":
				System.err.println("---Kết thúc chương trình---   ---Good Bye---");
				System.exit(0);
			default:
				System.err.println("CHỨC NĂNG NÀY KHÔNG CÓ \n" + "MỜI BẠN CHỌN LẠI TRONG KHOẢNG 1-->7\n" + "NHẬP LẠI");
			}
		} while (true);

	}

	/**
	 * Hiển thị danh sách ứng viên
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	public static void display() {
		List<Candidate> candidates = CandidateService.display();
		for (Candidate candidate : candidates) {
			System.out.println(candidate.showMe());
		}
	}

	/**
	 * Thêm mới ứng viên bằng câu lệnh sql
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @throws SQLException
	 */

	public static void caseAddNewSQL() throws SQLException {
		do {
			System.out.println("1.Thêm mới ứng viên có kinh nghiệm(Experience) \n"
					+ "2.Thêm mới ứng viên mới tốt nghiệp(Fresher) \n" + "3.Thêm mới ứng viên thực tập(Intern) \n"
					+ "4.Trở về menu chính \n" + " Nhập để chọn tính năng: ");
			String input = scanner.nextLine();
			switch (input) {
			case "1":
				System.err.println("----Thêm mới ứng viên có kinh nghiệm(Experience)-----");
				experienceService.add();
				caseAddNewSQL();
				break;
			case "2":
				System.err.println("----Thêm mới ứng viên mới tốt nghiệp(Fresher)----");
				fresherService.add();
				caseAddNewSQL();
				break;
			case "3":
				System.err.println("----Thêm mới ứng viên thực tập(Intern)----");
				interService.add();
				caseAddNewSQL();
				break;
			case "4":
				displayMainMenu();
				return;
			default:
				System.err.println("CHỨC NĂNG KHÔNG CÓ\n" + "CHƯƠNG TRÌNH MỜI BẠN CHỌN TỪ 1--->4\n" + "NHẬP LẠI");
			}
		} while (true);
	}

	/**
	 * Thêm mới ứng viên bằng ConcurUpdatable
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @throws SQLException
	 */
	public static void caseAddByConcurUpdatable() throws SQLException {
		do {
			System.out.println("1.Thêm mới ứng viên Experience \n" + "2.Thêm mới ứng viên Fresher \n"
					+ "3.Thêm mới ứng viên Intern \n " + "4.Trở về menu chính \n" + "Mời bạn chọn");

			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				CandidateService.addExperienceByConcurUpdatable();
				break;
			case "2":
				CandidateService.addFresherByConcurUpdatable();
				break;
			case "3":
				CandidateService.addInternByConcurUpdatable();
				break;
			case "4":
				displayMainMenu();
				return;
			default:
				System.out.println("Chỉ được chọn 1 ---> 4 ");
			caseAddByConcurUpdatable();
			}
		} while (true);
	}

	/**
	 * Hiển thị tên ứng viên sử dung String vs StringBuffer
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @throws SQLException
	 */
	public static void displayCandidateName() throws SQLException {
		List<Candidate> candidates = CandidateService.display();
		String str = "";
		StringBuffer strBuf = new StringBuffer();

		for (Candidate candidate : candidates) {
			str += candidate.getFullName() + ",";
			strBuf.append(candidate.getFullName() + ",");
		}
		strBuf.deleteCharAt(strBuf.lastIndexOf(","));
		System.out.println("Hiển thị tên ứng viên sử dụng String: " + str);
		System.out.println("Hiển thị tên ứng viên sử dụng StringBuffer: " + strBuf);
	}

	/**
	 * Sắp xếp ứng viên theo loại ứng viên và năm sinh
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @throws SQLException
	 */
	public static void sortCandidate() throws SQLException {
		List<Candidate> candidates = CandidateService.sortCandidate();
		for (Candidate candidate : candidates) {
			System.out.println(candidate.showMe());
		}
	}

}
