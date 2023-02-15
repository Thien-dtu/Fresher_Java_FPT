package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Candidate {
	private static List<Candidate> candidateList = new ArrayList<>();
	
	private String candidateId;
	private String fullName;
	private String birthday;
	private String phone;
	private String email;
	private int candidateType;
	static int candidateCount = 0;
	List<Certificated> certificateList = new ArrayList<>();

	public Candidate() {
	}

	public Candidate( String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, List<Certificated> certificateList) {
		super();
		this.candidateId = candidateId;
		this.fullName = fullName;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.candidateType = candidateType;
		this.certificateList = certificateList;
	}
	
	public Candidate( String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType) {
		super();
		this.candidateId = candidateId;
		this.fullName = fullName;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.candidateType = candidateType;
	}
	
	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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

	public int getCandidateType() {
		return candidateType;
	}

	public void setCandidateType(int candidateType) {
		this.candidateType = candidateType;
	}
	
	public static int getCandidateCount() {
		return candidateCount;
	}

	public static void setCandidateCount(int candidateCount) {
		Candidate.candidateCount = candidateCount;
	}

	public List<Certificated> getCertificateList() {
		return certificateList;
	}

	public void setCertificateList(List<Certificated> certificateList) {
		this.certificateList = certificateList;
	}
	
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", fullName=" + fullName + ", birthday="
				+ birthday + ", phone=" + phone + ", email=" + email + ", candidateType=" + candidateType
				+ "]";
	}

	public Candidate isCandidate(String candidateCode) {
		for (Candidate candidate : candidateList) {
			if (candidate.getCandidateId().equals(candidateCode)) {
				return candidate;
			}
		}
		return null;
	}

	/**
	 * Show info.
	 * 
	 * Create date : Feb 15, 2023 10:52:08 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 */
	public abstract void showInfo();

	/**
	 * Save data.
	 * 
	 * Create date : Feb 15, 2023 10:52:14 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the string
	 */
	public abstract String saveData();

	/**
	 * Gets the pro string.
	 * 
	 * Create date : Feb 15, 2023 10:52:18 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the pro string
	 */
	public abstract String getProString();
}
