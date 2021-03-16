package proyecto.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Distribuidora;
import proyecto.modelo.entidades.Videojuego;

public class VideojuegoDAOImpl implements VideojuegoDAO{
	
	Connection conn;
	DistribuidoraDAO dao;
	
	public VideojuegoDAOImpl(Connection conn) {
		this.conn = conn;
		dao = new DistribuidoraDAOImpl();
	}
	
	public VideojuegoDAOImpl() {
		dao = new DistribuidoraDAOImpl();
	}
	
	@Override
	public Videojuego conseguirVideojuego(int id) {
		boolean closeConnection = false;
		String query = "SELECT * FROM videojuego WHERE id_vid = ?";
		Videojuego vid = null;
		try {
			System.out.println("Consiguiendo un videojuego");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				int idValue = result.getInt("id_vid");
				int distribuidoraId = result.getInt("iddis_vid");
				String nombre = result.getString("nom_vid");
				String desarrolladora = result.getString("des_vid");
				String consola = result.getString("con_vid");
				String clasificacion = result.getString("cla_vid");
				String generos = result.getString("gen_vid");
				float precio = result.getFloat("pre_vid");
				int stock = result.getInt("stock_vid");
				Distribuidora dis = dao.conseguirDistribuidora(distribuidoraId);
				vid = new Videojuego(idValue, distribuidoraId, nombre, desarrolladora, consola, clasificacion, generos, precio, stock);
				vid.setDistribuidora(dis);
				System.out.println("Se encontro el videojuego");
			}else {
				System.out.println("No se encontro la videojuego");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo un videojuego");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return vid;
	}

	@Override
	public ArrayList<Videojuego> conseguirVideojuegos() {
		boolean closeConnection = false;
		String query = "SELECT * FROM videojuego";
		ArrayList<Videojuego> videojuegos = null;
		try {
			System.out.println("Consiguiendo todos los videojuegos");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			videojuegos = new ArrayList<Videojuego>();
			while(result.next()) {
				int idValue = result.getInt("id_vid");
				int distribuidoraId = result.getInt("iddis_vid");
				String nombre = result.getString("nom_vid");
				String desarrolladora = result.getString("des_vid");
				String consola = result.getString("con_vid");
				String clasificacion = result.getString("cla_vid");
				String generos = result.getString("gen_vid");
				float precio = result.getFloat("pre_vid");
				int stock = result.getInt("stock_vid");
				Distribuidora dis = dao.conseguirDistribuidora(distribuidoraId);
				Videojuego vid = new Videojuego(idValue, distribuidoraId, nombre, desarrolladora, consola, clasificacion, generos, precio, stock);
				vid.setDistribuidora(dis);
				videojuegos.add(vid);
			}
			System.out.println("Se encontraron todos los videojuegos");
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo todos los videojuego");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return videojuegos;
	}

	@Override
	public int agregarVideojuego(Videojuego videojuego) {
		boolean closeConnection = false;
		String query = "INSERT INTO videojuego (iddis_vid, nom_vid, des_vid, con_vid, cla_vid, gen_vid, pre_vid, stock_vid) VALUES (?,?,?,?,?,?,?,?)";
		int success = 0;
		try {
			System.out.println("Agregando un videojuego");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,videojuego.getDistribuidoraId());
			ps.setString(2, videojuego.getNombre());
			ps.setString(3, videojuego.getDesarrolladora());
			ps.setString(4, videojuego.getConsola());
			ps.setString(5, videojuego.getClasificacion());
			ps.setString(6, videojuego.getGeneros());
			ps.setFloat(7, videojuego.getPrecio());
			ps.setInt(8, videojuego.getStock());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Videojuego agregado correctamente");
			}else {
				System.out.println("El videojuego no se pudo agregar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error agregando un videojuego");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return success;
	}

	@Override
	public int editarVideojuego(Videojuego videojuego) {
		boolean closeConnection = false;
		String query = "UPDATE videojuego SET iddis_vid = ?, nom_vid = ?, des_vid = ?, con_vid = ?, cla_vid = ?, gen_vid = ?, pre_vid = ?, stock_vid = ? WHERE id_vid = ?";
		int success = 0;
		try {
			System.out.println("Editando un videojuego");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,videojuego.getDistribuidoraId());
			ps.setString(2, videojuego.getNombre());
			ps.setString(3, videojuego.getDesarrolladora());
			ps.setString(4, videojuego.getConsola());
			ps.setString(5, videojuego.getClasificacion());
			ps.setString(6, videojuego.getGeneros());
			ps.setFloat(7, videojuego.getPrecio());
			ps.setInt(8, videojuego.getStock());
			ps.setInt(9, videojuego.getId());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Videojuego editado correctamente");
			}else {
				System.out.println("El videojuego no se pudo editar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error editando un videojuego");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return success;
	}

	@Override
	public int eliminarVideojuego(int id) {
		boolean closeConnection = false;
		String query = "DELETE FROM videojuego WHERE id_vid = ?";
		int success = 0;
		try {
			System.out.println("Eliminando un videojuego");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Videojuego elimado correctamente");
			}else {
				System.out.println("El videojuego no se pudo eliminar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error eliminando un videojuego");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return success;
	}
	
	@Override
	public int checarStock(int id) {
		boolean closeConnection = false;
		String query = "SELECT stock_vid FROM videojuego WHERE id_vid = ?";
		int stock = -1;
		try {
			System.out.println("Consiguiendo el stock");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				stock = result.getInt("stock_vid");
				System.out.println("Stock conseguido");
			}else {
				System.out.println("No se encontro el stock");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo el stock");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return stock;
	}

}
