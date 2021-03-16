package proyecto.modelo.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private static String url = "jdbc:mysql://localhost/tienda_de_videojuegos";
	private static String usuario = "sistema";
	private static String password = "sistema";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			System.out.println("Conectando a la Base de datos...");
			conn = DriverManager.getConnection(url,usuario,password);
			System.out.println("Conexion exitosa!");
		}catch(Exception ex) {
			System.err.println("Ocurrio un error conectando a la base de datos");
			ex.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		try {
			System.out.println("Cerrando una conexion");
			conn.close();
			System.out.println("Se cerro exitosamente!");
		}catch(Exception e) {
			System.err.println("Ocurrio un error cerrando la conexion");
		}
	}
	
	public static boolean thereIsConnection() {
		Connection conn = getConnection();
		if(conn == null) {
			return false;
		} else {
			closeConnection(conn);
			return true;
		}
	}
}
