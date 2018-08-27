package forms;

import java.util.List;
import java.util.Scanner;

import jpa.entidades.Cliente;

public class FormPrompt {

	private static Scanner scanner;
	
	public static void cleanScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public static Cliente inputClientsData(Cliente cliente) {
		
		scanner = new Scanner(System.in);
		
		String option = "";
		
		cleanScreen();
		
		System.out.print("Nome: ");
		cliente.setNome(scanner.nextLine());
		
		System.out.print("Sobrenome: ");
		cliente.setSobrenome(scanner.nextLine());
		
		System.out.print("Matrícula: ");
		cliente.setMatricula(scanner.nextLine());
		
		System.out.print("Deseja ativa-lo? [S/n]: ");
		option = scanner.nextLine();
		
		if (option.equals(new String("S"))) {
			cliente.setAtivo(true);
		} else {
			cliente.setAtivo(false);
		}
		
		return cliente;
		
	}
	
	public static void PressioneEnter() {
		
		scanner = new Scanner(System.in);
		System.out.println("Pressione enter para continuar...");
		scanner.nextLine();
		
	}
	
	public static void listarClientes(List<Cliente> clientes) {
		
		if (clientes.size() > 0) {
			for (Cliente cliente : clientes) {
				System.out.println("-> " + cliente.getId() + " - " + cliente.getNome() + " " + cliente.getSobrenome());
			}
		} else {
			System.out.println("Nenhum cliente cadastrado !");
		}
	}
	
	public static int menuPrincipal() {
		int op = 0;
		
		System.out.println("Projeto de java JPA\n");
		
		System.out.println(" ==  Menu Cliente == \n");
		
		System.out.println("1 - Adicionar");
		System.out.println("2 - Listar");
		System.out.println("3 - Excluir");
		System.out.println("4 - Ativar");
		System.out.println("5 - Desativar");
		System.out.println("6 - Sair\n");
		
		System.out.print("Digite uma opcao: ");
		scanner = new Scanner(System.in);
		op = scanner.nextInt();

		return op;
	}
	
	public static int receberInteiroComMensagem(String mensagem) {
		int id = -1;
		scanner = new Scanner(System.in);
		
		try {
			System.out.println(mensagem + " (0 para sair)");
			System.out.print("-> ");
			id = scanner.nextInt();
			
		} catch (Exception e) {}
		
		return id;
		
	}
	
	public static void mensagemResultado(String mensagem) {
		cleanScreen();
		System.out.println(mensagem);
		PressioneEnter();
		cleanScreen();
	}
	
	
	
}
