package main.java.com.utils;

import java.time.LocalDate;

import main.java.com.error.BirthdaySmall1900Exception;

public class CustomerValid {
	public static void checkBirthdayBefore1900(LocalDate day) throws BirthdaySmall1900Exception {
		if (day.getYear() < 1900)
			throw new BirthdaySmall1900Exception("ngày sinh không được nhỏ hơn 1900");
	}

	public static boolean validChoice(String str, String list) {
		try {
			int choiceNumber = Integer.parseInt(str);
			if (choiceNumber < 0 || choiceNumber > list.split(",").length)
				return true;
		} catch (Exception e) {
			return true;
		}
		return false;
	}
}
