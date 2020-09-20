package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User implements Serializable {

	@Id
	@XmlID
	private String NAN;
	private String izena;
	private String abizena1;
	private String abizena2;
	private String email;
	private String pasahitza;
	private Date jaiotzeData;

	public User() {
		super();
	}

	public User(String NAN, String pass, String iz, String ab1, String ab2, String email) {
		this.NAN = NAN;
		this.pasahitza = pass;
		this.izena = iz;
		this.abizena1 = ab1;
		this.abizena2 = ab2;
		this.email = email;

	}

	public String getNAN() {
		return NAN;
	}

	public void setNAN(String nAN) {
		NAN = nAN;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena1() {
		return abizena1;
	}

	public void setAbizena1(String abizena1) {
		this.abizena1 = abizena1;
	}

	public String getAbizena2() {
		return abizena2;
	}

	public void setAbizena2(String abizena2) {
		this.abizena2 = abizena2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public Date getJaiotzeData() {
		return jaiotzeData;
	}

	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}

	@Override
	public String toString() {
		return "User [NAN=" + NAN + ", izena=" + izena + ", abizena1=" + abizena1 + ", abizena2=" + abizena2
				+ ", email=" + email + ", pasahitza=" + pasahitza + ", jaiotzeData=" + jaiotzeData + "]";
	}
}
