package model;

import java.util.List;
import java.util.Scanner;

public class Fresher extends Candidate {

	private String graduationDate;
	private String education;
	private String graduationRank;

	public Fresher() {

	}
	
	public Fresher(String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, String graduationDate, String education, String graduationRank) {
		super(candidateId, fullName, birthday, phone, email, candidateType);
		this.graduationDate = graduationDate;
		this.education = education;
		this.graduationRank = graduationRank;
	}

	public Fresher(String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, List<Certificated> certificateList, String graduationDate, String education, String graduationRank) {
		super(candidateId, fullName, birthday, phone, email, candidateType, certificateList);
		this.graduationDate = graduationDate;
		this.education = education;
		this.graduationRank = graduationRank;
	}

	public String getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getGraduationRank() {
		return graduationRank;
	}

	public void setGraduationRank(String graduationRank) {
		this.graduationRank = graduationRank;
	}

	@Override
	public String toString() {
		return "Fresher [Graduation_date=" + graduationDate + ", Education=" + education + ", Graduation_rank="
				+ graduationRank + " " + super.toString() + "]";
	}

	@Override
	public void update(Candidate candidate, Scanner sc) {
		try {
			super.updateInfo(candidate, sc);
			System.out.println("Moi ban chinh sua thong tin chi tiet ve doi tuong Fresher: ");
			System.out.println("Nhan 1 de chinh sua ngay tot nghiep cua ung vien Fresher: ");
			System.out.println("Nhan 2 de chinh sua truong tot nghiep cua ung vien Fresher: ");
			System.out.println("Nhan 3 de chinh sua xep loai tot nghiep cua ung vien Fresher: ");
			System.out.println("Nhan 4 de thoat trinh chinh sua danh cho ung vien Fresher. ");
			int key = Integer.parseInt(sc.nextLine());
			
			switch (key) {
			case 1:
				System.out.println("Moi ban nhap vao ngay tot nghiep: ");
				((Fresher) candidate).setGraduationDate(graduationDate);
				break;
			case 2:
				System.out.println("Moi ban nhap truong tot nghiep: ");
				((Fresher) candidate).setEducation(education);
				break;
			case 3:
				System.out.println("Moi ban nhap vao xep loai tot nghiep: ");
				((Fresher) candidate).setGraduationRank(graduationRank);
				break;
			case 4:
				return;
			default:
				break;
			}
		} catch (Exception e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
		}
		
	}

}
