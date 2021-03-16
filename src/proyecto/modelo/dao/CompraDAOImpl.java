package proyecto.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Compra;
import proyecto.modelo.entidades.DetallesCompra;

public class CompraDAOImpl implements CompraDAO {
	
	Connection conn;
	DetallesCompraDAO dao;
	
	public CompraDAOImpl(Connection conn) {
		this.conn = conn;
		dao = new DetallesCompraDAOImpl();
	}
	
	public CompraDAOImpl() {
		dao = new DetallesCompraDAOImpl();
	}

	@Override
	public Compra conseguirCompra(String id) {
		boolean closeConnection = false;
		String query = "SELECT * FROM compra WHERE id_com = ?";
		Compra com = null;
		try {
			System.out.println("Realizando una compra...");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				String idValue = result.getString("id_com");
				int idUsu = result.getInt("idusu_com");
				int idDis = result.getInt("iddis_com");
				float tot = result.getFloat("tot_com");
				Date fec = result.getDate("fec_com",Calendar.getInstance());
				ArrayList<DetallesCompra> dCompra = dao.conseguirDetalles(idValue);
				com = new Compra(idValue,idUsu,idDis,fec,tot);
				com.setDetallesCompra(dCompra);
				System.out.println("Se puede realizar la compra");
			}else {
				System.out.println("No se puede realizar la compra");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error al realizar la compra");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return com;
	}

	@Override
	public ArrayList<Compra> conseguirCompras() {
		boolean closeConnection = false;
		String query = "SELECT * FROM compra";
		ArrayList<Compra> compras = null;
		try {
			System.out.println("Consiguiendo todas las compra");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			compras = new ArrayList<Compra>();
			while(result.next()) {
				String idValue = result.getString("id_com");
				int idUsu = result.getInt("idusu_com");
				int idDis = result.getInt("iddis_com");
				float tot = result.getFloat("tot_com");
				Date fec = result.getDate("fec_com",Calendar.getInstance());
				ArrayList<DetallesCompra> dCompra = dao.conseguirDetalles(idValue);
				Compra com = new Compra(idValue, idUsu, idDis, fec, tot);
				com.setDetallesCompra(dCompra);
				compras.add(com);
			}
			System.out.println("Se consiguieron todas las compras!");
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo todas las compras");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return compras;
	}
	
	@Override
	public int agregarCompra(Compra compra) {
		boolean closeConnection = false;
		String query = "INSERT INTO compra (id_com, idusu_com, iddis_com, fec_com, tot_com) VALUES (?,?,?,?,?)";
		int success = 0;
		try {
			System.out.println("Agregando una compra...");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, compra.getId());
			ps.setInt(2, compra.getUsuarioId());
			ps.setInt(3, compra.getDistribuidoraId());
			ps.setDate(4, compra.getFecha(), Calendar.getInstance());
			ps.setFloat(5,compra.getTotal());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Compra agregada correctamente");
			}else {
				System.out.println("La compra no se pudo agregar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error agregando una compra");
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
	public int editarCompra(Compra compra) {
		boolean closeConnection = false;
		String query = "UPDATE compra SET fec_com = ? WHERE idusu_com = ?, iddis_com = ?, tot_com = ? WHERE id_com = ?";
		int success = 0;
		try {
			System.out.println("Editando una compra");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1,compra.getFecha());
			ps.setInt(2, compra.getUsuarioId());
			ps.setInt(3, compra.getDistribuidoraId());
			ps.setFloat(4, compra.getTotal());
			ps.setString(5, compra.getId());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Compra editada correctamente");
			}else {
				System.out.println("La compra no se pudo editar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error editando una compra");
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
	public int eliminarCompra(String id) {
		boolean closeConnection = false;
		String query = "DELETE FROM compra WHERE id_com = ?";
		int success = 0;
		try {
			System.out.println("Eliminando una compra");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Compra eliminada correctamente");
			}else {
				System.out.println("La compra no se pudo eliminar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error eliminando una compra");
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
