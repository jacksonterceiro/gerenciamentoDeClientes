package dao;

import java.util.List;

import javax.persistence.EntityManager;

import entities.Client;
import entities.Preference;

public class ClientDAOImp implements ClientDAO {

	private EntityManager manager;
	
	public ClientDAOImp(EntityManager manager) {
		this.manager = manager;
	}
	
	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void addClient(Client client) {
		manager.persist(client);
	}

	public Client getClientById(int id) {
		return manager.find(Client.class, id);
	}
	
	public Client getClientByIdLazy(int id) {
		return manager.getReference(Client.class, id);
	}
	
	public List<Preference> getPreferencesById(int id) {
		List<Preference> preferences = manager.createQuery("SELECT preference FROM Preference preference WHERE id_client = " + id, Preference.class).getResultList();
		return preferences;		
	}

	public List<Client> getAllClients() {
		List<Client> clients; 
		clients = manager.createQuery("SELECT client FROM Client client", Client.class).getResultList();
		return clients;
	}

	public void removeClient(int id) {
		
		for (Preference preference : getPreferencesById(id)) {
			manager.remove(preference);
		}
		manager.remove(getClientById(id));
	}

	public void updateClient(Client client) {
		manager.merge(client);
	}

	public List<Preference> getPreferencesByRegistry(String registry) {
		return manager.createQuery("SELECT p FROM Preference p WHERE p.client.registry = " + registry, Preference.class).getResultList();		
	}
	
	public void addPreferences(List<Preference> preferences) {
		
		for (Preference preference : preferences) {
			System.out.println(preference.getPreference());
			manager.persist(preference);
		}
		
	}
	
	public void detachClient(Client client) {
		manager.detach(client);
	}
	
	public Client refreshClient(Client client) {
		manager.refresh(client);
		return client;
	}

}
