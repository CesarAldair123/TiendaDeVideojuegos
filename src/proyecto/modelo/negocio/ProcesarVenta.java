package proyecto.modelo.negocio;

import java.sql.Connection;

import proyecto.modelo.dao.DetallesVentaDAO;
import proyecto.modelo.dao.DetallesVentaDAOImpl;
import proyecto.modelo.dao.VentaDAO;
import proyecto.modelo.dao.VentaDAOImpl;
import proyecto.modelo.dao.VideojuegoDAO;
import proyecto.modelo.dao.VideojuegoDAOImpl;
import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.DetallesVenta;
import proyecto.modelo.entidades.Venta;
import proyecto.modelo.entidades.Videojuego;

public class ProcesarVenta {
	public String procesarVenta(Venta venta) {
		String mensaje = "";
		int count = 0;
		if (Conexion.thereIsConnection()) {
			Connection conn = Conexion.getConnection();
			try {
				conn.setAutoCommit(false);
			} catch (Exception e) {
				System.err.println("Hubo un error setteando el autoCommit");
			}
			VentaDAO ventaDAO = new VentaDAOImpl(conn);
			DetallesVentaDAO detallesDAO = new DetallesVentaDAOImpl(conn);
			VideojuegoDAO videojuegoDAO = new VideojuegoDAOImpl(conn);

			count += ventaDAO.agregarVenta(venta);
			for (DetallesVenta detalles : venta.getDetallesVenta()) {
				Videojuego vid = detalles.getVideojuego();
				int maxStock = videojuegoDAO.checarStock(vid.getId());
				if(maxStock < detalles.getCantidad()) {
					mensaje = "El stock del videojuego: " + detalles.getVideojuego().getNombre() + " es equivocado, solo quedan " + maxStock;
					break;
				}
				vid.setStock(maxStock - detalles.getCantidad());
				count += detallesDAO.agregarDetalles(detalles);
				count += videojuegoDAO.editarVideojuego(vid);

			}

			if (count == venta.getDetallesVenta().size() * 2 + 1) {
				try {
					conn.commit();
					System.out.println("Se ha echo commit");
				} catch (Exception e) {
					try {
						conn.rollback();
					} catch (Exception ex) {
						System.out.println("No se pudo hacer rollback");
						ex.printStackTrace();
					}
				} finally {
					Conexion.closeConnection(conn);
				}
			} else {
				if(mensaje.equals("")) {
					mensaje = "Ocurrio un error procesando la venta";
				}
				try {
					conn.rollback();
				} catch (Exception e) {
					System.out.println("No se pudo hacer rollback");
					e.printStackTrace();
				} finally {
					Conexion.closeConnection(conn);
				}
			}
		}else {
			mensaje = "Ocurrio un error";
		}
		return mensaje;

	}
}
