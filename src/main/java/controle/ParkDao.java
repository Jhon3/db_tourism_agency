package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Local_;
import modelo.Park;

public class ParkDao {
	
	private Connection conn;
	private Local_Dao local_Dao;
	
	public ParkDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		local_Dao = new Local_Dao();
	}
	
	public void inserirPark(Park park) throws SQLException {
		
		int idLocal_ = park.getLocal_().getIdLocal_();

		String sql = "Insert into Park values(default, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, park.getNome());
		pst.setInt(2, idLocal_);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Park " + park.getNome()+ " cadastrado(a).");
	}
	
	public void alterarPark(Park park) throws SQLException{
		int idLocal_ = park.getLocal_().getIdLocal_();

		String sql = "update Park set nome = ? where idLocal_ = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, park.getNome());
		pstmt.setInt(2, idLocal_);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Park " + park.getNome()+ " alterado(a).");
	}
	
	public void deletarPark(Park park) throws SQLException{
		String sql = "delete from Park where nome like '% ? %'";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1, park.getNome());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Park " + park.getNome()+ " deletado(a).");
	}
	
	
	public List<Park> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Park");
		List<Park> parks = new ArrayList<Park>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Park p = new Park();
			p.setNome(rs.getString("nome"));
			p.setIdPark(rs.getInt("idPark"));
			Local_ local_ = local_Dao.buscarLocal_PorId(rs.getInt("idLocal"));
			p.setLocal_(local_);
			parks.add(p);
		}
		return parks;
	}
	
	
	public int buscarIdPark(String ParkNome) throws SQLException {
		int id=0;
		String nome = ParkNome;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idPark From Park Where nome like '%" + nome + "%'";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idPark");
		}
		return id;
	}
	
	public Park buscarParkPorId(int idPark) throws SQLException {
		int id = idPark;
		Local_ local_ = new Local_();
		Park park = new Park();
		
		String nome = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select nome, idLocal_ From Park Where idPark ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			nome = rs.getString("nome");
			local_ = local_Dao.buscarLocal_PorId(rs.getInt("idLocal_"));
		}
		park.setNome(nome);
		park.setIdPark(id);
		park.setLocal_(local_);
		return park;
	}
	
/*	
	public static void main(String[] args) throws SQLException {

		
	}
*/
}
