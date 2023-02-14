package models;

public class Certificate {
	private Integer certificateID;
	private String certificateName;
	private String certificateRank;
	private String certificateDate;
	private Integer candidateID;

	public Certificate(String certificateName, String certificateRank, String certificateDate, Integer candidateID) {
		super();
		this.certificateName = certificateName;
		this.certificateRank = certificateRank;
		this.certificateDate = certificateDate;
		this.candidateID = candidateID;
	}

	public Certificate(Integer certificateID, String certificateName, String certificateRank, String certificateDate) {
		super();
		this.certificateID = certificateID;
		this.certificateName = certificateName;
		this.certificateRank = certificateRank;
		this.certificateDate = certificateDate;
	}

	public Integer getCertificateID() {
		return certificateID;
	}

	public void setCertificateID(Integer certificateID) {
		this.certificateID = certificateID;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getCertificateRank() {
		return certificateRank;
	}

	public void setCertificateRank(String certificateRank) {
		this.certificateRank = certificateRank;
	}

	public String getCertificateDate() {
		return certificateDate;
	}

	public void setCertificateDate(String certificateDate) {
		this.certificateDate = certificateDate;
	}

	public Integer getCandidate() {
		return candidateID;
	}

	public void setCandidate(Integer candidate) {
		this.candidateID = candidate;
	}

	@Override
	public String toString() {
		return "MãBằng: " + certificateID + " TênBằng: " + certificateName + " XếpLoại: " + certificateRank
				+ " NgàyTN: " + certificateDate;
	}

}
