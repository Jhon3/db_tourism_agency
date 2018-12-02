package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Evento;
import modelo.Park;
import modelo.Restaurante;
import modelo.Restaurantes_has_park;

public class Restaurantes_has_park_Dao {
	
	private Connection conn;
	private ParkDao parkDao;
	private RestauranteDao restauranteDao;
	
	public Restaurantes_has_park_Dao() throws SQLException
	{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		parkDao = new ParkDao();
		restauranteDao = new RestauranteDao();
	}

	public void inserirRestauranteEmPark(int idRestaurante, int idPark) throws SQLException {
		
		String sql = "Insert into Restaurantes_has_park values(default, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, idRestaurante);
		pst.setInt(2, idPark);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Novo restaurante cadastrado em park.");
	}
	
	public void alterarRestauranteEmPark(int newRestaurante, int newPark, int oldIdPark) throws SQLException{
		
		String sql = "update Restaurantes_has_park set idrestaurante = ?, idpark = ? where idPark = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, newRestaurante);
		pstmt.setInt(2, newPark);
		pstmt.setInt(3, oldIdPark);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Alteração concluída");
	}
	
	public void deletarRestauranteEmPark(int idPark) throws SQLException{
		String sql = "delete Restaurantes_has_park where idPark = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, idPark);
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Deleção concluída");
	}
	
	public List<Restaurantes_has_park> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Restaurantes_has_park");
		List<Restaurantes_has_park> r_has_p = new ArrayList<Restaurantes_has_park>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Restaurantes_has_park rp = new Restaurantes_has_park();
			Park park = parkDao.buscarParkPorId(rs.getInt("idPark"));
			Restaurante restaurante = restauranteDao.buscarRestaurantePorId(rs.getInt("idRestaurante"));
			
			rp.setPark(park);
			rp.setRestaurante(restaurante);
			r_has_p.add(rp);
		}
		return r_has_p;
	}
	
	public Restaurante buscarRestaurantePorParkId(int idPark) throws SQLException {
		
		Restaurante restaurante = new Restaurante();
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idRestaurante From Restaurantes_has_park Where idPark ="+ idPark;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		restaurante = restauranteDao.buscarRestaurantePorId(rs.getInt("idRestaurante"));
		
		return restaurante;
	}
	
	public Park buscarParkPorRestauranteId(int idRestaurante) throws SQLException {
		
		Park park = new Park();
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idPark From Restaurantes_has_park Where idRestaurante ="+ idRestaurante;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		park = parkDao.buscarParkPorId(rs.getInt("idPark"));
		
		return park;
	}
}
