package model;

import java.util.List;

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

	
	/**
	 * To string.
	 * 
	 * Create date : Feb 15, 2023 10:51:56 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return String.format(
				"Experience Candidate[id: %s, fullname: %s, birthday: %s, phone: %s, email: %s, exp in year: %d, pro skill: %s] with  %s",
				this.getCandidateId(), this.getFullName(),
				this.getBirthday(), this.getPhone(), this.getEmail(),
				this.getExpInYear(), this.getProSkill(),
				super.getCertificateList().toString());
	}

	/**
	 * Show info.
	 * 
	 * Create date : Feb 15, 2023 10:51:50 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 */
	@Override
	public void showInfo() {
		System.out.println(toString());
	}

	/**
	 * Save data.
	 * 
	 * Create date : Feb 15, 2023 10:51:43 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the string
	 */
	@Override
	public String saveData() {
		return String.format("%s,%s,%s,%s,%s,%d,%s,empty,empty,empty,empty,empty,empty,0", getCandidateId(),
				getFullName(), getBirthday(), getPhone(), getEmail(), getExpInYear(), getProSkill());
	}

	/**
	 * Gets the pro string.
	 * 
	 * Create date : Feb 15, 2023 10:51:33 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the pro string
	 */
	@Override
	public String getProString() {
		return "candidateId,FullName,Birthday,Phone,Email,expInYear,proSkill";
	}

	
}
