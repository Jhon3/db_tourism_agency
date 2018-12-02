package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controle.Conexao;
import modelo.Atracao;

public class View_AtracoesPorPark {
	
	private Connection conn;
	
	private int idPark;
	private List<Atracao> listaAtracoes;
	
	public View_AtracoesPorPark(int idPark) throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		this.idPark = idPark;
		this.listaAtracoes = new ArrayList<Atracao>();
		this.execute_view();
	}
	
	private void execute_view() throws SQLException
	{
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("SELECT a.nome, a.descricao, a.idade_minima, a.limite_pessoas, a.horario_abertura, a.horario_encerramento, a.altura_minima\n" + 
				"FROM atracao a\n" + 
				"JOIN atracoes_has_park ap on ap.idAtracoes = a.idAtracao\n" + 
				"WHERE ap.idPark = " + idPark);		
		rs = pst.executeQuery();
		
		while(rs.next()){
			Atracao a = new Atracao();
			a.setNome(rs.getString("nome"));
			a.setDescricao(rs.getString("descricao"));
			a.setIdadeMinima(rs.getInt("idade_minima"));
			a.setLimitePessoas(rs.getInt("limite_pessoas"));
			a.setHorarioAbertura(rs.getString("horario_abertura"));
			a.setHorarioEncerramento(rs.getString("horario_encerramento"));
			a.setAlturaMinima(rs.getFloat("altura_minima"));
			listaAtracoes.add(a);
		}		
	}

	public List<Atracao> getListaAtracoes() {
		return listaAtracoes;
	}	
	
}
