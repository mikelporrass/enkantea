package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Enkantea implements Serializable {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id@ GeneratedValue
	private Integer enkanteId;
	private String deskripzioa;
	private float egungoPrezioa;
	private float prezioMin;
	private Date amaieraData;
	private Boolean itxita;

	//@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST,cascade = CascadeType.REMOVE)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Eskaintza> enkanteEskaintza = new Vector<Eskaintza>();

	@XmlIDREF
	private Jabea jabea;

	public Enkantea() {
		super();
		
	}

	public Enkantea(Integer enkanteId, String deskripzioa, float egungoPrezioa, float prezioMin, Date amaieraData,
			Boolean itxita, Jabea jabea) {

		this.enkanteId = enkanteId;
		this.deskripzioa = deskripzioa;
		this.egungoPrezioa = egungoPrezioa;
		this.prezioMin = prezioMin;
		this.amaieraData = amaieraData;
		this.itxita = itxita;
		this.jabea = jabea;
	}

	public Enkantea(String deskripzioa, float egungoPrezioa, float prezioMin, Date amaieraData, Boolean itxita,
			Jabea jabea) {

		this.deskripzioa = deskripzioa;
		if (prezioMin >= egungoPrezioa)
			this.egungoPrezioa = prezioMin;
		else
			this.egungoPrezioa = egungoPrezioa;
		this.prezioMin = prezioMin;

		this.amaieraData = amaieraData;
		this.itxita = itxita;
		this.jabea = jabea;
	}

	public Eskaintza addEskaintza(Eskaintza es) {
		enkanteEskaintza.add(es);
		return es;
	}

	public Eskaintza addEskaintza(float prezioa, User user) {
		Eskaintza es = new Eskaintza(prezioa, this, user);
		enkanteEskaintza.add(es);
		return es;
	}

	public Integer getEnkanteId() {
		return enkanteId;
	}

	public void setEnkanteId(Integer enkanteId) {
		this.enkanteId = enkanteId;
	}

	public String getDeskripzioa() {
		return deskripzioa;
	}

	public void setDeskripzioa(String deskripzioa) {
		this.deskripzioa = deskripzioa;
	}

	public float getEgungoPrezioa() {
		return egungoPrezioa;
	}

	public void setEgungoPrezioa(float egungoPrezioa) {
		this.egungoPrezioa = egungoPrezioa;
	}

	public float getPrezioMin() {
		return prezioMin;
	}

	public void setPrezioMin(float prezioMin) {
		this.prezioMin = prezioMin;
	}

	public Date getAmaieraData() {
		return amaieraData;
	}

	public void setAmaieraData(Date amaieraData) {
		this.amaieraData = amaieraData;
	}

	public Boolean getItxita() {
		return itxita;
	}

	public void setItxita(Boolean itxita) {
		this.itxita = itxita;
	}

	public Vector<Eskaintza> getEskaintza() {
		return enkanteEskaintza;
	}

	public void setEskaintza(Vector<Eskaintza> eEskaintza) {
		this.enkanteEskaintza = eEskaintza;
	}

	public Jabea getJabea() {
		return jabea;
	}

	public void setJabea(Jabea jabea) {
		this.jabea = jabea;
	}
	public Vector<Eskaintza> getEnkanteEskaintza() {
		return enkanteEskaintza;
	}

	public void setEnkanteEskaintza(Vector<Eskaintza> enkanteEskaintza) {
		this.enkanteEskaintza = enkanteEskaintza;
	}
}
