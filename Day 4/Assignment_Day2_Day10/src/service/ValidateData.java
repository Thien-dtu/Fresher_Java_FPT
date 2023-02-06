package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateData {
	private Matcher matcher;

	private static final String REGEX_DATE = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$"
			+ "|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$"
			+ "|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

	private static final String REGEX_EMAIL = "^[a-zA-Z][a-z0-9_\\.]{4,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";

	private static final String REGEX_PHONE = "^(\\+84|0[35789])+([0-9]{8,9})$";

	public boolean validateDate(String regex) {
		matcher = Pattern.compile(REGEX_DATE).matcher(regex);
		return matcher.matches();
	}

	public boolean validateEmail(String regex) {
		matcher = Pattern.compile(REGEX_EMAIL).matcher(regex);
		return matcher.matches();
	}

	public boolean validatePhone(String regex) {
		matcher = Pattern.compile(REGEX_PHONE).matcher(regex);
		return matcher.matches();
	}
}
