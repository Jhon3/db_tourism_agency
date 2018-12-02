package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controle.Conexao;

public class View_parkVisitas {
	
	private Connection conn;
	private List<VisitasPorPark> listaParks;
	
	public View_parkVisitas() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);

		this.listaParks = new ArrayList<VisitasPorPark>();
		this.execute_view();
	}
	
	private void execute_view() throws SQLException
	{
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("SELECT p.nome, l.cidade, l.pais, l.moeda, count(v.idPacote) as pacotes_comprados \n" + 
				"FROM park p\n" + 
				"JOIN local_ l on p.idlocal_ = l.idlocal_\n" + 
				"JOIN pacote pct on pct.idPark = p.idPark\n" + 
				"JOIN venda v on pct.idPacote = v.iPacote\n" + 
				"Group by p.nome, l.cidade, l.pais, l.moeda");
		rs = pst.executeQuery();
		
		while(rs.next()){
			VisitasPorPark e = new VisitasPorPark();
			e.setNome(rs.getString("nome"));
			e.setCidade(rs.getString("cidade"));
			e.setPais(rs.getString("pais"));
			e.setMoeda(rs.getString("moeda"));
			e.setPacotes_comprados(rs.getInt("pacotes_comprados"));
			listaParks.add(e);
		}		
	}

	public List<VisitasPorPark> getListaParks() {
		return listaParks;
	}	
}
