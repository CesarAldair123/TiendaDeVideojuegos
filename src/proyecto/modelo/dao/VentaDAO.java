package proyecto.modelo.dao;

import java.util.ArrayList;

import proyecto.modelo.entidades.Venta;

public interface VentaDAO {
	public Venta conseguirVenta(String id);
	public ArrayList<Venta> conseguirVentas();
	public int agregarVenta(Venta venta);
	public int editarVenta(Venta venta);
	public int eliminarVenta(String id);
}
