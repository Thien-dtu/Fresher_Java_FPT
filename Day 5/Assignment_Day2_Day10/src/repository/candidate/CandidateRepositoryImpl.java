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

	private final String SHOW_ALL = "SELECT * FROM CANDIDATE";
	private final String FIND_BY_ID = "SELECT * FROM CANDIDATE where candidateId like ?";
	private final String INSERT_CANDIDATE = "INSERT INTO CANDIDATE (candidateId, fullName, birthday, phone, email, candidateType, certificatedId, expInYear, proSkill, graduationDate, graduationRank, education, majors, semester, universityName) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String CHECK_EXIXTS_CANDIDATE_ID="SELECT * FROM CANDIDATE WHERE candidateId=?";
	private final String DELETE_CANDIDATE_BY_ID="DELETE FROM CANDIDATE WHERE candidateId=?";

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
				String candidateId = rs.getString("candidateId");
				String fullName = rs.getString("fullName");
				String birthday = rs.getString("birthday");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String certificatedId = rs.getString("certificatedId");
				int candidateType = rs.getInt("candidateType");
				if (candidateType == 1) {
					int expInYear = rs.getInt("expInYear");
					String proSkill = rs.getString("proSkill");
					candidate = new Experience(candidateId, fullName, birthday, phone, email, candidateType,
							certificatedId, expInYear, proSkill);
				} else if (candidateType == 2) {
					String graduationDate = rs.getString("graduationDate");
					String graduationRank = rs.getString("graduationRank");
					String education = rs.getString("education");
					candidate = new Fresher(candidateId, fullName, birthday, phone, email, candidateType,
							certificatedId, graduationDate, education, graduationRank);
				} else if (candidateType == 3) {
					String majors = rs.getString("majors");
					String semester = rs.getString("semester");
					String universityName = rs.getString("universityName");
					candidate = new Intern(candidateId, fullName, birthday, phone, email, candidateType,
							certificatedId, majors, semester, universityName);
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
			stmt.setString(7, candidate.getCandidateId());
             if (candidate instanceof Experience) {
                 Experience exp = (Experience) candidate;
                 stmt.setInt(8, exp.getExpInYear());
                 stmt.setString(9, exp.getProSkill());
                 stmt.setString(10, null);
                 stmt.setString(11, null);
                 stmt.setString(12, null);
                 stmt.setString(13, null);
                 stmt.setString(14, null);
                 stmt.setString(15, null);
             } else if (candidate instanceof Fresher) {
                 Fresher fresher = (Fresher) candidate;
                 stmt.setInt(8, 0);
                 stmt.setString(9, null);
                 stmt.setString(10, fresher.getGraduationDate());
                 stmt.setString(11, fresher.getGraduationRank());
                 stmt.setString(12, fresher.getEducation());
                 stmt.setString(13, null);
                 stmt.setString(14, null);
                 stmt.setString(15, null);
             } else if (candidate instanceof Intern) {
                 Intern intern = (Intern) candidate;
                 stmt.setInt(8, 0);
                 stmt.setString(9, null);
                 stmt.setString(10, null);
                 stmt.setString(11, null);
                 stmt.setString(12, null);
                 stmt.setString(13, intern.getMajors());
                 stmt.setString(14, intern.getSemester());
                 stmt.setString(15, intern.getUniversityName());
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

	@Override
	public boolean deleteWithID(String id) {
		Connection connection = null;
		connection = connectionDB.getConnection();
		boolean check = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement(CHECK_EXIXTS_CANDIDATE_ID);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			  int c = 0 ; 
			  while(rs.next()){
			      c++ ; 
			  } 
			if(c>0){
				
				PreparedStatement pstmt1 = connection.prepareStatement(DELETE_CANDIDATE_BY_ID);
				pstmt1.setString(1, id);
				int rowsDeleted = pstmt1.executeUpdate();
			    System.out.println("Rows deleted: " + rowsDeleted);
				for (Candidate candidate : findAll()) {
					System.out.println(candidate.toString());
				}
			 }else{
				 System.out.println("CandidateId not exixts");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
