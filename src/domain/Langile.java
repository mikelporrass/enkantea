package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Langile extends User implements Serializable {

	public Langile() {
		super();
	}

	public Langile(String NAN, String pass, String iz, String ab1, String ab2, String email) {
		super(NAN, pass, iz, ab1, ab2, email);
	}

	
	
	
}
