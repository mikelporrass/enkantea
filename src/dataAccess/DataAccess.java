package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import configuration.ConfigXML;

import domain.Bezero;
import domain.Enkantea;
import domain.EnkanteaContainer;
import domain.Eskaintza;
import domain.EskaintzaContainer;
import domain.Jabea;
import domain.Langile;
import domain.User;

public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c;

	public DataAccess(boolean initializeMode) {

		c = ConfigXML.getInstance();

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode)
			fileName = fileName + ";drop";

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}
	}

	public DataAccess() {
		new DataAccess(false);
	}

	public void initializeDB() {
		db.getTransaction().begin();
		try {

			// Admin Juan = new Admin("","","","Juan","Juan","","","","","","","","","");

			// Admin Admin = new Admin("","","","Admin","Admin","","","","","","","","","");
			User b1 = new Bezero("bezero1", "bezero1", "Gorka", "Porras", "Larrinaga", "gmail.com");
			User b2 = new Bezero("bezero2", "bezero2", "Ibon", "Zatika", "Orue", "gmail.com");
			Langile l1 = new Langile("langile", "langile", "Mikel", "Porras", "Larrinaga", "gmail.com");
			User j1 = new Jabea("jabe1", "jabe1", "Markel", "Markel", "Markel", "gmail.com", 103);
			User j2 = new Jabea("jabe2", "jabe2", "Aitor", "Aitor", "Aitor", "gmail.com", 30);

			// Enkatean irekita
			Enkantea enkantea1 = ((Jabea) j1).addEnkantea("Tesla", 0, 3000, newDate(2020, 2, 1), false);
			Enkantea enkantea2 = ((Jabea) j1).addEnkantea("Ordenagailua", 0, 2000, newDate(2020, 2, 1), false);
			Enkantea enkantea3 = ((Jabea) j2).addEnkantea("Mobila", 0, 300, newDate(2019, 7, 1), false);
			Enkantea enkantea4 = ((Jabea) j2).addEnkantea("kalkulagailua", 0, 30, newDate(2020, 2, 1), false);

			// pasatuta itxi gabe
			Enkantea enkantea5 = ((Jabea) j2).addEnkantea("Pantaila", 0, 100, newDate(2019, 4, 1), false);// pasatuta
																											// itxi gabe

			// pasatuta itxi
			Enkantea enkantea6 = ((Jabea) j1).addEnkantea("irabazi", 0, 5, newDate(2019, 1, 1), true);

			/*
			 * 
			 * Eskaintza e2 = ((Bezero) b2).addEskaintza(14, enkantea1); Eskaintza e3 =
			 * ((Bezero) b1).addEskaintza(30, enkantea1); Eskaintza e4 = ((Bezero)
			 * b1).addEskaintza(30, enkantea4);
			 * 
			 * enkantea1.addEskaintza(e1); enkantea1.addEskaintza(e2);
			 * enkantea2.addEskaintza(e3); enkantea4.addEskaintza(e4);
			 * 
			 * ((Bezero) b2).addEskaintza(14, enkantea2);
			 * 
			 * ((Bezero) b2).addEskaintza(e4);
			 */
			Eskaintza e1 = ((Bezero) b1).addEskaintza(2000, enkantea1);
			enkantea1.addEskaintza(e1);
			((Bezero) b2).addEskaintza(e1);

			Eskaintza e2 = ((Bezero) b1).addEskaintza(100, enkantea5);
			enkantea5.addEskaintza(e2);
			((Bezero) b2).addEskaintza(e2);

			db.persist(b1);
			db.persist(b2);
			db.persist(l1);

			db.persist(j1);

			db.persist(enkantea1);
			db.persist(enkantea2);
			db.persist(j2);
			db.persist(enkantea3);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean existitzenDa(String NAN, String Pasahitza) {
		TypedQuery<User> query = db.createQuery("SELECT u FROM User u WHERE u.NAN=?1 AND u.pasahitza=?2", User.class);
		query.setParameter(1, NAN);
		query.setParameter(2, Pasahitza);
		List<User> users = query.getResultList();

		if (users.isEmpty())
			return false;
		else
			return true;
	}

	public User getUser(String NAN) {
		User us = db.find(User.class, NAN);
		return us;
	}

	public Langile getLangile(String NAN) {
		Langile us = db.find(Langile.class, NAN);
		return us;
	}

	public Bezero getBezero(String NAN) {
		Bezero us = db.find(Bezero.class, NAN);
		return us;
	}

	public Jabea getJabea(String NAN) {
		Jabea us = db.find(Jabea.class, NAN);
		return us;
	}

	public Enkantea getEnkantea(Integer Id) {
		Enkantea enk = db.find(Enkantea.class, Id);
		return enk;
	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public void irabazitakoDirua(Float prezioa, Integer enkanteId) {
		Enkantea enk = getEnkantea(enkanteId);
		Jabea u = enk.getJabea();

		db.getTransaction().begin();
		// db.persist(u);
		u.setDiruZorroa(u.getDiruZorroa() + prezioa);
		db.getTransaction().commit();
	}

	public boolean erregistratu(String NAN, String pasahitza, String iz, String ab1, String ab2, String email) {
		db.getTransaction().begin();
		if (db.find(User.class, NAN) == null) {
			Bezero bezero = new Bezero(NAN, pasahitza, iz, ab1, ab2, email);

			db.persist(bezero);
			db.getTransaction().commit();
			return true;
		} else
			return false;

	}

	public boolean enkanteaItxi(Integer enkanteaId) {
		Enkantea enk = getEnkantea(enkanteaId);
		boolean b = true;
		if (!enk.getItxita()) {
			db.getTransaction().begin();
			enk.setItxita(true);
			db.persist(enk);
			db.getTransaction().commit();
		} else
			b = false;
		return b;
	}

	public Vector<Enkantea> getEnkanteak() {
		Vector<Enkantea> enkanteZerrenda = new Vector<Enkantea>();
		TypedQuery<Enkantea> query = db.createQuery("SELECT en FROM Enkantea en where en.itxita=false", Enkantea.class);
		List<Enkantea> enkanteak = query.getResultList();
		for (Enkantea enkantea : enkanteak) {
			System.out.println(enkantea.toString());
			enkanteZerrenda.add(enkantea);
		}
		return enkanteZerrenda;

	}

	public Vector<Enkantea> getPasatutakoEnkanteak() {

		Vector<Enkantea> enkanteZerrenda = new Vector<Enkantea>();
		TypedQuery<Enkantea> query = db.createQuery("SELECT en FROM Enkantea en where en.amaieraData<?1 ",
				Enkantea.class);
		query.setParameter(1, new Date());
		List<Enkantea> enkanteak = query.getResultList();
		for (Enkantea enkantea : enkanteak) {
			System.out.println(enkantea.toString());
			enkanteZerrenda.add(enkantea);
		}
		return enkanteZerrenda;

	}

	public Vector<Enkantea> getBesteenEnkanteak(String NAN) {
		Vector<Enkantea> enkanteZerrenda = new Vector<Enkantea>();
		TypedQuery<Enkantea> query = db.createQuery(
				"SELECT en FROM Enkantea en where en.itxita=false AND en.jabea.NAN!=?1 AND en.amaieraData>?2",
				Enkantea.class);
		query.setParameter(1, NAN);
		query.setParameter(2, new Date());
		List<Enkantea> enkanteak = query.getResultList();
		for (Enkantea enkantea : enkanteak) {
			System.out.println(enkantea.toString());
			enkanteZerrenda.add(enkantea);
		}
		return enkanteZerrenda;

	}

	public Vector<Enkantea> getIrabazitakoEnkanteak(String NAN) {
		Vector<Enkantea> enkanteZerrenda = new Vector<Enkantea>();
		TypedQuery<Enkantea> query = db.createQuery(
				"SELECT en FROM Enkantea en where en.itxita=true AND en.enkanteEskaintza.eskaintzailea.NAN=?1",
				Enkantea.class);
		query.setParameter(1, NAN);
		List<Enkantea> enkanteak = query.getResultList();

		for (Enkantea enkantea : enkanteak) {
			if (NAN.equals(userEskaintzaMax(enkantea).getNAN())) {
				System.out.println(enkantea.toString());
				enkanteZerrenda.add(enkantea);
			}
		}
		return enkanteZerrenda;

	}

	public User userEskaintzaMax(Enkantea enk) {
		Vector<Eskaintza> eskaintzak = enk.getEskaintza();
		float eskaitzaMax = 0;
		User u = null;
		for (Eskaintza esk : eskaintzak) {
			if (esk.getPrezioa() > eskaitzaMax) {
				eskaitzaMax = esk.getPrezioa();
				u = esk.getEskaintzailea();
			}
		}

		return u;

	}

	public Float eskaintzaMax(Enkantea enk) {
		Vector<Eskaintza> eskaintzak = enk.getEskaintza();
		float eskaitzaMax = 0;
		for (Eskaintza esk : eskaintzak) {
			if (esk.getPrezioa() > eskaitzaMax) {
				eskaitzaMax = esk.getPrezioa();
			}
		}

		return eskaitzaMax;

	}

	public Vector<Enkantea> getNireEnkanteak(String NAN) {
		Vector<Enkantea> NireEnkanteZerrenda = new Vector<Enkantea>();
		TypedQuery<Enkantea> query = db.createQuery("SELECT en FROM Enkantea en where en.jabea.NAN=?1", Enkantea.class);
		query.setParameter(1, NAN);
		List<Enkantea> NireEnkanteak = query.getResultList();

		for (Enkantea NireEnkantea : NireEnkanteak) {
			System.out.println(NireEnkantea.toString());
			NireEnkanteZerrenda.add(NireEnkantea);
		}
		return NireEnkanteZerrenda;

	}

	public void enkanteaSortu(User u, String deskripzioa, float prezioMin, Date amaieraData) {

		db.getTransaction().begin();
		User user = db.find(User.class, u);
		Enkantea enkantea = null;
		if (user instanceof Bezero) {
			// deleteEskaintzak(user);
			User GordeUser = new Jabea(user.getNAN(), user.getPasahitza(), user.getIzena(), user.getAbizena1(),
					user.getAbizena2(), user.getEmail(), ((Bezero) user).getDiruZorroa());

			Vector<Eskaintza> eskaintzak = ((Bezero) user).getBezeroEskaintza();

			for (Eskaintza es : eskaintzak) {// jabeari eta enkanteari eskaintzak ipini
				((Jabea) GordeUser).addEskaintza(es.getPrezioa(), es.getEnkantea());
				es.getEnkantea().addEskaintza(es.getPrezioa(), GordeUser);
			}

			db.remove(user);
			db.persist(GordeUser);
			enkantea = ((Jabea) GordeUser).addEnkantea(deskripzioa, prezioMin, prezioMin, amaieraData, false);

		} else {

			enkantea = ((Jabea) user).addEnkantea(deskripzioa, prezioMin, prezioMin, amaieraData, false);
		}
		db.persist(enkantea);

		db.getTransaction().commit();

	}

	public void diruaSartu(User u, float diruZorroa) {
		float hasierakoDirua = 0;
		User user = db.find(User.class, u);
		if (user instanceof Bezero) {
			hasierakoDirua = ((Bezero) user).getDiruZorroa();
			((Bezero) user).setDiruZorroa(diruZorroa + hasierakoDirua);
		} else {
			hasierakoDirua = ((Jabea) user).getDiruZorroa();
			((Jabea) user).setDiruZorroa(diruZorroa + hasierakoDirua);
		}

		db.getTransaction().begin();
		db.persist(user);
		db.getTransaction().commit();

	}

	public Vector<Eskaintza> getNireEnkantearenEskaintzak(String NAN, Integer enkanteId) {
		Vector<Eskaintza> eskaintzenZerrenda = new Vector<Eskaintza>();
		TypedQuery<Eskaintza> query = db.createQuery(
				"SELECT es FROM Eskaintza es where es.enkantea.jabea.NAN=?1 AND es.enkantea.enkanteId=?2",
				Eskaintza.class);
		query.setParameter(1, NAN);
		query.setParameter(2, enkanteId);
		List<Eskaintza> eskaintzak = query.getResultList();
		for (Eskaintza eskaintza : eskaintzak) {
			System.out.println(eskaintza.toString());
			eskaintzenZerrenda.add(eskaintza);
		}
		return eskaintzenZerrenda;
	}

	public void eskaintzaSortu(User u, float prezioa, Integer enkanteId) {
		User user = db.find(User.class, u);
		Enkantea enk = getEnkantea(enkanteId);

		// diruaiGaltzaileariItzuli(enk);
		enk.setEgungoPrezioa(prezioa);
		Eskaintza esk = new Eskaintza(prezioa, enk, user);

		if (user instanceof Bezero) {
			((Bezero) user).addEskaintza(esk);
			enk.addEskaintza(esk);
			((Bezero) user).setDiruZorroa(((Bezero) user).getDiruZorroa() - prezioa);

		} else {
			((Jabea) user).addEskaintza(esk);
			enk.addEskaintza(esk);
			((Jabea) user).setDiruZorroa(((Jabea) user).getDiruZorroa() - prezioa);
		}

		db.getTransaction().begin();

		db.persist(esk);
		db.getTransaction().commit();

	}

	public void diruaiGaltzaileariItzuli(Integer id) {
		Enkantea enk = getEnkantea(id);
		// JOptionPane.showMessageDialog(null, enk.getEnkanteId(), "Login Error",
		// JOptionPane.INFORMATION_MESSAGE);
		if (enk.getEskaintza().size() != 0) {
			User u = userEskaintzaMax(enk);
			Float dirua = (eskaintzaMax(enk));
			// JOptionPane.showMessageDialog(null, dirua +" diru max", "Login Error",
			// JOptionPane.INFORMATION_MESSAGE);
			if (u instanceof Bezero)
				((Bezero) u).setDiruZorroa(((Bezero) u).getDiruZorroa() + dirua);
			else
				((Jabea) u).setDiruZorroa(((Jabea) u).getDiruZorroa() + dirua);

		}
	}

	private Date newDate(int year, int month, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public EnkanteaContainer getEnkanteContainer(Enkantea enkantea) {
		Enkantea en = db.find(Enkantea.class, enkantea.getEnkanteId());
		EnkanteaContainer EC = new EnkanteaContainer(en);
		return EC;
	}

	public EskaintzaContainer getEskaintzaContainer(Eskaintza eskaintza) {
		Eskaintza es = db.find(Eskaintza.class, eskaintza.getEskaintzaId());
		EskaintzaContainer EC = new EskaintzaContainer(es);
		return EC;
	}

	public Float EskaintzaMax(Enkantea enk) {
		Vector<Eskaintza> eskaintzak = enk.getEskaintza();
		float eskaitzaMax = 0;
		for (Eskaintza esk : eskaintzak) {
			if (esk.getPrezioa() > eskaitzaMax) {
				eskaitzaMax = esk.getPrezioa();
			}
		}

		return eskaitzaMax;

	}

/*	public void deleteEskaintzak(User u) {
		Vector<Enkantea> enkantea = getEnkanteak();
		for (Enkantea enk : enkantea) {
			for (Eskaintza esk : enk.getEnkanteEskaintza()) {
				if (esk.getEskaintzailea() == u)

					db.remove(esk);

			}
		}

	}*/

}
