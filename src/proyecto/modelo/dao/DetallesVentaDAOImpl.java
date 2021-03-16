package proyecto.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.DetallesVenta;

public class DetallesVentaDAOImpl implements DetallesVentaDAO{
	
	private Connection conn;
	
	public DetallesVentaDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	
	public DetallesVentaDAOImpl() {
	}

	@Override
	public ArrayList<DetallesVenta> conseguirDetalles(String ventaId) {
		boolean closeConnection = false;
		String query = "SELECT * FROM detallesventa";
		ArrayList<DetallesVenta> detallesC = null;
		try {
			System.out.println("Consiguiendo todas los detalles de compra");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			detallesC = new ArrayList<DetallesVenta>();
			while(result.next()) { 
				String idven = result.getString("idven_detven");
				int idVid = result.getInt("idvid_detven");
				int cant = result.getInt("can_detven");
				float mont = result.getFloat("tot_detven");
				DetallesVenta detven = new DetallesVenta(idven, idVid, cant, mont);
				detallesC.add(detven);
			}
			System.out.println("Se consiguieron todas los detalles de venta!");
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo todas los detalles de venta");
			e.printStackTrace();
		}finally {
			if(closeConnection) {
				Conexion.closeConnection(conn);
				conn = null;
			}
		}
		return detallesC;
	}


	@Override
	public int agregarDetalles(DetallesVenta venta) {
		boolean closeConnection = false;
		String query = "INSERT INTO detallesventa (idvid_detven,idven_detven,can_detven,tot_detven) VALUES (?,?,?,?)";
		int success = 0;
		try {
			System.out.println("Agregando un detalle de vente...");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, venta.getVideojuegoId());
			ps.setString(2, venta.getVentaId());
			ps.setInt(3, venta.getCantidad());
			ps.setFloat(4, venta.getMonto());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Detalle de Vente agregada correctamente");
			}else {
				System.out.println("Detalle de Vente no se pudo agregar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error agregando un detalle de venta");
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
	public int editarDetalles(DetallesVenta venta) {
		boolean closeConnection = false;
		String query = "UPDATE detallesventa SET can_detven = ?, tot_detven = ? WHERE idven_detven = ? AND idvid_detven = ?";
		int success = 0;
		try {
			System.out.println("Editando un detalle de venta");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,venta.getCantidad());
			ps.setFloat(2,venta.getMonto());
			ps.setString(3, venta.getVentaId());
			ps.setInt(4, venta.getVideojuegoId());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Detalle de Venta editada correctamente");
			}else {
				System.out.println("Detalle de Venta no se pudo editar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error editando un detalle de Venta");
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
	public int eliminarDetalles(String venta) {
		boolean closeConnection = false;
		String query = "DELETE FROM detallesventa WHERE idven_detven = ?";
		int success = 0;
		try {
			System.out.println("Eliminando un detalle de venta");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, venta);
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Detalle de venta eliminada correctamente");
			}else {
				System.out.println("Detalle de venta no se pudo eliminar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error eliminando un detalle de venta");
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
