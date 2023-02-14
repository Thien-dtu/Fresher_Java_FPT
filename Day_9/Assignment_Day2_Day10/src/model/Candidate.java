package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.BirthdayException;
import exception.MailException;
import exception.PhoneException;
import service.ValidateData;

public abstract class Candidate {
	private static List<Candidate> candidateList = new ArrayList<>();
	
	private String candidateId;
	private String fullName;
	private String birthday;
	private String phone;
	private String email;
	private int candidateType;
	static int candidateCount = 0;
	List<Certificated> certificateList = new ArrayList<>();

	public Candidate() {
	}

	public Candidate( String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType, List<Certificated> certificateList) {
		super();
		this.candidateId = candidateId;
		this.fullName = fullName;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.candidateType = candidateType;
		this.certificateList = certificateList;
	}
	
	public Candidate( String candidateId, String fullName, String birthday, String phone, String email,
			int candidateType) {
		super();
		this.candidateId = candidateId;
		this.fullName = fullName;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.candidateType = candidateType;
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

	public List<Certificated> getCertificateList() {
		return certificateList;
	}

	public void setCertificateList(List<Certificated> certificateList) {
		this.certificateList = certificateList;
	}
	
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", fullName=" + fullName + ", birthday="
				+ birthday + ", phone=" + phone + ", email=" + email + ", candidateType=" + candidateType
				+ "]";
	}

	public Candidate isCandidate(String candidateCode) {
		for (Candidate candidate : candidateList) {
			if (candidate.getCandidateId().equals(candidateCode)) {
				return candidate;
			}
		}
		return null;
	}

	public abstract void update(Candidate candidate, Scanner sc);

	//gui
	public void updateInfo(Candidate candidate, Scanner sc) {
		ValidateData validateData = new ValidateData();
		if (candidate != null) {
			System.out.println("Chon chuc nang ban can dung: ");
			System.out.println("1. Ho va ten:  ");
			System.out.println("2. Ngay sinh: ");
			System.out.println("3. Dien thoai: ");
			System.out.println("4. Email: ");
			System.out.println("5. Loai ung vien: ");
			System.out.println("6. Ma chung chi: ");
			System.out.println("7. Thoat: ");
			int choice = 1;
			while (choice != 0) {
				try {
					System.out.println("nhap choice :");
					choice = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Nhap sai chuc nang, nhap lai: ");
				}
				switch (choice) {
				case 1:
					System.out.println("Moi nhap ho va ten : ");
					String fullName = sc.nextLine();
					candidate.setFullName(fullName);
					System.out.println(toString());
					break;
				case 2:
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
					toString();
					break;
				case 3:
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
					toString();
					break;
				case 4:
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
					toString();
					break;
				case 5:
					System.out.println("Moi nhap loai ung vien : ");
					int candidateType = Integer.parseInt(sc.nextLine());
					candidate.setCandidateType(candidateType);
					toString();
					break;
				case 6:
					System.out.println("Moi nhap ID chung chi cua ung vien : ");
					String certificateId = sc.nextLine();
//					candidate.setCertificatedId(certificateId);
					toString();
					break;
				case 7:
					return;
				default:
					System.out.println("Ban da thoat!");
				}
			}
		}
	}
}
