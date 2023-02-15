package repository.candidate;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Candidate;

public interface CandidateRepository {
	
	/**
	 * Find all.
	 * 
	 * Create date : Feb 15, 2023 10:40:14 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the list
	 */
	List<Candidate> findAll();
	
	/**
	 * Them moi.
	 * 
	 * Create date : Feb 15, 2023 10:40:18 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param candidate the candidate
	 * @param sc the sc
	 * @return true, if successful
	 */
	boolean themMoi(Candidate candidate, Scanner sc);
	
	/**
	 * Adds the more cer.
	 * 
	 * Create date : Feb 15, 2023 10:40:23 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param candidate the candidate
	 * @param sc the sc
	 * @return true, if successful
	 */
	boolean addMoreCer(Candidate candidate, Scanner sc);
	
	/**
	 * Find byid.
	 * 
	 * Create date : Feb 15, 2023 10:40:28 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param id the id
	 * @return the candidate
	 */
	Candidate findByid(String id);
	
	/**
	 * Update candidate.
	 * 
	 * Create date : Feb 15, 2023 10:40:36 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param candidateEdit the candidate edit
	 * @param getDataFromController the get data from controller
	 */
	void updateCandidate(Candidate candidateEdit, Map<String, String> getDataFromController);
	
	/**
	 * Delete with id.
	 * 
	 * Create date : Feb 15, 2023 10:40:41 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	boolean deleteWithId(String id);
	
	/**
	 * Gets the full name.
	 * 
	 * Create date : Feb 15, 2023 10:43:02 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the full name
	 */
	StringBuffer getFullName();
	
	/**
	 * Delete dupli cef id.
	 * 
	 * Create date : Feb 15, 2023 10:43:08 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return true, if successful
	 */
	boolean deleteDupliCefId();
	
	/**
	 * Dele cef id.
	 * 
	 * Create date : Feb 15, 2023 10:43:14 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return true, if successful
	 */
	boolean deleCefId();
	
	/**
	 * Sort candidate.
	 * 
	 * Create date : Feb 15, 2023 10:43:19 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the list
	 */
	List<Candidate> sortCandidate();
}
