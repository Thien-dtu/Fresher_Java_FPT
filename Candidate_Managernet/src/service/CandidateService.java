package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import connect_jdbc.ConnectJDBC;
import models.Candidate;
import models.Certificate;
import models.Experience;
import models.Fresher;
import models.Intern;
import until.CheckRegex;
import until.CompareTypeAndDate;

public class CandidateService {
	static Logger log = Logger.getLogger(CandidateService.class.getName());
	static CertificateService certificateService = new CertificateService();
	static Scanner scanner = new Scanner(System.in);
	static final String SELECT_ALL_CANDIDATE = "Select * From Candidate";
	static final String FIND_CANDIDATE_BY_ID = "Select * From Candidate Where Candidate_ID = ?";
	static final String SELECT_NEW_ADD_CANDIDATE = "Select Top 1 * From Candidate Order By Candidate_Id Desc;";
	static Connection connection;
	static ResultSet resultSet;
	static PreparedStatement preparedStatement;
	static Statement statement;

	/**
	 * Hiển thị tất cả ứng viên
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @return List<Candidate>
	 */
	public static List<Candidate> display() {
		List<Certificate> certificates = new ArrayList<>();

		List<Candidate> candidates = new ArrayList<>();

		try {
			connection = ConnectJDBC.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_CANDIDATE);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int candidateID = resultSet.getInt("Candidate_ID");
				String fullName = resultSet.getString("Full_Name");
				String birthDate = resultSet.getString("Birth_Day");
				int phone = resultSet.getInt("Phone");
				String email = resultSet.getString("Email");
				int candidateType = resultSet.getInt("Candidate_Type");
				int expInYear = resultSet.getInt("Exp_In_Year");
				String proSkill = resultSet.getString("Pro_Skill");
				String graduationDate = resultSet.getString("Graduation_Date");
				String graduationRank = resultSet.getString("Graduation_Rank");
				String education = resultSet.getString("Eduation");
				String majors = resultSet.getString("Majors");
				String semester = resultSet.getString("Semester");
				String university = resultSet.getString("University_Name");

				certificates = CertificateService.findCertificateByCandidateId(candidateID);

				if (candidateType == 0) {
					candidates.add(new Experience(candidateID, fullName, birthDate, phone, email, candidateType,
							certificates, expInYear, proSkill));
				} else if (candidateType == 1) {
					candidates.add(new Fresher(candidateID, fullName, birthDate, phone, email, candidateType,
							certificates, graduationDate, graduationRank, education));
				} else {
					candidates.add(new Intern(candidateID, fullName, birthDate, phone, email, candidateType,
							certificates, majors, semester, university));
				}
			}
			log.info(LocalDateTime.now() + " - Thực hiện : " + SELECT_ALL_CANDIDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return candidates;
	}

	/**
	 * Sắp xếp theo kiểu ứng viên và năm sinh
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @return candidates
	 * @throws SQLException
	 */
	public static List<Candidate> sortCandidate() throws SQLException {
		List<Candidate> candidates = display();
		candidates.sort(new CompareTypeAndDate());
		return candidates;
	}

