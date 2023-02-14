package view;

import java.sql.SQLException;

import controller.Controller;

public class Main {
	/**
	 * Hàm main chạy chương trình 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Controller.displayMainMenu();
	}
}
