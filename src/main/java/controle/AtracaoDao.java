package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Atracao;

public class AtracaoDao {
	
	private Connection conn;
	private ParkDao parkDao;
	
	public AtracaoDao() throws SQLException{
		conn = Conexao.getConnection();
		conn.setAutoCommit(false);
		parkDao = new ParkDao();
	}
	
	public void inserirAtracao(Atracao atracao) throws SQLException {
		String sql = "Insert into atracao values(default, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, atracao.getNome());
		pst.setString(2, atracao.getDescricao());
		pst.setInt(3, atracao.getIdadeMinima());
		pst.setInt(4, atracao.getLimitePessoas());
		pst.setString(5, atracao.getHorarioAbertura());
		pst.setString(6, atracao.getHorarioEncerramento());
		pst.setFloat(7, atracao.getAlturaMinima());
		pst.execute();
		conn.commit();
		pst.close();
		System.out.println("Atraco " + atracao.getNome()+ " cadastrado(a).");
	}
	
	public void alterarAtracao(Atracao atracao) throws SQLException{
		String sql = "update atracao set nome = ?, descricao = ?,"
				+ " idade_minima = ?, limite_pessoas = ?, horario_abertura = ?,"
				+ " horario_encerramento = ?, altura_minima = ? where idAtracao = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, atracao.getNome());
		pstmt.setString(2, atracao.getDescricao());
		pstmt.setInt(3, atracao.getIdadeMinima());
		pstmt.setInt(4, atracao.getLimitePessoas());
		pstmt.setString(5, atracao.getHorarioAbertura());
		pstmt.setString(6, atracao.getHorarioEncerramento());
		pstmt.setFloat(7, atracao.getAlturaMinima());
		pstmt.setInt(8, atracao.getIdAtracao());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Atracao " + atracao.getNome()+ " alterado(a).");
	}
	
	public void deletarAtracao(Atracao atracao) throws SQLException{
		String sql = "delete from atracao where idAtracao = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1, atracao.getIdAtracao());
		pstmt.execute();
		conn.commit();
		pstmt.close();
		System.out.println("Atracao " + atracao.getNome()+ " deletado(a).");
	}
	
	
	public List<Atracao> listar() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null; 
		pst = conn.prepareStatement("Select * from atracao");
		List<Atracao> atracoes = new ArrayList<Atracao>();
		rs = pst.executeQuery(); 
		
		while(rs.next()){ 
			Atracao a = new Atracao();
			a.setIdAtracao(rs.getInt("idAtracao"));
			a.setNome(rs.getString("nome"));
			a.setDescricao(rs.getString("descricao"));
			a.setIdadeMinima(rs.getInt("idade_minima"));
			a.setLimitePessoas(rs.getInt("limite_pessoas"));
			a.setHorarioAbertura(rs.getString("horario_abertura"));
			a.setHorarioEncerramento(rs.getString("horario_encerramento"));
			a.setAlturaMinima(rs.getFloat("altura_minima"));
			atracoes.add(a);
		}
		return atracoes;
	}
	
	
	public int buscarIdAtracao(String atracaoNome, String nomePark) throws SQLException {
		int idPark = parkDao.buscarIdPark(nomePark);
		int id=0;
		PreparedStatement pst = null; 
		ResultSet rs = null;
		String sql = "Select idAtracao From atracao a join atracoes_has_park p on p." +idPark+ " = a.idPark where a.nome ="+ atracaoNome;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			id = rs.getInt("idAtracao");
		}
		return id;
	}
	
	public Atracao buscarAtracaoPorId(int idAtracao) throws SQLException {
		Atracao atracao = new Atracao();
		int id = idAtracao;
		String nome = null;
		String descricao = null;
		int idade_minima = 0;
		int limite_pessoas = 0;
		String horario_abertura = null;
		String horario_encerramento = null;
		float altura_minima = 0;
		
		PreparedStatement pst = null; 
		ResultSet rs = null;
		
		String sql = "Select nome, descricao, idade_minima, limite_pessoas, horario_abertura"
				+ ", horario_encerramento, altura_minima From Atracao Where idAtracao ="+ id;

		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			nome = rs.getString("nome");
			descricao = rs.getString("descricao");
			idade_minima = rs.getInt("idade_minima");
			limite_pessoas = rs.getInt("limite_pessoas");
			horario_abertura = rs.getString("horario_abertura");
			horario_encerramento = rs.getString("horario_encerramento");
			altura_minima = rs.getFloat("altura_minima");
		}
		atracao.setNome(nome);
		atracao.setIdAtracao(id);
		atracao.setAlturaMinima(altura_minima);
		atracao.setDescricao(descricao);
		atracao.setHorarioAbertura(horario_abertura);
		atracao.setHorarioEncerramento(horario_encerramento);
		atracao.setIdadeMinima(idade_minima);
		atracao.setLimitePessoas(limite_pessoas);
		
		return atracao;
	}
	
	
	public static void main(String[] args) throws SQLException {
		
		/*
		AtracaoDao atracaoDao = new AtracaoDao();
		
		Atracao atracao1 = new Atracao();
		atracao1.setNome("castelo");
		atracao1.setAlturaMinima((float) 1.70);
		atracao1.setDescricao("castelo qualquer");
		atracao1.setHorarioAbertura("13h");
		atracao1.setHorarioEncerramento("18h");
		atracao1.setIdadeMinima(18);
		atracao1.setLimitePessoas(30);
		*/
		//atracaoDao.inserirAtracao(atracao1);
		

		/*
		atracao2.setNome("castelo2");
		atracao2.setAlturaMinima((float) 1.70);
		atracao2.setDescricao("castelo qualquer");
		atracao2.setHorarioAbertura("13h");
		atracao2.setHorarioEncerramento("18h");
		atracao2.setIdadeMinima(18);
		atracao2.setLimitePessoas(30);
		
		atracaoDao.inserirAtracao(atracao2);
		*/

	
		
		//atracao1.setNome("jhon1");
		//atracaoDao.alterarAtracao(atracao1);
		//atracaoDao.deletarAtracao(atracao1);
		
		/*
		ArrayList<Atracao> list = (ArrayList<Atracao>) atracaoDao.listar();
		for(Atracao a: list) {
			System.out.println(a.getNome() + " --- ");
		}
		*/
		
		/*
		int idJhon = atracaoDao.buscarAtracao("castelo");
		System.out.println(idJhon);
		*/
	}
}
