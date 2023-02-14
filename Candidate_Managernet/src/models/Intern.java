package models;

import java.util.List;

public class Intern extends Candidate {
	private String majors;
	private String semester;
	private String universityName;

	public Intern() {
		super();
	}

	public Intern(String fullName, String birthDay, Integer phone, String email, int candidateType, String majors,
			String semester, String universityName) {
		super(fullName, birthDay, phone, email, candidateType);
		this.majors = majors;
		this.semester = semester;
		this.universityName = universityName;
	}

	public Intern(Integer candidateID, String fullName, String birthDay, Integer phone, String email, int candidateType,
			List<Certificate> certificateList, String majors, String semester, String universityName) {
		super(candidateID, fullName, birthDay, phone, email, candidateType, certificateList);
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
	 * Hàm nội dung sẻ được hiển thị lên conslog của ứng viên Intern
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	@Override
	public String showMe() {
		return String.format(
				"MãSố:%s, HọTên:%s, SinhNhật:%s, SĐT:%s, Email:%s, KiểuỨngViên:%s, BằngCấp:%s, ChuyênNgành:%s, HọcKỳ:%s, TênTrường:%s",
				super.getCandidateID(), super.getFullName(), super.getBirthDay(), super.getPhone(), super.getEmail(),
				super.getCandidateType(), super.getCertificateList().toString(), getMajors(), getSemester(),
				getUniversityName());
	}

	@Override
	public String toString() {
		return "Ma So Ung Vien : " + getCandidateID() + " Ho Ten Ung Vien : " + getFullName() + " Sinh Nhat : "
				+ getBirthDay() + " So Dien Thoai : " + getPhone() + " Email: " + getEmail() + " Kieu Ung Vien : "
				+ getCandidateType() + " Chuyen Nganh Hoc : " + majors + " Hoc Ky Dang Hoc : " + semester
				+ " Ten Truong Dang Hoc " + universityName;
	}

}
