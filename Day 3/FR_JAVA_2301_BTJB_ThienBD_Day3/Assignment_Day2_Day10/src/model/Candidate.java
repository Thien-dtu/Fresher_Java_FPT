package model;

public class Candidate {
	private String CandidateID;
	private String FullName;
	private String Birthday;
	private String Phone;
	private String Email;
	private int Candidate_type;
	private int Candidate_count;
	private String CertificatedID;

	public Candidate() {
	}

	public Candidate(String candidateID, String fullName, String birthday, String phone, String email,
			int candidate_type, String certificatedID) {
		this.CandidateID = candidateID;
		this.FullName = fullName;
		this.Birthday = birthday;
		this.Phone = phone;
		this.Email = email;
		this.Candidate_type = candidate_type;
		this.CertificatedID = certificatedID;
	}

	public String getCandidateID() {
		return CandidateID;
	}

	public void setCandidateID(String i) {
		CandidateID = i;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getCandidate_type() {
		return Candidate_type;
	}

	public void setCandidate_type(int candidate_type) {
		Candidate_type = candidate_type;
	}

	public int getCandidate_count() {
		return Candidate_count;
	}

	public void setCandidate_count(int candidate_count) {
		Candidate_count = candidate_count;
	}

	public String getCertificatedID() {
		return CertificatedID;
	}

	public void setCertificatedID(String certificatedID) {
		CertificatedID = certificatedID;
	}

	@Override
	public String toString() {
		return "Candidate [CandidateID=" + CandidateID + ", FullName=" + FullName + ", Birthday=" + Birthday
				+ ", Phone=" + Phone + ", Email=" + Email + ", Candidate_type=" + Candidate_type + ", CertificatedID="
				+ CertificatedID + "]";
	}
}
