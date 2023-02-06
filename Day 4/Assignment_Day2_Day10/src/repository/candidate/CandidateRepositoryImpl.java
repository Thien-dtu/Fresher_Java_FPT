package repository.candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;
import repository.DBConnection;

public class CandidateRepositoryImpl implements CandidateRepository {

	List<Candidate> candidatesList = new ArrayList<>();
	private DBConnection connectionDB = new DBConnection();

	private final String SHOW_ALL = "SELECT * FROM candidate";
	private final String FIND_BY_ID = "SELECT * FROM candidate where candidateID like ?";
	private final String INSERT_CANDIDATE = "INSERT INTO CANDIDATE (candidateId, fullName, birthday, phone, email, candidateType, expInYear, proSkill, graduationDate, graduationRank, education, majors, semester, universityName) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	public List<Candidate> findAll() {
		Connection connection = null;
		Candidate candidate = null;
		List<Candidate> candidatelList = new ArrayList<Candidate>();
		connection = connectionDB.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SHOW_ALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String candidateID = rs.getString("candidateID");
				String fullName = rs.getString("fullName");
				String birthday = rs.getString("birthday");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String certificatedID = rs.getString("certificateID");
				int candidate_type = rs.getInt("candidate_type");
				if (candidate_type == 1) {
					int expInYear = rs.getInt("expInYear");
					String proSkill = rs.getString("proSkill");
					candidate = new Experience(candidateID, fullName, birthday, phone, email, candidate_type,
							certificatedID, expInYear, proSkill);
				} else if (candidate_type == 2) {
					String graduation_date = rs.getString("graduation_date");
					String graduation_rank = rs.getString("graduation_rank");
					String education = rs.getString("education");
					candidate = new Fresher(candidateID, fullName, birthday, phone, email, candidate_type,
							certificatedID, graduation_date, education, graduation_rank);
				} else if (candidate_type == 3) {
					String majors = rs.getString("majors");
					String semester = rs.getString("semester");
					String university_name = rs.getString("university_name");
					candidate = new Intern(candidateID, fullName, birthday, phone, email, candidate_type,
							certificatedID, majors, semester, university_name);
				}
				candidatelList.add(candidate);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return candidatelList;
	}

//	@Override
//	public boolean themMoi(Candidate candidate) {
//		Connection connection = null;
//		connection = connectionDB.getConnection();
//		boolean check = false;
//		try {
//			PreparedStatement stmt = connection.prepareStatement(SHOW_ALL, ResultSet.TYPE_SCROLL_SENSITIVE,
//					ResultSet.CONCUR_UPDATABLE);
//			ResultSet rs = stmt.executeQuery();
//			rs = stmt.executeQuery();
//			rs.moveToInsertRow();
//			rs.updateString("candidateID", candidate.getCandidateId());
//			rs.updateString("fullName", candidate.getFullName());
//			rs.updateString("birthday", candidate.getBirthday());
//			rs.updateString("phone", candidate.getPhone());
//			rs.updateString("email", candidate.getEmail());
//			rs.updateInt("candidate_type", candidate.getCandidateType());
//			rs.updateString("certificateID", candidate.getCertificatedId());
//			if (candidate instanceof Fresher) {
//				rs.updateString("graduation_date", ((Fresher) candidate).getGraduationDate());
//				rs.updateString("graduation_rank", ((Fresher) candidate).getGraduationRank());
//				rs.updateString("Education", ((Fresher) candidate).getEducation());
//			}
//			if (candidate instanceof Intern) {
//
//				rs.updateString("majors", ((Intern) candidate).getMajors());
//				rs.updateString("semester", ((Intern) candidate).getSemester());
//				rs.updateString("university_name", ((Intern) candidate).getUniversityName());
//			}
//			if (candidate instanceof Experience) {
//				rs.updateInt("expInYear", ((Experience) candidate).getExpInYear());
//				rs.updateString("proSkill", ((Experience) candidate).getProSkill());
//			}
//			rs.insertRow();
//			check = true;
//			stmt.close();
//			connection.close();
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//			check = false;
//		}
//		return check;
//	}

	@Override
	public boolean themMoi(Candidate candidate) {
		Connection connection = null;
		connection = connectionDB.getConnection();
		boolean check = false;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(INSERT_CANDIDATE);
			stmt.setString(1, candidate.getCandidateId());
			stmt.setString(2, candidate.getFullName());
			stmt.setString(3, candidate.getBirthday());
			stmt.setString(4, candidate.getPhone());
			stmt.setString(5, candidate.getEmail());
			stmt.setInt(6, candidate.getCandidateType());
             if (candidate instanceof Experience) {
                 Experience exp = (Experience) candidate;
                 stmt.setInt(7, exp.getExpInYear());
                 stmt.setString(8, exp.getProSkill());
                 stmt.setString(9, null);
                 stmt.setString(10, null);
                 stmt.setString(11, null);
                 stmt.setString(12, null);
                 stmt.setString(13, null);
                 stmt.setString(14, null);
             } else if (candidate instanceof Fresher) {
                 Fresher fresher = (Fresher) candidate;
                 stmt.setInt(7, 0);
                 stmt.setString(8, null);
                 stmt.setString(9, fresher.getGraduationDate());
                 stmt.setString(10, fresher.getGraduationRank());
                 stmt.setString(11, fresher.getEducation());
                 stmt.setString(12, null);
                 stmt.setString(13, null);
                 stmt.setString(14, null);
             } else if (candidate instanceof Intern) {
                 Intern intern = (Intern) candidate;
                 stmt.setInt(7, 0);
                 stmt.setString(8, null);
                 stmt.setString(9, null);
                 stmt.setString(10, null);
                 stmt.setString(11, null);
                 stmt.setString(12, intern.getMajors());
                 stmt.setString(13, intern.getSemester());
                 stmt.setString(14, intern.getUniversityName());
             }
            stmt.executeUpdate();
            check = true;
 			stmt.close();
 			connection.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			check = false;
		}
		return check;
	}

	@Override
	public Candidate findByiD(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateWithID(Candidate candidate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkConnection() {
		connectionDB.getConnection();
		return true;
	}

}
