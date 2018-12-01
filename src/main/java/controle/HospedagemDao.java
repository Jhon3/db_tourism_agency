package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Hospedagem;
import modelo.Park;

public class HospedagemDao {
	
	private Connection conn;
	private ParkDao parkDao;
	
	public HospedagemDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		parkDao = new ParkDao();
	}
	
	public void inserirHospedagem(Hospedagem hospedagem) throws SQLException {
		
		int idPark = hospedagem.getPark().getIdPark();

		String sql = "Insert into Hospedagem values(default, ?, ?, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, hospedagem.getNome());
		pst.setFloat(2, hospedagem.getPrecoNoite());
		pst.setString(3, hospedagem.getTipoHotel());
		pst.setString(4, hospedagem.getTransporteAcesso());
		pst.setInt(5, idPark);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Hospedagem " + hospedagem.getNome()+ " cadastrado(a).");
	}
	
	public void alterarHospedagem(Hospedagem hospedagem) throws SQLException{
		int idPark = hospedagem.getPark().getIdPark();

		String sql = "update Hospedagem set preco_noite = ?, tipo_hotel = ?,  transporte_acesso = ?, idPark = ? where nome = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(5, hospedagem.getNome());
		pstmt.setFloat(1, hospedagem.getPrecoNoite());
		pstmt.setString(2, hospedagem.getTipoHotel());
		pstmt.setString(3, hospedagem.getTransporteAcesso());
		pstmt.setInt(4, idPark);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Hospedagem " + hospedagem.getNome()+ " alterado(a).");
	}
	
	public void deletarHospedagem(Hospedagem hospedagem) throws SQLException{
		String sql = "delete from Hospedagem where nome like '% ? %'";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1, hospedagem.getNome());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Hospedagem " + hospedagem.getNome()+ " deletado(a).");
	}
	
	
	public List<Hospedagem> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Hospedagem");
		List<Hospedagem> hospedagems = new ArrayList<Hospedagem>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Hospedagem h = new Hospedagem();
			h.setNome(rs.getString("nome"));
			h.setPrecoNoite(rs.getFloat("preco_noite"));
			h.setTipoHotel(rs.getString("tipo_hotel"));
			h.setTransporteAcesso(rs.getString("transporte_acesso"));
			h.setIdHospedagem(rs.getInt("idHospedagem"));
			Park park = parkDao.buscarParkPorId(rs.getInt("idPark"));
			h.setPark(park);
			hospedagems.add(h);
		}
		return hospedagems;
	}
	
	
	public int buscarIdHospedagem(String hospedagemNome) throws SQLException {
		int id=0;
		String nome = hospedagemNome;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idHospedagem From Hospedagem Where nome like '%" + nome + "%'";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idHospedagem");
		}
		return id;
	}
	
	public Hospedagem buscarHospedagemPorId(int idhospedagem) throws SQLException {
		int id = idhospedagem;
		Hospedagem Hospedagem = new Hospedagem();
		Park park = new Park();
		
		String nome = null;
		float preco_noite = 0;
		String tipo_hotel = null;
		String transporte_acesso = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select nome, preco_noite, tipo_hotel, transporte_acesso, idPark From Hospedagem Where idHospedagem ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			nome = rs.getString("nome");
			preco_noite = rs.getFloat("preco_noite");
			tipo_hotel = rs.getString("tipo_hotel");
			transporte_acesso = rs.getString("transporte_acesso");
			park = parkDao.buscarParkPorId(rs.getInt("idPark"));
		}
		Hospedagem.setNome(nome);
		Hospedagem.setIdHospedagem(id);
		Hospedagem.setPrecoNoite(preco_noite);
		Hospedagem.setTipoHotel(tipo_hotel);
		Hospedagem.setTransporteAcesso(transporte_acesso);
		Hospedagem.setPark(park);
		
		return Hospedagem;
	}
	
/*	
	public static void main(String[] args) throws SQLException {

		
	}
*/
}
