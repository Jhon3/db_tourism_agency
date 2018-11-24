package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static String url = "jdbc:postgresql://localhost/db_tourism_agency"; //Caminho do banco
	public static String user = "jhon"; //Nome do owner
	public static String pass = "powerless"; //senha
	
	private static Connection conexao;
	
	public static Connection getConnection() throws SQLException {
		if (conexao == null){
			conexao = fazerConexao();
		}
		return conexao;
	}

	private static Connection fazerConexao() throws SQLException {
		try {
			conexao = DriverManager.getConnection(url, user, pass);
			System.out.println("Conected.");
		} catch (SQLException e) {
			System.out.println("Conection error!!");
		}
		return conexao;
	}			
}