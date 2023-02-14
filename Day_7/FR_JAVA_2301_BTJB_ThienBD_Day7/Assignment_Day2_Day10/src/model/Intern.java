package model;

import java.util.Scanner;

import exception.MailException;

public class Intern extends Candidate {

	private String majors;
	private String semester;
	private String universityName;

	public Intern() {

	}

	public Intern(String candidateId, String fullName, String birthday, String phone, String email, int candidateType,
			String certificatedId, String majors, String semester, String universityName) {
		super(candidateId, fullName, birthday, phone, email, candidateType, certificatedId);
		majors = majors;
		semester = semester;
		universityName = universityName;
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
				+ ", toString()=" + super.toString() + "]";
	}
	
	@Override
	public void input(Candidate candidate) {
		try {
			Scanner sc = new Scanner(System.in);
			super.inputInfo(candidate);
			System.out.println("Hay nhap vao nganh hoc: ");
			((Intern) candidate).setMajors(sc.nextLine());
			System.out.println("Hay nhap vao ky hoc: ");
			((Intern) candidate).setSemester(sc.nextLine());
			System.out.println("Hay nhap vao ten truong: ");
			((Intern) candidate).setUniversityName(universityName);
			((Intern) candidate).setCandidateType(2);
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
