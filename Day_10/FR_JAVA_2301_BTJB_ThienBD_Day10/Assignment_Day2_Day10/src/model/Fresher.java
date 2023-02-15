package model;

import java.util.List;

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
				+ graduationRank + super.getCertificateList().toString() + " " + super.toString() + "]";
	}


	/**
	 * Show info.
	 * 
	 * Create date : Feb 15, 2023 10:52:33 PM
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
	 * Create date : Feb 15, 2023 10:52:39 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the string
	 */
	@Override
	public String saveData() {
		return String.format("%s,%s,%s,%s,%s,empty,empty,%s,%s,%s,empty,empty,empty,1", getCandidateId(), getFullName(),
				getBirthday(), getPhone(), getEmail(), getGraduationDate(), getGraduationRank(), getEducation());
	}

	/**
	 * Gets the pro string.
	 * 
	 * Create date : Feb 15, 2023 10:52:45 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the pro string
	 */
	@Override
	public String getProString() {
		return "candidateId,fullName,birthday,phone,email,graduationDay,graduationRank,education";
	}

}
