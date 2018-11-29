package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Restaurante;

public class RestauranteDao {
	
	private Connection conn;
	private ParkDao parkDao;
	
	public RestauranteDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		parkDao = new ParkDao();
	}
	
	public void inserirRestaurante(Restaurante restaurante) throws SQLException {
		String sql = "Insert into Restaurante values(default, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, restaurante.getNome());
		pst.setString(2, restaurante.getPontoReferencia());
		pst.setString(3, restaurante.getTipoCulinaria());
		pst.setString(4, restaurante.getTipoRestaurante());
		pst.setString(5, restaurante.getFaixaPrecos());
		pst.setString(6, restaurante.getHorarioAbertura());
		pst.setString(7, restaurante.getHorarioEncerramento());
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Restaurante " + restaurante.getNome()+ " cadastrado(a).");
	}
	
	public void alterarRestaurante(Restaurante restaurante) throws SQLException{
		String sql = "update Restaurante set nome = ? where ponto_referencia = ? AND"
				+ " tipo_culinaria = ? AND tipo_restaurante = ? AND faixa_precos = ? AND"
				+ " horario_abertura = ? AND horario_encerramento = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, restaurante.getNome());
		pstmt.setString(2, restaurante.getPontoReferencia());
		pstmt.setString(3, restaurante.getTipoCulinaria());
		pstmt.setString(4, restaurante.getTipoRestaurante());
		pstmt.setString(5, restaurante.getFaixaPrecos());
		pstmt.setString(6, restaurante.getHorarioAbertura());
		pstmt.setString(7, restaurante.getHorarioEncerramento());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Restaurante " + restaurante.getNome()+ " alterado(a).");
	}
	
	public void deletarRestaurante(Restaurante restaurante) throws SQLException{
		String sql = "delete from Restaurante where nome like '% ? %'";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1, restaurante.getNome());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Restaurante " + restaurante.getNome()+ " deletado(a).");
	}
	
	
	public List<Restaurante> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from Restaurante");
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Restaurante r = new Restaurante();
			r.setIdRestaurante(rs.getInt("idRestaurante"));
			r.setNome(rs.getString("nome"));
			r.setPontoReferencia(rs.getString("ponto_referencia"));
			r.setTipoCulinaria(rs.getString("tipo_culinaria"));
			r.setTipoRestaurante(rs.getString("tipo_restaurante"));
			r.setFaixaPrecos(rs.getString("faixa_precos"));
			r.setHorarioAbertura(rs.getString("horario_abertura"));
			r.setHorarioEncerramento(rs.getString("horario_encerramento"));
			restaurantes.add(r);
		}
		return restaurantes;
	}
	
	
	public int buscarRestaurante(String restauranteNome, String nomePark) throws SQLException {
		int idPark = parkDao.buscarIdPark(nomePark);
		int id=0;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idRestaurante From restaurante r join restaurante_has_park p on p." +idPark+ " = r.idPark where r.nome ="+ restauranteNome;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idRestaurante");
		}
		return id;
	}
	
	public Restaurante buscarRestaurantePorId(int idRestaurante) throws SQLException {
		Restaurante restaurante = new Restaurante();
		int id = idRestaurante;
		String nome = null;
		String ponto_referencia = null;
		String tipo_culinaria = null;
		String tipo_restaurante = null;
		String faixa_precos = null;
		String horario_abertura = null;
		String horario_encerramento = null;

		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		
		String sql = "Select nome, ponto_referencia, tipo_culinaria, tipo_restaurante, faixa_precos"
				+ ", horario_abertura, horario_encerramento From restaurante Where idrestaurante ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			nome = rs.getString("nome");
			ponto_referencia = rs.getString("ponto_referencia");
			tipo_culinaria = rs.getString("tipo_culinaria");
			tipo_restaurante = rs.getString("tipo_restaurante");
			faixa_precos = rs.getString("faixa_precos");
			horario_abertura = rs.getString("horario_abertura");
			horario_encerramento = rs.getString("horario_encerramento");
		}
		
		restaurante.setNome(nome);
		restaurante.setIdRestaurante(id);
		restaurante.setFaixaPrecos(faixa_precos);
		restaurante.setHorarioAbertura(horario_abertura);
		restaurante.setHorarioEncerramento(horario_encerramento);
		restaurante.setPontoReferencia(ponto_referencia);
		restaurante.setTipoCulinaria(tipo_culinaria);
		restaurante.setTipoRestaurante(tipo_restaurante);
		
		return restaurante;
	}
	
	
	public static void main(String[] args) throws SQLException {
		
		/*
		RestauranteDao RestauranteDao = new RestauranteDao();
		
		Restaurante Restaurante1 = new Restaurante();
		Restaurante1.setNome("castelo");
		Restaurante1.setAlturaMinima((float) 1.70);
		Restaurante1.setDescricao("castelo qualquer");
		Restaurante1.setHorarioAbertura("13h");
		Restaurante1.setHorarioEncerramento("18h");
		Restaurante1.setIdadeMinima(18);
		Restaurante1.setLimitePessoas(30);
		*/
		//RestauranteDao.inserirRestaurante(Restaurante1);
		

		/*
		Restaurante2.setNome("castelo2");
		Restaurante2.setAlturaMinima((float) 1.70);
		Restaurante2.setDescricao("castelo qualquer");
		Restaurante2.setHorarioAbertura("13h");
		Restaurante2.setHorarioEncerramento("18h");
		Restaurante2.setIdadeMinima(18);
		Restaurante2.setLimitePessoas(30);
		
		RestauranteDao.inserirRestaurante(Restaurante2);
		*/

	
		
		//Restaurante1.setNome("jhon1");
		//RestauranteDao.alterarRestaurante(Restaurante1);
		//RestauranteDao.deletarRestaurante(Restaurante1);
		
		/*
		ArrayList<Restaurante> list = (ArrayList<Restaurante>) RestauranteDao.listar();
		for(Restaurante a: list) {
			System.out.println(a.getNome() + " --- ");
		}
		*/
		
		/*
		int idJhon = RestauranteDao.buscarRestaurante("castelo");
		System.out.println(idJhon);
		*/
	}
}
