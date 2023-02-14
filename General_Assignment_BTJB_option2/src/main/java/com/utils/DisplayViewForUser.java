package main.java.com.utils;

import java.util.List;

import main.java.com.entities.Degree;

public class DisplayViewForUser {
	public void displayMenuFromDataList(String data) {
		int index = 0;
		String[] strings = data.split(",");
		for (String string : strings) {
			System.out.printf("%d. %s\n", ++index, string);
		}
		System.out.printf("Sự lựa chọn của bạn là ");
	}

	public static String displayListDegreeOfCandidate(List<Degree> list) {
		if (list.isEmpty())
			return "\nChưa cập nhật!";
		StringBuilder builder = new StringBuilder();
		list.forEach(x -> builder.append("\n").append(x.toString()));
		return builder.toString();
	}

	public static String coverterStringChoiceFromNumber(String choice, String list) {
		String[] strings = list.split(",");
		return strings[Integer.parseInt(choice) - 1];
	}

}
