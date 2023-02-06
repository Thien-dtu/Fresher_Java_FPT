package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.BirthdayException;
import exception.MailException;
import exception.PhoneException;
import service.ValidateData;

public abstract class Candidate {
	private Scanner sc = new Scanner(System.in);
	private static List<Candidate> candidateList = new ArrayList<>();
	
	private String candidateId;
	private String fullName;
	private String birthday;
	private String phone;
	private String email;
	private int candidateType;
//	private int candidateCount;
	static int candidateCount;
	private String certificatedId;

	public Candidate() {
	}

	public Candidate(String candidateID, String fullName, String birthday, String phone, String email,
			int candidate_type, String certificatedID) {
		this.candidateId = candidateID;
		this.fullName = fullName;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.candidateType = candidate_type;
		this.certificatedId = certificatedID;
		candidateCount++;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCandidateType() {
		return candidateType;
	}

	public void setCandidateType(int candidateType) {
		this.candidateType = candidateType;
	}

	public static int getCandidateCount() {
		return candidateCount;
	}

	public static void setCandidateCount(int candidateCount) {
		Candidate.candidateCount = candidateCount;
	}

	public String getCertificatedId() {
		return certificatedId;
	}

	public void setCertificatedId(String certificatedId) {
		this.certificatedId = certificatedId;
	}

	@Override
	public String toString() {
		return "Candidate [CandidateID=" + candidateId + ", FullName=" + fullName + ", Birthday=" + birthday
				+ ", Phone=" + phone + ", Email=" + email + ", Candidate_type=" + candidateType + ", CertificatedID="
				+ certificatedId + "]";
	}
	
	public abstract void input(Candidate candidate);

	public Candidate isCandidate(String candidateCode) {
		for (Candidate candidate : candidateList) {
			if (candidate.getCandidateId().equals(candidateCode)) {
				return candidate;
			}
		}
		return null;
	}

	public void inputInfo(Candidate candidate) throws MailException {
		ValidateData validateData = new ValidateData();
		System.out.println("Nhap thong tin cua ung vien");

		System.out.print("Nhap ID ung vien: ");
		candidate.setCandidateId(sc.nextLine());

		System.out.print("Nhap ho va ten cua ung vien: ");
		candidate.setFullName(sc.nextLine());

		String birthday = "";
		do {
			try {
				System.out.print("Nhap ngay sinh cua ung vien: ");
				birthday = sc.nextLine();
				if (birthday.equals("")) {
					throw new BirthdayException("Khong duoc de trong ngay sinh, moi ban nhap vao ngay sinh ");
				}
				if (!validateData.validateDate(birthday)) {
					throw new BirthdayException("Ban da nhap sai dinh dang cua ngay sinh");
				}
				if ((Integer.parseInt(birthday.substring(6, 10)) < 1900)){
					throw new BirthdayException("Ban khong duoc nhap nam duoi 1900");
				}				
			} catch (BirthdayException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			}
		}while (!validateData.validateDate(birthday));

		candidate.setBirthday(birthday);

		String phone = "";
		do {
			try {
				System.out.print("Nhap so dien thoai cua ung vien: ");
				phone = sc.nextLine();
				if (phone.isEmpty()) {
					throw new PhoneException("so dien thoai cua ung vien khong duoc de trong. ");
				} else if (!validateData.validatePhone(phone)) {
					throw new PhoneException("so dien thoai cua ung vien khong dung dinh dang. ");
				}
			} catch (PhoneException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
			}
		} while (!validateData.validatePhone(phone));
		candidate.setPhone(phone);

		String email = "";
		do {
			try {
				System.out.print("Nhap email cua ung vien: ");
				email = sc.nextLine();
				if (email.isEmpty()) {
					throw new MailException("Email khong duoc de trong. ");
				} else if (!validateData.validateEmail(email)) {
					throw new MailException("Emai khong dung dinh dang. ");
				}
			} catch (MailException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
			}
		} while (!validateData.validateEmail(email));

		candidate.setEmail(email);
		
		System.out.print("Nhap ID chung chi cua ban: ");
		candidate.setCertificatedId(sc.nextLine());
		
		candidateList.add(candidate);
		System.out.println(candidateList);
	}
}
