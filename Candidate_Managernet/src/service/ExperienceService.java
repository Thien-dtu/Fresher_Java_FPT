package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import connect_jdbc.ConnectJDBC;
import models.Candidate;
import models.Experience;
import until.CheckRegex;

public class ExperienceService {
	static Scanner scanner = new Scanner(System.in);
	final String ADD_EXPRIENCE = "Insert Into Candidate(Exp_In_Year,Pro_Skill,Full_Name,Birth_Day,Phone,Email,Candidate_Type) Values(?,?,?,?,?,?,?)";
	static Connection connection;
	static PreparedStatement preparedStatement;

	/**
	 * Thêm mới ứng viên là Experience
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	public void add() {
		try {
			connection = ConnectJDBC.getConnection();

			preparedStatement = connection.prepareStatement(ADD_EXPRIENCE);

			System.out.print("Nhập số năm kinh nghiệm : ");
			int expInYear = Integer.parseInt(scanner.nextLine());

			System.out.print("Nhập kỹ năng : ");
			String proSkill = scanner.nextLine();

			System.out.print("Nhập họ tên : ");
			String fullName = scanner.nextLine();

			System.out.print("Nhập số điện thoại : ");
			int phone = Integer.parseInt(scanner.nextLine());

			String birthDay = CheckRegex.checkBirthday();

			String email = CheckRegex.checkEmail();

			preparedStatement.setInt(1, expInYear);
			preparedStatement.setString(2, proSkill);
			preparedStatement.setString(3, fullName);
			preparedStatement.setString(4, birthDay);
			preparedStatement.setInt(5, phone);
			preparedStatement.setString(6, email);
			preparedStatement.setInt(7, 0);
			preparedStatement.executeUpdate();
			Candidate.candidateCount += 1;
			CandidateService.log.info(LocalDateTime.now() + " - Thực hiện : " + ADD_EXPRIENCE);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		@SuppressWarnings("unused")
		Experience experience ;
		try {
			experience= (Experience) CandidateService.getNewAddCandidate();
			CertificateService.addNewCertificate(experience.getCandidateID(), experience.getFullName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
