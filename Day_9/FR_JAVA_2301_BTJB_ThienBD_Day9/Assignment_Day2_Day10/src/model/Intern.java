package model;

import java.util.List;
import java.util.Scanner;

public class Intern extends Candidate {

	private String majors;
	private String semester;
	private String universityName;

	public Intern() {

	}
	
	public Intern(String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, String majors, String semester, String universityName) {
		super(candidateId, fullName, birthday, phone, email, candidateType);
		this.majors = majors;
		this.semester = semester;
		this.universityName = universityName;
	}

	public Intern(String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, List<Certificated> certificateList, String majors, String semester, String universityName) {
		super(candidateId, fullName, birthday, phone, email, candidateType, certificateList);
		this.majors = majors;
		this.semester = semester;
		this.universityName = universityName;
	}

	public String getMajors() {
		return majors;
	}

	public void setMajors(String majors) {
		this.majors = majors;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	@Override
	public String toString() {
		return "Intern [Majors=" + majors + ", Semester=" + semester + ", University_name=" + universityName
				+ " " + super.toString() + "]";
	}

	@Override
	public void update(Candidate candidate, Scanner sc) {
		try {
			super.updateInfo(candidate, sc);
			System.out.println("Moi ban chinh sua thong tin chi tiet ve doi tuong Intern: ");
			System.out.println("Nhan 1 de chinh sua nganh hoc ung vien Intern: ");
			System.out.println("Nhan 2 de chinh sua ky hoc cua ung vien Intern: ");
			System.out.println("Nhan 3 de chinh sua ten truong cua ung vien Intern: ");
			System.out.println("Nhan 4 de thoat trinh chinh sua danh cho ung vien Intern. ");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				System.out.println("Moi ban nhap vao nganh hoc: ");
				((Intern) candidate).setMajors(sc.nextLine());
				break;
			case 2:
				System.out.println("Moi ban nhap vao ky hoc: ");
				((Intern) candidate).setSemester(sc.nextLine());
				break;
			case 3:
				System.out.println("Moi ban nhap vap ten truong: ");
				((Intern) candidate).setUniversityName(universityName);
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
