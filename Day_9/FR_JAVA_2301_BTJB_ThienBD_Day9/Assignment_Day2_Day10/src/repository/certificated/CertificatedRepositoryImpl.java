package repository.certificated;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.BirthdayException;
import model.Certificated;
import repository.DBConnection;
import service.ValidateData;

public class CertificatedRepositoryImpl implements CertificatedRepository {
	private final String FIND_ALL_CERTIFICATE_BY_CANDIDATE_ID = 
			"SELECT cer.* From CERTIFICATED cer join CANDIDATE can on cer.candidateId = can.candidateId "
			+ "Where can.candidateId = ?";
	private static final String SELECT_ALL_CERTIFICATE = "Select * From CERTIFICATED";
	
//	static DisplayViewForUser displayViewForUser = new DisplayViewForUser();
	private DBConnection connectionDB = new DBConnection();
	
	PreparedStatement pstmt;
//	Statement stmt;
	ResultSet rs;
	Connection connection = null;

	@Override
	public List<Certificated> findCertificateByCandidateId(String candidateId) {
		Certificated certificated = null;
		List<Certificated> certificatedList = new ArrayList<>();
		try {
			connection = connectionDB.getConnection();
			pstmt = connection.prepareStatement(FIND_ALL_CERTIFICATE_BY_CANDIDATE_ID);
			pstmt.setString(1, candidateId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Integer certificatedId = rs.getInt("certificatedId");
				String certificatedName = rs.getString("certificatedName");
				String certificatedRank = rs.getString("certificatedRank");
				String certificatedDate = rs.getString("certificatedDate");
				certificated = new Certificated(certificatedId, certificatedName, certificatedRank, certificatedDate, candidateId);
				certificatedList.add(certificated);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return certificatedList;
	}

	@Override
	public boolean addNewCertificate(String candidateId, Scanner sc) {
		ValidateData validateData = new ValidateData();
		Certificated cert = new Certificated();
		boolean check = false;
		
		System.out.println("Nhập tên bằng cấp:");
		String certificatedName = sc.nextLine();
		cert.setCertificatedName(certificatedName);

		System.out.println("Nhập xếp hạng của bằng cấp:");
		String certificatedRank = sc.nextLine();
		cert.setCertificatedRank(certificatedRank);

		String certificatedDate = "";
		
		boolean checkCertificatedDate = true;
		do {
			try {
				System.out.print("Nhap ngay tot nghiep theo dinh dang dd/MM/yyyy: ");
				certificatedDate = sc.nextLine();
				checkCertificatedDate = false;
				if (certificatedDate.equals("")) {
					checkCertificatedDate = true;
					throw new BirthdayException("Khong duoc de trong ngay tot nghiep, moi ban nhap vao ngay tot nghiep ");
				}
				if (!validateData.validateDate(certificatedDate)) {
					checkCertificatedDate = true;
					throw new BirthdayException("Ban da nhap sai dinh dang cua ngay ngay tot nghiep, dinh dang dung la dd/MM/yyyy: ");
				}
				if ((Integer.parseInt(certificatedDate.substring(6, 10)) < 1900)){
					checkCertificatedDate = true;
					throw new BirthdayException("Ban khong duoc nhap nam duoi 1900");
				}				
			} catch (BirthdayException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			}
		}while (checkCertificatedDate);

		cert.setCertificatedDate(certificatedDate);

		try {
			connection = connectionDB.getConnection();
			pstmt = connection.prepareStatement(SELECT_ALL_CERTIFICATE, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();

			rs.last();
			rs.moveToInsertRow();

			rs.updateString("certificatedName", cert.getCertificatedName());
			rs.updateString("certificatedRank", cert.getCertificatedRank());
			rs.updateDate("certificatedDate", java.sql.Date.valueOf(cert.getCertificatedDate().substring(6,10) + "-" + cert.getCertificatedDate().substring(3,5) + "-"  + cert.getCertificatedDate().substring(0,2)));
			rs.updateString("candidateId", candidateId);

			rs.insertRow();
			rs.beforeFirst();
			
			check = true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return check;
	}
}
