package proyecto.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import proyecto.modelo.dao.DistribuidoraDAO;
import proyecto.modelo.dao.DistribuidoraDAOImpl;
import proyecto.modelo.dao.VideojuegoDAO;
import proyecto.modelo.dao.VideojuegoDAOImpl;
import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Distribuidora;
import proyecto.modelo.entidades.Videojuego;
import proyecto.vista.AdministrarVideojuegoPanel;

public class VideojuegoControladorImpl implements VideojuegoControlador {

	private AdministrarVideojuegoPanel vista;
	private VideojuegoDAO videojuegoDao;
	private DistribuidoraDAO distribuidoraDao;

	public VideojuegoControladorImpl(AdministrarVideojuegoPanel vista) {
		this.vista = vista;
		videojuegoDao = new VideojuegoDAOImpl();
		distribuidoraDao = new DistribuidoraDAOImpl();
	}

	@Override
	public int inicializar() {
		if(Conexion.thereIsConnection()) {
			vista.llenarCampos(null);
			ArrayList<Videojuego> videojuegos = videojuegoDao.conseguirVideojuegos();
			vista.llenarTabla(videojuegos);
			ArrayList<Distribuidora> distribuidoras = distribuidoraDao.conseguirDistribuidoras();
			vista.setDistribuidorasEnElCombo(distribuidoras);
			vista.getAgregar().setEnabled(true);
			vista.getEditar().setEnabled(false);
			vista.getEliminar().setEnabled(false);
			vista.getId().setEnabled(false);
			return 1;
		}else {
			JOptionPane.showMessageDialog(vista, "No hay Conexion");
			return 0;
		}
	}

	@Override
	public void agregarVideojuego() {
		String idString = vista.getId().getText();
		int id = 0;
		try {
			id = Integer.parseInt(idString);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(vista, "Introduce un Id Valido");
			return;
		}
		String nombre = vista.getNombre().getText();
		String desarrolladora = vista.getDesarrolladora().getText();
		if (nombre.equals("") || desarrolladora.equals("")) {
			JOptionPane.showMessageDialog(vista, "Llena todos los campos antes de continuar");
			return;
		}
		String disId = null;
		int distribuidoraId = 0;
		try {
			disId = ((String) vista.getDistribuidora().getSelectedItem()).split("-")[0].trim();
			distribuidoraId = Integer.parseInt(disId);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vista, "Selecciona una distribuidora");
			return;
		}
		String consola = (String) vista.getConsola().getSelectedItem();
		String clasificacion = (String) vista.getClasificacion().getSelectedItem();
		StringBuilder generos = new StringBuilder();
		List<String> genreList = vista.getGeneros().getSelectedValuesList();
		for (int i = 0; i < genreList.size(); i++) {
			generos.append(genreList.get(i));
			if (i != genreList.size() - 1)
				generos.append(",");
		}
		if(generos.toString().equals("")) {
			JOptionPane.showMessageDialog(vista, "Introduce al menos un genero");
			return;
		}
		float precio = 0;
		try {
			precio = Float.parseFloat(vista.getPrecio().getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vista, "Introduce un numero valido en el precio");
			return;
		}
		int cantidad = (int) vista.getStock().getValue();
		
		Videojuego videojuego = new Videojuego(id, distribuidoraId, nombre, desarrolladora, consola, clasificacion,
				generos.toString(), precio, cantidad);
		
		int success = videojuegoDao.agregarVideojuego(videojuego);
		if (success == 1) {
			JOptionPane.showMessageDialog(vista, "El videojuego se agrego correctamente");
			ArrayList<Videojuego> videojuegos = videojuegoDao.conseguirVideojuegos();
			vista.llenarTabla(videojuegos);
			nuevoVideojuego();
		} else {
			JOptionPane.showMessageDialog(vista, "El videojuego no se pudo agregar");
		}
	}

	@Override
	public void editarVideojuego() {
		String idString = vista.getId().getText();
		int id = 0;
		try {
			id = Integer.parseInt(idString);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(vista, "Introduce un Id Valido");
			return;
		}
		String nombre = vista.getNombre().getText();
		String desarrolladora = vista.getDesarrolladora().getText();
		if (nombre.equals("") || desarrolladora.equals("")) {
			JOptionPane.showMessageDialog(vista, "Llena todos los campos antes de continuar");
			return;
		}
		String disId = null;
		int distribuidoraId = 0;
		try {
			disId = ((String) vista.getDistribuidora().getSelectedItem()).split("-")[0].trim();
			distribuidoraId = Integer.parseInt(disId);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vista, "Selecciona una distribuidora");
			return;
		}
		String consola = (String) vista.getConsola().getSelectedItem();
		String clasificacion = (String) vista.getClasificacion().getSelectedItem();
		StringBuilder generos = new StringBuilder();
		List<String> genreList = vista.getGeneros().getSelectedValuesList();
		for (int i = 0; i < genreList.size(); i++) {
			generos.append(genreList.get(i));
			if (i != genreList.size() - 1)
				generos.append(",");
		}
		if(generos.toString().equals("")) {
			JOptionPane.showMessageDialog(vista, "Introduce al menos un genero");
			return;
		}
		float precio = 0;
		try {
			precio = Float.parseFloat(vista.getPrecio().getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(vista, "Introduce un numero valido en el precio");
			return;
		}
		int cantidad = (int) vista.getStock().getValue();
		
		Videojuego videojuego = new Videojuego(id, distribuidoraId, nombre, desarrolladora, consola, clasificacion,
				generos.toString(), precio, cantidad);
		
		int success = videojuegoDao.editarVideojuego(videojuego);
		if (success == 1) {
			JOptionPane.showMessageDialog(vista, "El videojuego se edito correctamente");
			int index = vista.getTabla().getSelectedRow();
			ArrayList<Videojuego> videojuegos = videojuegoDao.conseguirVideojuegos();
			vista.llenarTabla(videojuegos);
			vista.getTabla().setRowSelectionInterval(index, index);
		} else {
			JOptionPane.showMessageDialog(vista, "El videojuego no se pudo editar");
		}
	}

	@Override
	public void eliminarVideojuego() {
		
		String idString = vista.getId().getText();
		int id = 0;
		try {
			id = Integer.parseInt(idString);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(vista, "Introduce un Id Valido");
			return;
		}
		
		int success = videojuegoDao.eliminarVideojuego(id);
		if (success == 1) {
			JOptionPane.showMessageDialog(vista, "El videojuego se elimino correctamente");
			ArrayList<Videojuego> videojuegos = videojuegoDao.conseguirVideojuegos();
			vista.llenarTabla(videojuegos);
			nuevoVideojuego();
		} else {
			JOptionPane.showMessageDialog(vista, "El videojuego no se pudo eliminar");
		}
	}

	@Override
	public void nuevoVideojuego() {
		vista.llenarCampos(null);
		vista.getAgregar().setEnabled(true);
		vista.getEditar().setEnabled(false);
		vista.getEliminar().setEnabled(false);
		vista.getTabla().clearSelection();
	}

	@Override
	public void filaSeleccionada() {
		Videojuego videojuego = vista.getVideojuegoSeleccionado();
		vista.llenarCampos(videojuego);
		vista.getAgregar().setEnabled(false);
		vista.getEditar().setEnabled(true);
		vista.getEliminar().setEnabled(true);
	}

}
