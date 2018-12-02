package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Atracao;
import modelo.Atracoes_has_park;
import modelo.Park;

public class Atracoes_has_park_Dao {
	
	private Connection conn;
	private ParkDao parkDao;
	private AtracaoDao atracaoDao;
	
	public Atracoes_has_park_Dao() throws SQLException
	{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		parkDao = new ParkDao();
		atracaoDao = new AtracaoDao();
	}

	public void inserirAtracaoEmPark(int idAtracao, int idPark) throws SQLException {
		
		String sql = "Insert into Atracoes_has_park values(default, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, idAtracao);
		pst.setInt(2, idPark);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Nova atração cadastrada em park.");
	}
	
	public void alterarAtracaoEmPark(int newAtracao, int newPark, int oldIdPark) throws SQLException{
		
		String sql = "update Atracoes_has_park set idatracao = ?, idpark = ? where idPark = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, newAtracao);
		pstmt.setInt(2, newPark);
		pstmt.setInt(3, oldIdPark);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Alteração concluída");
	}
	
	public void deletarAtracaoEmPark(int idPark) throws SQLException{
		String sql = "delete Atracoes_has_park where idPark = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, idPark);
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Deleção concluída");
	}
	
	public List<Atracoes_has_park> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Atracoes_has_park");
		List<Atracoes_has_park> atracoesList = new ArrayList<Atracoes_has_park>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Atracoes_has_park rp = new Atracoes_has_park();
			Park park = parkDao.buscarParkPorId(rs.getInt("idPark"));
			Atracao atracao = atracaoDao.buscarAtracaoPorId(rs.getInt("idAtracao"));
					
			rp.setPark(park);
			rp.setAtracao(atracao);
			atracoesList.add(rp);
		}
		return atracoesList;
	}
	
	public Atracao buscarAtracaoPorParkId(int idPark) throws SQLException {
		
		Atracao atracao = new Atracao();
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idAtracao From Atracoes_has_park Where idPark ="+ idPark;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		atracao = atracaoDao.buscarAtracaoPorId(rs.getInt("idAtracao"));
		
		return atracao;
	}
	
	public Park buscarParkPorAtracaoId(int idAtracao) throws SQLException {
		
		Park park = new Park();
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idPark From Atracoes_has_park Where idAtracao ="+ idAtracao;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		park = parkDao.buscarParkPorId(rs.getInt("idPark"));
		
		return park;
	}
	
}
