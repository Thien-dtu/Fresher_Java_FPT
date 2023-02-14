package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import connect_jdbc.ConnectJDBC;
import models.Candidate;
import models.Fresher;
import until.CheckRegex;

public class FresherService {
	final String ADD_FRESHER = "Insert Into Candidate(Graduation_Date,Graduation_Rank,Eduation,Full_Name,Birth_Day,Phone,Email,Candidate_Type) Values(?,?,?,?,?,?,?,?)";
	static Scanner scanner = new Scanner(System.in);
	static Connection connection;
	static PreparedStatement preparedStatement;

	/**
	 * Thêm mới ứng viên là Fresher
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 */
	public void add() {
		try {
			connection = ConnectJDBC.getConnection();
			preparedStatement = connection.prepareStatement(ADD_FRESHER);

			String graduationDate = CheckRegex.checkDate();

			System.out.print("Nhập xếp loại tốt nghiệp: ");
			String graduationRank = scanner.nextLine();

			System.out.print("Nhập trường tốt nghiệp: ");
			String eduation = scanner.nextLine();

			System.out.print("Nhập họ tên : ");
			String fullName = scanner.nextLine();

			System.out.print("Nhập số điện thoại : ");
			int phone = Integer.parseInt(scanner.nextLine());

			String birthDay = CheckRegex.checkBirthday();

			String email = CheckRegex.checkEmail();

			preparedStatement.setString(1, graduationDate);
			preparedStatement.setString(2, graduationRank);
			preparedStatement.setString(3, eduation);
			preparedStatement.setString(4, fullName);
			preparedStatement.setString(5, birthDay);
			preparedStatement.setInt(6, phone);
			preparedStatement.setString(7, email);
			preparedStatement.setInt(8, 1);
			preparedStatement.executeUpdate();
			Candidate.candidateCount += 1;
			CandidateService.log.info(LocalDateTime.now() + " - Thực hiện : " + ADD_FRESHER);

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
		Fresher fresher;
			try {
				fresher = (Fresher) CandidateService.getNewAddCandidate();
				CertificateService.addNewCertificate(fresher.getCandidateID(), fresher.getFullName());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
