package repository.certificated;

import java.util.List;
import java.util.Scanner;

import model.Certificated;

public interface CertificatedRepository {
	
	/**
	 * Find certificate by candidate id.
	 * 
	 * Create date : Feb 15, 2023 10:54:06 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param candidateId the candidate id
	 * @return the list
	 */
	List<Certificated> findCertificateByCandidateId(String candidateId);
	
	/**
	 * Adds the new certificate.
	 * 
	 * Create date : Feb 15, 2023 10:54:13 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param candidateId the candidate id
	 * @param sc the sc
	 * @return true, if successful
	 */
	public boolean addNewCertificate(String candidateId, Scanner sc);
}
