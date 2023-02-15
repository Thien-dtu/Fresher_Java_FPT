package model;

import java.util.List;
import java.util.Scanner;

public class Experience extends Candidate {

	private int expInYear;
	private String proSkill;

	public Experience() {

	}
	
	public Experience(String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, int expInYear, String proSkill) {
		super(candidateId, fullName, birthday, phone, email, candidateType);
		this.expInYear = expInYear;
		this.proSkill = proSkill;
	}

	public Experience(String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, List<Certificated> certificateList, int expInYear, String proSkill) {
		super(candidateId, fullName, birthday, phone, email, candidateType, certificateList);
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
		return "Experience [ExpInYear = " + expInYear + ", ProSkill = " + proSkill + " , " + super.getCertificateList().toString() + " " + super.toString()
				+ "]";
	}

	@Override
	public void update(Candidate candidate, Scanner sc) {
		try {
			super.updateInfo(candidate, sc);
			System.out.println("Moi ban chinh sua thong tin chi tiet ve doi tuong Experience: ");
			System.out.println("Nhan 1 de chinh sua so nam kinh nghiem cua ung vien Experience: ");
			System.out.println("Nhan 2 de chinh sua ky nang cua ung vien Experience: ");
			System.out.println("Nhan 3 de thoat trinh chinh sua danh cho ung vien Experience. ");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				System.out.println("Moi ban nhap vao so nam kinh nghiem: ");
				((Experience) candidate).setExpInYear(Integer.parseInt(sc.nextLine()));
				break;
			case 2:
				System.out.println("Moi ban nhap vao so ky nang: ");
				((Experience) candidate).setProSkill(sc.nextLine());
				break;
			case 3:
				return;
			default:
				break;
			}
		} catch (Exception e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
		}

	}
}
