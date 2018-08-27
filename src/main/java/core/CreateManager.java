package core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateManager {
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	
	
	public static EntityManager GetEntityManager(){
		
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("store");
		}
		
		if(manager == null) {
			manager = factory.createEntityManager();
		}
		return manager;
	}


	
	
}
