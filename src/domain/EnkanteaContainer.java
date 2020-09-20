package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType. FIELD )

public class EnkanteaContainer {
	private Enkantea enkantea;
	private Jabea jabea;
	
	public EnkanteaContainer(Enkantea e) {
		this.enkantea = e;
		this.jabea = e.getJabea();
	}
	public EnkanteaContainer() {
		enkantea = null;
		jabea = null;
	}
	public Enkantea getEnkantea() {
		return enkantea;
	}
	public Jabea getJabea() {
		return jabea;
	}
}
