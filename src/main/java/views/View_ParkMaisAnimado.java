package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controle.Conexao;

public class View_ParkMaisAnimado 
{
	private Connection conn;
	private List<EventosEAtracoesPorPark> listaParks;
	
	public View_ParkMaisAnimado() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);

		this.listaParks = new ArrayList<EventosEAtracoesPorPark>();
		this.execute_view();
	}
	
	private void execute_view() throws SQLException
	{
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("SELECT p.nome, l.cidade, l.pais, l.moeda, count(ap.idPark) as numero_atracoes, count (e.idPark) as numero_eventos\n" + 
				"FROM park p\n" + 
				"JOIN local_ l on p.idlocal_ = l.idlocal_\n" + 
				"JOIN atracoes_has_park ap on ap.idPark = p.idPark\n" + 
				"JOIN evento e on e.idPark = p.idPark\n" + 
				"Group by p.nome, l.cidade, l.pais, l.moeda");
		rs = pst.executeQuery();
		
		while(rs.next()){
			EventosEAtracoesPorPark e = new EventosEAtracoesPorPark();
			e.setNome(rs.getString("nome"));
			e.setCidade(rs.getString("cidade"));
			e.setPais(rs.getString("pais"));
			e.setMoeda(rs.getString("moeda"));
			e.setNum_atracoes(rs.getInt("numero_eventos"));
			listaParks.add(e);
		}		
	}

	public List<EventosEAtracoesPorPark> getListaParks() {
		return listaParks;
	}	
}