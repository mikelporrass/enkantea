package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Eskaintza implements Serializable {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer eskaintzaId;
	private float prezioa;
	
	@XmlIDREF
	private User eskaintzailea;
	
	@XmlIDREF
	private Enkantea enkantea;

	public Eskaintza(float prezioa, Enkantea enkantea, User eskaintzailea) {
		super();
		this.prezioa = prezioa;
		this.eskaintzailea = eskaintzailea;
		this.enkantea = enkantea;
	}

	public Eskaintza(Integer eskaintzaId, float prezioa, Enkantea enkantea, User eskaintzailea) {
		super();
		this.eskaintzaId = eskaintzaId;
		this.prezioa = prezioa;
		this.eskaintzailea = eskaintzailea;
		this.enkantea = enkantea;
	}
	public void setEskaintzailea(User eskaintzailea) {
		this.eskaintzailea = eskaintzailea;
	}
	public Eskaintza() {
		super();
	}

	public Integer getEskaintzaId() {
		return eskaintzaId;
	}

	public void setEskaintzaId(Integer eskaintzaId) {
		this.eskaintzaId = eskaintzaId;
	}

	public float getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(float prezioa) {
		this.prezioa = prezioa;
	}

	public User getEskaintzailea() {
		return eskaintzailea;
	}

	public void SetEskaintzailea(User eskaintzailea) {
		this.eskaintzailea = eskaintzailea;
	}

	public Enkantea getEnkantea() {
		return enkantea;
	}

	public void setEnkantea(Enkantea enkantea) {
		this.enkantea = enkantea;
	}

}
