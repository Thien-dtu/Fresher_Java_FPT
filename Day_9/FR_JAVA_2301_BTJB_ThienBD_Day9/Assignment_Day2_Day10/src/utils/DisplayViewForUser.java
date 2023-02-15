package utils;

public class DisplayViewForUser {

	public void displayMenuFromDataList(String data) {
		int index = 0;
		System.out.println("Moi ban nhap chuc nang can thao tac: ");
		String[] strings = data.split(",");
		for (String string : strings) {
			System.out.printf("%d. %s\n", ++index, string);
		}
		System.out.printf("Sự lựa chọn của bạn là ");
	}
}
