package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Client;
import entities.Preference;

public class FormPrompt {
	
	private static Scanner scanner;

	public static void cleanScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public static void printMenu() {
		cleanScreen();
		
		System.out.println("#################### STORE ####################");
		System.out.println("# 1 - Cadastrar cliente");
		System.out.println("# 2 - Alterar cliente");
		System.out.println("# 3 - Excluir cliente");
		System.out.println("# 4 - Listar clientes");
		System.out.println("# 5 - Listar Preferencia por matrícula");
		System.out.println("# 6 - Ativar cliente");
		System.out.println("# 7 - Desativar cliente");
		System.out.println("# 8 - Adicionar preferências");
		System.out.println("# 9 - Procurar cliente por ID (find)");
		System.out.println("# 10 - Procurar cliente por ID (getReference)");
		System.out.println("# 11 - Procurar cliente por ID e desvincular");
		System.out.println("# 12 - Procurar cliente por ID para fazer refresh");
		System.out.println("# 0 - Sair");
		System.out.println("###############################################");
		System.out.print("# > ");
	}
	
	public static void printLine() {
		System.out.println("###############################################");
	}

	public static void printPressEnter() {
		
		scanner = new Scanner(System.in);
		
		printLine();
		System.out.println("#");
		System.out.println("# Pressione enter para continuar...");
		System.out.println("#");
		printLine();
		
		scanner.nextLine();
		
	}
	
	public static void printGenericMessage(String message) {
		printLine();
		System.out.println("#");
		System.out.println("# " + message);
		System.out.println("#");
		printLine();
	}
	
	public static void printGenericResultMessage(String message) {
		cleanScreen();
		printGenericMessage(message);
		printPressEnter();
		cleanScreen();
	}
	
	public static void printClients(List<Client> clients) {
		
		cleanScreen();
		printLine();
		
		if (clients.size() > 0) {
			for (Client client : clients) {
				System.out.println("# " + client.getId() + " - " + client.getFirstName() + " " + client.getLastName());
			}
		} else {
			System.out.println("# Nenhum cliente cadastrado !");
		}
		
		printLine();
		
	}

	public static void printPreferences(List<Preference> preferences) {
		
		cleanScreen();
		printLine();
		
		if (preferences.size() > 0) {
			for (Preference preference : preferences) {
				System.out.println("# " + preference.getPreference());
			}
		} else {
			System.out.println("# Nenhuma preferência salva !");
		}
		
		printLine();
		
	}

	public static void printActiveClients(List<Client> clients) {
		
		cleanScreen();
		printLine();
		
		if (clients.size() > 0) {
			for (Client client : clients) {
				if (client.isActive()) {
					System.out.println("# " + client.getId() + " - " + client.getFirstName() + " " + client.getLastName());	
				}
			}
		} else {
			System.out.println("# Nenhum cliente ativo cadastrado !");
		}
		
		printLine();
		
	}
	
	public static void printDisabledClients(List<Client> clients) {
		
		cleanScreen();
		printLine();
		
		if (clients.size() > 0) {
			for (Client client : clients) {
				if (!client.isActive()) {
					System.out.println("# " + client.getId() + " - " + client.getFirstName() + " " + client.getLastName());	
				}
			}
		} else {
			System.out.println("# Nenhum cliente desativado cadastrado !");
		}
		
		printLine();
		
	}

	public static Client inputClientsData(Client client) {
		
		scanner = new Scanner(System.in);
		
		String option = "";
		
		cleanScreen();
		
		System.out.print("Nome: ");
		client.setFirstName(scanner.nextLine());
		
		System.out.print("Sobrenome: ");
		client.setLastName(scanner.nextLine());
		
		System.out.print("Matrícula: ");
		client.setRegistry(scanner.nextLine());
		
		System.out.print("Deseja ativa-lo? [S/n]: ");
		option = scanner.nextLine();
		
		if (option.equals(new String("S"))) {
			client.setActive(true);
		} else {
			client.setActive(false);
		}
		
		return client;
		
	}
	
	public static Client inputClientsData() {
		
		Client client = new Client();
		return inputClientsData(client);
		
	}
	
	public static String inputGenericIdStringClientIfExists(String message) {
		
		scanner = new Scanner(System.in);
		String string;
		
		System.out.println("# " + message + " (0 para sair)");
		System.out.print("# > ");
		string = scanner.nextLine();
		
		return string;
		
	}


	public static int inputGenericIdClientIfExists(String message, int sizeOfCustomerList) {
		
		scanner = new Scanner(System.in);
		
		int id = -1;
		
		try {
			
			if (sizeOfCustomerList > 0) {
				
				System.out.println("# " + message + " (0 para sair)");
				System.out.print("# > ");
				id = scanner.nextInt();
				
			}
			
		} catch (Exception e) {
			
		}
		
		return id;
		
	}
	
	public static List<Preference> inputPreferences(Client client) {
		
		scanner = new Scanner(System.in);
		List<Preference> preferences = new ArrayList<Preference>();
		Preference preference;
		
		while (true) {
			FormPrompt.cleanScreen();
			
			preference = new Preference();
			
			System.out.print("Preferencia: ");
			preference.setPreference(scanner.nextLine());
			preference.setClient(client);
			preferences.add(preference);
			
			FormPrompt.cleanScreen();
			System.out.print("Deseja adicionar outra? [S/n]: ");
			
			if (!scanner.nextLine().equals(new String("S"))) {
				break;
			}
			
		}
		
		return preferences;
		
	}
}
