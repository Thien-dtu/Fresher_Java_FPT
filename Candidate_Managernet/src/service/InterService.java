package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import connect_jdbc.ConnectJDBC;
import models.Candidate;
import models.Intern;
import until.CheckRegex;

public class InterService {
	final String ADD_INTERN = "Insert Into Candidate(Majors,Semester,University_Name,Full_Name,Birth_Day,Phone,Email,Candidate_Type) Values(?,?,?,?,?,?,?,?)";
	static Scanner scanner = new Scanner(System.in);
	static Connection connection;
	static PreparedStatement preparedStatement;

	/**
	 * Thêm mới ứng viên là Intern
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	public void add() {
		try {
			connection = ConnectJDBC.getConnection();
			preparedStatement = connection.prepareStatement(ADD_INTERN);

			System.out.print("Nhập chuyên ngành: ");
			String majors = scanner.nextLine();

			System.out.print("Nhập học kỳ: ");
			String semester = scanner.nextLine();

			System.out.print("Nhập trường học:");
			String universityName = scanner.nextLine();

			System.out.print("Nhập họ tên: ");
			String fullName = scanner.nextLine();

			System.out.print("Nhập số điện thoại: ");
			int phone = Integer.parseInt(scanner.nextLine());

			String birthDay = CheckRegex.checkBirthday();

			String email = CheckRegex.checkEmail();

			preparedStatement.setString(1, majors);
			preparedStatement.setString(2, semester);
			preparedStatement.setString(3, universityName);
			preparedStatement.setString(4, fullName);
			preparedStatement.setString(5, birthDay);
			preparedStatement.setInt(6, phone);
			preparedStatement.setString(7, email);
			preparedStatement.setInt(8, 2);
			preparedStatement.executeUpdate();
			Candidate.candidateCount += 1;
			CandidateService.log.info(LocalDateTime.now() + " - Thực hiện : " + ADD_INTERN);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		@SuppressWarnings("unused")
		Intern intern;
		try {
			intern = (Intern) CandidateService.getNewAddCandidate();
			CertificateService.addNewCertificate(intern.getCandidateID(), intern.getFullName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
