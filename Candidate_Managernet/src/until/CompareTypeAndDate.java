package until;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import models.Candidate;

public class CompareTypeAndDate implements Comparator<Candidate> {

	/**
	 * Sắp xếp ứng viên theo Loại ứng viên và năm sinh
	 * 
	 * @author DucNH59
	 * @date 2023-02-07
	 * @return
	 */
	@Override
	public int compare(Candidate o1, Candidate o2) {
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthDate1 = LocalDate.parse(o1.getBirthDay(), fomatter);
		LocalDate birthDate2 = LocalDate.parse(o2.getBirthDay(), fomatter);

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
