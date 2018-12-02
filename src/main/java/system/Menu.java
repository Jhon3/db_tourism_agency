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
import controle.Atracoes_has_personagem_Dao;
import controle.ClienteDao;
import controle.EventoDao;
import controle.Evento_has_personagem_Dao;
import controle.HospedagemDao;
import controle.Local_Dao;
import controle.PacoteDao;
import controle.ParkDao;
import controle.PersonagemDao;
import controle.PessoaDao;
import controle.RestauranteDao;
import controle.Restaurantes_has_park_Dao;
import controle.VendaDao;
import controle.VooDao;
import modelo.Agente;
import modelo.Atracao;
import modelo.Cliente;
import modelo.Evento;
import modelo.Hospedagem;
import modelo.Pacote;
import modelo.Park;
import modelo.Personagem;
import modelo.Pessoa;
import modelo.Restaurante;
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
	private Restaurantes_has_park_Dao rest_has_park_Dao;
	private Atracoes_has_personagem_Dao atra_has_person_Dao;
	private Evento_has_personagem_Dao even_has_person_Dao;
	private VendaDao vendaDao;
	private VooDao vooDao;
	private Scanner entrada;
	
	public Menu() throws SQLException {
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
	
	/*M�todos para o Pacote*/
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
	
	public void listarCliente() {
		/*
		ArrayList<Cliente> clientes;
		try {
			clientes = (ArrayList<Cliente>) clienteDao.listar();
			for(Cliente c: clientes) {
				System.out.println(c.getIdCliente() + ". " + c.getPessoa().getNome());
			}
		} catch (SQLException e) {
			System.out.println("Nao foi possivel listar clientes!");
		}

		*/
	}
	
	public void cadastrarCliente() { /*
		Pessoa pessoa = new Pessoa();
		String nome = null;
		String email = null;
		String telefone = null;
		Cliente cliente = new Cliente();
		String cpf = null;
		
		System.out.println("Por favor, insira o nome: ");
		nome = entrada.nextLine();
		
		System.out.println("Por favor, insira o email: ");
		email = entrada.nextLine();
		
		System.out.println("Por favor, insira o telefone: ");
		telefone = entrada.nextLine();
		
		System.out.println("Insira o cpf: ");
		cpf = entrada.nextLine();
		
		pessoa.setNome(nome);
		pessoa.setEmail(email);
		pessoa.setTelefone(telefone);
		
		cliente.setCpf(cpf);
		cliente.setPessoa(pessoa);
		
		try {
			clienteDao.inserirCliente(cliente);
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel inserir cliente!");
		}
		*/
	}
	
	public void deletarCliente() {
		/*
		System.out.println("Por favor, insira o ID de um dos clientes listados acima: ");
		int idCliente = entrada.nextInt();
		
		try {
			Cliente cliente = clienteDao.buscarClientePorId(idCliente);
			clienteDao.deletarCliente(cliente);
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel deletar o cliente");
		}
		*/
	}
	
	public void editarCliente() {
		/*
		System.out.println("Por favor, insira o ID do cliente que sera excluido: ");
		int idCliente = entrada.nextInt();
		String nome = null;
		String email = null;
		String telefone = null;
		String cpf = null;
		
		try {
		Cliente cliente = clienteDao.buscarClientePorId(idCliente);
		Pessoa pessoa = cliente.getPessoa();
		System.out.println("Por favor, insira o nome: ");
		nome = entrada.nextLine();
		
		System.out.println("Por favor, insira o email: ");
		email = entrada.nextLine();
		
		System.out.println("Por favor, insira o telefone: ");
		telefone = entrada.nextLine();
		
		System.out.println("Insira o cpf: ");
		cpf = entrada.nextLine();
		
		pessoa.setNome(nome);
		pessoa.setEmail(email);
		pessoa.setTelefone(telefone);
		cliente.setCpf(cpf);
		cliente.setPessoa(pessoa);
		
		clienteDao.alterarCliente(cliente);
		} catch(SQLException ex) {
			System.out.println("Erro ao editar cliente!");
		}
		*/
	}
	
	/*M�todos para o park*/
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
	
	public void cadastrarEvento() throws NumberFormatException, SQLException { 
		/*
		Evento evento = new Evento();
		
		String nome = null;
		String descricao = null;
		String idadeIndicacao = null;
		String tipoEvento = null;
		
		String idPark = null;
		Park park = null;
		
		System.out.println("Nome do evento: ");
		nome = entrada.nextLine();
		
		System.out.println("Descrição do evento: ");
		descricao = entrada.nextLine();
		
		System.out.println("Informe a idade indicada para participar do evento: ");
		idadeIndicacao = entrada.nextLine();
		
		System.out.println("Informe o tipo de evento: ");
		tipoEvento = entrada.nextLine();
		
		System.out.println("Informe o número de identificação do park em que o evento ocorrerá: ");
		idPark = entrada.nextLine();
		park = parkDao.buscarParkPorId(Integer.parseInt(idPark));
		
		evento.setNome(nome);
		evento.setDescricao(descricao);
		evento.setIdadeIndicacao(Integer.parseInt(idadeIndicacao));
		evento.setTipoEvento(tipoEvento);
		evento.setPark(park);
				
		try {
			eventoDao.inserirEvento(evento);
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel inserir um novo evento no sistema!");
		}
		*/
	}
	
	public void cadastrarRestaurante() { 
		/*
		Restaurante restaurante = new Restaurante();
		String nome = null;
		String pontoReferencia = null;
		String tipoCulinaria = null;
		String tipoRestaurante = null;
		String faixaPrecos = null;
		String horarioAbertura = null;
		String horarioEncerramento = null;
		
		System.out.println("Nome do restaurante: ");
		nome = entrada.nextLine();
		
		System.out.println("Informe um ponto de referência: ");
		pontoReferencia = entrada.nextLine();
		
		System.out.println("Informe o tipo de culinária do restaurante: ");
		tipoCulinaria = entrada.nextLine();
		
		System.out.println("Informe o tipo de restaurante: ");
		tipoRestaurante = entrada.nextLine();
		
		System.out.println("Informe a faixa de preços do restaurante: ");
		faixaPrecos = entrada.nextLine();
		
		System.out.println("Informe o horário de abertura: ");
		horarioAbertura = entrada.nextLine();
		
		System.out.println("Informe o horário de encerramento: ");
		horarioEncerramento = entrada.nextLine();
		
		restaurante.setNome(nome);
		restaurante.setPontoReferencia(pontoReferencia);
		restaurante.setTipoCulinaria(tipoCulinaria);
		restaurante.setTipoRestaurante(tipoRestaurante);
		restaurante.setFaixaPrecos(faixaPrecos);
		restaurante.setHorarioAbertura(horarioAbertura);
		restaurante.setHorarioEncerramento(horarioEncerramento);
		
		try {
			restauranteDao.inserirRestaurante(restaurante);
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel inserir um novo restaurante no sistema!");
		}
		*/
	}
	
	public void cadastrarRestauranteEmPark()
	{
		/*
		String idPark;
		String idRestaurante;
		
		System.out.println("Informe o id do park: ");
		idPark = entrada.nextLine();
		
		System.out.println("Informe o id do restaurante: ");
		idRestaurante = entrada.nextLine();
		
		try {
			rest_has_park_Dao.inserirRestauranteEmPark(Integer.parseInt(idRestaurante), Integer.parseInt(idPark));
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel efetuar este cadastro!");
		}
		*/
	}
		
	public void cadastrarAtracao() { 
		/*
		Atracao atracao = new Atracao();
		
		String nome = null;
		String descricao = null;
		String idadeMinima = null;
		String limitePessoas = null;
		String horarioAbertura = null;
		String horarioEncerramento = null;
		String alturaMinima = null;
		
		System.out.println("Nome da atração: ");
		nome = entrada.nextLine();
		
		System.out.println("Descrição da atração: ");
		descricao = entrada.nextLine();
		
		System.out.println("Informe a idade mínima para brincar na atração: ");
		idadeMinima = entrada.nextLine();
		
		System.out.println("Informe o limite de pessoas da atração: ");
		limitePessoas = entrada.nextLine();
		
		System.out.println("Informe o horário de abertura: ");
		horarioAbertura = entrada.nextLine();
		
		System.out.println("Informe o horário de encerramento: ");
		horarioEncerramento = entrada.nextLine();
		
		System.out.println("Informe a altura mínima para brincar na atração: ");
		alturaMinima = entrada.nextLine();
		
		atracao.setNome(nome);
		atracao.setDescricao(descricao);
		atracao.setIdadeMinima(Integer.parseInt(idadeMinima));
		atracao.setLimitePessoas(Integer.parseInt(limitePessoas));
		atracao.setHorarioAbertura(horarioAbertura);
		atracao.setHorarioEncerramento(horarioEncerramento);
		atracao.setAlturaMinima(Float.parseFloat(alturaMinima));
		
		try {
			atracaoDao.inserirAtracao(atracao);
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel inserir uma nova atração no sistema!");
		}
		*/
	}
	
	public void cadastrarHospedagem() throws NumberFormatException, SQLException { 
		
		Hospedagem hospedagem = new Hospedagem();		
		
		String nome = null;
		String precoNoite = null;
		String tipoHotel = null;
		String transporteAcesso = null;
		
		String idPark = null;
		Park park = null;
		
		System.out.println("Nome da hospedagem: ");
		nome = entrada.nextLine();
		
		System.out.println("Informe o preço por noite nesta hospedagem: ");
		precoNoite = entrada.nextLine();
		
		System.out.println("Informe o tipo de hospedagem: ");
		tipoHotel = entrada.nextLine();
		
		System.out.println("Informe a forma de acesso à hospedagem: ");
		transporteAcesso = entrada.nextLine();
		
		System.out.println("Informe o número de identificação do park em que o evento ocorrerá: ");
		idPark = entrada.nextLine();
		park = parkDao.buscarParkPorId(Integer.parseInt(idPark));
		
		hospedagem.setNome(nome);
		hospedagem.setPrecoNoite(Float.parseFloat(precoNoite));
		hospedagem.setTipoHotel(tipoHotel);
		hospedagem.setTransporteAcesso(transporteAcesso);
		hospedagem.setPark(park);
				
		try {
			hospedagemDao.inserirHospedagem(hospedagem);
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel inserir um novo evento no sistema!");
		}
		
	}
	
	public void cadastrarPersonagem() { 
		
		Personagem personagem = new Personagem();
		
		String nome = null;
		String animacao = null;
		
		System.out.println("Nome do personagem: ");
		nome = entrada.nextLine();
		
		System.out.println("Animação ao qual o personagem faz parte: ");
		animacao = entrada.nextLine();
		
		personagem.setNome(nome);
		personagem.setAnimacao(animacao);
		
		try {
			personagemDao.inserirPersonagem(personagem);
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel inserir um novo personagem no sistema!");
		}
		
	}
	
	public void cadastrarPersonagemEmAtracao()
	{
		/*
		String idAtracao;
		String idPersonagem;
		
		System.out.println("Informe o id do personagem: ");
		idPersonagem = entrada.nextLine();
		
		System.out.println("Informe o id da atracao: ");
		idAtracao = entrada.nextLine();
		
		try {
			atra_has_person_Dao.inserirPersonagemEmAtracao(Integer.parseInt(idAtracao), Integer.parseInt(idPersonagem));
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel efetuar este cadastro!");
		}
		*/
	}
	
	public void cadastrarPersonagemEmEvento()
	{
	/*
		String idEvento;
		String idPersonagem;
		
		System.out.println("Informe o id do personagem: ");
		idPersonagem = entrada.nextLine();
		
		System.out.println("Informe o id do evento: ");
		idEvento = entrada.nextLine();
		
		try {
			even_has_person_Dao.inserirPersonagemEmEvento(Integer.parseInt(idEvento), Integer.parseInt(idPersonagem));
		} catch(SQLException ex) {
			System.out.println("Nao foi possivel efetuar este cadastro!");
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
			this.listarCliente();
			System.out.println("1. Cadastrar cliente || 2. Deletar cliente || 3. Editar cliente || 4. Voltar || 5. Sair");
			int c = entrada.nextInt();
			
			switch(c) {
			case 1:
				this.cadastrarCliente();
				break;
			case 2:
				this.deletarCliente();
				break;
			case 3:
				this.editarCliente();
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
	
	public void menuAvancado(){
		while(true) {
			System.out.println("1. Cadastrar Hospedagem || 2. Cadastrar Restaurante || 3. Cadastrar Atração || 4. Cadastrar Evento || 5. Cadastrar Personagem");
			System.out.println("6. Cadastrar Restaurante em Park || 7. Cadastrar Personagem em Atração || 8. Cadastrar Personagem em Eventos");
			System.out.println("9. Voltar || 10. Sair");
			
			int c = entrada.nextInt();
			
			switch(c) {
			case 1:
				try {
					this.cadastrarHospedagem();
				} catch (NumberFormatException e1) {					
					e1.printStackTrace();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
				break;
			case 2:
				this.cadastrarRestaurante();
				break;
			case 3:
				this.cadastrarAtracao();
				break;
			case 4:
				try {
					this.cadastrarEvento();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				break;
			case 5:
				this.cadastrarPersonagem();
				break;
			case 6:
				this.cadastrarRestauranteEmPark();
				break;
			case 7:
				this.cadastrarPersonagemEmAtracao();
				break;
			case 8:
				this.cadastrarPersonagemEmEvento();
				break;
			case 9:
				this.menuGlobal();
				break;
			case 10:
				System.exit(0);
				break;
			default:
				System.out.println("Informe um número do menu");
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
				this.menuAvancado();
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
