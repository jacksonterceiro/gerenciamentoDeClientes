package core;

import javax.persistence.EntityManager;

public class Core {

	public static EntityManager manager;
	
	public static void initAppCliente() {
		manager = CreateManager.GetEntityManager();
	}
}
