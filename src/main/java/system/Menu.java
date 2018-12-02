package system;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import modelo.Agente;
import modelo.Cliente;
import modelo.Pacote;
import modelo.Park;
import modelo.Venda;

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
	
	public void inserirPacote() {/*
		
		Pacote pacote = new Pacote();
		int qtd_pessoas = 0;
		float valor = 0;
		String nome = null;
		
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
			pacote.setNome(nome);
			pacote.setPark(park);
			pacote.setQtdPessoas(qtd_pessoas);
			pacote.setValor(valor);
			pacoteDao.inserirPacote(pacote);
			
		} catch(SQLException e) {
			System.out.println("Falha ao listar parks");
		}*/
		
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
	
	public void cadastrarVenda() {
		/*
		Venda venda = new Venda();
		String dataString=null;
		Date data = new Date();
        DateFormat dataFormat=DateFormat.getInstance();
	    
		Pacote pacote = new Pacote();
		Agente agente = new Agente();
		Cliente cliente = new Cliente();
		
		System.out.println("Insira a data da venda no formato DD/MM/YY: ");
		dataString = entrada.nextLine();
        try {
            data= dataFormat.parse(dataString);
    		try {
				
    			ArrayList<Cliente> clientes = (ArrayList<Cliente>) clienteDao.listar();
    			for(Cliente c: clientes) {
    				System.out.println(c.getIdCliente() + ". " + c.getPessoa().getNome() + " >CPF: " + c.getCpf());
    			}
    			System.out.println("Por favor, insira o ID de um dos clientes listados acima: ");
    			int idCliente = entrada.nextInt();
    			cliente = clienteDao.buscarClientePorId(idCliente);
    			
    			ArrayList<Agente> agentes = (ArrayList<Agente>) agenteDao.listar();
    			for(Agente a: agentes) {
    				System.out.println(a.getIdAgente() + ". " + a.getPessoa().getNome() + " >CNPJ: " + a.getCnpj());
    			}
    			System.out.println("Por favor, insira o ID de um dos agentes listados acima: ");
    			int idAgente = entrada.nextInt();
    			agente = agenteDao.buscarAgentePorId(idAgente);
    			
    			ArrayList<Pacote> pacotes = (ArrayList<Pacote>) pacoteDao.listar();
    			for(Pacote p: pacotes) {
    				System.out.println(p.getIdPacote() + ". " + p.getNome() + " >Valor: " + p.getValor());
    			}
    			System.out.println("Por favor, insira o ID de um dos pacotes listados acima: ");
    			int idPacote = entrada.nextInt();
    			pacote = pacoteDao.buscarPacotePorId(idPacote);

    			venda.setAgente(agente);
    			venda.setDataVenda(data);
    			venda.setCliente(cliente);
    			venda.setPacote(pacote);
    			
    			vendaDao.inserirVenda(venda);
    		} catch(SQLException e) {
    			System.out.println("Falha ao listar clientes ou agentes");
    		}
        } catch (ParseException ex) {
            System.out.println("Por favor, insira a data em um formato valido!");
            this.cadastrarVenda();
        }
        	*/
	}
	
	public void listarVendas() {
		/*
		ArrayList<Venda> vendas;
		try {
			vendas = (ArrayList<Venda>) vendaDao.listar();
			for(Venda v: vendas) {
				System.out.println(v.getIdVenda() + ". -Agente: " + v.getAgente().getPessoa().getNome() + " -Cliente: " 
				+ v.getCliente().getPessoa().getNome() + " -Pacote: " + v.getPacote().getNome());
			}
		} catch (SQLException e) {
			System.out.println("Nao foi possivel listar vendas!");
		}

		*/
	}
	public void deletarVenda() {
		/*
		int id;
        
		System.out.println("Por favor, insira o ID da venda para exclui-la");
        id = entrada.nextInt();
        
        try {
        	vendaDao.deletarVendaPorId(id);
        } catch(SQLException e) {
        	System.out.println("Nao foi possivel excluir venda");
        }
		*/
	}
	
	public void editarVenda() {
	/*
		String dataString=null;
		Date data = new Date();
        DateFormat dataFormat=DateFormat.getInstance();
	    
		Pacote pacote = new Pacote();
		Agente agente = new Agente();
		Cliente cliente = new Cliente();
		System.out.println("Insira o ID da venda que deseja editar: ");
		int idVenda = entrada.nextInt();
		try {
			Venda venda = vendaDao.buscarVendaPorId(idVenda);
			System.out.println("Insira a data da venda no formato DD/MM/YY: ");
			dataString = entrada.nextLine();
	        try {
	            data= dataFormat.parse(dataString);
	    		try {
					
	    			ArrayList<Cliente> clientes = (ArrayList<Cliente>) clienteDao.listar();
	    			for(Cliente c: clientes) {
	    				System.out.println(c.getIdCliente() + ". " + c.getPessoa().getNome() + " >CPF: " + c.getCpf());
	    			}
	    			System.out.println("Por favor, insira o ID de um dos clientes listados acima: ");
	    			int idCliente = entrada.nextInt();
	    			cliente = clienteDao.buscarClientePorId(idCliente);
	    			
	    			ArrayList<Agente> agentes = (ArrayList<Agente>) agenteDao.listar();
	    			for(Agente a: agentes) {
	    				System.out.println(a.getIdAgente() + ". " + a.getPessoa().getNome() + " >CNPJ: " + a.getCnpj());
	    			}
	    			System.out.println("Por favor, insira o ID de um dos agentes listados acima: ");
	    			int idAgente = entrada.nextInt();
	    			agente = agenteDao.buscarAgentePorId(idAgente);
	    			
	    			ArrayList<Pacote> pacotes = (ArrayList<Pacote>) pacoteDao.listar();
	    			for(Pacote p: pacotes) {
	    				System.out.println(p.getIdPacote() + ". " + p.getNome() + " >Valor: " + p.getValor());
	    			}
	    			System.out.println("Por favor, insira o ID de um dos pacotes listados acima: ");
	    			int idPacote = entrada.nextInt();
	    			pacote = pacoteDao.buscarPacotePorId(idPacote);
	
	    			venda.setAgente(agente);
	    			venda.setDataVenda(data);
	    			venda.setCliente(cliente);
	    			venda.setPacote(pacote);
	    			
	    			vendaDao.inserirVenda(venda);
	    		} catch(SQLException e) {
	    			System.out.println("Falha ao listar clientes ou agentes");
	    		}
	        } catch (ParseException ex) {
	            System.out.println("Por favor, insira a data em um formato valido!");
	            
	        }
		} catch(SQLException e) {
			System.out.println("Erro ao editar venda!");
			this.cadastrarVenda();
		}
	*/
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
			System.out.println("1. Selecionar pacote || 2. Inserir pacote ||3. Deletar pacote || 4. Atualizar pacote || 5. Voltar || 6. Sair");
			int c = entrada.nextInt();
			
			switch(c) {
			case 1:
				this.selecionarPacote();
				break;
			case 2:
				this.inserirPacote();
				break;
			case 3:
				this.deletarPacote();
				break;
			case 4:
				this.atualizarPacote();
				break;
			case 5:
				this.menuGlobal();
				break;
			case 6:
				System.exit(0);
				break;
			}
		}
	}
	
	public void menuVenda() {
		while(true) {
			this.listarVendas();
			System.out.println("1. Cadastrar venda || 2. Deletar venda || 3. Editar venda || 4. Voltar || 5. Sair");
			int c = entrada.nextInt();
			
			switch(c) {
			case 1:
				this.cadastrarVenda();
				break;
			case 2:
				this.deletarVenda();
				break;
			case 3:
				this.editarVenda();
				break;
			case 4:
				this.menuGlobal();
				break;
			case 5:
				System.exit(0);
				break;
			}
		}
	}
	
	public void menuCliente() {
		while(true) {
			this.listarVendas();
			System.out.println("1. Cadastrar cliente || 2. Deletar cliente || 3. Editar cliente || 4. Voltar || 5. Sair");
			int c = entrada.nextInt();
			
			switch(c) {
			case 1:
				this.cadastrarVenda();
				break;
			case 2:
				this.deletarVenda();
				break;
			case 3:
				this.editarVenda();
				break;
			case 4:
				this.menuGlobal();
				break;
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
				this.menuVenda();
				break;
			case 3:
				this.menuCliente();
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
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
