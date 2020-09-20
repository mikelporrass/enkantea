package businessLogic;


import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Bezero;
import domain.Enkantea;
import domain.EnkanteaContainer;
import domain.Eskaintza;
import domain.EskaintzaContainer;
import domain.Jabea;
import domain.Langile;
import domain.User;

@WebService
public interface BLFacade {
	
	 
	@WebMethod public User getUser(String NAN);
	
	@WebMethod public Langile getLangile(String NAN);
	
	@WebMethod public Bezero getBezero(String NAN);
	
	@WebMethod public Jabea getJabea(String NAN);
	
	@WebMethod public Enkantea getEnkantea(Integer Id);

	@WebMethod public boolean existitzenDa(String NAN, String Pasahitza);
	
	@WebMethod public boolean erregistratu(String NAN, String Pasahitza,String iz, String ab1, String ab2, String email);
	
	@WebMethod public Vector<Enkantea> getEnkanteak();
	
	@WebMethod public Vector<Enkantea> getBesteenEnkanteak(String NAN);
	
	@WebMethod public Vector<Enkantea> getNireEnkanteak(String NAN);

	@WebMethod public void enkanteaSortu( User u,String deskripzioa, float prezioMin, Date amaieraData);
	
	@WebMethod public void diruaSartu(User u,float diruZorroa);
	
	@WebMethod public Vector<Eskaintza> getNireEnkantearenEskaintzak(String NAN,Integer enkanteId);

	@WebMethod public boolean eskaintzaSortu(User u,float prezioa,Integer enkanteId);
	
	@WebMethod public Vector<Enkantea> getIrabazitakoEnkanteak(String NAN);
	
	@WebMethod public Vector<Enkantea> getPasatutakoEnkanteak();
	
	@WebMethod public boolean enkanteaItxi(Integer enkanteId);
	
	@WebMethod public User userEskaintzaMax(Enkantea enk) ;
	
	//----- CONTAINER

	@WebMethod public EnkanteaContainer getEnkanteContainer(Enkantea enkantea);
	@WebMethod public EskaintzaContainer getEskaintzaContainer(Eskaintza eskaintza);


	
	
}
///String NAN, String iz, String ab1, String ab2, String email, String pass, Date jaiotzeData,float diruZorroa 