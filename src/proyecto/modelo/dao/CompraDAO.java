package proyecto.modelo.dao;

import java.util.ArrayList;

import proyecto.modelo.entidades.Compra;

public interface CompraDAO {
	public Compra conseguirCompra(String id);
	public ArrayList<Compra> conseguirCompras();
	public int agregarCompra(Compra compra);
	public int editarCompra(Compra compra);
	public int eliminarCompra(String id);
}
