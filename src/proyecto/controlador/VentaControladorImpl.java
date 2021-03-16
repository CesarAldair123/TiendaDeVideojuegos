package proyecto.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.JOptionPane;

import proyecto.modelo.dao.VideojuegoDAO;
import proyecto.modelo.dao.VideojuegoDAOImpl;
import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Venta;
import proyecto.modelo.entidades.Videojuego;
import proyecto.modelo.negocio.ProcesarVenta;
import proyecto.utilidades.Constantes;
import proyecto.vista.VentaPanel;
import proyecto.modelo.entidades.*;

public class VentaControladorImpl implements VentaControlador{
	
	private VideojuegoDAO vidDAO;
	private ArrayList<Videojuego> videojuegos;
	private Venta venta;
	private VentaPanel vista;
	private Usuario usuario;
	
	public VentaControladorImpl(VentaPanel vista) {
		this.vista = vista;
		vidDAO = new VideojuegoDAOImpl();
	}

	@Override
	public int inicializar() {
		if(Conexion.thereIsConnection()) {
			venta = new Venta();
			venta.setId(UUID.randomUUID().toString());
			venta.setDetallesVenta(new ArrayList<DetallesVenta>());
			venta.setUsuarioId(usuario.getId());
			vista.llenarTabla(venta.getDetallesVenta());
			
			ArrayList<Videojuego> videojuegos = vidDAO.conseguirVideojuegos();
			this.videojuegos = videojuegos;
			vista.setVideojuegosEnELCombo(videojuegos);
			return 1;
		}else {
			JOptionPane.showMessageDialog(vista, "No hay Conexion");
			return 0;
		}
	}

	@Override
	public void filtrarPorConsola() {
		filtrar();
	}

	@Override
	public void filtrarPorGenero() {
		filtrar();
	}
	
	@Override
	public void filtrarPorNombre() {
		filtrar();
	}

	@Override
	public void agregar() {
		String vidString = (String)vista.getVideojuego().getSelectedItem();
		Videojuego videojuego = null;
		if(vidString == null) {
			JOptionPane.showMessageDialog(vista, "Introduce un videojuego valido");
			return;
		}
		int cantidad = (int)vista.getCantidad().getValue();
		if(cantidad < 0) {
			JOptionPane.showMessageDialog(vista, "Introduce una cantidad valida");
			return;
		}else{
			int idVid = Integer.parseInt(vidString.split("-")[0].trim());
			if(cantidad == 0) {
				for(int i = 0; i < venta.getDetallesVenta().size(); i++) {
					if(venta.getDetallesVenta().get(i).getVideojuegoId() == idVid) {
						float tot = venta.getDetallesVenta().get(i).getMonto();
						venta.setTotal(venta.getTotal() - tot);
						venta.getDetallesVenta().remove(i);
						break;
					}
				}
				vista.getTotal().setText(""+venta.getTotal());
				vista.llenarTabla(venta.getDetallesVenta());
				return;
			}
			for(int i = 0; i < this.videojuegos.size(); i++) {
				if(this.videojuegos.get(i).getId() == idVid) {
					videojuego = videojuegos.get(i);
				}
			}
			if(videojuego.getStock() < cantidad) {
				JOptionPane.showMessageDialog(vista, "Introduce un stock dentro del rango");
				return;
			}
			for(int i = 0; i < venta.getDetallesVenta().size(); i++) {
				if(venta.getDetallesVenta().get(i).getVideojuegoId() == videojuego.getId()) {
					float tot = venta.getDetallesVenta().get(i).getMonto();
					venta.setTotal(venta.getTotal()-tot);
					venta.getDetallesVenta().remove(i);
					break;
				}
			}
			float total = cantidad * videojuego.getPrecio();
			venta.setTotal(venta.getTotal()+total);
			DetallesVenta detalles = new DetallesVenta(venta.getId(),videojuego.getId(),cantidad,total);
			detalles.setVideojuego(videojuego);
			venta.getDetallesVenta().add(detalles);
			vista.getTotal().setText(""+venta.getTotal());
			vista.llenarTabla(venta.getDetallesVenta());
		}
	}

	@Override
	public void cancelar() {
		int i = JOptionPane.showOptionDialog(vista, "Estas seguro?", "Cancelar Venta", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(i == 0) {
			inicializar();
			vista.getTotal().setText("0");
		}
	}

	@Override
	public void procesarVenta() {
		if(venta.getDetallesVenta().size() == 0) {
			JOptionPane.showMessageDialog(vista, "Introduce por lo menos un videojuego antes de proceder");
			return;
		}
		ProcesarVenta proceso = new ProcesarVenta();
		float total = 0;
		for(DetallesVenta ven : venta.getDetallesVenta()) {
			total += ven.getMonto();
		}
		venta.setTotal(total);
		venta.setFecha(new java.sql.Date(new Date().getTime()));
		String success = proceso.procesarVenta(venta);
		if(success.equals("")) {
			JOptionPane.showMessageDialog(vista, "La venta se realizo correctamente");
			inicializar();
			vista.getTotal().setText("0");
		}else {
			JOptionPane.showMessageDialog(vista, success);
		}
	}

	@Override
	public void setUsuario(Usuario usu) {
		usuario = usu;
	}
	
	public void filtrar() {
		String select = (String) vista.getConsola().getSelectedItem();
		ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>(this.videojuegos);
		if (!select.equals("Todas")) {
			for (Videojuego vid : this.videojuegos) {
				if (!vid.getConsola().equals(select)) {
					videojuegos.remove(vid);
				}
			}
			vista.setVideojuegosEnELCombo(videojuegos);
		} else {
			vista.setVideojuegosEnELCombo(this.videojuegos);
		}
		List<String> generos = vista.getGeneros().getSelectedValuesList();
		ArrayList<Videojuego> videojuegos2 = new ArrayList<Videojuego>(videojuegos);
		if (generos.size() != 0 && generos.size() != Constantes.generos.length) {
			for (Videojuego vid : videojuegos) {
				String[] generosInGame = vid.getGeneros().split(",");
				boolean toDelete = true;
				for (String genero : generos) {
					for (String generoIn : generosInGame) {
						if (generoIn.equals(genero)) {
							toDelete = false;
						}
					}
				}
				if (toDelete) {
					videojuegos2.remove(vid);
				}
			}
			vista.setVideojuegosEnELCombo(videojuegos2);
		}

		String nombre = vista.getNombre().getText().toLowerCase().replace(" ","");
		if (!nombre.equals("")) {
			ArrayList<Videojuego> videojuegos3 = new ArrayList<Videojuego>(videojuegos2);
			for (Videojuego videojuego : videojuegos2) {
				if(!videojuego.getNombre().toLowerCase().replace(" ","").startsWith(nombre)) {
					videojuegos3.remove(videojuego);
				}
			}
			vista.setVideojuegosEnELCombo(videojuegos3);
		}
	}
}
