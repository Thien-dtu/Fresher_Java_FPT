package model;

public class Fresher extends Candidate {

	private String Graduation_date;
	private String Education;
	private String Graduation_rank;

	public Fresher() {

	}

	public Fresher(String candidateID, String fullName, String birthday, String phone, String email, int candidate_type,
			String certificatedID, String graduation_date, String education, String graduation_rank) {
		super(candidateID, fullName, birthday, phone, email, candidate_type, certificatedID);
		Graduation_date = graduation_date;
		Education = education;
		Graduation_rank = graduation_rank;
	}

	public String getGraduation_date() {
		return Graduation_date;
	}

	public void setGraduation_date(String graduation_date) {
		Graduation_date = graduation_date;
	}

	public String getEducation() {
		return Education;
	}

	public void setEducation(String education) {
		Education = education;
	}

	public String getGraduation_rank() {
		return Graduation_rank;
	}

	public void setGraduation_rank(String graduation_rank) {
		Graduation_rank = graduation_rank;
	}

	@Override
	public String toString() {
		return "Fresher [Graduation_date=" + Graduation_date + ", Education=" + Education + ", Graduation_rank="
				+ Graduation_rank + ", toString()=" + super.toString() + "]";
	}

}
