package main.java.com.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class Degree {
	String certificatedID;
	String certificateName;
	String certificateRank;
	Date certificatedDate;

	public static String getProperties = "Certificated ID,Certificate Name,Certificate Rank,Certificated Day";

	public Degree(String certificatedID, String certificateName, String certificateRank, Date certificatedDate) {
		this.certificatedID = certificatedID;
		this.certificateName = certificateName;
		this.certificateRank = certificateRank;
		this.certificatedDate = certificatedDate;
	}

	public Degree(List<String> list) {
		this.certificatedID = list.get(0);
		this.certificateName = list.get(1);
		this.certificateRank = list.get(2);
		this.certificatedDate = Date.valueOf(list.get(3));
	}

	public String getCertificatedID() {
		return certificatedID;
	}

	public void setCertificatedID(String certificatedID) {
		this.certificatedID = certificatedID;
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

	public Date getCertificatedDate() {
		return certificatedDate;
	}

	public void setCertificatedDate(Date certificatedDate) {
		this.certificatedDate = certificatedDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Degrees[id: %s, name: %s, rank: %s, date: %s]", getCertificatedID(), getCertificateName(),
				getCertificateRank(), new SimpleDateFormat("dd/MM/yyyy").format(getCertificatedDate()));
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return getCertificatedID().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Degree) {
			Degree another = (Degree) obj;
			return this.getCertificatedID().equals(another.getCertificatedID());
		}
		return false;
	}

}
