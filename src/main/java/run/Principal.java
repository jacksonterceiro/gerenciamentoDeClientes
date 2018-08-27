package run;

import core.Core;
import dao.ClienteDAO;
import forms.FormPrompt;
import jpa.entidades.Cliente;

public class Principal {
	
	//JOGAR A CHAMADA DO MANAGER DENTRO DO CORE E RODAR UM initAppCliente() no inicio da aplicacao

	public static void main(String[] args) {
		int op = 0;

		Core.initAppCliente();
		
		
		while(op != 6) {
			FormPrompt.cleanScreen();
			op = FormPrompt.menuPrincipal();
			
			switch(op) {
			case 1:
				Cliente cliente = new Cliente();
				FormPrompt.inputClientsData(cliente);
				ClienteDAO.adicionarCliente(cliente);
				break;
			case 2:
				ClienteDAO.listarCliente();
				FormPrompt.PressioneEnter();
				break;
			case 3:
				ClienteDAO.excluirCliente();
				break;
			case 4:
				ClienteDAO.ativarCliente();
				break;
			case 5:
				ClienteDAO.desativarCliente();
				break;
			case 6:
				FormPrompt.cleanScreen();
				System.out.println("Finalizando aplicacao!");
				break;
			default:
				System.out.println("Este não é uma opcao valida!");
				op = 0;
			}
		}
	}

}

//Description	Resource	Path	Location	Type
//Project configuration is not up-to-date with pom.xml. Select: Maven->Update Project... from the project context menu or use Quick Fix.	geriamentoDeCliente		line 1	Maven Configuration Problem



