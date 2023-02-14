package repository.candidate;

import java.util.List;

import model.Candidate;

public interface CandidateRepository {
	List<Candidate> findAll();
	boolean themMoi(Candidate candidate);
	Candidate findByid(String id);
	boolean updateWithId(Candidate candidate);
	boolean deleteWithId(String id);
	boolean checkConnection();
}
