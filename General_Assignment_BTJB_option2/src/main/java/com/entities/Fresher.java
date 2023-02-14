package main.java.com.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import main.java.com.utils.DisplayViewForUser;

public class Fresher extends Candidate {
	Date graduation_date;
	String graduation_rank;
	String education;
//	public static String getProperties = "ID,Full Name,Birthday,Phone,Email,Graduation Date,graduation Rank,Education";

	public Fresher() {
	}

	public Fresher(String candidateID, String fullName, Date birthDate, String phone, String email,
			String candidateType, List<Degree> degrees, Date graduation_date, String graduation_rank,
			String education) {
		super(candidateID, fullName, birthDate, phone, email, candidateType, degrees);
		this.graduation_date = graduation_date;
		this.graduation_rank = graduation_rank;
		this.education = education;
	}

	public Fresher(List<String> inputDataByUser) {
		// TODO Auto-generated constructor stub
		super(inputDataByUser.get(0), inputDataByUser.get(1), Date.valueOf(inputDataByUser.get(2)),
				inputDataByUser.get(3), inputDataByUser.get(4), inputDataByUser.get(5), null);
		this.graduation_date = Date.valueOf(inputDataByUser.get(6));
		this.graduation_rank = inputDataByUser.get(7);
		this.education = inputDataByUser.get(8);
	}

	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		System.out.println(toString());
	}

	public Date getGraduation_date() {
		return graduation_date;
	}

	public void setGraduation_date(Date graduation_date) {
		this.graduation_date = graduation_date;
	}

	public String getGraduation_rank() {
		return graduation_rank;
	}

	public void setGraduation_rank(String graduation_rank) {
		this.graduation_rank = graduation_rank;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format(
				"Fresher Candidate[id: %s, fullname: %s, birthday: %s, phone: %s, email: %s, graduation_date: %s, graduation_rank: %s, education: %s] with degrees %s",
				this.getCandidateID(), this.getFullName(),
				new SimpleDateFormat("dd/MM/yyyy").format(this.getBirthDate()), this.getPhone(), this.getEmail(),
				new SimpleDateFormat("dd/MM/yyyy").format(this.getGraduation_date()), this.getGraduation_rank(),
				this.getEducation(), DisplayViewForUser.displayListDegreeOfCandidate(this.getDegrees()));
	}

	@Override
	public String saveData() {
		// TODO Auto-generated method stub
		return String.format("%s,%s,%s,%s,%s,empty,empty,%s,%s,%s,empty,empty,empty,2", getCandidateID(), getFullName(),
				getBirthDate(), getPhone(), getEmail(), getGraduation_date(), getGraduation_rank(), getEducation());
	}

	@Override
	public String getProString() {
		// TODO Auto-generated method stub
		return "candidateID,FullName,Birthday,Phone,Email,Graduation_day,graduation_Rank,Education";
	}
}
