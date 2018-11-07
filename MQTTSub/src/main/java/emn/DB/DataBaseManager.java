package emn.DB;


import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import emn.MQTTBroker.PropertiesManager;
import model.Measure;

public class DataBaseManager {
	
	public void putData(Measure toput) {
		CapteurdataEntity capteur = new CapteurdataEntity();
		capteur.setValue(toput.getValeur());
		capteur.setGps(toput.getGps());
		
		// On recoit un nombre de secondes, on multiplie par 1000 pour obtenir les millisecondes
		
		capteur.setTimestamp(new Timestamp(toput.getDate()));
		capteur.setType(MappingUtils.measureTypeStringToInt(toput.getType()));
		capteur.setIdCapteur(toput.getId());
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();	
        
		session.save(capteur);
		
        //Commit the transaction
        session.getTransaction().commit();
        	
		session.close();
	}
}