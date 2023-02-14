package model;

import java.util.Scanner;

public class Experience extends Candidate {

	private int expInYear;
	private String proSkill;
	
	public Experience() {
		
	}
	
	public Experience(String candidateID, String fullName, String birthday, String phone, String email,
			int candidateType, String certificatedId,int expInYear, String proSkill) {
		super(candidateID, fullName, birthday, phone, email, candidateType, certificatedId);
		this.expInYear = expInYear;
		this.proSkill = proSkill;
	}
	
	public int getExpInYear() {
		return expInYear;
	}

	public void setExpInYear(int expInYear) {
		this.expInYear = expInYear;
	}

	public String getProSkill() {
		return proSkill;
	}

	public void setProSkill(String proSkill) {
		this.proSkill = proSkill;
	}



	@Override
	public String toString() {
		return "Experience [ExpInYear=" + expInYear + ", ProSkill=" + proSkill + ", toString()=" + super.toString()
				+ "]";
	}
	
	@Override
	public void input(Candidate candidate) {
	try {
		Scanner sc = new Scanner(System.in);
		super.inputInfo(candidate);
		
		System.out.println("Moi ban nhap vao so nam kinh nghiem: ");
		((Experience) candidate).setExpInYear(Integer.parseInt(sc.nextLine()));
		System.out.println("Moi ban nhap vao ky nang cua minh: ");
		((Experience) candidate).setProSkill(sc.nextLine());
		((Experience) candidate).setCandidateType(1);		
		
		} catch (Exception e) {
		e.printStackTrace();
	}
	}

}
