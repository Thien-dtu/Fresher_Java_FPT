package main.java.com.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import main.java.com.utils.DisplayViewForUser;

public class Intern extends Candidate {
	String majors;

	String semester;

	String universityName;

//	public static String getProperties = "ID,Full Name,Birthday,Phone,Email,Major,Semester,University Name";

	public Intern() {
	}

	public Intern(String candidateID, String fullName, Date birthDate, String phone, String email, String type,
			List<Degree> degrees, String majors, String semester, String universityName) {
		super(candidateID, fullName, birthDate, phone, email, type, degrees);
		this.majors = majors;
		this.semester = semester;
		this.universityName = universityName;
	}

	public Intern(List<String> inputDataByUser) {
		// TODO Auto-generated constructor stub
		super(inputDataByUser.get(0), inputDataByUser.get(1), Date.valueOf(inputDataByUser.get(2)),
				inputDataByUser.get(3), inputDataByUser.get(4), inputDataByUser.get(5), null);
		this.majors = inputDataByUser.get(6);
		this.semester = inputDataByUser.get(7);
		this.universityName = inputDataByUser.get(8);
	}

	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		System.out.println(toString());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format(
				"Intern Candidate[id: %s, fullname: %s, birthday: %s, phone: %s, email: %s, majors: %s, semester: %s, University_name: %s] with degrees %s",
				this.getCandidateID(), this.getFullName(),
				new SimpleDateFormat("dd/MM/yyyy").format(this.getBirthDate()), this.getPhone(), this.getEmail(),
				this.getMajors(), this.getSemester(), this.getUniversityName(),
				DisplayViewForUser.displayListDegreeOfCandidate(this.getDegrees()));
	}

	public String getMajors() {
		return majors;
	}

	public void setMajors(String majors) {
		this.majors = majors;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String university_name) {
		this.universityName = university_name;
	}

	@Override
	public String saveData() {
		// TODO Auto-generated method stub
		return String.format("%s,%s,%s,%s,%s,empty,empty,empty,empty,empty,%s,%s,%s,3", getCandidateID(), getFullName(),
				new SimpleDateFormat("dd/MM/yyyy").format(this.getBirthDate()), getPhone(), getEmail(), getMajors(),
				getSemester(), getUniversityName());
	}

	@Override
	public String getProString() {
		// TODO Auto-generated method stub
		return "candidateID,FullName,Birthday,Phone,Email,Majors,Semester,UniversityName";
	}
}