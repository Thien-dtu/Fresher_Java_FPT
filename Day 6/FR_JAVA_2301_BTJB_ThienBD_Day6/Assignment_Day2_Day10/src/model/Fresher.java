package model;

import java.util.Scanner;

import exception.MailException;

public class Fresher extends Candidate {

	private String graduationDate;
	private String education;
	private String graduationRank;

	public Fresher() {

	}

	public Fresher(String candidateId, String fullName, String birthday, String phone, String email, int candidateType,
			String certificatedId, String graduationDate, String education, String graduationRank) {
		super(candidateId, fullName, birthday, phone, email, candidateType, certificatedId);
		graduationDate = graduationDate;
		education = education;
		graduationRank = graduationRank;
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
				+ graduationRank + ", toString()=" + super.toString() + "]";
	}
	@Override
	public void input(Candidate candidate) {
		try {
			Scanner sc = new Scanner(System.in);
			super.inputInfo(candidate);

			System.out.println("Moi ban nhap vao ngay tot nghiep: ");
			((Fresher) candidate).setGraduationDate(sc.nextLine());
			System.out.println("Moi ban nhap vao truong tot nghiep: ");
			((Fresher) candidate).setEducation(sc.nextLine());
			System.out.println("Moi ban nhap vao xep loai tot nghiep: ");
			((Fresher) candidate).setGraduationRank(sc.nextLine());
			((Fresher) candidate).setCandidateType(1);

		} catch (MailException e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
			e.printStackTrace();
		}
	}

	@Override
	public void update(Candidate candidate) {
		try {
			super.updateInfo(candidate);
			Scanner sc = new Scanner(System.in);
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
