package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Pessoa;

public class ClienteDao {
	
	private PessoaDao pessoaDao;
	private Connection conn;
	
	public ClienteDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		pessoaDao = new PessoaDao();
	}
	
	public void inserirCliente(Cliente cliente) throws SQLException {
		int idPessoa = 0;
		try {
		pessoaDao.inserirPessoa(cliente.getPessoa()); //insere primeiro a pessoa no Banco
		idPessoa = pessoaDao.buscarIdPessoa(cliente.getPessoa().getEmail()); //apos inserir, pegar o ID
		} catch(SQLException e) {
			System.out.println("Problema ao inserir na tabela Pessoa");
		}
		
		String sql = "Insert into Cliente values(default, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, cliente.getCpf());
		pst.setInt (2, idPessoa);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Cliente " + cliente.getPessoa().getNome() + " cadastrado(a).");
	}
	
	
	public void alterarCliente(Cliente cliente) throws SQLException{
		String sql = "update Cliente set cpf = ? where idPessoa = ?";
		int idPessoa = 0;
		try {
			pessoaDao.alterarPessoa(cliente.getPessoa()); //altera primeiro a pessoa no Banco
			idPessoa = pessoaDao.buscarIdPessoa(cliente.getPessoa().getEmail()); //apos inserir, pegar o ID
		} catch(SQLException e) {
			System.out.println("Problema ao alterar a tabela Pessoa: " + e.getMessage());
		}
		System.out.println(idPessoa);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cliente.getCpf());
		pstmt.setInt(2, idPessoa);
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Cliente " + cliente.getPessoa().getNome()+ " alterado(a).");
	}
	
	
	public void deletarCliente(Cliente cliente) throws SQLException{
		String sql = "delete from Cliente where cpf =?";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1, cliente.getCpf());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Cliente " + cliente.getPessoa().getNome()+ " deletado(a).");
		
		try {
			pessoaDao.deletarPessoa(cliente.getPessoa()); //deletar primeiro a pessoa no Banco
		} catch(SQLException e) {
			System.out.println("Problema para deletar na tabela Pessoa: " + e.getMessage());
		}
	}
	
	
	public List<Cliente> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Cliente");
		List<Cliente> clientes = new ArrayList<Cliente>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Cliente c = new Cliente();
			c.setIdCliente(rs.getInt("idCliente"));
			c.setCpf(rs.getString("cpf"));
			Pessoa pessoa = pessoaDao.buscarPessoaPorId(rs.getInt("idPessoa"));
			c.setPessoa(pessoa);
			clientes.add(c);
		}
		return clientes;
	}
	
	
	public int buscarIdCliente(String clientecpf) throws SQLException {
		int id=0;
		String cpf = clientecpf;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idCliente From Cliente Where cpf like '%" + cpf + "%'";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idCliente");
		}
		return id;
	}
	
	public Cliente buscarClientePorId(int idCliente) throws SQLException {
		int id = idCliente;
		Cliente Cliente = new Cliente();
		Pessoa pessoa = new Pessoa();
		String cpf = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		
		String sql = "Select cpf, idPessoa From Cliente Where idCliente ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			cpf = rs.getString("cpf");
			pessoa = pessoaDao.buscarPessoaPorId(rs.getInt("idPessoa"));
		}
		Cliente.setCpf(cpf);
		Cliente.setIdCliente(id);
		Cliente.setPessoa(pessoa);
		
		return Cliente;
	}
	
	public static void main(String[] args) throws SQLException {
		ClienteDao clienteDao = new ClienteDao();
		
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("Carlos");
		pessoa1.setEmail("carlos@gmail.com");
		pessoa1.setTelefone("11111111");
		
		
		Cliente cliente1 = new Cliente();
		cliente1.setPessoa(pessoa1);
		cliente1.setCpf("111111111111");
		
		//clienteDao.inserirCliente(cliente1);
		clienteDao.deletarCliente(cliente1);
		
		/*
		ArrayList<Cliente> Clientes = (ArrayList<Cliente>) ClienteDao.listar();
		
		for(Cliente a : Clientes) {
			System.out.println(a.getPessoa().getNome()+ "---");
		}
		*/
		//System.out.println(ClienteDao.buscarIdCliente(Cliente1.getcpf()));
		//Cliente Cliente2 = ClienteDao.buscarClientePorId(7);
		//System.out.println(Cliente2.getPessoa().getNome());
		
		
	}
}
