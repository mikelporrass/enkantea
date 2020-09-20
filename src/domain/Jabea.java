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
public class Jabea extends User implements Serializable{
	
	private float diruZorroa;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Vector<Enkantea> enkantea=new Vector<Enkantea>();

	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Vector<Eskaintza> jabeEskaintza=new Vector<Eskaintza>();
	
	
	public Jabea() {
		super();
		this.diruZorroa = 0;
	}
	
	public Jabea(String NAN, String pass, String iz, String ab1, String ab2, String email,float diruZorroa) {
		super(NAN, pass, iz, ab1, ab2, email);
		this.diruZorroa=diruZorroa;
	}
	
	public Vector<Enkantea> getEnkantea() {
		return enkantea;
	}

	public void setEnkantea(Vector<Enkantea> enkantea) {
		this.enkantea = enkantea;
	}

	public Vector<Eskaintza> getJabeEskaintza() {
		return jabeEskaintza;
	}

	public void setJabeEskaintza(Vector<Eskaintza> jabeEskaintza) {
		this.jabeEskaintza = jabeEskaintza;
	}

	

	public Eskaintza addEskaintza( float prezioa,Enkantea enkantea)  {
		Eskaintza es= new Eskaintza(prezioa,enkantea, this);
		jabeEskaintza.add(es);
		return es;
	}
	public Eskaintza addEskaintza(Eskaintza es)    {
		jabeEskaintza.add(es);
		return es;
	}
	

	public Enkantea addEnkantea(String deskripzioa, float egungoPrezioa, float prezioMin, Date amaieraData,
			Boolean itxita)  {
		Enkantea r= new Enkantea(deskripzioa,  egungoPrezioa,  prezioMin,  amaieraData,itxita, this);
		enkantea.add(r);
		return r;
	}
	
	public float getDiruZorroa() {
		return diruZorroa;
	}

	public void setDiruZorroa(float diruZorroa) {
		this.diruZorroa = diruZorroa;
	}
	
}