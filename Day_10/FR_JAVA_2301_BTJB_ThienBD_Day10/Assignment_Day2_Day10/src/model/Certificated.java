package model;

public class Certificated {

	private Integer certificatedId;
	private String certificatedName;
	private String certificatedRank;
	private String certificatedDate;
	private String candidateId;

	public Certificated() {

	}

	public Certificated(String certificatedName, String certificatedRank, String certificatedDate, String candidateId) {
		super();
		this.certificatedName = certificatedName;
		this.certificatedRank = certificatedRank;
		this.certificatedDate = certificatedDate;
		this.candidateId = candidateId;
	}

	public Certificated(Integer certificatedId, String certificatedName, String certificatedRank,
			String certificatedDate) {
		super();
		this.certificatedId = certificatedId;
		this.certificatedName = certificatedName;
		this.certificatedRank = certificatedRank;
		this.certificatedDate = certificatedDate;
	}
	
	public Certificated(Integer certificatedId, String certificatedName, String certificatedRank,
			String certificatedDate, String candidateId) {
		super();
		this.certificatedId = certificatedId;
		this.certificatedName = certificatedName;
		this.certificatedRank = certificatedRank;
		this.certificatedDate = certificatedDate;
		this.candidateId = candidateId;
	}

	public Integer getCertificatedId() {
		return certificatedId;
	}

	public void setCertificatedId(Integer certificatedId) {
		this.certificatedId = certificatedId;
	}

	public String getCertificatedName() {
		return certificatedName;
	}

	public void setCertificatedName(String certificatedName) {
		this.certificatedName = certificatedName;
	}

	public String getCertificatedRank() {
		return certificatedRank;
	}

	public void setCertificatedRank(String certificatedRank) {
		this.certificatedRank = certificatedRank;
	}

	public String getCertificatedDate() {
		return certificatedDate;
	}

	public void setCertificatedDate(String certificatedDate) {
		this.certificatedDate = certificatedDate;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	@Override
	public String toString() {
		return "Certificated [certificatedId=" + certificatedId + ", certificatedName=" + certificatedName
				+ ", certificatedRank=" + certificatedRank + ", certificatedDate=" + certificatedDate + ", candidateId="
				+ candidateId + "]";
	}

}