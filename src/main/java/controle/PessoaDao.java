package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Pessoa;

public class PessoaDao {
	
	private Connection conn;
	
	public PessoaDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
	}
	
	public void inserirPessoa(Pessoa pessoa) throws SQLException {
		String sql = "Insert into pessoa values(default, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, pessoa.getNome());
		pst.setString(2, pessoa.getEmail());
		pst.setString(3, pessoa.getTelefone());
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Pessoa " + pessoa.getNome()+ " cadastrado(a).");
	}
	
	public void alterarPessoa(Pessoa pessoa) throws SQLException{
		String sql = "update pessoa set nome = ?, telefone = ?, email = ? where idPessoa = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pessoa.getNome());
		pstmt.setString(2, pessoa.getTelefone());
		pstmt.setString(3, pessoa.getEmail());
		pstmt.setInt(3, pessoa.getIdPessoa());
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Pessoa " + pessoa.getNome()+ " alterado(a).");
	}
	
	public void deletarPessoa(Pessoa pessoa) throws SQLException{
		String sql = "delete from pessoa where idPessoa = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, pessoa.getIdPessoa());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Pessoa " + pessoa.getNome()+ " deletado(a).");
	}
	
	
	public List<Pessoa> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from pessoa");
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Pessoa p = new Pessoa();
			p.setIdPessoa(rs.getInt("idpessoa"));
			p.setNome(rs.getString("nome"));
			p.setEmail(rs.getString("email"));
			p.setTelefone(rs.getString("telefone"));
			pessoas.add(p);
		}
		return pessoas;
	}
	
	
	public int buscarIdPessoa(String pessoaEmail) throws SQLException {
		int id=0;
		String email = pessoaEmail;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idPessoa From pessoa Where email like '%" + email + "%'";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idPessoa");
		}
		return id;
	}
	
	public Pessoa buscarPessoaPorId(int idPessoa) throws SQLException {
		int id = idPessoa;
		Pessoa pessoa = new Pessoa();
		String nome = null;
		String email = null;
		String telefone = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select nome, email, telefone From pessoa Where idpessoa ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			nome = rs.getString("nome");
			email = rs.getString("email");
			telefone = rs.getString("telefone");
		}
		pessoa.setEmail(email);
		pessoa.setIdPessoa(id);
		pessoa.setNome(nome);
		pessoa.setTelefone(telefone);
		
		return pessoa;
	}
	
}
