package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controle.Conexao;
import modelo.Atracao;

public class View_maioresClientes {
private Connection conn;
	
	private int idPark;
	private List<BonsClientes> listaBonsClientes;
	
	public View_maioresClientes(int idPark) throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		this.idPark = idPark;
		this.listaBonsClientes = new ArrayList<BonsClientes>();
		this.execute_view();
	}
	
	private void execute_view() throws SQLException
	{
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("SELECT c.idCliente, p.nome, p.email\n" + 
				"FROM cliente c\n" + 
				"JOIN pessoa p on p.idPessoa = c.idPessoa\n" + 
				"JOIN venda v on v.idCliente = v.idCliente\n" + 
				"Group by c.idCliente, p.nome, p.email\n" + 
				"HAVING count(v.idPacote) >= 3");		
		rs = pst.executeQuery();
		
		while(rs.next()){
			BonsClientes a = new BonsClientes();
			a.setNome(rs.getString("nome"));
			a.setEmail(rs.getString("email"));
			a.setIdCliente(rs.getInt("idade_minima"));
			listaBonsClientes.add(a);
		}		
	}

	public List<BonsClientes> getListaAtracoes() {
		return listaBonsClientes;
	}	
}