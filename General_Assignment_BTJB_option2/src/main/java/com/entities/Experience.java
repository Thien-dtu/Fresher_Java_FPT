package main.java.com.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import main.java.com.utils.DisplayViewForUser;

public class Experience extends Candidate {

	int expInYear;

	String proSkill;

	public Experience() {

	}

	public Experience(String candidateID, String fullName, Date birthDate, String phone, String email,
			String candidateType, List<Degree> degrees, int expInYear, String proSkill) {
		super(candidateID, fullName, birthDate, phone, email, candidateType, degrees);
		this.expInYear = expInYear;
		this.proSkill = proSkill;
	}

	public Experience(List<String> inputDataByUser) {
		// TODO Auto-generated constructor stub
		super(inputDataByUser.get(0), inputDataByUser.get(1), Date.valueOf(inputDataByUser.get(2)),
				inputDataByUser.get(3), inputDataByUser.get(4), inputDataByUser.get(5), null);
		this.expInYear = Integer.parseInt((inputDataByUser.get(6)));
		this.proSkill = inputDataByUser.get(7);

	}

	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		System.out.println(toString());
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
		// TODO Auto-generated method stub
		return String.format(
				"Experience Candidate[id: %s, fullname: %s, birthday: %s, phone: %s, email: %s, exp in year: %d, pro skill: %s] with degrees %s",
				this.getCandidateID(), this.getFullName(),
				new SimpleDateFormat("dd/MM/yyyy").format(this.getBirthDate()), this.getPhone(), this.getEmail(),
				this.getExpInYear(), this.getProSkill(),
				DisplayViewForUser.displayListDegreeOfCandidate(this.getDegrees()));
	}

	@Override
	public String saveData() {
		// TODO Auto-generated method stub
		return String.format("%s,%s,%s,%s,%s,%d,%s,empty,empty,empty,empty,empty,empty,1", getCandidateID(),
				getFullName(), getBirthDate(), getPhone(), getEmail(), getExpInYear(), getProSkill());
	}

	@Override
	public String getProString() {
		// TODO Auto-generated method stub
		return "candidateID,FullName,Birthday,Phone,Email,expInYear,proSkill";
	}
}