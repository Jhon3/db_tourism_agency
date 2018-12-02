package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Local_;

public class Local_Dao {
	
	private Connection conn;
	
	public Local_Dao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
	}
	
	public void inserirLocal_(Local_ local_) throws SQLException {
		String sql = "Insert into Local_ values(default, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, local_.getCidade());
		pst.setString(2, local_.getPais());
		pst.setString(3, local_.getMoeda());
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Local_ " + local_.getCidade()+ " cadastrado(a).");
	}
	
	public void alterarLocal_(Local_ local_) throws SQLException{
		String sql = "update Local_ set pais = ?, moeda = ? where cidade = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, local_.getCidade());
		pstmt.setString(2, local_.getPais());
		pstmt.setString(3, local_.getMoeda());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Local_ " + local_.getCidade()+ " alterado(a).");
	}
	
	public void deletarLocal_(Local_ local_) throws SQLException{
		String sql = "delete from Local_ where cidade like '% ? %'";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1, local_.getCidade());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Local " + local_.getCidade()+ " deletado(a).");
	}
	
	
	public List<Local_> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Local_");
		List<Local_> locais = new ArrayList<Local_>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Local_ l = new Local_();
			l.setIdLocal_(rs.getInt("idLocal_"));
			l.setCidade(rs.getString("cidade"));
			l.setPais(rs.getString("pais"));
			l.setMoeda(rs.getString("moeda"));
			locais.add(l);
		}
		return locais;
	}
	
	
	public int buscarIdLocal_(String localCidade) throws SQLException {
		int id=0;
		String cidade = localCidade;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idLocal_ From Local_ Where cidade =" + cidade;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idLocal_");
		}
		return id;
	}
	
	public Local_ buscarLocal_PorId(int idLocal_) throws SQLException {
		int id = idLocal_;
		Local_ local_ = new Local_();
		String cidade = null;
		String pais = null;
		String moeda = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select cidade, pais, moeda From Local_ Where idLocal_ ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			cidade = rs.getString("cidade");
			pais = rs.getString("pais");
			moeda = rs.getString("moeda");
		}
		local_.setPais(pais);
		local_.setIdLocal_(id);
		local_.setCidade(cidade);
		local_.setMoeda(moeda);
		
		return local_;
	}
	
}
