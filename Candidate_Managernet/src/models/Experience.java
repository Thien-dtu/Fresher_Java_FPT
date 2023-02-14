package models;

import java.util.List;

public class Experience extends Candidate {
	private Integer expInYear;
	private String proSkill;

	public Experience() {
		super();
	}

	public Experience(String fullName, String birthDay, Integer phone, String email, int candidateType,
			Integer expInYear, String proSkill) {
		super(fullName, birthDay, phone, email, candidateType);
		this.expInYear = expInYear;
		this.proSkill = proSkill;

	}

	public Experience(Integer candidateID, String fullName, String birthDay, Integer phone, String email,
			int candidateType, List<Certificate> certificateList, Integer expInYear, String proSkill) {
		super(candidateID, fullName, birthDay, phone, email, candidateType, certificateList);
		this.expInYear = expInYear;
		this.proSkill = proSkill;

	}

	public Integer getExpInYear() {
		return expInYear;
	}

	public void setExpInYear(Integer expInYear) {
		this.expInYear = expInYear;
	}

	public String getProSkill() {
		return proSkill;
	}

	public void setProSkill(String proSkill) {
		this.proSkill = proSkill;
	}

	/**
	 * Hàm nội dung sẻ được hiển thị lên conslog của ứng viên Experience
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	@Override
	public String showMe() {
		return String.format(
				"Mãsố:%s, Họtên:%s, Sinhnhật:%s, SĐT:%s, Email:%s, KiểuỨngViên:%s, BằngCấp:%s, KinhNghiệm:%s, KỹNăng:%s",
				super.getCandidateID(), super.getFullName(), super.getBirthDay(), super.getPhone(), super.getEmail(),
				super.getCandidateType(), super.getCertificateList().toString(), getExpInYear(), getProSkill());
	}

	@Override
	public String toString() {
		return "Mãsố:" + getCandidateID() + " Họtên:" + getFullName() + " Sinhnhật:" + getBirthDay() + " SĐT:"
				+ getPhone() + " Email:" + getEmail() + " KieuUngVien:" + getCandidateType() + " KinhNghiệm:"
				+ expInYear + " KỹNăng:" + proSkill;
	}

}
