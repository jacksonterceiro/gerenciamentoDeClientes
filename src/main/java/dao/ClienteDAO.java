package dao;

import java.util.List;

import core.Core;
import forms.FormPrompt;
import jpa.entidades.Cliente;
import jpa.entidades.Preferences;



public class ClienteDAO {

	
	public static void adicionarCliente(Cliente cliente) {
		
		//EntityManager manager = CreateManager.GetEntityManager();
		
		Core.manager.getTransaction().begin();
		Core.manager.persist(cliente);		
		Core.manager.getTransaction().commit();
		
	}
	
	public static void listarCliente() {
		
		List<Cliente> clientes = getDbClientes();
		
		FormPrompt.cleanScreen();
		
		FormPrompt.listarClientes(clientes);
		System.out.println("");
		
	}
	
	public static void excluirCliente() {
		int id = -1;
		
		listarCliente();
		id = FormPrompt.receberInteiroComMensagem("Digite o codigo do cliente para remove-lo");
		
		if(id == 0) {
			return;
		}
	
		try {
			for (Preferences preference : PreferencesDAO.getPreferencesById(id)) {
				Core.manager.remove(preference);
			}
			Core.manager.remove(getClienteDB(id));
			FormPrompt.mensagemResultado("Usuário Removido !");
			
			Core.manager.getTransaction().commit();
			Core.manager.close();
		}  catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			System.out.println();
			FormPrompt.mensagemResultado("Usuário inválido !");
		}
		
		
	}
	
	public static void ativarCliente() {
		Cliente cliente;
		int id = -1;
		
		List<Cliente> clientes = getDbClientes();
		listarCliente();
		id = FormPrompt.receberInteiroComMensagem("Digite o codigo do cliente para ativa-lo");
		
		if(id == 0) {
			return;
		}
		
		try {
			cliente = getClienteDB(id);
			cliente.setAtivo(true);
			Core.manager.merge(cliente);
			FormPrompt.mensagemResultado("Usuário Ativado !");
		} catch (Exception e) {
			FormPrompt.mensagemResultado("Usuário inválido !");
		}
		
	}
	
	public static void desativarCliente() {
		
		Cliente cliente;
		int id = -1;
		
		List<Cliente> clientes = getDbClientes();
		listarCliente();
		id = FormPrompt.receberInteiroComMensagem("Digite o codigo do cliente para desativa-lo");
		
		if(id == 0) {
			return;
		}
		
		try {
			cliente = getClienteDB(id);
			cliente.setAtivo(false);
			Core.manager.merge(cliente);
			FormPrompt.mensagemResultado("Usuário Desativado !");
		} catch (Exception e) {
			FormPrompt.mensagemResultado("Usuário Desativado !");
		}
		
	}
	
	public static List<Cliente> getDbClientes() {
		List<Cliente> clientes;
		//EntityManager manager = CreateManager.GetEntityManager();
		
		clientes = Core.manager.createQuery("SELECT cliente FROM Cliente cliente", Cliente.class).getResultList();
		return clientes;
	}
	
	public static Cliente getClienteDB(int id) {
		List<Cliente> clients = Core.manager.createQuery("SELECT cliente FROM Cliente cliente WHERE id = " + id, Cliente.class).getResultList();
		return clients.get(0);
	}
	
}




/*

Cliente cliente = new Cliente();
cliente.setNome("Rodrigo Fujioka");
cliente.setDataFim(new Date());
cliente.setDataInicio(new Date());
System.out.println(cliente);
manager.getTransaction().begin();
manager.persist(cliente);		
manager.getTransaction().commit();

manager.close();

*/

