package model;

public class Experience extends Candidate {

	private int ExpInYear;
	private String ProSkill;
	
	public Experience() {
		
	}
	
	
	
	public Experience(String candidateID, String fullName, String birthday, String phone, String email,
			int candidate_type, String certificatedID,int expInYear, String proSkill) {
		super(candidateID, fullName, birthday, phone, email, candidate_type, certificatedID);
		this.ExpInYear = expInYear;
		this.ProSkill = proSkill;
	}



	public int getExpInYear() {
		return ExpInYear;
	}

	public void setExpInYear(int expInYear) {
		ExpInYear = expInYear;
	}

	public String getProSkill() {
		return ProSkill;
	}

	public void setProSkill(String proSkill) {
		ProSkill = proSkill;
	}

	@Override
	public String toString() {
		return "Experience [ExpInYear=" + ExpInYear + ", ProSkill=" + ProSkill + ", toString()=" + super.toString()
				+ "]";
	}

}
