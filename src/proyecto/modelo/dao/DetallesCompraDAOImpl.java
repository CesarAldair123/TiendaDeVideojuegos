package proyecto.modelo.dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.DetallesCompra;


public class DetallesCompraDAOImpl implements DetallesCompraDAO {
	

	Connection conn;
	
	public DetallesCompraDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	public DetallesCompraDAOImpl() {
	}
	
	@Override
	public ArrayList<DetallesCompra> conseguirDetalles(String compraId) {
		boolean closeConnection = false;
		String query = "SELECT * FROM detallescompra";
		ArrayList<DetallesCompra> detallesC = null;
		try {
			System.out.println("Consiguiendo todas los detalles de compra");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			detallesC = new ArrayList<DetallesCompra>();
			while(result.next()) { 
				String idCom = result.getString("idcom_detcom");
				int idVid = result.getInt("idvid_detcom");
				int cant = result.getInt("can_detcom");
				float mont = result.getFloat("tot_detcom");
				DetallesCompra detcom = new DetallesCompra(idCom, idVid, cant, mont);
				detallesC.add(detcom);
			}
			System.out.println("Se consiguieron todas los detalles de compra!");
		}catch(Exception e) {
			System.err.println("Ocurrio un error consiguiendo todas los detalles de compra");
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
	public int agregarDetalles(DetallesCompra detalles) {
		boolean closeConnection = false;
		String query = "INSERT INTO detallescompra (idvid_detcom,idcom_detcom,can_detcom,tot_detcom) VALUES (?,?,?,?)";
		int success = 0;
		try {
			System.out.println("Agregando un detalle de compra...");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, detalles.getVideojuegoId());
			ps.setString(2, detalles.getCompraId());
			ps.setInt(3, detalles.getCantidad());
			ps.setFloat(4, detalles.getMonto());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Detalle de Compra agregada correctamente");
			}else {
				System.out.println("Detalle de Compra no se pudo agregar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error agregando un detalle de Compra");
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
	public int editarDetalles(DetallesCompra detalles) {
		boolean closeConnection = false;
		String query = "UPDATE detallescompra SET can_detcom = ?, tot_detcom = ? WHERE idcom_detcom = ? AND idvid_detcom = ?";
		int success = 0;
		try {
			System.out.println("Editando un detalle de compra");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,detalles.getCantidad());
			ps.setFloat(2,detalles.getMonto());
			ps.setString(3, detalles.getCompraId());
			ps.setInt(4, detalles.getVideojuegoId());
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Detalle de Compra editada correctamente");
			}else {
				System.out.println("Detalle de Compra no se pudo editar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error editando un detalle de Compra");
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
	public int eliminarDetalles(String id) {
		boolean closeConnection = false;
		String query = "DELETE FROM detallescompra WHERE idcom_detcom = ?";
		int success = 0;
		try {
			System.out.println("Eliminando un detalle de compra");
			if(conn == null) {
				conn = Conexion.getConnection();
				closeConnection = true;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			success = ps.executeUpdate();
			if(success == 1) {
				System.out.println("Detalle de compra eliminada correctamente");
			}else {
				System.out.println("Detalle de compra no se pudo eliminar");
			}
		}catch(Exception e) {
			System.err.println("Ocurrio un error eliminando un detalle de compra");
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
