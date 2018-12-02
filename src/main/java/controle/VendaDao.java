package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Venda;
import modelo.Cliente;
import modelo.Pacote;
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
	
	public void inserirVenda(Venda venda) throws SQLException {
		
		int idCliente = venda.getCliente().getIdCliente();
		int idAgente = venda.getAgente().getIdAgente();
		int idPacote = venda.getPacote().getIdPacote();
		
		String sql = "Insert into Venda values(default, ?, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setDate(1, (Date) venda.getDataVenda());
		pst.setInt(2, idCliente);
		pst.setInt(3, idAgente);
		pst.setInt(4, idPacote);
		
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Venda cadastrada.");
	}
	
	public void alterarVenda(Venda venda) throws SQLException{
		int idCliente = venda.getCliente().getIdCliente();
		int idAgente = venda.getAgente().getIdAgente();
		int idPacote = venda.getPacote().getIdPacote();
		
		String sql = "update Venda set data_venda =?, idCliente, idAgente, idPacote where idVenda = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setDate(1, (Date) venda.getDataVenda());
		pstmt.setInt(3, idCliente);
		pstmt.setInt(4, idAgente);
		pstmt.setInt(5, idPacote);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Venda alterado(a).");
	}
	
	public void deletarVenda(Venda venda) throws SQLException{
		String sql = "delete from Venda where idVenda = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, venda.getIdVenda());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Venda deletado(a).");
	}
	
	
	public List<Venda> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Venda");
		List<Venda> vendas = new ArrayList<Venda>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Venda v = new Venda();
			v.setDataVenda(rs.getDate("data_venda"));
			v.setIdVenda(rs.getInt("idVenda"));
			Cliente cliente = clienteDao.buscarClientePorId(rs.getInt("idcliente"));
			Agente agente = agenteDao.buscarAgentePorId(rs.getInt("idagente"));
			Pacote pacote = pacoteDao.buscarPacotePorId(rs.getInt("idPacote"));
			v.setCliente(cliente);
			v.setAgente(agente);
			v.setPacote(pacote);
			vendas.add(v);
		}
		return vendas;
	}
	
	
	public int buscarIdVenda(Date data_venda, Cliente cliente, Agente agente) throws SQLException {
		int idCliente = cliente.getIdCliente();
		int idAgente = agente.getIdAgente();

		int id=0;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idVenda From Venda Where data_venda=" + data_venda +" AND idCliente=" +idCliente+ " AND idAgente=" +idAgente;
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
		Cliente cliente = new Cliente();
		Agente agente = new Agente();
		Pacote pacote = new Pacote();
		
		Date data_venda = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select data_venda, valor, idCliente,  idAgente, idPacote Where idVenda ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			data_venda = rs.getDate("data_venda");
			agente = agenteDao.buscarAgentePorId(rs.getInt("idAgente"));
			pacote = pacoteDao.buscarPacotePorId(rs.getInt("idPacote"));
			cliente = clienteDao.buscarClientePorId(rs.getInt("idCliente"));
		}
		Venda.setDataVenda(data_venda);
		Venda.setIdVenda(id);
		Venda.setAgente(agente);
		Venda.setPacote(pacote);
		Venda.setCliente(cliente);
		
		return Venda;
	}
	
/*	
	public static void main(String[] args) throws SQLException {

		
	}
*/
}
