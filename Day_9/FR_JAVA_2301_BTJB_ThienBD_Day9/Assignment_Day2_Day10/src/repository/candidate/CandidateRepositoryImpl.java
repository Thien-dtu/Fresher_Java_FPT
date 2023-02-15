package repository.candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import utils.DisplayViewForUser;

public class CandidateRepositoryImpl implements CandidateRepository {

	private DBConnection connectionDB = new DBConnection();
	static CertificatedRepository repositoryCertificated = new CertificatedRepositoryImpl();
	static DisplayViewForUser displayViewForUser = new DisplayViewForUser();
	PreparedStatement stmt = null;

	private final String SHOW_ALL = "SELECT * FROM CANDIDATE";
	private final String FIND_BY_ID = "SELECT * FROM CANDIDATE where candidateId like ?";
	private final String INSERT_CANDIDATE = "INSERT INTO CANDIDATE (candidateId, fullName, birthday, phone, email, candidateType, expInYear, proSkill, graduationDate, graduationRank, education, majors, semester, universityName) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String DELETE_CANDIDATE_BY_ID = "DELETE FROM CANDIDATE WHERE candidateId=?";

	@Override
	public List<Candidate> findAll() {
		Connection connection = null;
		Candidate candidate = null;
		Certificated certificated = null;
		
		List<Certificated> certificatedList = new ArrayList<Certificated>();
		List<Candidate> candidateList = new ArrayList<Candidate>();
		
		connection = connectionDB.getConnection();
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

	@Override
	public boolean themMoi(Candidate candidate, Scanner sc) {
		Connection connection = null;
		connection = connectionDB.getConnection();
		boolean check = false;
		try {
			stmt = connection.prepareStatement(SHOW_ALL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery();
			rs = stmt.executeQuery();

			rs.last();
			rs.moveToInsertRow();

			rs.updateString("candidateId", candidate.getCandidateId());
			rs.updateString("fullName", candidate.getFullName());
			rs.updateString("birthday", candidate.getBirthday());
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

//	@Override
//	public boolean themMoi(Candidate candidate) {
//		Connection connection = null;
//		connection = connectionDB.getConnection();
//		boolean check = false;
//
//		try {
//			PreparedStatement stmt = connection.prepareStatement(INSERT_CANDIDATE);
//			stmt.setString(1, candidate.getCandidateId());
//			stmt.setString(2, candidate.getFullName());
//			stmt.setString(3, candidate.getBirthday());
//			stmt.setString(4, candidate.getPhone());
//			stmt.setString(5, candidate.getEmail());
//			stmt.setInt(6, candidate.getCandidateType());
//			stmt.setString(7, candidate.getCertificatedId());
//			if (candidate instanceof Experience) {
//				Experience exp = (Experience) candidate;
//				stmt.setInt(8, exp.getExpInYear());
//				stmt.setString(9, exp.getProSkill());
//				stmt.setString(10, null);
//				stmt.setString(11, null);
//				stmt.setString(12, null);
//				stmt.setString(13, null);
//				stmt.setString(14, null);
//				stmt.setString(15, null);
//			} else if (candidate instanceof Fresher) {
//				Fresher fresher = (Fresher) candidate;
//				stmt.setInt(8, 0);
//				stmt.setString(9, null);
//				stmt.setString(10, fresher.getGraduationDate());
//				stmt.setString(11, fresher.getGraduationRank());
//				stmt.setString(12, fresher.getEducation());
//				stmt.setString(13, null);
//				stmt.setString(14, null);
//				stmt.setString(15, null);
//			} else if (candidate instanceof Intern) {
//				Intern intern = (Intern) candidate;
//				stmt.setInt(8, 0);
//				stmt.setString(9, null);
//				stmt.setString(10, null);
//				stmt.setString(11, null);
//				stmt.setString(12, null);
//				stmt.setString(13, intern.getMajors());
//				stmt.setString(14, intern.getSemester());
//				stmt.setString(15, intern.getUniversityName());
//			}
//			stmt.executeUpdate();
//			check = true;
//			stmt.close();
//			connection.close();
//			System.err.println("Ban da nhap " + candidate.getCandidateCount() + " ung vien vao database.");
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//			check = false;
//		}
//		return check;
//	}

	@Override
	public Candidate findByid(String id) {
		Connection connection = null;
		Candidate candidate = null;
		connection = connectionDB.getConnection();
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

	// gui
	@Override
	public boolean updateWithId(Candidate candidate) {
		Connection connection = null;
		connection = connectionDB.getConnection();
		boolean check = false;
		try {
			stmt = connection.prepareStatement(FIND_BY_ID, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, candidate.getCandidateId());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				rs.absolute(1);
				rs.updateString("candidateId", candidate.getCandidateId());
				rs.updateString("fullName", candidate.getFullName());
				rs.updateString("birthday", candidate.getBirthday());
				rs.updateString("phone", candidate.getPhone());
				rs.updateString("email", candidate.getEmail());
				rs.updateInt("candidateType", candidate.getCandidateType());
				if (candidate.getCandidateType() == 0) {
					rs.updateInt("expInYear", ((Experience) candidate).getExpInYear());
					rs.updateString("proSkill", ((Experience) candidate).getProSkill());
				}
				if (candidate.getCandidateType() == 1) {
					rs.updateString("graduationDate", ((Fresher) candidate).getGraduationDate());
					rs.updateString("graduation_rank", ((Fresher) candidate).getGraduationRank());
					rs.updateString("education", ((Fresher) candidate).getEducation());
				}
				if (candidate.getCandidateType() == 2) {
					rs.updateString("majors", ((Intern) candidate).getMajors());
					rs.updateString("semester", ((Intern) candidate).getSemester());
					rs.updateString("universityName", ((Intern) candidate).getUniversityName());
				}
				rs.updateRow();
				check = true;
			} else {
				System.err.println("No matching row found in the database.");
			}
			for (Candidate updatedCandidate : findAll()) {
				System.out.println(updatedCandidate.toString());
			}
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
		return check;
	}

	@Override
	public boolean deleteWithId(String id) {
		Connection connection = null;
		connection = connectionDB.getConnection();
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

				check = true;
			} else {
				System.out.println("CandidateId not exixts");
			}
		} catch (SQLException e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public StringBuffer getFullName() {
		StringBuffer stringBuffer = new StringBuffer();
		for (Candidate candidate : findAll()) {
			stringBuffer.append(candidate.getFullName()).append(", ");
		}
		stringBuffer.replace(stringBuffer.length() - 2, stringBuffer.length(), ".");
		return stringBuffer;
	}

	@Override
	public boolean deleteDupliCefId() {
		Connection connection = null;
		connection = connectionDB.getConnection();
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

	@Override
	public boolean deleCefId() {
		try {
			Connection connection = null;
			connection = connectionDB.getConnection();
			boolean check = false;
			PreparedStatement pstmt;

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

}
