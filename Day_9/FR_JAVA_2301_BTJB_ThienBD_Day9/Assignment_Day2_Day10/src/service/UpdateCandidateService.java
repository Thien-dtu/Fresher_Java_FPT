package service;

import java.util.Scanner;

import exception.BirthdayException;
import exception.MailException;
import exception.PhoneException;
import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;
import utils.DisplayViewForUser;

public class UpdateCandidateService {

	static DisplayViewForUser displayViewForUser = new DisplayViewForUser();
	static ValidateData validateData = new ValidateData();

	public Candidate updateCandidate(Candidate candidate, Scanner sc) {

		if (candidate != null) {
			boolean cont = true;
			do {
				System.out.println("Chon chuc nang ban can dung: ");
				displayViewForUser.displayMenuFromDataList("Ho va ten: ," + "Ngay sinh: ," + "Dien thoai: ,"
						+ "Email: ," + "Loai ung vien: ," + "Thoat: ");

				int key = sc.nextInt();
				switch (key) {
				case 1:
					System.out.println("Moi nhap ho va ten : ");
					String fullName = sc.next();
					sc.nextLine();
					candidate.setFullName(fullName);
					break;
				case 2:
					String birthday = "";
					do {
						try {
							System.out.print("Nhap ngay sinh cua ung vien: ");
							birthday = sc.next();
							sc.nextLine();
							if (birthday.equals("")) {
								throw new BirthdayException(
										"Khong duoc de trong ngay sinh, moi ban nhap vao ngay sinh ");
							}
							if (!validateData.validateDate(birthday)) {
								throw new BirthdayException("Ban da nhap sai dinh dang cua ngay sinh");
							}
							if ((Integer.parseInt(birthday.substring(6, 10)) < 1900)) {
								throw new BirthdayException("Ban khong duoc nhap nam duoi 1900");
							}
						} catch (BirthdayException e) {
							System.err.println(e.getMessage());
						} catch (Exception e) {
							System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
						}
					} while (!validateData.validateDate(birthday));
					candidate.setBirthday(birthday);
					break;
				case 3:
					String phone = "";
					do {
						try {
							System.out.print("Nhap so dien thoai cua ung vien: ");
							phone = sc.next();
							sc.nextLine();
							if (phone.isEmpty()) {
								throw new PhoneException("so dien thoai cua ung vien khong duoc de trong. ");
							} else if (!validateData.validatePhone(phone)) {
								throw new PhoneException("so dien thoai cua ung vien khong dung dinh dang. ");
							}
						} catch (PhoneException e) {
							System.err.println(e.getMessage());
						} catch (Exception e) {
							System.err
									.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
						}
					} while (!validateData.validatePhone(phone));
					candidate.setPhone(phone);
					break;
				case 4:
					String email = "";
					do {
						try {
							System.out.print("Nhap email cua ung vien: ");
							email = sc.next();
							sc.nextLine();
							if (email.isEmpty()) {
								throw new MailException("Email khong duoc de trong. ");
							} else if (!validateData.validateEmail(email)) {
								throw new MailException("Emai khong dung dinh dang. ");
							}
						} catch (MailException e) {
							System.err.println(e.getMessage());
						} catch (Exception e) {
							System.err
									.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
						}
					} while (!validateData.validateEmail(email));

					candidate.setEmail(email);
					break;
				case 5:
					System.out.println("Moi nhap loai ung vien : ");
					int candidateType = Integer.parseInt(sc.next());
					sc.nextLine();
					candidate.setCandidateType(candidateType);
					break;
				case 6:
					cont = false;
					break;
				default:
					cont = false;
					System.out.println("Ban da thoat!");
				}
			} while (cont);
		}
		return candidate;
	}

