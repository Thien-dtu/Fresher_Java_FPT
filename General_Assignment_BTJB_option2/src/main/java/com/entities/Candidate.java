package main.java.com.entities;

import java.sql.Date;
import java.util.List;

public abstract class Candidate {
	public static int canidate = 0;

	String candidateID;

	String fullName;

	Date birthDate;

	String phone;

	String email;

	String candidateType;

	List<Degree> degrees;

	public Candidate() {

	}

	/**
	 * @author TuanLN6
	 * @since 2022/02/09
	 * @paramcandidateID
	 * @paramfullName
	 * @parambirthDate
	 * @paramphone
	 * @paramemail
	 * @paramcandidateType
	 * @paramdegrees
	 */
	public Candidate(String candidateID, String fullName, Date birthDate, String phone, String email,
			String candidateType, List<Degree> degrees) {
		this.candidateID = candidateID;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.phone = phone;
		this.email = email;
		this.candidateType = candidateType;
		this.degrees = degrees;
	}

	public String getCandidateID() {
		return this.candidateID;
	}

	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}

	public String getCandidateType() {
		return candidateType;
	}

	public void setCandidateType(String candidateType) {
		this.candidateType = candidateType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Candidate) {
			Candidate another = (Candidate) obj;
			return this.getFullName().equals(another.getFullName());
		}
		return false;
	}

	public abstract void showInfo();

	public abstract String saveData();

	public abstract String getProString();
}