package businessLogic;

import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Bezero;
import domain.Enkantea;
import domain.EnkanteaContainer;
import domain.Eskaintza;
import domain.EskaintzaContainer;
import domain.Jabea;
import domain.Langile;
import domain.User;

@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	
	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}
	}

	@WebMethod
	public boolean existitzenDa(String NAN, String Pasahitza) {
		// TODO Auto-generated method stub
		DataAccess dbManager = new DataAccess();
		boolean b = dbManager.existitzenDa(NAN, Pasahitza);
		// dbManager.close();
		return b;
	}

	@WebMethod
	public User getUser(String NAN) {
		DataAccess dbManager = new DataAccess();
		User user = dbManager.getUser(NAN);
		dbManager.close();
		return user;
	}
	
	@WebMethod
	public Langile getLangile(String NAN) {
		DataAccess dbManager = new DataAccess();
		Langile user = dbManager.getLangile(NAN);
		dbManager.close();
		return user;
	}
	
	@WebMethod
	public Bezero getBezero(String NAN) {
		DataAccess dbManager = new DataAccess();
		Bezero user = dbManager.getBezero(NAN);
		dbManager.close();
		return user;
	}
	
	@WebMethod
	public Jabea getJabea(String NAN) {
		DataAccess dbManager = new DataAccess();
		Jabea user = dbManager.getJabea(NAN);
		dbManager.close();
		return user;
	}

	@WebMethod
	public Enkantea getEnkantea(Integer Id) {
		DataAccess dbManager = new DataAccess();
		Enkantea enk = dbManager.getEnkantea(Id);
		dbManager.close();
		return enk;
	}

	@WebMethod
	public boolean erregistratu(String NAN, String Pasahitza, String iz, String ab1, String ab2, String email) {

		DataAccess dbManager = new DataAccess();
		if (!existitzenDa(NAN, Pasahitza)) {
			boolean b = dbManager.erregistratu(NAN, Pasahitza, iz, ab1, ab2, email);
			dbManager.close();
			return b;
		} else {
			dbManager.close();
			return false;
		}
	}

	@WebMethod
	public Vector<Enkantea> getEnkanteak() {
		DataAccess dbManager = new DataAccess();
		Vector<Enkantea> enkateak = dbManager.getEnkanteak();
		dbManager.close();
		return enkateak;
	}

	@WebMethod
	public Vector<Enkantea> getPasatutakoEnkanteak() {
		DataAccess dbManager = new DataAccess();
		Vector<Enkantea> enkateak = dbManager.getPasatutakoEnkanteak();
		dbManager.close();
		return enkateak;
	}

	@WebMethod
	public Vector<Enkantea> getBesteenEnkanteak(String NAN) {
		DataAccess dbManager = new DataAccess();
		Vector<Enkantea> enkateak = dbManager.getBesteenEnkanteak(NAN);
		dbManager.close();
		return enkateak;
	}

	@WebMethod
	public Vector<Enkantea> getIrabazitakoEnkanteak(String NAN) {
		DataAccess dbManager = new DataAccess();
		Vector<Enkantea> enkateak = dbManager.getIrabazitakoEnkanteak(NAN);
		dbManager.close();
		return enkateak;
	}

	@WebMethod
	public Vector<Enkantea> getNireEnkanteak(String NAN) {
		DataAccess dbManager = new DataAccess();
		Vector<Enkantea> nireEnkateak = dbManager.getNireEnkanteak(NAN);
		dbManager.close();
		return nireEnkateak;
	}

	@WebMethod
	public void enkanteaSortu(User u,String deskripzioa, float prezioMin, Date amaieraData) {
		DataAccess dbManager = new DataAccess();
		dbManager.enkanteaSortu(u,deskripzioa, prezioMin, amaieraData);
		dbManager.close();

	}

	@WebMethod
	public void diruaSartu(User u,float diruZorroa) {
		DataAccess dbManager = new DataAccess();

		dbManager.diruaSartu(u,diruZorroa);
		dbManager.close();

	}

	@WebMethod
	public Vector<Eskaintza> getNireEnkantearenEskaintzak(String NAN, Integer enkanteId) {
		DataAccess dbManager = new DataAccess();
		Vector<Eskaintza> nireEnkantearenEskaintzak = dbManager.getNireEnkantearenEskaintzak(NAN, enkanteId);
		dbManager.close();
		return nireEnkantearenEskaintzak;
	}

	@WebMethod
	public boolean eskaintzaSortu(User u, float prezioa, Integer enkanteId) {
		Enkantea enk = getEnkantea(enkanteId);
		if (enk.getEgungoPrezioa() < prezioa) {
			DataAccess dbManager = new DataAccess();	
			dbManager.diruaiGaltzaileariItzuli(enkanteId);
			dbManager.eskaintzaSortu(u, prezioa, enkanteId);
			dbManager.close();
			return true;
		} else
			return false;
	}
	

	@WebMethod
	public boolean enkanteaItxi(Integer enkanteId) {
		DataAccess dbManager = new DataAccess();
		boolean b = dbManager.enkanteaItxi(enkanteId);
		Float diruMax = dbManager.EskaintzaMax(getEnkantea(enkanteId));
		DataAccess dbManager1 = new DataAccess();
		dbManager1.irabazitakoDirua(diruMax, enkanteId);
		//dbManager.close();
		return b;

	}



	@WebMethod	
	public EnkanteaContainer getEnkanteContainer(Enkantea enkantea) {
		DataAccess dbManager = new DataAccess();
		EnkanteaContainer  EnkanteaContainer = dbManager.getEnkanteContainer(enkantea);
		dbManager.close();
		return EnkanteaContainer;
	}

	@WebMethod	
	public EskaintzaContainer getEskaintzaContainer(Eskaintza eskaintza) {
		DataAccess dbManager = new DataAccess();
		EskaintzaContainer  EskaintzaContainer = dbManager.getEskaintzaContainer(eskaintza);
		dbManager.close();
		return EskaintzaContainer;
	}
	@WebMethod	
	public User userEskaintzaMax(Enkantea enk) {
		DataAccess dbManager = new DataAccess();
		User  u = dbManager.userEskaintzaMax(enk);
		dbManager.close();
		return u;
	}
}
