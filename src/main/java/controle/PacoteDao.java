package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Pacote;
import modelo.Park;

public class PacoteDao {
	
	private Connection conn;
	private ParkDao parkDao;
	
	public PacoteDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		parkDao = new ParkDao();
	}
	
	public void inserirPacote(Pacote pacote) throws SQLException {
		
		int idPark = 0;
		try {
			parkDao.inserirPark(pacote.getPark()); 
			idPark = parkDao.buscarIdPark(pacote.getPark().getNome()); //apos inserir, pegar o ID
		} catch(SQLException e) {
				System.out.println("Problema ao inserir na tabela Park");
			}
		String sql = "Insert into Pacote values(default, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, pacote.getQtdPessoas());
		pst.setInt(2, idPark);
		pst.setString(3, pacote.getNome());
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Pacote cadastrado.");
	}
	
	public void alterarPacote(Pacote pacote) throws SQLException{
		int idPark = 0;
		try {
			parkDao.alterarPark(pacote.getPark()); 
			idPark = parkDao.buscarIdPark(pacote.getPark().getNome()); //apos inserir, pegar o ID
		} catch(SQLException e) {
				System.out.println("Problema ao alterar a tabela Park");
			}
		String sql = "update Pacote set qtdpessoas = ?, idPark = ?, nome = ? where idPacote = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pacote.getQtdPessoas());
		pstmt.setInt(2, idPark);
		pstmt.setString(3, pacote.getNome());
		pstmt.setInt(4, pacote.getIdPacote());
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Pacote alterado.");
	}
	
	public void deletarPacote(Pacote pacote) throws SQLException{
		String sql = "delete from Pacote where idPacote = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, pacote.getIdPacote());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Pacote deletado(a).");
	}
	
	
	public List<Pacote> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Pacote");
		List<Pacote> pacotes = new ArrayList<Pacote>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Pacote h = new Pacote();
			h.setNome(rs.getString("nome"));
			h.setQtdPessoas(rs.getInt("qtdpessoas"));
			Park park = parkDao.buscarParkPorId(rs.getInt("idPark"));
			h.setPark(park);
			pacotes.add(h);
		}
		return pacotes;
	}
	
	
	public int buscarIdPacote(String PacoteNome) throws SQLException {
		int id=0;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idPacote From Pacote Where nome =" + PacoteNome;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idPacote");
		}
		return id;
	}
	
	public Pacote buscarPacotePorId(int idPacote) throws SQLException {
		int id = idPacote;
		Pacote pacote = new Pacote();
		Park park = new Park();
		int qtdPessoas = 0;
		String nome = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select qtdpessoas, idPark, nome From Pacote Where idPacote ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			qtdPessoas = rs.getInt("qtdpessoas");
			park = parkDao.buscarParkPorId(rs.getInt("idPark"));
			nome = rs.getString("nome");
		}
		pacote.setQtdPessoas(qtdPessoas);
		pacote.setPark(park);
		pacote.setIdPacote(idPacote);
		pacote.setNome(nome);
		
		return pacote;
	}
	
/*	
	public static void main(String[] args) throws SQLException {

		
	}
*/
}