	/**
	 * Tìm ứng viên theo id để update
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @param findID
	 * @return Candidate
	 * @throws SQLException
	 */
	public static Candidate findCandidateById(Integer findID) throws SQLException {
		List<Certificate> certificates = new ArrayList<>();
		Candidate candidate = null;
		try {
			connection = ConnectJDBC.getConnection();

			preparedStatement = connection.prepareStatement(FIND_CANDIDATE_BY_ID);

			preparedStatement.setInt(1, findID);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int candidateID = resultSet.getInt("Candidate_ID");
				String fullName = resultSet.getString("Full_Name");
				String birthDate = resultSet.getString("Birth_Day");
				int phone = resultSet.getInt("Phone");
				String email = resultSet.getString("Email");
				int candidateType = resultSet.getInt("Candidate_Type");
				int expInYear = resultSet.getInt("Exp_In_Year");
				String proSkill = resultSet.getString("Pro_Skill");
				String graduationDate = resultSet.getString("Graduation_Date");
				String graduationRank = resultSet.getString("Graduation_Rank");
				String education = resultSet.getString("Eduation");
				String majors = resultSet.getString("Majors");
				String semester = resultSet.getString("Semester");
				String university = resultSet.getString("University_Name");

				certificates = CertificateService.findCertificateByCandidateId(candidateID);

				if (candidateType == 0) {
					candidate = new Experience(candidateID, fullName, birthDate, phone, email, candidateType,
							certificates, expInYear, proSkill);
				} else if (candidateType == 1) {
					candidate = new Fresher(candidateID, fullName, birthDate, phone, email, candidateType, certificates,
							graduationDate, graduationRank, education);
				} else {
					candidate = new Intern(candidateID, fullName, birthDate, phone, email, candidateType, certificates,
							majors, semester, university);
				}
			}

			log.info(LocalDateTime.now() + " - Thực hiện : " + FIND_CANDIDATE_BY_ID);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			resultSet.close();
			preparedStatement.close();
			connection.close();
		}
		return candidate;
	}

	/**
	 * update ứng viên dùng CONCUR_UPDATABLE
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @throws SQLException
	 */
	public static void updateByConcurUpdatable() throws SQLException {
		boolean check = false;
		do {
			System.out.println("Nhập id ứng viên muốn SỬA:");
			Integer candidateID = Integer.parseInt(scanner.nextLine());

			Candidate candidate = findCandidateById(candidateID);
			if (candidate == null) {
				System.out.println("CandidateID không tồn tại");
				check = true;
			} else {
				System.out.println(candidate.showMe());
				check = false;

				System.out.print("Nhập họ tên : ");
				String fullName = scanner.nextLine();

				String birthDay = CheckRegex.checkBirthday();

				System.out.print("Nhập số điện thoại : ");
				int phone = Integer.parseInt(scanner.nextLine());

				String email = CheckRegex.checkEmail();

				try {
					connection = ConnectJDBC.getConnection();
					preparedStatement = connection.prepareStatement(SELECT_ALL_CANDIDATE,
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					resultSet = preparedStatement.executeQuery();

					while (resultSet.next()) {
						if (resultSet.getInt("Candidate_Id") == candidateID) {
							resultSet.updateString("Full_Name", fullName);
							resultSet.updateString("Birth_Day", birthDay);
							resultSet.updateInt("Phone", phone);
							resultSet.updateString("Email", email);
							resultSet.updateInt("Candidate_Type", candidate.getCandidateType());

							if (candidate instanceof Intern) {

								System.out.println("Nhập Chuyên Ngành:");
								String majors = scanner.nextLine();

								System.out.println("Nhập Học Kỳ:");
								String semester = scanner.nextLine();

								System.out.println("Nhập Trường Học:");
								String university = scanner.nextLine();

								resultSet.updateString("Majors", majors);
								resultSet.updateString("Semester", semester);
								resultSet.updateString("University_Name", university);

							} else if (candidate instanceof Fresher) {
								String graduationDate = CheckRegex.checkDate();

								System.out.print("Nhập xếp loại tốt nghiệp: ");
								String graduationRank = scanner.nextLine();

								System.out.print("Nhập trường tốt nghiệp: ");
								String eduation = scanner.nextLine();

								resultSet.updateString("Graduation_Date", graduationDate);
								resultSet.updateString("Graduation_Rank", graduationRank);
								resultSet.updateString("Education", eduation);

							} else {
								System.out.print("Nhập số năm kinh nghiệm : ");
								int expInYear = Integer.parseInt(scanner.nextLine());

								System.out.print("Nhập kỹ năng : ");
								String proSkill = scanner.nextLine();

								resultSet.updateInt("Exp_In_Year", expInYear);
								resultSet.updateString("Pro_Skill", proSkill);
							}
							resultSet.updateRow();
							resultSet.moveToCurrentRow();
						}
					}
					resultSet.beforeFirst();
					log.info(LocalDate.now() + " Thực hiện " + SELECT_ALL_CANDIDATE);
					System.err.println("--Cập nhật thành công--");
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					resultSet.close();
					statement.close();
					connection.close();
				}
			}
		} while (check);
	}

	/**
	 * Thêm ứng viên Exprience = CONCUR_UPDATABLE
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @throws SQLException
	 */
	public static void addExperienceByConcurUpdatable() throws SQLException {

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

		Experience experience = new Experience(fullName, birthDay, phone, email, 0, expInYear, proSkill);
		// Thêm ứng viên
		try {
			connection = ConnectJDBC.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSet = statement.executeQuery(SELECT_ALL_CANDIDATE);

			resultSet.last();
			resultSet.moveToInsertRow();

			resultSet.updateString("Full_Name", experience.getFullName());
			resultSet.updateString("Birth_Day", experience.getBirthDay());
			resultSet.updateInt("Phone", experience.getPhone());
			resultSet.updateString("Email", experience.getEmail());
			resultSet.updateInt("Candidate_Type", experience.getCandidateType());
			resultSet.updateInt("Exp_In_Year", experience.getExpInYear());
			resultSet.updateString("Pro_Skill", experience.getProSkill());

			resultSet.insertRow();
			resultSet.beforeFirst();
			log.info(LocalDate.now() + " Thực hiện " + SELECT_ALL_CANDIDATE);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			connection.close();
		}
//Lấy ra thông tin ưng viên vừa thêm
		experience = (Experience) getNewAddCandidate();
		CertificateService.addNewCertificate(experience.getCandidateID(), experience.getFullName());
	}

	/**
	 * Thêm mới ứng viên Fresher bằng CONCUR_UPDATABLE
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @throws SQLException
	 */
	public static void addFresherByConcurUpdatable() throws SQLException {

		System.out.print("Nhập họ tên : ");
		String fullName = scanner.nextLine();

		String graduationDate = CheckRegex.checkDate();

		System.out.print("Nhập xếp loại tốt nghiệp: ");
		String graduationRank = scanner.nextLine();

		System.out.print("Nhập trường tốt nghiệp: ");
		String eduation = scanner.nextLine();

		System.out.print("Nhập số điện thoại : ");
		int phone = Integer.parseInt(scanner.nextLine());

		String birthDay = CheckRegex.checkBirthday();

		String email = CheckRegex.checkEmail();

		Fresher fresher = new Fresher(fullName, birthDay, phone, email, 1, graduationDate, graduationRank, eduation);
//Thêm ứng viên fresher
		try {
			connection = ConnectJDBC.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSet = statement.executeQuery(SELECT_ALL_CANDIDATE);

			resultSet.last();
			resultSet.moveToInsertRow();

			resultSet.updateString("Full_Name", fresher.getFullName());
			resultSet.updateString("Birth_Day", fresher.getBirthDay());
			resultSet.updateInt("Phone", fresher.getPhone());
			resultSet.updateString("Email", fresher.getEmail());
			resultSet.updateInt("Candidate_Type", fresher.getCandidateType());
			resultSet.updateString("Graduation_Date", fresher.getGraduationDate());
			resultSet.updateString("Graduation_Rank", fresher.getGraduationRank());
			resultSet.updateString("Eduation", fresher.getEduacation());

			resultSet.insertRow();
			resultSet.beforeFirst();
			log.info(LocalDate.now() + " Thực Hiện " + SELECT_ALL_CANDIDATE);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			connection.close();
		}
		// Lấy ra thông tin fresher mới thêm
		fresher = (Fresher) getNewAddCandidate();
		CertificateService.addNewCertificate(fresher.getCandidateID(), fresher.getFullName());

	}

	/**
	 * Thêm mới ứng viên Intern bằng CONCUR_UPDATABLE
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @throws SQLException
	 */
	public static void addInternByConcurUpdatable() throws SQLException {

		System.out.print("Nhập họ tên: ");
		String fullName = scanner.nextLine();

		System.out.print("Nhập chuyên ngành: ");
		String majors = scanner.nextLine();

		System.out.print("Nhập học kỳ: ");
		String semester = scanner.nextLine();

		System.out.print("Nhập trường học:");
		String universityName = scanner.nextLine();

		System.out.print("Nhập số điện thoại: ");
		int phone = Integer.parseInt(scanner.nextLine());

		String birthDay = CheckRegex.checkBirthday();

		String email = CheckRegex.checkEmail();

		Intern intern = new Intern(fullName, birthDay, phone, email, 2, majors, semester, universityName);
//Thêm ứng viên intern
		try {
			connection = ConnectJDBC.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			resultSet = statement.executeQuery(SELECT_ALL_CANDIDATE);

			resultSet.last();
			resultSet.moveToInsertRow();

			resultSet.updateString("Full_Name", intern.getFullName());
			resultSet.updateString("Birth_Day", intern.getBirthDay());
			resultSet.updateInt("Phone", intern.getPhone());
			resultSet.updateString("Email", intern.getEmail());
			resultSet.updateInt("Candidate_Type", intern.getCandidateType());
			resultSet.updateString("Majors", intern.getMajors());
			resultSet.updateString("Semester", intern.getSemester());
			resultSet.updateString("University_Name", intern.getUniversityName());

			resultSet.insertRow();
			resultSet.beforeFirst();
			log.info(LocalDate.now() + " Thực Hiện " + SELECT_ALL_CANDIDATE);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			resultSet.close();
			statement.close();
			connection.close();
		}
		// Lấy ra thông tin Itern mới thêm
		intern = (Intern) getNewAddCandidate();
		CertificateService.addNewCertificate(intern.getCandidateID(), intern.getFullName());
	}

	/**
	 * Lấy thông tin của ứng viên mới khởi tạo để thêm bằng cấp
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @return Candidate
	 * @throws SQLException
	 */
	public static Candidate getNewAddCandidate() throws SQLException {
		List<Certificate> certificates = new ArrayList<>();
		Candidate candidate = null;
		try {
			connection = ConnectJDBC.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_NEW_ADD_CANDIDATE);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("Candidate_ID");
				String fullName = resultSet.getString("Full_Name");
				String birthDate = resultSet.getString("Birth_Day");
				int phone = resultSet.getInt("Phone");
				String email = resultSet.getString("Email");
				int candidateType = resultSet.getInt("Candidate_Type");
				int expInYear = resultSet.getInt("Exp_In_Year");
				String proSkill = resultSet.getString("Pro_Skill");
				String graduationDate = resultSet.getString("Graduation_Date");
				String graduationRank = resultSet.getString("Graduation_Rank");
				String education = resultSet.getString("Eduation");
				String majors = resultSet.getString("Majors");
				String semester = resultSet.getString("Semester");
				String university = resultSet.getString("University_Name");

				certificates = CertificateService.findCertificateByCandidateId(id);

				if (candidateType == 0) {
					candidate = new Experience(id, fullName, birthDate, phone, email, candidateType, certificates,
							expInYear, proSkill);
				} else if (candidateType == 1) {
					candidate = new Fresher(id, fullName, birthDate, phone, email, candidateType, certificates,
							graduationDate, graduationRank, education);
				} else {
					candidate = new Intern(id, fullName, birthDate, phone, email, candidateType, certificates, majors,
							semester, university);
				}
			}
			log.info(LocalDate.now() + " Thực hiện " + SELECT_NEW_ADD_CANDIDATE);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			resultSet.close();
			preparedStatement.close();
			connection.close();
		}
		return candidate;
	}

}
