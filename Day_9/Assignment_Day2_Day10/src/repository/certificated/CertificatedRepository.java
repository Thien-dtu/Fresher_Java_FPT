package repository.certificated;

import java.util.List;
import java.util.Scanner;

import model.Certificated;

public interface CertificatedRepository {
	List<Certificated> findCertificateByCandidateId(String candidateId);
	public boolean addNewCertificate(String candidateId, Scanner sc);
}
