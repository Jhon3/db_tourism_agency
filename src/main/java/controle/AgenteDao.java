package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Agente;
import modelo.Pessoa;

public class AgenteDao {
	
	private PessoaDao pessoaDao;
	private Connection conn;
	
	public AgenteDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		pessoaDao = new PessoaDao();
	}
	
	public void inserirAgente(Agente agente) throws SQLException {
		int idPessoa = 0;
		try {
		pessoaDao.inserirPessoa(agente.getPessoa()); //insere primeiro a pessoa no Banco
		idPessoa = pessoaDao.buscarIdPessoa(agente.getPessoa().getEmail()); //apos inserir, pegar o ID
		} catch(SQLException e) {
			System.out.println("Problema ao inserir na tabela Pessoa");
		}
		
		String sql = "Insert into Agente values(default, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, agente.getCnpj());
		pst.setInt (2, idPessoa);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Agente " + agente.getPessoa().getNome() + " cadastrado(a).");
	}
	
	
	public void alterarAgente(Agente agente) throws SQLException{
		String sql = "update Agente set cnpj = ? where idPessoa = ?";
		int idPessoa = 0;
		try {
			pessoaDao.alterarPessoa(agente.getPessoa()); //altera primeiro a pessoa no Banco
			idPessoa = pessoaDao.buscarIdPessoa(agente.getPessoa().getEmail()); //apos inserir, pegar o ID
		} catch(SQLException e) {
			System.out.println("Problema ao alterar a tabela Pessoa: " + e.getMessage());
		}
		System.out.println(idPessoa);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, agente.getCnpj());
		pstmt.setInt(2, idPessoa);
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Agente " + agente.getPessoa().getNome()+ " alterado(a).");
	}
	
	
	public void deletarAgente(Agente agente) throws SQLException{
		
		String sql = "delete from agente where cnpj = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1, agente.getCnpj());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Agente " + agente.getPessoa().getNome()+ " deletado(a).");
		
		try {
			pessoaDao.deletarPessoa(agente.getPessoa()); //deletar primeiro a pessoa no Banco
		} catch(SQLException e) {
			System.out.println("Problema para deletar na tabela Pessoa: " + e.getMessage());
		}
	}
	
	
	public List<Agente> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Agente");
		List<Agente> agentes = new ArrayList<Agente>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Agente a = new Agente();
			a.setIdAgente(rs.getInt("idAgente"));
			a.setCnpj(rs.getString("cnpj"));
			Pessoa pessoa = pessoaDao.buscarPessoaPorId(rs.getInt("idPessoa"));
			a.setPessoa(pessoa);
			agentes.add(a);
		}
		return agentes;
	}
	
	
	public int buscarIdAgente(String agenteCnpj) throws SQLException {
		int id=0;
		String cnpj = agenteCnpj;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idAgente From Agente Where cnpj like '%" + cnpj + "%'";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idAgente");
		}
		return id;
	}
	
	public Agente buscarAgentePorId(int idAgente) throws SQLException {
		int id = idAgente;
		Agente agente = new Agente();
		Pessoa pessoa = new Pessoa();
		String cnpj = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		
		String sql = "Select cnpj, idPessoa From Agente Where idAgente ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			cnpj = rs.getString("cnpj");
			pessoa = pessoaDao.buscarPessoaPorId(rs.getInt("idPessoa"));
		}
		agente.setCnpj(cnpj);
		agente.setIdAgente(id);
		agente.setPessoa(pessoa);
		
		return agente;
	}
	
	public static void main(String[] args) throws SQLException {
		AgenteDao agenteDao = new AgenteDao();
		
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("Daniel");
		pessoa1.setEmail("daniel@gmail.com");
		pessoa1.setTelefone("111111111");
		
		
		Agente agente1 = new Agente();
		agente1.setPessoa(pessoa1);
		agente1.setCnpj("111111111111");
		//agenteDao.inserirAgente(agente1);
		agenteDao.deletarAgente(agente1);
		
		
		/*
		ArrayList<Agente> agentes = (ArrayList<Agente>) agenteDao.listar();
		
		for(Agente a : agentes) {
			System.out.println(a.getPessoa().getNome()+ "---");
		}
		*/
		//System.out.println(agenteDao.buscarIdAgente(agente1.getCnpj()));
		//Agente agente2 = agenteDao.buscarAgentePorId(7);
		//System.out.println(agente2.getPessoa().getNome());
		
		
	}
}
