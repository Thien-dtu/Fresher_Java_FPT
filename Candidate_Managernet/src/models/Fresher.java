package models;

import java.util.List;

public class Fresher extends Candidate {
	private String graduationDate;
	private String graduationRank;
	private String eduacation;

	public Fresher() {
		super();
	}

	public Fresher(String fullName, String birthDay, Integer phone, String email, int candidateType,
			String graduationDate, String graduationRank, String eduacation) {
		super(fullName, birthDay, phone, email, candidateType);
		this.graduationDate = graduationDate;
		this.graduationRank = graduationRank;
		this.eduacation = eduacation;
	}

	public Fresher(Integer candidateID, String fullName, String birthDay, Integer phone, String email,
			int candidateType, List<Certificate> certificateList, String graduationDate, String graduationRank,
			String eduacation) {
		super(candidateID, fullName, birthDay, phone, email, candidateType, certificateList);
		this.graduationDate = graduationDate;
		this.graduationRank = graduationRank;
		this.eduacation = eduacation;
	}

	public String getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getGraduationRank() {
		return graduationRank;
	}

	public void setGraduationRank(String graduationRank) {
		this.graduationRank = graduationRank;
	}

	public String getEduacation() {
		return eduacation;
	}

	public void setEduacation(String eduacation) {
		this.eduacation = eduacation;
	}
	
	/**
	 * Hàm nội dung sẻ được hiển thị lên conslog của ứng viên Fresher
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	@Override
	public String showMe() {
		return String.format(
				"Mãsố:%s, Họtên:%s, Sinhnhật:%s, SĐT:%s, Email:%s,"
						+ " KiểuỨngViên:%s, BằngCấp:%s, TGTốtNghiệp:%s, XếpLoại:%s, TênTrường:%s",
				super.getCandidateID(), super.getFullName(), super.getBirthDay(), super.getPhone(), super.getEmail(),
				super.getCandidateType(), super.getCertificateList().toString(), getGraduationDate(),
				getGraduationRank(), getEduacation());
	}

	@Override
	public String toString() {
		return "Ma So Ung Vien : " + getCandidateID() + " Ho Ten Ung Vien : " + getFullName() + " Sinh Nhat : "
				+ getBirthDay() + " So Dien Thoai : " + getPhone() + " Email: " + getEmail() + " Kieu Ung Vien : "
				+ getCandidateType() + " Nam Tot Nghiep : " + graduationDate + " Xep Loai Tot Nghiep : "
				+ graduationRank + " Truong Tot Nghiep : " + eduacation;
	}
}
