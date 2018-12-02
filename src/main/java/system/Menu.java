package system;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controle.AgenteDao;
import controle.AtracaoDao;
import controle.ClienteDao;
import controle.EventoDao;
import controle.HospedagemDao;
import controle.Local_Dao;
import controle.PacoteDao;
import controle.ParkDao;
import controle.PersonagemDao;
import controle.PessoaDao;
import controle.RestauranteDao;
import controle.VendaDao;
import controle.VooDao;
import modelo.Pacote;
import modelo.Park;

public class Menu {
	private PacoteDao pacoteDao;
	private ParkDao parkDao;
	private AgenteDao agenteDao;
	private AtracaoDao atracaoDao;
	private ClienteDao clienteDao;
	private EventoDao eventoDao;
	private HospedagemDao hospedagemDao;
	private Local_Dao localDao;
	private PersonagemDao personagemDao;
	private PessoaDao pessoaDao;
	private RestauranteDao restauranteDao;
	private VendaDao vendaDao;
	private VooDao vooDao;
	private Scanner entrada;
	
	private Menu() throws SQLException {
		super();
		this.pacoteDao = new PacoteDao();
		this.agenteDao = new AgenteDao();
		this.atracaoDao = new AtracaoDao();
		this.clienteDao = new ClienteDao();
		this.eventoDao = new EventoDao();
		this.hospedagemDao = new HospedagemDao();
		this.localDao = new Local_Dao();
		this.personagemDao = new PersonagemDao();
		this.pessoaDao = new PessoaDao();
		this.restauranteDao = new RestauranteDao();
		this.vendaDao = new VendaDao();
		this.vooDao = new VooDao();
		this.parkDao = new ParkDao();
		this.entrada = new Scanner(System.in);
	}
	
	/*Métodos para o Pacote*/
	public void listarPacotes() {
		/*
		ArrayList<Pacote> pacotes;
		try {
			pacotes = (ArrayList<Pacote>) pacoteDao.listar();
			for(Pacote p: pacotes) {
				System.out.println(">> " + p.getIdPacote() + ". " + p.getNome());
			}
		} catch (SQLException e) {
			System.out.println("Nao foi possivel listar os pacotes");
		}
		*/
	}
	
	public void selecionarPacote() {
        
		int id;
        
		System.out.println("Por favor, insira o ID do pacote para seleciona-lo");
        id = entrada.nextInt();
        /*
        try {
        	Pacote pacote = pacoteDao.buscarPacotePorId(id);
        	System.out.println(">Nome: " + pacote.getNome());
        	System.out.println("Valor R$: " + pacote.getValor());
        	System.out.println("Park: " + pacote.getIdPacote() +". " + pacote.getPark().getNome());
        	System.out.println("Quantidade de pessoas: " + pacote.getQtdPessoas());
        	
        	
        } catch(SQLException e) {
        	System.out.println("Nao foi possivel selecionar pacote");
        }
	*/
	}
    
	public void deletarPacote() {
    /*
		int id;
        
		System.out.println("Por favor, insira o ID do pacote para exclui-lo");
        id = entrada.nextInt();
        
        try {
        	pacoteDao.deletarPacotePorId(id);
        } catch(SQLException e) {
        	System.out.println("Nao foi possivel excluir pacote");
        }
	*/	
	}
	
	public void atualizarPacote()  {/*		
		Pacote pacote = new Pacote();
		int qtd_pessoas = 0;
		float valor = 0;
		String nome = null;
		
		System.out.println("Por favor, insira o pacote que deseja editar: ");
		int idPacote = entrada.nextInt();
		
		System.out.println("Insira o nome do pacote: ");
		nome = entrada.nextLine();
		System.out.println("Insira o valor do pacote: ");
		valor = entrada.nextFloat();
		System.out.println("Insira a Quantidade de pessoas: ");
		 qtd_pessoas = entrada.nextInt();
		try {
			
			
			ArrayList<Park> parks = (ArrayList<Park>) parkDao.listar();
			for(Park p: parks) {
				System.out.println(p.getIdPark() + ". " + p.getNome());
			}
			System.out.println("Por favor, insira o ID de um dos parks listados acima: ");
			int idPark = entrada.nextInt();
			Park park = parkDao.buscarParkPorId(idPark);
			pacote.setIdPacote(idPacote);
			pacote.setNome(nome);
			pacote.setPark(park);
			pacote.setQtdPessoas(qtd_pessoas);
			pacote.setValor(valor);
			pacoteDao.alterarPacote(pacote);
			
		} catch(SQLException e) {
			System.out.println("Falha ao listar parks");
		}*/
	}
	
	/*Métodos para o park*/
	public void selecionarPark() {
      
		int id;
        
		System.out.println("Por favor, insira o ID do park para seleciona-lo");
        id = entrada.nextInt();
      /*  
        try {
        	Park park = parkDao.buscarParkPorId(id);
        	System.out.println(">Nome: " + park.getNome());
        	System.out.println("Local: " + park.getLocal_().getCidade() + " - " + park.getLocal_().getPais());
        	
        	
        } catch(SQLException e) {
        	System.out.println("Nao foi possivel selecionar park");
        }
	*/
	}
	
	/*Menus */
	public void menuPacotes() {
		while(true) {
			this.listarPacotes();
			System.out.println("1. Selecionar pacote || 2. Deletar pacote || 3. Atualizar pacote || 4. Voltar || 5. Sair");
			int c = entrada.nextInt();
			
			switch(c) {
			case 1:
				this.selecionarPacote();
			case 2:
				this.deletarPacote();
				break;
			case 3:
				this.atualizarPacote();
				break;
			case 4:
				this.menuGlobal();
			case 5:
				System.exit(0);
				break;
			}
		}
	}
	
	
	public void menuGlobal() {
		while(true) {
			System.out.println(">>Tourism Agency - Disney");
			System.out.println("1. Pacotes \n2. Venda \n3. Cliente \n4. Park \n5. Avancado \n6. Sair");
			int c = entrada.nextInt();
			
			switch (c) {
			case 1:
				this.menuPacotes();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				System.exit(0);
				break;
			}
		}
	}
	
	
	public static void main(String[] args) throws SQLException {
		Menu menu = new Menu();
		menu.menuGlobal();
		
	}
	
	

}
