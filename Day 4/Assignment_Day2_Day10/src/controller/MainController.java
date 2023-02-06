package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;
import repository.candidate.CandidateRepository;
import repository.candidate.CandidateRepositoryImpl;

public class MainController {
	static CandidateRepository repository = new CandidateRepositoryImpl();
	
	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String test = dtf.format(now);
		String nam = test.substring(0, 4);
//		System.out.println(nam);
		int year = Integer.parseInt(nam);

		String thang = test.substring(5, 7);
//		System.out.println(thang);X
		String ngay = test.substring(8, 10);
//		System.out.println(ngay);
//		System.out.println(test);
//		System.out.println(dtf.format(now));

		start();
	}
	
	public static void start() {
		Scanner sc = new Scanner(System.in);
		repository.checkConnection();
		System.out.println("Moi ban nhap chuc nang can dung: ");
		System.out.println("1. Nhap thong tin cua ung vien moi : ");
		System.out.println("2. Cap nhap thong tin cua ung vien da co trong he thong : ");
		System.out.println("3. Xoa thong tin cua ung vien da co trong he thong : ");
		System.out.println("4. Xem thong tin cua ung vien da co trong he thong : ");
		int key = 1;
		while (key != 0) {
			try {
				key = sc.nextInt();
			} catch (NumberFormatException e) {
				System.out.println("Nhap sai chuc nang, nhap lai: ");
			}
			switch (key) {
			case 1:
				nhap();
				break;
			case 2:
//				update();
				break;
			case 3:
				break;
			case 4:
				for (Candidate candidate : repository.findAll()) {
					System.out.println(candidate.toString());
				}
				break;
			default:
				break;
			}
		}
	}
	
	public static void nhap() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Moi ban nhap loai ung vien can thao tac: ");
		System.out.println("1. Experience: ");
		System.out.println("2. Fresher: ");
		System.out.println("3. Intern: ");
		int key = sc.nextInt();

		switch (key) {
		case 1:
			Candidate experience = new Experience();
			experience.input(experience);
			repository.themMoi(experience);
			break;
		case 2:
			Candidate fresher = new Fresher();
			fresher.input(fresher);
			repository.themMoi(fresher);
			break;
		case 3:
			Candidate inter = new Intern();
			inter.input(inter);
			repository.themMoi(inter);
			break;
		default:
			break;
		}
	}

}
