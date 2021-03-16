package proyecto.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Distribuidora;

public class DistribuidoraDAOImpl implements DistribuidoraDAO{
	
	Connection conn;
	
	public DistribuidoraDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	public DistribuidoraDAOImpl() {
		
	}

	@Override
	public Distribuidora conseguirDistribuidora(int id) {
		boolean closeConnection = false;
		String query = "SELECT * FROM distribuidora WHERE id_dis = ?";
		Distribuidora dis = null;
		try {
			System.out.println("Consiguiendo una distribuidora");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				int idValue = result.getInt("id_dis");
				String nombre = result.getString("nom_dis");
				String telefono = result.getString("tel_dis");
				String direccion = result.getString("dir_dis");
				String cuentaBancaria = result.getString("cue_dis");
				dis = new Distribuidora(idValue, nombre, telefono, direccion, cuentaBancaria);
				System.out.println("Se encontro la distribuidora");
			}else {
				System.out.println("No se encontro la distribuidora");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo una distribuidora");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return dis;
	}

	@Override
	public ArrayList<Distribuidora> conseguirDistribuidoras() {
		boolean closeConnection = false;
		String query = "SELECT * FROM distribuidora";
		ArrayList<Distribuidora> distribuidoras = null;
		try {
			System.out.println("Consiguiendo todas las distribuidoras");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			distribuidoras = new ArrayList<Distribuidora>();
			while(result.next()) {
				int idValue = result.getInt("id_dis");
				String nombre = result.getString("nom_dis");
				String telefono = result.getString("tel_dis");
				String direccion = result.getString("dir_dis");
				String cuentaBancaria = result.getString("cue_dis");
				Distribuidora dis = new Distribuidora(idValue, nombre, telefono, direccion, cuentaBancaria);
				distribuidoras.add(dis);
			}
			System.out.println("Se consiguieron todas las distribuidoras!");
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo todas las distribuidoras");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return distribuidoras;
	}

	@Override
	public int agregarDistribuidora(Distribuidora distribuidora) {
		boolean closeConnection = false;
		String query = "INSERT INTO distribuidora (nom_dis,tel_dis,dir_dis,cue_dis) VALUES (?,?,?,?)";
		int success = 0;
		try {
			System.out.println("Agregando una distribuidora");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,distribuidora.getNombre());
			ps.setString(2, distribuidora.getTelefono());
			ps.setString(3,  distribuidora.getDireccion());
			ps.setString(4, distribuidora.getCuentaBancaria());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Distribuidora agregada correctamente");
			}else {
				System.out.println("La distribuidora no se pudo agregar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error agregando una distribuidora");
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
	public int editarDistribuidora(Distribuidora distribuidora) {
			boolean closeConnection = false;
			String query = "UPDATE distribuidora SET nom_dis = ?, tel_dis = ?, dir_dis = ?, cue_dis = ? WHERE id_dis = ?";
			int success = 0;
			try {
				System.out.println("Editando una distribuidora");
				if(conn == null) {
					conn = Conexion.getConnection();
					closeConnection = true;
				}
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1,distribuidora.getNombre());
				ps.setString(2, distribuidora.getTelefono());
				ps.setString(3,  distribuidora.getDireccion());
				ps.setString(4, distribuidora.getCuentaBancaria());
				ps.setInt(5, distribuidora.getId());
				success = ps.executeUpdate();
				if(success == 1) {
					System.out.println("Distribuidora editada correctamente");
				}else {
					System.out.println("La distribuidora no se pudo editar");
				}
			}catch(Exception e) {
				System.err.println("Ocurrio un error editando una distribuidora");
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
	public int eliminarDistribuidora(int id) {
		boolean closeConnection = false;
		String query = "DELETE FROM distribuidora WHERE id_dis = ?";
		int success = 0;
		try {
			System.out.println("Eliminando una distribuidora");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Distribuidora eliminada correctamente");
			}else {
				System.out.println("La distribuidora no se pudo eliminar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error eliminando una distribuidora");
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
