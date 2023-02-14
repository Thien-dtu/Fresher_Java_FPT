package main.java.com.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import main.java.com.entities.Candidate;
import main.java.com.entities.Experience;
import main.java.com.entities.Fresher;
import main.java.com.entities.Intern;
import main.java.com.utils.ConnectDB;

/**
 * 
 * @author USER = TuanLN6
 * @date: 10/2/2023
 * @version 1.0
 */
public class CandidateService implements ICandidarteService {
	DegreesService degreesService = new DegreesService();
	Logger logger = Logger.getLogger(CandidateService.class);

	@Override
	public <E extends Candidate> void saveCandidateAfterInputData(E e, List<String> list) {

		if (e instanceof Experience) {
			saveCandidateByListString(list, e.getProString(), "1");
			return;
		}

		if (e instanceof Fresher) {
			saveCandidateByListString(list, e.getProString(), "2");
			return;
		}

		saveCandidateByListString(list, e.getProString(), "3");
	}

	/**
	 * @description find all by description string immutable
	 * @return
	 */
	public String findAllByString() {
		String resultString = "Name by String: ";
		String sqlString = "select fullname from candidate";
		try (Connection connection = ConnectDB.getConnect();
				PreparedStatement statement = connection.prepareStatement(sqlString)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				resultString += resultSet.getString("fullname");
				resultString += ",";
			}
			logger.info("success");
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.warn(e.getMessage());
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
		}
		return resultString;
	}

	/**
	 * @description find all by description stringBuilder
	 * @return
	 */
	public String findAllByStringBuilder() {
		StringBuilder resultString = new StringBuilder("Name by StringBuilder: ");
		String sqlString = "select fullname from candidate";
		try (Connection connection = ConnectDB.getConnect();
				PreparedStatement statement = connection.prepareStatement(sqlString)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				resultString.append(resultSet.getString("fullname")).append(",");
			}
			logger.info("success");
		} catch (SQLException e) {
			logger.warn(e.getMessage());
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
		}
		return resultString.toString();
	}

	/**
	 * @description find all with order by choice of user
	 * @return
	 */
	public Set<Candidate> findAll(String choice) {
		Set<Candidate> set = new LinkedHashSet<>();
		String sqlString = "select * from candidate".concat(choice);
		try (Connection connection = ConnectDB.getConnect();
				PreparedStatement statement = connection.prepareStatement(sqlString)) {
			ResultSet resultSet = statement.executeQuery();
			set = getCandidateByResultSet(resultSet);
		} catch (SQLException e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
		}
		return set;
	}

	/**
	 * @description find by id
	 * @param id
	 * @return Candidate
	 */
	public Candidate findByScannerLine(String id) {
		String sqlString = "select * from candidate where candidateID = ? ";
		// TODO Auto-generated method stub
		try (Connection connection = ConnectDB.getConnect();
				PreparedStatement statement = connection.prepareStatement(sqlString)) {
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			return getCandidateByResultSet(resultSet).stream().filter(x -> x.getCandidateID().equalsIgnoreCase(id))
					.findFirst().orElse(null);
		} catch (Exception e) {
			System.err.printf("Không tìm thấy ai với id %s \n", id);
		}
		return null;
	}

	/**
	 * @description filter and remove all candidate not valid properties
	 * @param resultSet
	 * @return List<Candidate>
	 * @throws SQLException
	 */
	public Set<Candidate> getCandidateByResultSet(ResultSet resultSet) throws SQLException {
		Set<Candidate> set = new LinkedHashSet<>();
		while (resultSet.next()) {
			switch (resultSet.getString("candidateType")) {
			case "1":
				Experience experience = saveExpByResultSet(resultSet);
				if (validResultSet(resultSet, experience.getProString())) {
					set.add(saveExpByResultSet(resultSet));
				}
				break;
			case "2":
				Fresher fresher = saveFresherByResultSet(resultSet);
				if (validResultSet(resultSet, fresher.getProString())) {
					set.add(fresher);
				}
				break;
			case "3":
				Intern intern = saveInternByResultSet(resultSet);
				if (validResultSet(resultSet, intern.getProString())) {
					set.add(intern);
				}
				break;
			}
		}
		return set;
	}

	/**
	 * @description save Experience by result set
	 * @param resultSet
	 * @return Experience
	 * @throws SQLException
	 */

	public Experience saveExpByResultSet(ResultSet resultSet) throws SQLException {
		return new Experience(resultSet.getString("candidateID"), resultSet.getString("fullName"),
				resultSet.getDate("birthDay"), resultSet.getString("phone"), resultSet.getString("email"),
				resultSet.getString("candidateType"),
				degreesService.findAllByCandidateID(resultSet.getString("candidateID")), resultSet.getInt("expInYear"),
				resultSet.getString("proSkill"));
	}

	/**
	 * @description save Intern by result set
	 * @param resultSet
	 * @return Intern
	 * @throws SQLException
	 */

	public Intern saveInternByResultSet(ResultSet resultSet) throws SQLException {
		return new Intern(resultSet.getString("candidateID"), resultSet.getString("fullName"),
				resultSet.getDate("birthDay"), resultSet.getString("phone"), resultSet.getString("email"),
				resultSet.getString("candidateType"),
				degreesService.findAllByCandidateID(resultSet.getString("candidateID")), resultSet.getString("majors"),
				resultSet.getString("semester"), resultSet.getString("universityName"));
	}

	/**
	 * @description save Fresher by result set
	 * @param resultSet
	 * @return Fresher
	 * @throws SQLException
	 */
	public Fresher saveFresherByResultSet(ResultSet resultSet) throws SQLException {
		return new Fresher(resultSet.getString("candidateID"), resultSet.getString("fullName"),
				resultSet.getDate("birthDay"), resultSet.getString("phone"), resultSet.getString("email"),
				resultSet.getString("candidateType"),
				degreesService.findAllByCandidateID(resultSet.getString("candidateID")),
				resultSet.getDate("graduation_day"), resultSet.getString("graduation_rank"),
				resultSet.getString("education"));
	}

	/**
	 * @description save Candidate by all properties in value of list String
	 * @param list
	 * @param getProperty
	 * @param type
	 * @return
	 */
	public void saveCandidateByListString(List<String> list, String getProperty, String type) {
		String sqString = String.format("select %s,candidateType from candidate where candidateType = %s", getProperty,
				type);
		try (Connection connection = ConnectDB.getConnect();
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {
			ResultSet resultSet = stmt.executeQuery(sqString);
			resultSet.moveToInsertRow();
			updateResultSetFromListString(getProperty, resultSet, list);
			resultSet.updateString("candidateType", type);
			resultSet.insertRow();
			resultSet.moveToCurrentRow();
			System.out.println("Thêm mới nhân viên thành công!!!");
			Candidate.canidate++;
		} catch (SQLException e) {
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
		}
	}

	/**
	 * @description update ResultSet From List String get by user do scanner
	 * @param getProperty
	 * @param resultSet
	 * @param list
	 * @return
	 */
	private void updateResultSetFromListString(String getProperty, ResultSet resultSet, List<String> list)
			throws NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		String[] string = getProperty.split(",");
		for (int i = 0; i < string.length; i++) {
			if (string[i].toLowerCase().contains("day")) {
				resultSet.updateDate(string[i], Date.valueOf(list.get(i)));
				continue;
			}

			if (string[i].toLowerCase().contains("year")) {
				resultSet.updateInt(string[i], Integer.valueOf(list.get(i)));
				continue;
			}
			resultSet.updateString(string[i], list.get(i));
		}
	}

	/**
	 * @description filter type for add Candidate From Map < String, String> get by
	 *              user do scanner
	 * @param candidateEdit
	 * @param getDataFromController
	 */
	public void updateCandidate(Candidate candidateEdit, Map<String, String> getDataFromController) {
		// TODO Auto-generated method stub
		if (candidateEdit instanceof Experience) {
			updateCandidate(getDataFromController, String.format(
					"select candidateID,fullName,birthDay,phone,email,expInYear,proSkill from candidate where candidateID = '%s'",
					candidateEdit.getCandidateID()));
			return;
		}
		if (candidateEdit instanceof Fresher) {
			updateCandidate(getDataFromController, String.format(
					"select candidateID,fullName,birthDay,phone,email,graduation_day,graduation_rank,education from candidate where candidateID = '%s' ",
					candidateEdit.getCandidateID()));
			return;
		}
		updateCandidate(getDataFromController, String.format(
				"select candidateID,fullName,birthDay,phone,email,majors,semester,universityName from candidate where candidateID = '%s'",
				candidateEdit.getCandidateID()));
	}

	/**
	 * @description update Candidate From Map < String, String> with sql String
	 * @param result
	 * @param sqlString
	 */
	private void updateCandidate(Map<String, String> result, String sqlString) {
		// TODO Auto-generated method stub
		try (Connection connection = ConnectDB.getConnect();
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
	 * @description push all entries in map into result
	 * @param result
	 * @param map
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
	 * @description filter result set get in database before add in list to show
	 * @param result
	 * @param str
	 * @return boolean
	 */
	private boolean validResultSet(ResultSet resultSet, String str) {
		try {
			String[] strings = str.split(",");
			for (String string : strings) {
				if (string.toLowerCase().contains("birthday")
						&& resultSet.getDate(string).toLocalDate().getYear() < 1900) {
					return false;
				}
				if (string.toLowerCase().contains("phone") && !resultSet.getString(string).matches("^0[0-9]{9}$"))
					return false;
				if (string.toLowerCase().contains("email")
						&& !resultSet.getString(string).matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
					return false;
				if (string.toLowerCase().contains("fullname") && !resultSet.getString(string)
						.matches("^[A-Za-z úùụũủịỉìỉĩâăôđêọòóõỏáàảãạèéẹẽẻưửữựừứốồổộỗếềểễệấầẫẩậặắẳẵằạáàảã.?!@#$%^&*]+$"))
					return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}