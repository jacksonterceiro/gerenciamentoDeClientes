package test;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import dao.ClientDAOImp;
import entities.Client;

public class Test {

	private static Scanner scanner;

	public static void main(String[] args) {
		
		List<Client> clients;
		Client client;
		String option;
		scanner = new Scanner(System.in);
		boolean exit = false;
		int id;
		
		EntityManagerProvider provider = new EntityManagerProvider("store");
		EntityManager manager = provider.createManager();
		ClientDAOImp clientDAOImp = new ClientDAOImp(manager);

		while (exit == false) {
			
			FormPrompt.printMenu();
			
			option = scanner.nextLine();
			
			switch (option) {
				case "1":
					
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					try {
						clientDAOImp.addClient(FormPrompt.inputClientsData());
						FormPrompt.printGenericResultMessage("Usuário cadastrado !");
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Erro ao cadastrar !");
					}
					
					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "2":
					
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					clients = clientDAOImp.getAllClients();
					FormPrompt.printClients(clients);
					id = FormPrompt.inputGenericIdClientIfExists("Digite o código do cliente para altera-lo", clients.size());
					
					try {
						clientDAOImp.updateClient(FormPrompt.inputClientsData(clientDAOImp.getClientById(id)));
						FormPrompt.printGenericResultMessage("Usuário alterado !");
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}
					
					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "3":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					clients = clientDAOImp.getAllClients();
					FormPrompt.printClients(clients);
					id = FormPrompt.inputGenericIdClientIfExists("Digite o código do cliente para remove-lo", clients.size());
					
					try {
						clientDAOImp.removeClient(id);
						FormPrompt.printGenericResultMessage("Usuário Removido !");
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}
					
					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "4":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					FormPrompt.printClients(clientDAOImp.getAllClients());
					FormPrompt.printPressEnter();
					
					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "5":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					FormPrompt.cleanScreen();

					try {
						FormPrompt.printPreferences(clientDAOImp.getPreferencesByRegistry(FormPrompt.inputGenericIdStringClientIfExists("Digite a matrícula do cliente para mostrar listar preferencias")));
						FormPrompt.printPressEnter();
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}
					
					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "6":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					clients = clientDAOImp.getAllClients();
					FormPrompt.printDisabledClients(clients);
					id = FormPrompt.inputGenericIdClientIfExists("Digite o código do cliente para ativa-lo", clients.size());
					
					try {
						client = clientDAOImp.getClientById(id);
						client.setActive(true);
						clientDAOImp.updateClient(client);
						FormPrompt.printGenericResultMessage("Usuário Ativado !");
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}
					
					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "7":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					clients = clientDAOImp.getAllClients();
					FormPrompt.printActiveClients(clients);
					id = FormPrompt.inputGenericIdClientIfExists("Digite o código do cliente para desativa-lo", clients.size());
					
					try {
						client = clientDAOImp.getClientById(id);
						client.setActive(false);
						clientDAOImp.updateClient(client);
						FormPrompt.printGenericResultMessage("Usuário Desativado !");
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}

					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "8":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					clients = clientDAOImp.getAllClients();
					FormPrompt.printClients(clients);
					id = FormPrompt.inputGenericIdClientIfExists("Digite o código do cliente para cadastrar preferências", clients.size());
					
					try {
						client = clientDAOImp.getClientById(id);
						clientDAOImp.addPreferences(FormPrompt.inputPreferences(client));
						FormPrompt.printGenericResultMessage("Preferências adicionadas !");
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}

					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "9":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					try {
						client = clientDAOImp.getClientById(Integer.parseInt(FormPrompt.inputGenericIdStringClientIfExists("Digite o ID do cliente: ")));
						FormPrompt.cleanScreen();
						FormPrompt.printLine();
						if (client == null) {
							System.out.println("# Nenhum cliente encontrado!");
						} else {
							System.out.println("# " + client.getFirstName());
						}
						FormPrompt.printLine();
						FormPrompt.printPressEnter();
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}
					
					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "10":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					try {
						client = clientDAOImp.getClientByIdLazy(Integer.parseInt(FormPrompt.inputGenericIdStringClientIfExists("Digite o ID do cliente: ")));
						FormPrompt.cleanScreen();
						FormPrompt.printLine();
						if (client == null) {
							System.out.println("# Nenhum cliente encontrado!");
						} else {
							System.out.println("# " + client.getFirstName());
						}
						FormPrompt.printLine();
						FormPrompt.printPressEnter();
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}
					
					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "11":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					manager.getTransaction().begin();
					
					try {
						client = clientDAOImp.getClientById(Integer.parseInt(FormPrompt.inputGenericIdStringClientIfExists("Digite o ID do cliente: ")));
						FormPrompt.cleanScreen();
						FormPrompt.printLine();
						System.out.println(client);
						FormPrompt.printLine();
						clientDAOImp.detachClient(client);
						System.out.println(client);
						FormPrompt.printLine();
						FormPrompt.printPressEnter();
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}
					
					manager.getTransaction().commit();
					manager.close();
					
					break;
					
				case "12":
					manager = provider.createManager();
					clientDAOImp.setManager(manager);
					
					try {
						client = clientDAOImp.getClientById(Integer.parseInt(FormPrompt.inputGenericIdStringClientIfExists("Digite o ID do cliente: ")));
						FormPrompt.cleanScreen();
						FormPrompt.printLine();
						System.out.println("# Sem refresh");
						System.out.println("# " + client.getFirstName());
						FormPrompt.printLine();
						FormPrompt.printPressEnter();
						client = clientDAOImp.refreshClient(client);
						FormPrompt.cleanScreen();
						FormPrompt.printLine();
						System.out.println("# Com refresh");
						System.out.println("# " + client.getFirstName());
						FormPrompt.printLine();
						FormPrompt.printPressEnter();
					} catch (Exception e) {
						FormPrompt.printGenericResultMessage("Usuário inválido !");
					}
					
					manager.close();
					
					break;
					
				case "0":
					exit = true;
					FormPrompt.cleanScreen();
					break;
					
				default:
					FormPrompt.printGenericResultMessage("Opção inválida!");
					break;
					
			}
			
		}
		
	}
	
}
