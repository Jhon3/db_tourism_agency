package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Atracao;
import modelo.Atracoes_has_personagem;
import modelo.Personagem;

public class Atracoes_has_personagem_Dao {
	private Connection conn;
	private AtracaoDao atracaoDao;
	private PersonagemDao personagemDao;
	
	public Atracoes_has_personagem_Dao() throws SQLException
	{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		personagemDao = new PersonagemDao();
		atracaoDao = new AtracaoDao();
	}

	public void inserirPersonagemEmAtracao(int idAtracao, int idPersonagem) throws SQLException {
		
		String sql = "Insert into Atracoes_has_personagem values(default, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, idAtracao);
		pst.setInt(2, idPersonagem);
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Novo personagem cadastrado em atração.");
	}
	
	public void alterarPersonagemEmAtracao(int newAtracao, int newPersonagem, int oldIdAtracao) throws SQLException{
		
		String sql = "update Atracoes_has_personagem set idatracao = ?, idpersonagem = ? where idAtracao = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, newAtracao);
		pstmt.setInt(2, newPersonagem);
		pstmt.setInt(3, oldIdAtracao);
		
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Alteração concluída");
	}
	
	public void deletarPersonagemEmAtracao(int idAtracao) throws SQLException{
		String sql = "delete Atracoes_has_personagem where idAtracao = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, idAtracao);
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Deleção concluída");
	}
	
	public List<Atracoes_has_personagem> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Atracoes_has_personagem");
		List<Atracoes_has_personagem> personagensList = new ArrayList<Atracoes_has_personagem>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Atracoes_has_personagem ap = new Atracoes_has_personagem();
			Personagem personagem = personagemDao.buscarPersonagemPorId(rs.getInt("idPersonagem"));
			Atracao atracao = atracaoDao.buscarAtracaoPorId(rs.getInt("idAtracao"));
					
			ap.setPersonagem(personagem);
			ap.setAtracao(atracao);
			personagensList.add(ap);
		}
		return personagensList;
	}
	
	public Atracao buscarAtracaoPorPersonagemkId(int idPersonagem) throws SQLException {
		
		Atracao atracao = new Atracao();
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idAtracao From Atracoes_has_personagem Where idPersonagem ="+ idPersonagem;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		atracao = atracaoDao.buscarAtracaoPorId(rs.getInt("idAtracao"));
		
		return atracao;
	}
	
	public Personagem buscarPersonagemPorAtracaoId(int idAtracao) throws SQLException {
		
		Personagem personagem = new Personagem();
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idPersonagem From Atracoes_has_personagem Where idAtracao ="+ idAtracao;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		personagem = personagemDao.buscarPersonagemPorId(rs.getInt("idPersonagem"));
		
		return personagem;
	}
}
