package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Voo;
import modelo.Pacote;

public class VooDao {
	
	private Connection conn;
	private PacoteDao PacoteDao;
	
	public VooDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		PacoteDao = new PacoteDao();
	}
	
	public void inserirVoo(Voo voo) throws SQLException {
		
		int idPacote = 0;
		try {
			idPacote = PacoteDao.buscarIdPacote(voo.getPacote().getNome()); 
		} catch(SQLException e) {
				System.out.println("Problema ao acessar a tabela Pacote");
			}
		String sql = "Insert into Voo values(default, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, voo.getAgenciaVoo());
		pst.setString(2, voo.getTempoEstimado());
		pst.setString(3, voo.getModeloAviao());
		pst.setDate(4, (Date) voo.getDataIda());
		pst.setDate(5, (Date) voo.getDataVolta());
		pst.setInt(6, idPacote);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Voo cadastrado(a).");
	}
	
	public void alterarVoo(Voo voo) throws SQLException{
		int idPacote = 0;
		try {
			idPacote = PacoteDao.buscarIdPacote(voo.getPacote().getNome()); 
		} catch(SQLException e) {
				System.out.println("Problema ao acessar a tabela Pacote");
			}
		String sql = "update Voo set agencia_voo = ?, tempo_estimado = ?, modelo_aviao = ?, data_ida = ?, data_volta = ? where idVoo = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, voo.getAgenciaVoo());
		pstmt.setString(2, voo.getTempoEstimado());
		pstmt.setString(3, voo.getModeloAviao());
		pstmt.setDate(4, (Date) voo.getDataIda());
		pstmt.setDate(5, (Date) voo.getDataVolta());
		pstmt.setInt(6, idPacote);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Voo alterado(a).");
	}
	
	public void deletarVoo(Voo voo) throws SQLException{
		String sql = "delete from Voo where idVoo = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, voo.getIdVoo());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Voo deletado(a).");
	}
	
	
	public List<Voo> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Voo");
		List<Voo> voos = new ArrayList<Voo>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Voo v = new Voo();
			v.setAgenciaVoo(rs.getString("agencia_voo"));
			v.setTempoEstimado(rs.getString("tempo_estimado"));
			v.setModeloAviao(rs.getString("modelo_aviao"));
			v.setDataIda(rs.getDate("data_ida"));
			v.setDataVolta(rs.getDate("data_volta"));
			v.setIdVoo(rs.getInt("idVoo"));
			Pacote Pacote = PacoteDao.buscarPacotePorId(rs.getInt("idPacote"));
			v.setPacote(Pacote);
			voos.add(v);
		}
		return voos;
	}
	
	
	public int buscarIdVoo(String agencia_voo, String modelo_aviao, Date data_ida, Date data_volta) throws SQLException {
		int id=0;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idVoo From Voo Where agencia_voo =" +agencia_voo+ "AND modelo_aviao=" +modelo_aviao+ "AND data_ida=" +data_ida+ "data_volta=" + data_volta;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idVoo");
		}
		return id;
	}
	
	public Voo buscarVooPorId(int idVoo) throws SQLException {
		int id = idVoo;
		Voo Voo = new Voo();
		Pacote pacote = new Pacote();
		
		String agencia_voo = null;
		String modelo_aviao = null;
		Date data_ida = null;
		Date data_volta = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select agencia_voo, modelo_aviao, data_ida, data_volta, idPacote From Voo Where idVoo ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			agencia_voo = rs.getString("agencia_voo");
			modelo_aviao = rs.getString("modelo_aviao");
			data_ida = rs.getDate("data_ida");
			data_volta = rs.getDate("data_volta");
			pacote = PacoteDao.buscarPacotePorId(rs.getInt("idPacote"));
		}
		Voo.setAgenciaVoo(agencia_voo);
		Voo.setIdVoo(id);
		Voo.setModeloAviao(modelo_aviao);
		Voo.setDataIda(data_ida);
		Voo.setDataVolta(data_volta);
		Voo.setPacote(pacote);
		
		return Voo;
	}
	
/*	
	public static void main(String[] args) throws SQLException {

		
	}
*/
}
