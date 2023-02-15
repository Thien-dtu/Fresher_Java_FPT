package model;

import java.text.SimpleDateFormat;
import java.util.List;

public class Intern extends Candidate {

	private String majors;
	private String semester;
	private String universityName;

	public Intern() {

	}
	
	public Intern(String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, String majors, String semester, String universityName) {
		super(candidateId, fullName, birthday, phone, email, candidateType);
		this.majors = majors;
		this.semester = semester;
		this.universityName = universityName;
	}

	public Intern(String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, List<Certificated> certificateList, String majors, String semester, String universityName) {
		super(candidateId, fullName, birthday, phone, email, candidateType, certificateList);
		this.majors = majors;
		this.semester = semester;
		this.universityName = universityName;
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

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	/**
	 * To string.
	 * 
	 * Create date : Feb 15, 2023 10:53:04 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Intern [Majors=" + majors + ", Semester=" + semester + ", University_name=" + universityName
				+ " "+ super.getCertificateList().toString() +" " + super.toString() + "]";
	}

	/**
	 * Show info.
	 * 
	 * Create date : Feb 15, 2023 10:52:58 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 */
	@Override
	public void showInfo() {
		toString();
	}

	/**
	 * Save data.
	 * 
	 * Create date : Feb 15, 2023 10:53:09 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the string
	 */
	@Override
	public String saveData() {
		return String.format("%s,%s,%s,%s,%s,empty,empty,empty,empty,empty,%s,%s,%s,2", getCandidateId(), getFullName(),
				new SimpleDateFormat("dd/MM/yyyy").format(this.getBirthday()), getPhone(), getEmail(), getMajors(),
				getSemester(), getUniversityName());
	}

	/**
	 * Gets the pro string.
	 * 
	 * Create date : Feb 15, 2023 10:53:12 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the pro string
	 */
	@Override
	public String getProString() {
		return "candidateId,fullName,birthday,phone,email,majors,semester,universityName";
	}

	
}
