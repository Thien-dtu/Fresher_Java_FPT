package utils;

public class DisplayViewForUser {

	/**
	 * Display menu from data list.
	 * 
	 * Create date : Feb 15, 2023 10:57:56 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param data the data
	 */
	public void displayMenuFromDataList(String data) {
		int index = 0;
		System.out.println("Moi ban nhap chuc nang can thao tac: ");
		String[] strings = data.split(",");
		for (String string : strings) {
			System.out.printf("%d. %s\n", ++index, string);
		}
		System.out.printf("Sự lựa chọn của bạn là ");
	}
	
	/**
	 * Coverter string choice from number.
	 * 
	 * Create date : Feb 15, 2023 10:58:02 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param choice the choice
	 * @param list the list
	 * @return the string
	 */
	public static String coverterStringChoiceFromNumber(String choice, String list) {
		String[] strings = list.split(",");
		return strings[Integer.parseInt(choice) - 1];
	}
}
