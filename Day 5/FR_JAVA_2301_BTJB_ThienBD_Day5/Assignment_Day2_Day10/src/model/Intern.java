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
			((Intern) candidate).setCandidateType(3);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}
}
