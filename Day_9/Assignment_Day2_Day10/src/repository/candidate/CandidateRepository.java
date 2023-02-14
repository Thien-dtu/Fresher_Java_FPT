package repository.candidate;

import java.util.List;
import java.util.Scanner;

import model.Candidate;

public interface CandidateRepository {
	List<Candidate> findAll();
	boolean themMoi(Candidate candidate, Scanner sc);
	boolean addMoreCer(Candidate candidate, Scanner sc);
	Candidate findByid(String id);
	boolean updateWithId(Candidate candidate);
	boolean deleteWithId(String id);
	StringBuffer getFullName();
	boolean deleteDupliCefId();
	boolean deleCefId();
}
