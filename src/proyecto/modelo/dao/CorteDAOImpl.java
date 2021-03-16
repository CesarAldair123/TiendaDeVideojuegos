package proyecto.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Corte;

public class CorteDAOImpl implements CorteDAO{

	Connection conn;
	UsuarioDAO dao;
	
	public CorteDAOImpl(Connection conn) {
		this.conn = conn;
		dao = new UsuarioDAOImpl();
	}
	
	public CorteDAOImpl() {
		dao = new UsuarioDAOImpl();
	}
	
	@Override
	public Corte conseguirCorte(int id) {
		boolean closeConnection = false;
		String query = "SELECT * FROM corte WHERE id_cor = ?";
		Corte cor = null;
		try {
			System.out.println("Consiguiendo corte");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				int idValue = result.getInt("id_cor");
				int usuarioId = result.getInt("idusu_cor");
				Date fecha = result.getDate("fec_cor");
				Float corte = result.getFloat("mon_cor");
				cor = new Corte(idValue, usuarioId, fecha, corte);
				System.out.println("Se encontro el corte");
			}else {
				System.out.println("No se encontro el corte");
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
		return cor;
	}

	@Override
	public ArrayList<Corte> conseguirCortes() {
		boolean closeConnection = false;
		String query = "SELECT * FROM corte";
		ArrayList<Corte> cortes = null;
		try {
			System.out.println("Consiguiendo todos los cortes");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			cortes = new ArrayList<Corte>();
			while(result.next()) {
				int idValue = result.getInt("id_cor");
				int usuarioId = result.getInt("idusu_cor");
				Date fecha = result.getDate("fec_cor");
				Float monto = result.getFloat("mon_cor");
				Corte cor = new Corte(idValue, usuarioId, fecha, monto);
				cortes.add(cor);
			}
			System.out.println("Se encontraron todos los cortes");
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo todos los cortes");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return cortes;
	}

	@Override
	public int agregarCorte(Corte corte) {
		boolean closeConnection = false;
		String query = "INSERT INTO corte (idusu_cor, fec_cor, mon_cor) VALUES (?,?,?)";
		int success = 0;
		try {
			System.out.println("Agregando un corte");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,corte.getUsuarioId());
			ps.setDate(2,corte.getFecha());
			ps.setFloat(3, corte.getMonto());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Corte agregado correctamente");
			}else {
				System.out.println("El corte no se pudo agregar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error agregando un corte");
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
	public int editarCorte(Corte corte) {
		boolean closeConnection = false;
		String query = "UPDATE corte SET idusu_cor = ?, fec_cor = ?, mon_cor = ? WHERE id_cor = ?";
		int success = 0;
		try {
			System.out.println("Editando corte");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,corte.getUsuarioId());
			ps.setDate(2,corte.getFecha());
			ps.setFloat(3, corte.getMonto());
			ps.setInt(4, corte.getId());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Corte editado correctamente");
			}else {
				System.out.println("El corte no se pudo editar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error editando un corte");
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
	public int eliminarCorta(int id) {
		boolean closeConnection = false;
		String query = "DELETE FROM corte WHERE id_cor = ?";
		int success = 0;
		try {
			System.out.println("Eliminando un corte");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Corte elimado correctamente");
			}else {
				System.out.println("El corte no se pudo eliminar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error eliminando un corte");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return success;
	}

}
