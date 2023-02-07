package model;

public class Certificated {
	
	private String CertificatedID;
	private String CertificateName;
	private String CertificateRank;
	private String CertificatedDate;
	
	public Certificated() {
		
	}

	public Certificated(String certificatedID, String certificateName, String certificateRank, String certificatedDate) {		
		this.CertificatedID = certificatedID;
		this.CertificateName = certificateName;
		this.CertificateRank = certificateRank;
		this.CertificatedDate = certificatedDate;
	}
	
	public String getCertificatedID() {
		return CertificatedID;
	}
	public void setCertificatedID(String certificatedID) {
		CertificatedID = certificatedID;
	}
	public String getCertificateName() {
		return CertificateName;
	}
	public void setCertificateName(String certificateName) {
		CertificateName = certificateName;
	}
	public String getCertificateRank() {
		return CertificateRank;
	}
	public void setCertificateRank(String certificateRank) {
		CertificateRank = certificateRank;
	}
	public String getCertificatedDate() {
		return CertificatedDate;
	}
	public void setCertificatedDate(String certificatedDate) {
		CertificatedDate = certificatedDate;
	}

	@Override
	public String toString() {
		return "Certificated [CertificatedID=" + CertificatedID + ", CertificateName=" + CertificateName
				+ ", CertificateRank=" + CertificateRank + ", CertificatedDate=" + CertificatedDate + "]";
	}
}