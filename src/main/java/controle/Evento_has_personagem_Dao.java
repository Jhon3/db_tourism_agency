package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Atracao;
import modelo.Atracoes_has_personagem;
import modelo.Evento;
import modelo.Evento_has_personagem;
import modelo.Personagem;

public class Evento_has_personagem_Dao {
	private Connection conn;
	private EventoDao eventoDao;
	private PersonagemDao personagemDao;
	
	public Evento_has_personagem_Dao() throws SQLException
	{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		personagemDao = new PersonagemDao();
		eventoDao = new EventoDao();
	}

	public void inserirPersonagemEmAtracao(int idEvento, int idPersonagem) throws SQLException {
		
		String sql = "Insert into Evento_has_personagem values(default, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, idEvento);
		pst.setInt(2, idPersonagem);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Novo personagem cadastrado em evento.");
	}
	
	public void alterarPersonagemEmAtracao(int newEvento, int newPersonagem, int oldIdEvento) throws SQLException{
		
		String sql = "update Evento_has_personagem set idevento = ?, idpersonagem = ? where idEvento = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, newEvento);
		pstmt.setInt(2, newPersonagem);
		pstmt.setInt(3, oldIdEvento);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Alteração concluída");
	}
	
	public void deletarPersonagemEmAtracao(int idEvento) throws SQLException{
		String sql = "delete Evento_has_personagem where idEvento = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, idEvento);
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Deleção concluída");
	}
	
	public List<Evento_has_personagem> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Evento_has_personagem");
		List<Evento_has_personagem> personagensList = new ArrayList<Evento_has_personagem>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Evento_has_personagem ep = new Evento_has_personagem();
			Personagem personagem = personagemDao.buscarPersonagemPorId(rs.getInt("idPersonagem"));
			Evento evento = eventoDao.buscarEventoPorId(rs.getInt("idAtracao"));
				
			ep.setPersonagem(personagem);
			ep.setEvento(evento);
			personagensList.add(ep);
		}
		return personagensList;
	}
	
	public Evento buscarEventoPorPersonagemkId(int idPersonagem) throws SQLException {
		
		Evento evento = new Evento();
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idEvento From Evento_has_personagem Where idPersonagem ="+ idPersonagem;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		evento = eventoDao.buscarEventoPorId(rs.getInt("idEvento"));
		
		return evento;
	}
	
	public Personagem buscarPersonagemPorEventoId(int idEvento) throws SQLException {
		
		Personagem personagem = new Personagem();
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idPersonagem From Evento_has_personagem Where idEvento ="+ idEvento;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		personagem = personagemDao.buscarPersonagemPorId(rs.getInt("idPersonagem"));
		
		return personagem;
	}
}
