package repository.candidate;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import model.Candidate;
import model.Certificated;
import model.Experience;
import model.Fresher;
import model.Intern;
import repository.DBConnection;
import repository.certificated.CertificatedRepository;
import repository.certificated.CertificatedRepositoryImpl;
import utils.CompareTypeAndDate;
import utils.DisplayViewForUser;

public class CandidateRepositoryImpl implements CandidateRepository {

	static CertificatedRepository repositoryCertificated = new CertificatedRepositoryImpl();
	static DisplayViewForUser displayViewForUser = new DisplayViewForUser();
	static PreparedStatement stmt = null;

	private final static String SHOW_ALL = "SELECT * FROM CANDIDATE";
	private final String FIND_BY_ID = "SELECT * FROM CANDIDATE where candidateId like ?";
//	private final String INSERT_CANDIDATE = "INSERT INTO CANDIDATE (candidateId, fullName, birthday, phone, email, candidateType, expInYear, proSkill, graduationDate, graduationRank, education, majors, semester, universityName) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String DELETE_CANDIDATE_BY_ID = "DELETE FROM CANDIDATE WHERE candidateId=?";

	/**
	 * Find all.
	 * 
	 * Create date : Feb 15, 2023 10:44:27 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the list
	 */
	@Override
	public List<Candidate> findAll() {
		Connection connection = null;
		
		List<Certificated> certificatedList = new ArrayList<Certificated>();
		List<Candidate> candidateList = new ArrayList<Candidate>();
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.prepareStatement(SHOW_ALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String candidateId = rs.getString("candidateId");
				String fullName = rs.getString("fullName");
				String birthday = rs.getString("birthday");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int candidateType = rs.getInt("candidateType");
				int expInYear = rs.getInt("expInYear");
				String proSkill = rs.getString("proSkill");
				String graduationDate = rs.getString("graduationDate");
				String graduationRank = rs.getString("graduationRank");
				String education = rs.getString("education");
				String majors = rs.getString("majors");
				String semester = rs.getString("semester");
				String universityName = rs.getString("universityName");
				
				certificatedList = repositoryCertificated.findCertificateByCandidateId(candidateId);
				
				if (candidateType == 0) {
					candidateList.add(new Experience(candidateId, fullName, birthday, phone, email, candidateType, certificatedList, expInYear,
							proSkill));
				} else if (candidateType == 1) {
					candidateList.add(new Fresher(candidateId, fullName, birthday, phone, email, candidateType, certificatedList,
							graduationDate, education, graduationRank));
				} else if (candidateType == 2) {
					candidateList.add(new Intern(candidateId, fullName, birthday, phone, email, candidateType, certificatedList, majors,
							semester, universityName));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return candidateList;
	}
	
	/**
	 * Find all candidates.
	 * 
	 * Create date : Feb 15, 2023 10:43:51 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the list
	 */
	public static List<Candidate> findAllCandidates() {
		Connection connection = null;
		
		List<Certificated> certificatedList = new ArrayList<Certificated>();
		List<Candidate> candidateList = new ArrayList<Candidate>();
		
		connection = DBConnection.getConnection();
		try {
			stmt = connection.prepareStatement(SHOW_ALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String candidateId = rs.getString("candidateId");
				String fullName = rs.getString("fullName");
				String birthday = rs.getString("birthday");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int candidateType = rs.getInt("candidateType");
				int expInYear = rs.getInt("expInYear");
				String proSkill = rs.getString("proSkill");
				String graduationDate = rs.getString("graduationDate");
				String graduationRank = rs.getString("graduationRank");
				String education = rs.getString("education");
				String majors = rs.getString("majors");
				String semester = rs.getString("semester");
				String universityName = rs.getString("universityName");
				
				certificatedList = repositoryCertificated.findCertificateByCandidateId(candidateId);
				
				if (candidateType == 0) {
					candidateList.add(new Experience(candidateId, fullName, birthday, phone, email, candidateType, certificatedList, expInYear,
							proSkill));
				} else if (candidateType == 1) {
					candidateList.add(new Fresher(candidateId, fullName, birthday, phone, email, candidateType, certificatedList,
							graduationDate, education, graduationRank));
				} else if (candidateType == 2) {
					candidateList.add(new Intern(candidateId, fullName, birthday, phone, email, candidateType, certificatedList, majors,
							semester, universityName));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return candidateList;
	}

	/**
	 * Them moi.
	 * 
	 * Create date : Feb 15, 2023 10:45:22 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param candidate the candidate
	 * @param sc the sc
	 * @return true, if successful
	 */
	@Override
	public boolean themMoi(Candidate candidate, Scanner sc) {
		Connection connection = null;
		connection = DBConnection.getConnection();
		boolean check = false;
		try {
			stmt = connection.prepareStatement(SHOW_ALL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery();
			rs = stmt.executeQuery();

			rs.last();
			rs.moveToInsertRow();

			rs.updateString("candidateId", candidate.getCandidateId());
			rs.updateString("fullName", candidate.getFullName());
//			rs.updateString("birthday", candidate.getBirthday());
			rs.updateDate("birthday", java.sql.Date.valueOf(candidate.getBirthday().substring(6, 10) + "-"
					+ candidate.getBirthday().substring(3, 5) + "-" + candidate.getBirthday().substring(0, 2)));
			rs.updateString("phone", candidate.getPhone());
			rs.updateString("email", candidate.getEmail());
			rs.updateInt("candidateType", candidate.getCandidateType());

			if (candidate instanceof Fresher) {
				rs.updateString("graduationDate", ((Fresher) candidate).getGraduationDate());
				rs.updateString("graduationRank", ((Fresher) candidate).getGraduationRank());
				rs.updateString("education", ((Fresher) candidate).getEducation());
			}
			if (candidate instanceof Intern) {

				rs.updateString("majors", ((Intern) candidate).getMajors());
				rs.updateString("semester", ((Intern) candidate).getSemester());
				rs.updateString("universityName", ((Intern) candidate).getUniversityName());
			}
			if (candidate instanceof Experience) {
				rs.updateInt("expInYear", ((Experience) candidate).getExpInYear());
				rs.updateString("proSkill", ((Experience) candidate).getProSkill());
			}

			rs.insertRow();
			check = true;

			repositoryCertificated.addNewCertificate(candidate.getCandidateId(), sc);
			addMoreCer(candidate, sc);
			System.err.println("Ban da nhap " + Candidate.getCandidateCount() + " ung vien vao database.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			check = false;
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return check;
	}

	/**
	 * Adds the more cer.
	 * 
	 * Create date : Feb 15, 2023 10:45:37 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param candidate the candidate
	 * @param sc the sc
	 * @return true, if successful
	 */
	@Override
	public boolean addMoreCer(Candidate candidate, Scanner sc) {
		boolean cont = true;
		do {
			displayViewForUser.displayMenuFromDataList("Tiep tuc them bang cap cho ung vien,thoat");
			int key = 0;
	        try {
	            key = sc.nextInt();
	            sc.nextLine();
	        } catch (NoSuchElementException e) {
	            System.err.println("Error: No input available");
	            cont = false;
	            break;
	        }
			switch (key) {
			case 1:
				repositoryCertificated.addNewCertificate(candidate.getCandidateId(), sc);
				break;
			case 2:
				cont = false;
				break;
			default:
				cont = false;
				break;
			}
		} while (cont);
		return false;
	}

	/**
	 * Find byid.
	 * 
	 * Create date : Feb 15, 2023 10:45:59 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param id the id
	 * @return the candidate
	 */
	@Override
	public Candidate findByid(String id) {
		Connection connection = null;
		Candidate candidate = null;
		connection = DBConnection.getConnection();
		boolean check = false;
		try {
			stmt = connection.prepareStatement(FIND_BY_ID, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String candidateId = rs.getString("candidateId");
				String fullName = rs.getString("fullName");
				String birthday = String.valueOf(rs.getDate("birthday"));
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				int candidateType = rs.getInt("candidateType");
				if (candidateType == 0) {
					int expInYear = rs.getInt("expInYear");
					String proSkill = rs.getString("proSkill");
					candidate = new Experience(candidateId, fullName, birthday, phone, email, candidateType, expInYear,
							proSkill);
				}
				if (candidateType == 1) {
					String graduationDate = rs.getString("graduationDate");
					String graduationRank = rs.getString("graduationRank");
					String education = rs.getString("education");
					candidate = new Fresher(candidateId, fullName, birthday, phone, email, candidateType,
							graduationDate, graduationRank, education);
				}
				if (candidateType == 2) {
					String majors = rs.getString("majors");
					String semester = rs.getString("semester");
					String universityName = rs.getString("universityName");
					candidate = new Intern(candidateId, fullName, birthday, phone, email, candidateType, majors,
							semester, universityName);
				}
			}
			check = true;
		} catch (SQLException e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return candidate;
	}
	
	/**
	 * Update candidate.
	 * 
	 * Create date : Feb 15, 2023 10:46:14 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param candidateEdit the candidate edit
	 * @param getDataFromController the get data from controller
	 */
	@Override
	public void updateCandidate(Candidate candidateEdit, Map<String, String> getDataFromController) {
		if (candidateEdit instanceof Experience) {
			updateCandidate(getDataFromController, String.format(
					"select candidateId,fullName,birthday,phone,email,expInYear,proSkill from CANDIDATE where candidateId = '%s'",
					candidateEdit.getCandidateId()));
			return;
		}
		if (candidateEdit instanceof Fresher) {
			updateCandidate(getDataFromController, String.format(
					"select candidateId,fullName,birthday,phone,email,graduationDate,graduationRank,education from CANDIDATE where candidateId = '%s' ",
					candidateEdit.getCandidateId()));
			return;
		}
		updateCandidate(getDataFromController, String.format(
				"select candidateId,fullName,birthday,phone,email,majors,semester,universityName from CANDIDATE where candidateId = '%s'",
				candidateEdit.getCandidateId()));
	}
	
	/**
	 * Update candidate.
	 * 
	 * Create date : Feb 15, 2023 10:46:25 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param result the result
	 * @param sqlString the sql string
	 */
	private void updateCandidate(Map<String, String> result, String sqlString) {
		try (Connection connection = DBConnection.getConnection();
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {
			ResultSet resultSet = stmt.executeQuery(sqlString);
			resultSet.next();
			updatePropertiesFromMap(result, resultSet);
			resultSet.updateRow();
			resultSet.moveToCurrentRow();
			System.out.println("Cập nhật thành công!!!");
		} catch (SQLException e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
		}
	}
	
	/**
	 * Update properties from map.
	 * 
	 * Create date : Feb 15, 2023 10:46:30 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param map the map
	 * @param resultSet the result set
	 * @throws SQLException the SQL exception
	 */
	private void updatePropertiesFromMap(Map<String, String> map, ResultSet resultSet) throws SQLException {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().toLowerCase().contains("day")) {
				resultSet.updateDate(entry.getKey(), Date.valueOf(entry.getValue()));
				continue;
			}
			if (entry.getKey().toLowerCase().contains("year")) {
				resultSet.updateInt(entry.getKey(), Integer.parseInt(entry.getKey()));
				continue;
			}
			resultSet.updateString(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Delete with id.
	 * 
	 * Create date : Feb 15, 2023 10:46:38 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	@Override
	public boolean deleteWithId(String id) {
		Connection connection = null;
		connection = DBConnection.getConnection();
		boolean check = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement(FIND_BY_ID);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			int c = 0;
			while (rs.next()) {
				c++;
			}
			if (c > 0) {

				PreparedStatement pstmt1 = connection.prepareStatement(DELETE_CANDIDATE_BY_ID);
				pstmt1.setString(1, id);
				int rowsDeleted = pstmt1.executeUpdate();
				System.out.println("Rows deleted: " + rowsDeleted);
				for (Candidate candidate : findAll()) {
					System.out.println(candidate.toString());
				}
			} else {
				System.out.println("CandidateId not exixts");
			}
			check = true;
		} catch (SQLException e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Gets the full name.
	 * 
	 * Create date : Feb 15, 2023 10:46:46 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the full name
	 */
	@Override
	public StringBuffer getFullName() {
		StringBuffer stringBuffer = new StringBuffer();
		for (Candidate candidate : findAll()) {
			stringBuffer.append(candidate.getFullName()).append(", ");
		}
		stringBuffer.replace(stringBuffer.length() - 2, stringBuffer.length(), ".");
		return stringBuffer;
	}

	/**
	 * Delete dupli cef id.
	 * 
	 * Create date : Feb 15, 2023 10:46:57 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean deleteDupliCefId() {
		Connection connection = null;
		connection = DBConnection.getConnection();
		boolean check = false;
		PreparedStatement pstmt;
		try {
			Set<String> certificateId = new HashSet<>();
			String DELETE_DUPLICATE_CEF_ID = "SELECT certificatedId FROM CANDIDATE";
			pstmt = connection.prepareStatement(DELETE_DUPLICATE_CEF_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String certificateIdStr = rs.getString("certificateId");
				if (!certificateId.add(certificateIdStr)) {
					String deleteSql = "DELETE FROM CANDIDATE WHERE certificatedId = ?";
					try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
						deleteStmt.setString(1, certificateIdStr);
						deleteStmt.executeUpdate();
					}
				}
			}
			check = true;
		} catch (SQLException e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * Dele cef id.
	 * 
	 * Create date : Feb 15, 2023 10:47:14 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean deleCefId() {
		try {
			Connection connection = null;
			connection = DBConnection.getConnection();

			Set<String> certificatedIds = new HashSet<>();
			String selectSql = "SELECT certificatedId FROM CANDIDATE";
			try (PreparedStatement selectStmt = connection.prepareStatement(selectSql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE); ResultSet rs = selectStmt.executeQuery()) {
				while (rs.next()) {
					String certificatedId = rs.getString("certificatedId");
					rs.absolute(2);
					if (!certificatedIds.add(certificatedId)) {
						String deleteSql = "DELETE FROM CANDIDATE WHERE certificatedId = ?";
						try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
							deleteStmt.setString(1, certificatedId);
							deleteStmt.executeUpdate();
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Sort candidate.
	 * 
	 * Create date : Feb 15, 2023 10:47:19 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the list
	 */
	@Override
	public List<Candidate> sortCandidate() {
		List<Candidate> candidates = findAllCandidates();
		candidates.sort(new CompareTypeAndDate());
		return candidates;
	}
}
