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
	
	
	
	public static void main(String[] args) throws SQLException {
		/*
		Local_Dao Local_Dao = new Local_Dao();
		
		Local_ Local_1 = new Local_();
		Local_1.setNome("jhon");
		Local_1.setEmail("jhon@gmail.com");
		Local_1.setTelefone("000000000");
		Local_Dao.inserirLocal_(Local_1);
		
		Local_2.setNome("daniel");
		Local_2.setEmail("daniel@gmail.com");
		Local_2.setTelefone("1111111111");
		Local_Dao.inserirLocal_(Local_2);
		*/
		//Local_.setNome("jhon1");
		//Local_Dao.alterarLocal_(Local_);
		//Local_Dao.deletarLocal_(Local_);
		
		/*
		ArrayList<Local_> list = (ArrayList<Local_>) Local_Dao.listar();
		for(Local_ p: list) {
			System.out.println(p.getNome() + " --- ");
		}
		
		
		int idJhon = Local_Dao.buscarLocal_("jhon@gmail.com");
		System.out.println(idJhon);
		*/
		
	}
}
