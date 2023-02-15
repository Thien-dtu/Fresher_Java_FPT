package service;

import java.util.Scanner;

import exception.BirthdayException;
import exception.MailException;
import exception.PhoneException;
import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;

public class InputCandidateService {

	public Candidate inputCandidate(Scanner sc) {
		ValidateData validateData = new ValidateData();

		Candidate candidate = new Candidate() {

			@Override
			public void update(Candidate candidate, Scanner sc) {
				// TODO Auto-generated method stub

			}

		};

		System.out.println("Nhap thong tin cua ung vien");

		System.out.print("Nhap ID ung vien: ");
		candidate.setCandidateId(sc.next());
		sc.nextLine();

		System.out.print("Nhap ho va ten cua ung vien: ");
		candidate.setFullName(sc.nextLine());

		String birthday = "";
		boolean checkBirth = true;
		do {
			try {
				System.out.print("Nhap ngay sinh cua ung vien thao dinh dang dd/MM/yyyy: ");
				birthday = sc.nextLine();
				checkBirth = false;
				if (birthday.equals("")) {
					checkBirth = true;
					throw new BirthdayException("Khong duoc de trong ngay sinh, moi ban nhap vao ngay sinh ");
				}
				if (!validateData.validateDate(birthday)) {
					checkBirth = true;
					throw new BirthdayException(
							"Ban da nhap sai dinh dang cua ngay sinh, dinh dang dung la dd/MM/yyyy: ");
				}
				if ((Integer.parseInt(birthday.substring(6, 10)) < 1900)) {
					checkBirth = true;
					throw new BirthdayException("Ban khong duoc nhap nam duoi 1900");
				}
			} catch (BirthdayException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			}
		} while (checkBirth);

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

		return candidate;
	}

	public Candidate inputCandidate(Candidate candidate, Scanner sc, int candidateType) {
		if (candidateType == 0) {
			Experience experience = new Experience();
			experience.setCandidateId(candidate.getCandidateId());
			experience.setFullName(candidate.getFullName());
			experience.setBirthday(candidate.getBirthday());
			experience.setPhone(candidate.getPhone());
			experience.setEmail(candidate.getEmail());
			try {
				System.out.println("Moi ban nhap vao so nam kinh nghiem: ");
				experience.setExpInYear(Integer.parseInt(sc.nextLine()));
			} catch (NumberFormatException e) {
				e.getMessage();
			}
			System.out.println("Moi ban nhap vao ky nang cua minh: ");
			experience.setProSkill(sc.nextLine());
			experience.setCandidateType(0);
			return experience;
		}
		if (candidateType == 1) {
			Fresher fresher = new Fresher();
			fresher.setCandidateId(candidate.getCandidateId());
			fresher.setFullName(candidate.getFullName());
			fresher.setBirthday(candidate.getBirthday());
			fresher.setPhone(candidate.getPhone());
			fresher.setEmail(candidate.getEmail());
			System.out.println("Moi ban nhap vao ngay tot nghiep: ");
			fresher.setGraduationDate(sc.nextLine());
			System.out.println("Moi ban nhap vao truong tot nghiep: ");
			fresher.setEducation(sc.nextLine());
			System.out.println("Moi ban nhap vao xep loai tot nghiep: ");
			fresher.setGraduationRank(sc.nextLine());
			fresher.setCandidateType(1);
			return fresher;
		}
		if (candidateType == 2) {
			Intern intern = new Intern();
			intern.setCandidateId(candidate.getCandidateId());
			intern.setFullName(candidate.getFullName());
			intern.setBirthday(candidate.getBirthday());
			intern.setPhone(candidate.getPhone());
			intern.setEmail(candidate.getEmail());
			System.out.println("Hay nhap vao nganh hoc: ");
			intern.setMajors(sc.nextLine());
			System.out.println("Hay nhap vao ky hoc: ");
			intern.setSemester(sc.nextLine());
			System.out.println("Hay nhap vao ten truong: ");
			intern.setUniversityName(sc.nextLine());
			intern.setCandidateType(2);
			return intern;
		}
		return candidate;
	}

}
