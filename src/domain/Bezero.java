package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bezero extends User implements Serializable {

	private float diruZorroa;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Vector<Eskaintza> bezeroEskaintza = new Vector<Eskaintza>();

	public Bezero() {
		super();
		this.diruZorroa = 0;
	}
 
	public Bezero(String NAN, String pass, String iz, String ab1, String ab2, String email) {
		super(NAN, pass, iz, ab1, ab2, email);
		this.diruZorroa = 0;
	}

	public Eskaintza addEskaintza(float prezioa, Enkantea enkantea) {
		Eskaintza es = new Eskaintza(prezioa, enkantea, this);
		bezeroEskaintza.add(es);
		return es;
	}

	public Eskaintza addEskaintza(Eskaintza es) {
		bezeroEskaintza.add(es);
		return es;
	}

	public Vector<Eskaintza> getBezeroEskaintza() {
		return bezeroEskaintza;
	}

	public void setBezeroEskaintza(Vector<Eskaintza> bezeroEskaintza) {
		this.bezeroEskaintza = bezeroEskaintza;
	}

	public float getDiruZorroa() {
		return diruZorroa;
	}

	public void setDiruZorroa(float diruZorroa) {
		this.diruZorroa = diruZorroa;
	}

}
