package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import connect_jdbc.ConnectJDBC;
import controller.Controller;
import models.Certificate;

public class CertificateService {
	private static final String FIND_ALL_CERTIFICATE_BY_CANDIDATE_ID = "Select cer.* From Certificated cer join Candidate can on cer.Candidate_ID = can.Candidate_ID  Where can.Candidate_ID = ?";
	private static final String SELECT_ALL_CERTIFICATE = "Select * From Certificated";
	static Scanner scanner = new Scanner(System.in);
	static Connection connection;
	static PreparedStatement preparedStatement;
	static ResultSet resultSet;
	static Statement statement;

	/**
	 * Lấy danh sách bằng cấp theo ID ứng viên
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @param candidateID
	 * @return List<Certificate>
	 */
	public static List<Certificate> findCertificateByCandidateId(int candidateID) {
		List<Certificate> certificates = new ArrayList<>();
		Certificate certificate = null;
		try {
			connection = ConnectJDBC.getConnection();
			preparedStatement = connection.prepareStatement(FIND_ALL_CERTIFICATE_BY_CANDIDATE_ID);
			preparedStatement.setInt(1, candidateID);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int certificatedID = resultSet.getInt("Certificated_ID");
				String certificatedName = resultSet.getString("Certificated_Name");
				String certificatedRank = resultSet.getString("Certificated_Rank");
				String certificatedDate = resultSet.getString("Certificated_Date");
				certificate = new Certificate(certificatedID, certificatedName, certificatedRank, certificatedDate);
				certificates.add(certificate);
			}
			CandidateService.log.info(LocalDateTime.now() + " - Thực hiện : " + FIND_ALL_CERTIFICATE_BY_CANDIDATE_ID);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return certificates;
	}

	/**
	 * Thêm mới bằng cấp cho ứng viên vừa tạo
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @param candidate_ID
	 * @param candidate_Name
	 */
	public static void addNewCertificate(Integer candidate_ID, String candidate_Name) {
		System.out.println("Nhập tên bằng cấp:");
		String certificatedName = scanner.nextLine();

		System.out.println("Nhập xếp hạng của bằng cấp:");
		String certificatedRank = scanner.nextLine();

		System.out.println("Nhập ngày tốt nghiệp:");
		String certificatedDate = scanner.nextLine();

		Certificate certificate = new Certificate(certificatedName, certificatedRank, certificatedDate, candidate_ID);

		try {
			connection = ConnectJDBC.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSet = statement.executeQuery(SELECT_ALL_CERTIFICATE);

			resultSet.last();
			resultSet.moveToInsertRow();

			resultSet.updateString("Certificated_Name", certificate.getCertificateName());
			resultSet.updateString("Certificated_Rank", certificate.getCertificateRank());
			resultSet.updateString("Certificated_Date", certificate.getCertificateDate());
			resultSet.updateInt("Candidate_ID", candidate_ID);

			resultSet.insertRow();
			resultSet.beforeFirst();
			CandidateService.log.info(LocalDateTime.now() + " - Thực hiện : " + SELECT_ALL_CERTIFICATE);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.err.println("Thêm mới ứng viên --" + candidate_Name + "-- với bằng cấp  --"
				+ certificate.getCertificateName() + "-- thành công");

		do {
			System.out.println("1.Tiếp tục thêm bằng cấp cho ứng viên \n" + "2.Thoát thêm về menu thêm ứng viên \n"
					+ "Nhập để chọn \n");
			String choice = scanner.nextLine();
			switch (choice) {
			case "1":
				System.out.println("Nhập tên bằng cấp:");
				String certificatedNames = scanner.nextLine();

				System.out.println("Nhập xếp hạng của bằng cấp:");
				String certificatedRanks = scanner.nextLine();

				System.out.println("Nhập ngày tốt nghiệp:");
				String certificatedDates = scanner.nextLine();

				Certificate certificates = new Certificate(certificatedNames, certificatedRanks, certificatedDates,
						candidate_ID);

				try {
					connection = ConnectJDBC.getConnection();
					statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					resultSet = statement.executeQuery(SELECT_ALL_CERTIFICATE);

					resultSet.last();
					resultSet.moveToInsertRow();

					resultSet.updateString("Certificated_Name", certificates.getCertificateName());
					resultSet.updateString("Certificated_Rank", certificates.getCertificateRank());
					resultSet.updateString("Certificated_Date", certificates.getCertificateDate());
					resultSet.updateInt("Candidate_ID", candidate_ID);

					resultSet.insertRow();
					resultSet.beforeFirst();
					CandidateService.log.info(LocalDateTime.now() + " - Thực hiện : " + SELECT_ALL_CERTIFICATE);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						resultSet.close();
						statement.close();
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				System.err.println("Thêm mới bằng cấp  --" + certificates.getCertificateName() + "-- cho ứng viên --"
						+ candidate_Name + "-- thành công");
				break;
			case "2":
				try {
					Controller.caseAddByConcurUpdatable();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Chọn 1--->2 ");
				break;
			}
		} while (true);
	}

}
