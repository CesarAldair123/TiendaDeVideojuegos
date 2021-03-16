package proyecto.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import javax.swing.JOptionPane;

import proyecto.modelo.dao.DistribuidoraDAO;
import proyecto.modelo.dao.DistribuidoraDAOImpl;
import proyecto.modelo.dao.VideojuegoDAO;
import proyecto.modelo.dao.VideojuegoDAOImpl;
import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Compra;
import proyecto.modelo.entidades.DetallesCompra;
import proyecto.modelo.entidades.Distribuidora;
import proyecto.modelo.entidades.Usuario;
import proyecto.modelo.entidades.Videojuego;
import proyecto.modelo.negocio.ProcesarCompra;
import proyecto.utilidades.Constantes;
import proyecto.vista.CompraPanel;

public class CompraControladorImpl implements CompraControlador {

	private DistribuidoraDAO disDAO;
	private VideojuegoDAO vidDAO;
	private ArrayList<Videojuego> videojuegos;
	private Compra compra;
	private CompraPanel vista;
	private Usuario usuario;

	public CompraControladorImpl(CompraPanel vista) {
		this.vista = vista;
		disDAO = new DistribuidoraDAOImpl();
		vidDAO = new VideojuegoDAOImpl();
	}

	@Override
	public int inicializar() {
		if (Conexion.thereIsConnection()) {
			ArrayList<Distribuidora> distribuidoras = disDAO.conseguirDistribuidoras();
			if (distribuidoras.isEmpty() || distribuidoras == null) {
				JOptionPane.showMessageDialog(vista, "No hay distribuidoras");
				return -1;
			}
			ArrayList<String> disString = new ArrayList<String>();
			for (Distribuidora distribuidora : distribuidoras) {
				String disS = "" + distribuidora.getId() + " - " + distribuidora.getNombre();
				disString.add(disS);
			}
			String entrada = (String) JOptionPane.showInputDialog(vista, "Elige una distribuidora", "Selecciona",
					JOptionPane.QUESTION_MESSAGE, null, disString.toArray(), disString.get(0));
			if (entrada == null) {
				return -1;
			}
			int disId = Integer.parseInt(entrada.split("-")[0].trim());
			compra = new Compra();
			compra.setId(UUID.randomUUID().toString());
			compra.setDistribuidoraId(disId);
			compra.setDetallesCompra(new ArrayList<DetallesCompra>());
			compra.setUsuarioId(usuario.getId());
			vista.llenarTabla(compra.getDetallesCompra());

			ArrayList<Videojuego> videojuegos = vidDAO.conseguirVideojuegos();
			ArrayList<Videojuego> filtradosPorDis = new ArrayList<Videojuego>();
			videojuegos.forEach((v) -> {
				if (v.getDistribuidora().getId() == disId)
					filtradosPorDis.add(v);
			});
			this.videojuegos = filtradosPorDis;
			vista.setVideojuegosEnELCombo(filtradosPorDis);
			return 1;
		} else {
			JOptionPane.showMessageDialog(vista, "No hay conexion");
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
		String vidString = (String) vista.getVideojuego().getSelectedItem();
		Videojuego videojuego = null;
		if (vidString == null) {
			JOptionPane.showMessageDialog(vista, "Introduce un videojuego valido");
			return;
		}
		int cantidad = (int) vista.getCantidad().getValue();
		if (cantidad < 0) {
			JOptionPane.showMessageDialog(vista, "Introduce una cantidad valida");
			return;
		} else {
			int idVid = Integer.parseInt(vidString.split("-")[0].trim());
			if (cantidad == 0) {
				for (int i = 0; i < compra.getDetallesCompra().size(); i++) {
					if (compra.getDetallesCompra().get(i).getVideojuegoId() == idVid) {
						float tot = compra.getDetallesCompra().get(i).getMonto();
						compra.setTotal(compra.getTotal() - tot);
						compra.getDetallesCompra().remove(i);
						break;
					}
				}
				vista.getTotal().setText("" + compra.getTotal());
				vista.llenarTabla(compra.getDetallesCompra());
				return;
			}
			for (int i = 0; i < this.videojuegos.size(); i++) {
				if (this.videojuegos.get(i).getId() == idVid) {
					videojuego = videojuegos.get(i);
				}
			}
			for (int i = 0; i < compra.getDetallesCompra().size(); i++) {
				if (compra.getDetallesCompra().get(i).getVideojuegoId() == videojuego.getId()) {
					float tot = compra.getDetallesCompra().get(i).getMonto();
					compra.setTotal(compra.getTotal() - tot);
					compra.getDetallesCompra().remove(i);
					break;
				}
			}
			float total = cantidad * videojuego.getPrecio();
			compra.setTotal(compra.getTotal() + total);
			DetallesCompra detalles = new DetallesCompra(compra.getId(), videojuego.getId(), cantidad, total);
			detalles.setVideojuego(videojuego);
			compra.getDetallesCompra().add(detalles);
			vista.getTotal().setText("" + compra.getTotal());
			vista.llenarTabla(compra.getDetallesCompra());
		}

	}

	@Override
	public void cancelar() {
		int i = JOptionPane.showOptionDialog(vista, "Estas seguro?", "Cancelar Compra", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(i == 0) {
			inicializar();
		}
	}

	@Override
	public void procesarCompra() {
		if (compra.getDetallesCompra().size() == 0) {
			JOptionPane.showMessageDialog(vista, "Introduce por lo menos un videojuego antes de proceder");
			return;
		}
		ProcesarCompra proceso = new ProcesarCompra();
		float total = 0;
		for (DetallesCompra com : compra.getDetallesCompra()) {
			total += com.getMonto();
		}
		compra.setTotal(total);
		compra.setFecha(new java.sql.Date(new Date().getTime()));
		int success = proceso.procesarCompra(compra);
		if (success == 1) {
			JOptionPane.showMessageDialog(vista, "La compra se realizo correctamente");
			Compra compra = new Compra();
			compra.setDistribuidoraId(this.compra.getDistribuidoraId());
			compra.setId(UUID.randomUUID().toString());
			compra.setUsuarioId(this.compra.getUsuarioId());
			compra.setDetallesCompra(new ArrayList<DetallesCompra>());
			this.compra = compra;
			vista.getTotal().setText("0");
			vista.llenarTabla(compra.getDetallesCompra());
		} else {
			JOptionPane.showMessageDialog(vista, "La compra no se pudo realizar");
		}
	}

	@Override
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
