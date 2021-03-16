package proyecto.modelo.negocio;

import java.sql.Connection;

import proyecto.modelo.dao.CompraDAO;
import proyecto.modelo.dao.CompraDAOImpl;
import proyecto.modelo.dao.DetallesCompraDAO;
import proyecto.modelo.dao.DetallesCompraDAOImpl;
import proyecto.modelo.dao.VideojuegoDAO;
import proyecto.modelo.dao.VideojuegoDAOImpl;
import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Compra;
import proyecto.modelo.entidades.DetallesCompra;
import proyecto.modelo.entidades.Videojuego;

public class ProcesarCompra {

	public int procesarCompra(Compra compra) {
		int exito = 0;
		int count = 0;
		if (Conexion.thereIsConnection()) {
			Connection conn = Conexion.getConnection();
			try {
				conn.setAutoCommit(false);
			}catch(Exception e) {
				System.err.println("Hubo un error setteando el autoCommit");
			}
			CompraDAO compraDAO = new CompraDAOImpl(conn);
			DetallesCompraDAO detallesDAO = new DetallesCompraDAOImpl(conn);
			VideojuegoDAO videojuegoDAO = new VideojuegoDAOImpl(conn);

			count += compraDAO.agregarCompra(compra);
			for (DetallesCompra detalles : compra.getDetallesCompra()) {
				Videojuego vid = detalles.getVideojuego();
				int maxStock = videojuegoDAO.checarStock(vid.getId());

				vid.setStock(maxStock + detalles.getCantidad());
				count += detallesDAO.agregarDetalles(detalles);
				count += videojuegoDAO.editarVideojuego(vid);

			}
	
			if (count == compra.getDetallesCompra().size()* 2 + 1) {
				try {
					conn.commit();
					exito = 1;
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
				try {
					conn.rollback();
				} catch (Exception e) {
					System.out.println("No se pudo hacer rollback");
					e.printStackTrace();
				} finally {
					Conexion.closeConnection(conn);
				}
			}
		}
		return exito;
	}

}
