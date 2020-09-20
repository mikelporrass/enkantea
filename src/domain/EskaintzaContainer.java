package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType. FIELD )
public class EskaintzaContainer {

	private User eskaintzailea;
	private Enkantea enkantea;
	private Eskaintza eskaintza;
	
	public EskaintzaContainer(Eskaintza e) {
		this.eskaintza = e;
		this.eskaintzailea = e.getEskaintzailea();
		this.enkantea = e.getEnkantea();
	}
	public EskaintzaContainer() {
		this.eskaintza = null;
		this.eskaintzailea =null;
		this.enkantea = null;
	}
	public User getEskaintzailea() {
		return eskaintzailea;
	}
	public Enkantea getEnkantea() {
		return enkantea;
	}
	public Eskaintza getEskaintza() {
		return eskaintza;
	}
}
