package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Evento;
import modelo.Park;

public class EventoDao {
	
	private Connection conn;
	private ParkDao parkDao;
	
	public EventoDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		parkDao = new ParkDao();
	}
	
	public void inserirEvento(Evento evento) throws SQLException {
		
		int idPark = evento.getPark().getIdPark();

		String sql = "Insert into Evento values(default, ?, ?, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, evento.getNome());
		pst.setString(2, evento.getDescricao());
		pst.setInt(3, evento.getIdadeIndicacao());
		pst.setString(4, evento.getTipoEvento());
		pst.setInt(5, idPark);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Evento " + evento.getNome()+ " cadastrado(a).");
	}
	
	public void alterarEvento(Evento evento) throws SQLException{
		int idPark = evento.getPark().getIdPark();

		String sql = "update Evento set nome = ?, descricao = ?, idade_indicacao = ?, tipo_evento = ? where idPark = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, evento.getNome());
		pstmt.setString(2, evento.getDescricao());
		pstmt.setInt(3, evento.getIdadeIndicacao());
		pstmt.setString(4, evento.getTipoEvento());
		pstmt.setInt(5, idPark);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Evento " + evento.getNome()+ " alterado(a).");
	}
	
	public void deletarEvento(Evento evento) throws SQLException{
		String sql = "delete from Evento where idEvento = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, evento.getIdEvento());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Evento " + evento.getNome()+ " deletado(a).");
	}
	
	
	public List<Evento> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Evento");
		List<Evento> eventos = new ArrayList<Evento>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Evento e = new Evento();
			e.setNome(rs.getString("nome"));
			e.setDescricao(rs.getString("descricao"));
			e.setIdadeIndicacao(rs.getInt("idade_indicacao"));
			e.setTipoEvento(rs.getString("tipo_evento"));
			e.setIdEvento(rs.getInt("idEvento"));
			Park park = parkDao.buscarParkPorId(rs.getInt("idPark"));
			e.setPark(park);
			eventos.add(e);
		}
		return eventos;
	}
	
	
	public int buscarIdEvento(String eventoNome) throws SQLException {
		int id=0;
		String nome = eventoNome;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idEvento From Evento Where nome like '%" + nome + "%'";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idEvento");
		}
		return id;
	}
	
	public Evento buscarEventoPorId(int idEvento) throws SQLException {
		int id = idEvento;
		Evento evento = new Evento();
		Park park = new Park();
		
		String nome = null;
		String descricao = null;
		int idade_indicacao = 0;
		String tipo_evento = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select nome, descricao, idade_indicacao, tipo_evento, idPark From Evento Where idEvento ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			nome = rs.getString("nome");
			descricao = rs.getString("descricao");
			idade_indicacao = rs.getInt("idade_indicacao");
			tipo_evento = rs.getString("tipo_evento");
			park = parkDao.buscarParkPorId(rs.getInt("idPark"));
		}
		evento.setNome(nome);
		evento.setIdEvento(id);
		evento.setIdadeIndicacao(idade_indicacao);
		evento.setDescricao(descricao);
		evento.setTipoEvento(tipo_evento);
		evento.setPark(park);
		
		return evento;
	}
	
/*	
	public static void main(String[] args) throws SQLException {

		
	}
*/
}
