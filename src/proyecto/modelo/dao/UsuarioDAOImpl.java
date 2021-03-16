package proyecto.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{

	Connection conn;
	
	public UsuarioDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	public UsuarioDAOImpl() {
	}
	
	
	@Override
	public Usuario conseguirUsuario(int id, String nombre, String password) {
		boolean closeConnection = false;
		String query = "SELECT * FROM usuario WHERE id_usu = ? AND nom_usu = ? AND con_usu = ?";
		Usuario usu = null;
		try {
			System.out.println("Consiguiendo usuario");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setString(3, password);
			ResultSet result = ps.executeQuery();
			usu = new Usuario();
			if(result.next()) {
				int idValue = result.getInt("id_usu");
				String nivel = result.getString("niv_usu");
				String nom = result.getString("nom_usu");
				String contrasena = result.getString("con_usu");
				usu = new Usuario(idValue, nivel, nom, contrasena);
				System.out.println("Se encontro el usuario");
			}else {
				System.out.println("No se encontro el usuario");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo el usuario");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return usu;
	}

	@Override
	public ArrayList<Usuario> conseguirUsuarios() {
		boolean closeConnection = false;
		String query = "SELECT * FROM usuario";
		ArrayList<Usuario> usuarios = null;
		try {
			System.out.println("Consiguiendo todos los usuarios");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			usuarios = new ArrayList<Usuario>();
			while(result.next()) {
				int idValue = result.getInt("id_usu");
				String nivel = result.getString("niv_usu");
				String nom = result.getString("nom_usu");
				String contrasena = result.getString("con_usu");
				Usuario usu = new Usuario(idValue, nivel, nom, contrasena);
				usuarios.add(usu);
			}
			System.out.println("Se encontraron todos los usuarios");
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo todos los usuarios");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return usuarios;
	}

	@Override
	public int agregarUsuario(Usuario usuario) {
		boolean closeConnection = false;
		String query = "INSERT INTO usuario (niv_usu, nom_usu, con_usu) VALUES (?,?,?)";
		int success = 0;
		try {
			System.out.println("Agregando un usuario");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,usuario.getNivel());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getPassword());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Usuario agregado correctamente");
			}else {
				System.out.println("El usuario no se pudo agregar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error agregando un usuario");
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
	public int editarUsuario(Usuario usuario) {
		boolean closeConnection = false;
		String query = "UPDATE usuario SET niv_usu = ?, nom_usu = ?, con_usu = ? WHERE id_usu = ?";
		int success = 0;
		try {
			System.out.println("Editando un videojuego");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,usuario.getNivel());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getPassword());
			ps.setInt(4, usuario.getId());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Usuario editado correctamente");
			}else {
				System.out.println("El usuario no se pudo editar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error editando un usuario");
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
	public int eliminarUsuario(int id) {
		boolean closeConnection = false;
		String query = "DELETE FROM usuario WHERE id_usu = ?";
		int success = 0;
		try {
			System.out.println("Eliminando un usuario");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Usuario elimado correctamente");
			}else {
				System.out.println("El usuario no se pudo eliminar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error eliminando un usuario");
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
