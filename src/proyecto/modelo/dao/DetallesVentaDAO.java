package proyecto.modelo.dao;

import java.util.ArrayList;

import proyecto.modelo.entidades.DetallesVenta;

public interface DetallesVentaDAO {
	public ArrayList<DetallesVenta> conseguirDetalles(String ventaId);
	public int agregarDetalles(DetallesVenta venta);
	public int editarDetalles(DetallesVenta venta);
	public int eliminarDetalles(String venta);
}
