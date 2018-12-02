package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Personagem;
//import modelo.Atracao;
//import modelo.Evento;

public class PersonagemDao {
	
	private Connection conn;
	//private AtracaoDao atracaoDao;
	//private EventoDao eventoDao;
	
	public PersonagemDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		//atracaoDao = new AtracaoDao();
		//eventoDao = new EventoDao();
	}
	
	public void inserirPersonagem(Personagem personagem) throws SQLException {
		
		//int idAtracao = personagem.getAtracao().getIdAtracao();
		//int idEvento = personagem.getEvento().getIdEvento();
		
		String sql = "Insert into Personagem values(default, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, personagem.getNome());
		pst.setString(2, personagem.getAnimacao());
		//pst.setInt(3, idEvento);
		//pst.setInt(4, idAtracao);
		
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Personagem cadastrada.");
	}
	
	public void alterarPersonagem(Personagem personagem) throws SQLException{
		//int idAtracao = personagem.getAtracao().getIdAtracao();
		//int idEvento = personagem.getEvento().getIdEvento();
		
		String sql = "update Personagem set nome =?, animacao =? where idPersonagem = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, personagem.getNome());
		pstmt.setString(2, personagem.getAnimacao());
		//pstmt.setInt(3, idEvento);
		//pstmt.setInt(4, idAtracao);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Personagem alterado(a).");
	}
	
	public void deletarPersonagem(Personagem personagem) throws SQLException{
		String sql = "delete from Personagem where idPersonagem = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, personagem.getIdPersonagem());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Personagem deletado(a).");
	}
	
	
	public List<Personagem> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Personagem");
		List<Personagem> Personagens = new ArrayList<Personagem>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Personagem p = new Personagem();
			p.setNome(rs.getString("nome"));
			p.setAnimacao(rs.getString("animacao"));
			
			//Atracao atracao = atracaoDao.buscarAtracaoPorId(rs.getInt("idAtracao"));
			//Evento evento = eventoDao.buscarEventoPorId(rs.getInt("idEvento"));
			//p.setEvento(evento);
			//p.setAtracao(atracao);
			Personagens.add(p);
		}
		return Personagens;
	}
	
	/*
	public int buscarIdPersonagem(String nome, String animacao) throws SQLException {

		int id=0;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idPersonagem From Personagem Where nome=" + data_Personagem +" AND idAtracao=" +idAtracao+ " AND idAgente=" +idAgente;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idPersonagem");
		}
		return id;
	}
	*/
	
	public Personagem buscarPersonagemPorId(int idPersonagem) throws SQLException {
		int id = idPersonagem;
		Personagem personagem = new Personagem();
		//Atracao atracao = new Atracao();
		//Evento evento = new Evento();
		
		String nome = null;
		String animacao = null;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select nome, animacao, idEvento,  idAtracao Where idPersonagem ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			nome = rs.getString("nome");
			animacao = rs.getString("valor");
			//evento = eventoDao.buscarEventoPorId(rs.getInt("idEvento"));
			//atracao = atracaoDao.buscarAtracaoPorId(rs.getInt("idAtracao"));
		}
		personagem.setNome(nome);
		personagem.setIdPersonagem(id);
		personagem.setAnimacao(animacao);
		//personagem.setEvento(evento);
		//personagem.setAtracao(atracao);
		
		return personagem;
	}
	
/*	
	public static void main(String[] args) throws SQLException {

		
	}
*/
}
