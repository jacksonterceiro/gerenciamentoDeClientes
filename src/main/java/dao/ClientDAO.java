package dao;

import java.util.List;

import entities.Client;
import entities.Preference;

public interface ClientDAO {

	public void addClient(Client client);
	
	public Client getClientById(int id);
	
	public void removeClient(int id);
	
	public void updateClient(Client client);
	
	public List<Client> getAllClients();
	
	public List<Preference> getPreferencesByRegistry(String registry);
	
}
