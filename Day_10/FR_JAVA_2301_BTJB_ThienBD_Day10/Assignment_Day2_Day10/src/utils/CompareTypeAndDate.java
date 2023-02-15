package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import model.Candidate;

public class CompareTypeAndDate implements Comparator<Candidate> {

	/**
	 * Compare.
	 * 
	 * Create date : Feb 15, 2023 10:57:25 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Candidate o1, Candidate o2) {
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthDate1 = LocalDate.parse(o1.getBirthday(), fomatter);
		LocalDate birthDate2 = LocalDate.parse(o2.getBirthday(), fomatter);

		if (o1.getCandidateType() > o2.getCandidateType()) {
			return 1;
		} else if (o1.getCandidateType() < o2.getCandidateType()) {
			return -1;
		} else {
			if (birthDate1.compareTo(birthDate2) < 0) {
				return 1;
			} else if (birthDate1.compareTo(birthDate2) > 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
