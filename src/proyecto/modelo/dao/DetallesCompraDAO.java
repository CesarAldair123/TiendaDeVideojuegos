package proyecto.modelo.dao;

import java.util.ArrayList;

import proyecto.modelo.entidades.DetallesCompra;

public interface DetallesCompraDAO {
	public ArrayList<DetallesCompra> conseguirDetalles(String compraId);
	public int agregarDetalles(DetallesCompra detalles);
	public int editarDetalles(DetallesCompra detalles);
	public int eliminarDetalles(String id);
}
