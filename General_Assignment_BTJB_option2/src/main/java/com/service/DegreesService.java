package main.java.com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import main.java.com.entities.Degree;
import main.java.com.utils.ConnectDB;

public class DegreesService {

	/**
	 * @author TuanLN6
	 * @date 10/02/2023
	 * @description save all element in set Degree into database with candidateID =
	 *              string
	 * @param degrees
	 * @param string
	 */
	public void saveDegrees(Set<Degree> degrees, String string) {
		// TODO Auto-generated method stub
		try (Connection connection = ConnectDB.getConnect();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {
			ResultSet resultSet = statement.executeQuery("select * from degees");
			for (Degree degree : degrees) {
				System.out.println(degree.toString());
				resultSet.moveToInsertRow();
				resultSet.updateString("certificatedID", degree.getCertificatedID());
				resultSet.updateString("certificateName", degree.getCertificateName());
				resultSet.updateString("certificateRank", degree.getCertificateRank());
				resultSet.updateDate("certificatedDay", degree.getCertificatedDate());
				resultSet.updateString("candidateID", string);
				resultSet.insertRow();
				resultSet.moveToCurrentRow();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
		}
		System.out.println("Đã thêm mới bằng cấp thành công cho " + string);
	}

	/**
	 * @author TuanLN6
	 * @date 10/02/2023
	 * @description find all degrees get from database with candidateID = string
	 * @param string
	 * @return List<Degree>
	 */

	public List<Degree> findAllByCandidateID(String id) {
		String sql = "select * from degees where candidateID = ? ";
		List<Degree> list = new ArrayList<>();
		try (Connection connection = ConnectDB.getConnect();
				PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE)) {
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Degree degree = new Degree(resultSet.getString("certificatedID"),
						resultSet.getString("certificateName"), resultSet.getString("certificateRank"),
						resultSet.getDate("certificatedDay"));
				list.add(degree);
			}
			return list;
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("The system has encountered an unexpected problem, sincerely sorry !!!");
		}
		return list;
	}

}
