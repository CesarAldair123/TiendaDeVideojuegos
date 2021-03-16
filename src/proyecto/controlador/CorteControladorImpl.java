package proyecto.controlador;

import java.util.Date;

import javax.swing.JOptionPane;

import java.util.ArrayList;

import proyecto.modelo.dao.CompraDAO;
import proyecto.modelo.dao.CompraDAOImpl;
import proyecto.modelo.dao.CorteDAO;
import proyecto.modelo.dao.CorteDAOImpl;
import proyecto.modelo.dao.VentaDAO;
import proyecto.modelo.dao.VentaDAOImpl;
import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Compra;
import proyecto.modelo.entidades.Corte;
import proyecto.modelo.entidades.Usuario;
import proyecto.modelo.entidades.Venta;
import proyecto.vista.CortePanel;

public class CorteControladorImpl implements CorteControlador{
	
	private CompraDAO compraDAO;
	private VentaDAO ventaDAO;
	
	private CorteDAO corteDAO;
	private Usuario usu;
	private CortePanel vista;
	public CorteControladorImpl(CortePanel vista) {
		this.vista = vista;
		compraDAO = new CompraDAOImpl();
		ventaDAO = new VentaDAOImpl();
		corteDAO = new CorteDAOImpl();
	}

	@Override
	public int inicializar() {
		return 1;
	}

	@Override
	public void generarCorte() {
		if(Conexion.thereIsConnection()) {
			ArrayList<Compra> compras = compraDAO.conseguirCompras();
			ArrayList<Venta> ventas = ventaDAO.conseguirVentas();
			
			float tot = 0;
			float totCompras = 0;
			float totVentas = 0;
			
			for(Compra compra : compras) {
				if(compra.getFecha().toString().equals(new java.sql.Date(new Date().getTime()).toString())) {
					totCompras += compra.getTotal();
				}
			}
			
			for(Venta venta : ventas) {
				if(venta.getFecha().toString().equals(new java.sql.Date(new Date().getTime()).toString())) {
					totVentas += venta.getTotal();
				}
			}
			
			tot = totVentas - totCompras;
			
			Corte corte = new Corte(0,usu.getId(),new java.sql.Date(new Date().getTime()),tot);
			corteDAO.agregarCorte(corte);
			
			vista.getTotalCompras().setText(""+totCompras);
			vista.getTotalVentas().setText(""+totVentas);
			vista.getTotal().setText(""+tot);
			vista.getFecha().setText(""+new java.sql.Date(new Date().getTime()));
		}else {
			JOptionPane.showMessageDialog(vista, "No hay Conexion");
		}
	}

	@Override
	public void setUsuario(Usuario usuario) {
		usu = usuario;
	}

}
