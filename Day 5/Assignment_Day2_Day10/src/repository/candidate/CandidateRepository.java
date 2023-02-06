package repository.candidate;

import java.util.List;

import model.Candidate;

public interface CandidateRepository {
	List<Candidate> findAll();
	boolean themMoi(Candidate candidate);
	Candidate findByiD(String id);
	boolean updateWithID(Candidate candidate);
	boolean deleteWithID(String id);
	boolean checkConnection();
}
