package proyecto.modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.DetallesVenta;
import proyecto.modelo.entidades.Venta;

public class VentaDAOImpl implements VentaDAO{
	
	private Connection conn;
	private DetallesVentaDAO dao;
	
	public VentaDAOImpl(Connection conn) {
		this.conn = conn;
		dao = new DetallesVentaDAOImpl();
	}
	
	public VentaDAOImpl() {
		dao = new DetallesVentaDAOImpl();
	}
	
	@Override
	public Venta conseguirVenta(String id) {
		boolean closeConnection = false;
		String query = "SELECT * FROM venta WHERE id_ven = ?";
		Venta ven = null;
		try {
			System.out.println("Realizando una venta...");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				String idValue = result.getString("id_ven");
				int idUsu = result.getInt("idusu_ven");
				float tot = result.getFloat("tot_ven");
				Date fec = result.getDate("fec_ven",Calendar.getInstance());
				ArrayList<DetallesVenta> dCompra = dao.conseguirDetalles(idValue);
				ven = new Venta(idValue,idUsu,fec,tot);
				ven.setDetallesVenta(dCompra);
				System.out.println("Se puede realizar la venta");
			}else {
				System.out.println("No se puede realizar la venta");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error al realizar la venta");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return ven;
	}

	@Override
	public ArrayList<Venta> conseguirVentas() {
		boolean closeConnection = false;
		String query = "SELECT * FROM venta";
		ArrayList<Venta> ventas = null;
		try {
			System.out.println("Consiguiendo todas las venta");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			ventas = new ArrayList<Venta>();
			while(result.next()) {
				String idValue = result.getString("id_ven");
				int idUsu = result.getInt("idusu_ven");
				float tot = result.getFloat("tot_ven");
				Date fec = result.getDate("fec_ven",Calendar.getInstance());
				ArrayList<DetallesVenta> dCompra = dao.conseguirDetalles(idValue);
				Venta ven = new Venta(idValue, idUsu, fec, tot);
				ven.setDetallesVenta(dCompra);
				ventas.add(ven);
			}
			System.out.println("Se consiguieron todas las ventas!");
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo todas las ventas");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return ventas;
	}

	@Override
	public int agregarVenta(Venta venta) {
		boolean closeConnection = false;
		String query = "INSERT INTO venta (id_ven, idusu_ven, fec_ven, tot_ven) VALUES (?,?,?,?)";
		int success = 0;
		try {
			System.out.println("Agregando una venta...");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, venta.getId());
			ps.setInt(2, venta.getUsuarioId());
			ps.setDate(3, venta.getFecha());
			ps.setFloat(4,venta.getTotal());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Venta agregada correctamente");
			}else {
				System.out.println("La venta no se pudo agregar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error agregando una venta");
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
	public int editarVenta(Venta venta) {
		boolean closeConnection = false;
		String query = "UPDATE venta SET fec_ven = ? WHERE idusu_ven = ?, tot_ven = ? WHERE id_ven = ?";
		int success = 0;
		try {
			System.out.println("Editando una venta");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1,venta.getFecha());
			ps.setInt(2, venta.getUsuarioId());
			ps.setFloat(3, venta.getTotal());
			ps.setString(4, venta.getId());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Venta editada correctamente");
			}else {
				System.out.println("La venta no se pudo editar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error editando una venta");
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
	public int eliminarVenta(String id) {
		boolean closeConnection = false;
		String query = "DELETE FROM venta WHERE id_ven = ?";
		int success = 0;
		try {
			System.out.println("Eliminando una venta");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Venta eliminada correctamente");
			}else {
				System.out.println("La venta no se pudo eliminar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error eliminando una venta");
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