	public Candidate updateCandidate(Candidate candidate, Scanner sc, int candidateType) {

		boolean cont = true;

		if (candidateType == 0) {
			Experience experience = new Experience();
			experience.setCandidateId(candidate.getCandidateId());
			experience.setFullName(candidate.getFullName());
			experience.setBirthday(candidate.getBirthday());
			experience.setPhone(candidate.getPhone());
			experience.setEmail(candidate.getEmail());
			do {
				try {
					System.out.println("Moi ban chinh sua thong tin chi tiet ve doi tuong Experience: ");
					displayViewForUser.displayMenuFromDataList(
							"chinh sua so nam kinh nghiem cua ung vien Experience: ,chinh sua ky nang cua ung vien Experience: ,thoat trinh chinh sua danh cho ung vien Experience.");

					int key = Integer.parseInt(sc.next());
					sc.nextLine();
					switch (key) {
					case 1:
						System.out.println("Moi ban nhap vao so nam kinh nghiem: ");
						experience.setExpInYear(Integer.parseInt(sc.next()));
						sc.nextLine();
						break;
					case 2:
						System.out.println("Moi ban nhap vao so ky nang: ");
						experience.setProSkill(sc.next());
						sc.nextLine();
						break;
					case 3:
						cont = false;
						break;
					default:
						cont = false;
						System.out.println("Tam biet");
						break;
					}
				} catch (Exception e) {
					System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
				}
			} while (cont);
			return experience;
		}
		if (candidateType == 1) {
			Fresher fresher = new Fresher();
			fresher.setCandidateId(candidate.getCandidateId());
			fresher.setFullName(candidate.getFullName());
			fresher.setBirthday(candidate.getBirthday());
			fresher.setPhone(candidate.getPhone());
			fresher.setEmail(candidate.getEmail());
			do {
				try {
					System.out.println("Moi ban chinh sua thong tin chi tiet ve doi tuong Fresher: ");
					displayViewForUser.displayMenuFromDataList("chinh sua ngay tot nghiep cua ung vien Fresher: ,"
							+ "chinh sua truong tot nghiep cua ung vien Fresher: : ,"
							+ "chinh sua xep loai tot nghiep cua ung vien Fresher: ,"
							+ "thoat trinh chinh sua danh cho ung vien Fresher.");

					int key = Integer.parseInt(sc.next());
					sc.nextLine();
					switch (key) {
					case 1:
						String graduationDate = "";

						boolean checkGraduationDate = true;
						do {
							try {
								System.out.print("Nhap ngay tot nghiep theo dinh dang dd/MM/yyyy: ");
								graduationDate = sc.next();
								sc.nextLine();
								checkGraduationDate = false;
								if (graduationDate.equals("")) {
									checkGraduationDate = true;
									throw new BirthdayException(
											"Khong duoc de trong ngay tot nghiep, moi ban nhap vao ngay tot nghiep ");
								}
								if (!validateData.validateDate(graduationDate)) {
									checkGraduationDate = true;
									throw new BirthdayException(
											"Ban da nhap sai dinh dang cua ngay ngay tot nghiep, dinh dang dung la dd/MM/yyyy: ");
								}
								if ((Integer.parseInt(graduationDate.substring(6, 10)) < 1900)) {
									checkGraduationDate = true;
									throw new BirthdayException("Ban khong duoc nhap nam duoi 1900");
								}
							} catch (BirthdayException e) {
								System.err.println(e.getMessage());
							} catch (Exception e) {
								System.err.println(
										"The system has encountered an unexpected problem, sincerely sorry !!!");
							}
						} while (checkGraduationDate);

						fresher.setGraduationDate(graduationDate);
						break;
					case 2:
						System.out.println("Moi ban nhap truong tot nghiep: ");
						String education = sc.next();
						sc.nextLine();
						fresher.setEducation(education);
						break;
					case 3:
						System.out.println("Moi ban nhap vao xep loai tot nghiep: ");
						String graduationRank = sc.next();
						sc.nextLine();
						fresher.setGraduationRank(graduationRank);
						break;
					case 4:
						cont = false;
						break;
					default:
						cont = false;
						System.out.println("Tam biet");
						break;
					}
				} catch (Exception e) {
					System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
				}
			} while (cont);
			return fresher;
		}
		if (candidateType == 2) {
			Intern intern = new Intern();
			intern.setCandidateId(candidate.getCandidateId());
			intern.setFullName(candidate.getFullName());
			intern.setBirthday(candidate.getBirthday());
			intern.setPhone(candidate.getPhone());
			intern.setEmail(candidate.getEmail());
			do {
			try {
				System.out.println("Moi ban chinh sua thong tin chi tiet ve doi tuong Experience: ");
				displayViewForUser.displayMenuFromDataList("de chinh sua nganh hoc ung vien Intern: ,"
						+ "chinh sua ky hoc cua ung vien Intern: ," + "chinh sua ten truong cua ung vien Intern: ,"
						+ "chinh sua ten truong cua ung vien Intern: ");

				int key = Integer.parseInt(sc.nextLine());
				switch (key) {
				case 1:
					System.out.println("Moi ban nhap vao nganh hoc: ");
					intern.setMajors(sc.next());
					sc.nextLine();
					break;
				case 2:
					System.out.println("Moi ban nhap vao ky hoc: ");
					intern.setSemester(sc.next());
					sc.nextLine();
					break;
				case 3:
					System.out.println("Moi ban nhap vap ten truong: ");
					intern.setUniversityName(sc.next());
					sc.nextLine();
					break;
				case 4:
					cont = false;
					break;
				default:
					cont = false;
					System.out.println("Tam biet");
					break;
				}
			} catch (Exception e) {
				System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
			}
			} while (cont);
			return intern;
		}

		return candidate;
	}

}
