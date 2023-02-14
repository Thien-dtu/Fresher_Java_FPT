package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Candidate {
	private Integer candidateID;
	private String fullName;
	private String birthDay;
	private Integer phone;
	private String email;
	private int candidateType;
	public static Integer candidateCount = 0;
	List<Certificate> certificateList = new ArrayList<>();

	public Candidate() {
		super();
	}

	public Candidate(String fullName, String birthDay, Integer phone, String email, int candidateType) {
		super();
		this.fullName = fullName;
		this.birthDay = birthDay;
		this.phone = phone;
		this.email = email;
		this.candidateType = candidateType;
		candidateCount++;
	}

	public Candidate(Integer candidateID, String fullName, String birthDay, Integer phone, String email,
			int candidateType, List<Certificate> certificateList) {
		super();
		this.candidateID = candidateID;
		this.fullName = fullName;
		this.birthDay = birthDay;
		this.phone = phone;
		this.email = email;
		this.candidateType = candidateType;
		this.certificateList = certificateList;
	}

	public Integer getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(Integer candidateID) {
		this.candidateID = candidateID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCandidateType() {
		return candidateType;
	}

	public void setCandidateType(int candidateType) {
		this.candidateType = candidateType;
	}

	public static Integer getCandidateCount() {
		return candidateCount;
	}

	public static void setCandidateCount(int candidateCount) {
		Candidate.candidateCount = candidateCount;
	}

	public List<Certificate> getCertificateList() {
		return certificateList;
	}

	public void setCertificateList(List<Certificate> certificateList) {
		this.certificateList = certificateList;
	}

	/**
	 * Abstract method
	 * 
	 * @author DucNH59
	 * @date 2022-02-07
	 * @return
	 */
	public abstract String showMe();

	@Override
	public String toString() {
		return "Ma So Ung Vien : " + candidateID + " Ho Ten Ung Vien : " + fullName + " Sinh Nhat : " + birthDay
				+ " So Dien Thoai : " + phone + " Email: " + email + " Kieu Ung Vien : " + candidateType
				+ " So Luong Ung Vien : " + candidateCount;
	}

}
