package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Venda;
import modelo.Cliente;
import modelo.Agente;

public class VendaDao {
	
	private Connection conn;
	private ClienteDao clienteDao;
	private AgenteDao agenteDao;
	private PacoteDao pacoteDao;
	
	public VendaDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		clienteDao = new ClienteDao();
	}
	
	public void inserirVenda(Venda Venda) throws SQLException {
		
		int idcliente = 0;
		try {
			clienteDao.inserirCliente(Venda.getCliente()); 
			idcliente = clienteDao.buscarIdCliente(Venda.getCliente().getPessoa().getNome()); //apos inserir, pegar o ID
		} catch(SQLException e) {
				System.out.println("Problema ao inserir na tabela cliente");
			}
		String sql = "Insert into Venda values(default, ?, ?, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, Venda.getNome());
		pst.setString(2, Venda.getDescricao());
		pst.setInt(3, Venda.getIdadeIndicacao());
		pst.setString(4, Venda.getTipoVenda());
		pst.setInt(5, idcliente);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Venda " + Venda.getNome()+ " cadastrado(a).");
	}
	
	public void alterarVenda(Venda Venda) throws SQLException{
		int idcliente = 0;
		try {
			clienteDao.alterarcliente(Venda.getcliente()); 
			idcliente = clienteDao.buscarIdcliente(Venda.getcliente().getNome()); //apos inserir, pegar o ID
		} catch(SQLException e) {
				System.out.println("Problema ao alterar a tabela cliente");
			}
		String sql = "update Venda set nome = ?, descricao = ?, idade_indicacao = ?, tipo_Venda = ? where idcliente = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Venda.getNome());
		pstmt.setString(2, Venda.getDescricao());
		pstmt.setInt(3, Venda.getIdadeIndicacao());
		pstmt.setString(4, Venda.getTipoVenda());
		pstmt.setInt(5, idcliente);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Venda " + Venda.getNome()+ " alterado(a).");
	}
	
	public void deletarVenda(Venda Venda) throws SQLException{
		String sql = "delete from Venda where idVenda = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, Venda.getIdVenda());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Venda " + Venda.getNome()+ " deletado(a).");
	}
	
	
	public List<Venda> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Venda");
		List<Venda> Vendas = new ArrayList<Venda>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Venda e = new Venda();
			e.setNome(rs.getString("nome"));
			e.setDescricao(rs.getString("descricao"));
			e.setIdadeIndicacao(rs.getInt("idade_indicacao"));
			e.setTipoVenda(rs.getString("tipo_Venda"));
			e.setIdVenda(rs.getInt("idVenda"));
			cliente cliente = clienteDao.buscarclientePorId(rs.getInt("idcliente"));
			e.setcliente(cliente);
			Vendas.add(e);
		}
		return Vendas;
	}
	
	
	public int buscarIdVenda(String VendaNome) throws SQLException {
		int id=0;
		String nome = VendaNome;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idVenda From Venda Where nome like '%" + nome + "%'";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idVenda");
		}
		return id;
	}
	
	public Venda buscarVendaPorId(int idVenda) throws SQLException {
		int id = idVenda;
		Venda Venda = new Venda();
		cliente cliente = new cliente();
		
		String nome = null;
		String descricao = null;
		int idade_indicacao = 0;
		String tipo_Venda = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select nome, descricao, idade_indicacao, tipo_Venda, idcliente From Venda Where idVenda ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			nome = rs.getString("nome");
			descricao = rs.getString("descricao");
			idade_indicacao = rs.getInt("idade_indicacao");
			tipo_Venda = rs.getString("tipo_Venda");
			cliente = clienteDao.buscarclientePorId(rs.getInt("idcliente"));
		}
		Venda.setNome(nome);
		Venda.setIdVenda(id);
		Venda.setIdadeIndicacao(idade_indicacao);
		Venda.setDescricao(descricao);
		Venda.setTipoVenda(tipo_Venda);
		Venda.setcliente(cliente);
		
		return Venda;
	}
	
/*	
	public static void main(String[] args) throws SQLException {

		
	}
*/
}
