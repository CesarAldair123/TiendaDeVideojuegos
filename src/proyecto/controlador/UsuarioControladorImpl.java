package proyecto.controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import proyecto.modelo.dao.UsuarioDAO;
import proyecto.modelo.dao.UsuarioDAOImpl;
import proyecto.modelo.db.Conexion;
import proyecto.modelo.entidades.Usuario;
import proyecto.vista.AdministrarUsuarioPanel;

public class UsuarioControladorImpl implements UsuarioControlador{
	
	private AdministrarUsuarioPanel vista;
	private UsuarioDAO dao;
		
	public UsuarioControladorImpl(AdministrarUsuarioPanel vista) {
		this.vista = vista;
	}
	
	@Override
	public int inicializar(){
		if(Conexion.thereIsConnection()) {
			vista.llenarCampos(null);
			dao = new UsuarioDAOImpl();
			ArrayList<Usuario> usuarios = dao.conseguirUsuarios();
			System.out.println(usuarios);
			vista.llenarTabla(usuarios);
			vista.getId().setEnabled(false);
			vista.getAgregar().setEnabled(true);
			vista.getEditar().setEnabled(false);
			vista.getEditar().setEnabled(false);
			return 1;
		}else {
			JOptionPane.showMessageDialog(vista, "No hay Conexion");
			return 0;
		}
	}

	@Override
	public void agregarUsuario() {
		String idString = vista.getId().getText();
		int id = 0;
		try {
			id = Integer.parseInt(idString);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(vista, "Introduce un Id Valido");
			return;
		}
		String nivel = (String)vista.getNivel().getSelectedItem();
		String nombre = vista.getNombre().getText();
		String password = new String(vista.getPassword().getPassword());
		if(nivel.equals("") || nombre.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(vista, "Llena todos los campos antes de continuar");
			return;
		}
		Usuario usu = new Usuario(id,nivel,nombre,password);
		int success = dao.agregarUsuario(usu);
		if(success == 1) {
			JOptionPane.showMessageDialog(vista, "El usuario se agrego correctamente");
			vista.llenarCampos(null);
			ArrayList<Usuario> distribuidoras = dao.conseguirUsuarios();
			vista.llenarTabla(distribuidoras);
		}else {
			JOptionPane.showMessageDialog(vista, "El usuario no se pudo agregar");
		}
	}

	@Override
	public void editarUsuario() {
		String idString = vista.getId().getText();
		int id = 0;
		try {
			id = Integer.parseInt(idString);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(vista, "Introduce un Id Valido");
			return;
		}
		String nivel = (String)vista.getNivel().getSelectedItem();
		String nombre = vista.getNombre().getText();
		String password = new String(vista.getPassword().getPassword());
		if(nivel.equals("") || nombre.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(vista, "Llena todos los campos antes de continuar");
			return;
		}
		Usuario usu = new Usuario(id,nivel,nombre,password);
		int success = dao.editarUsuario(usu);
		if(success == 1) {
			int index = vista.getTabla().getSelectedRow();
			JOptionPane.showMessageDialog(vista, "El usuario se edito correctamente");
			ArrayList<Usuario> distribuidoras = dao.conseguirUsuarios();
			vista.llenarTabla(distribuidoras);
			vista.getTabla().setRowSelectionInterval(index, index);
		}else {
			JOptionPane.showMessageDialog(vista, "El usuario no se pudo editar");
		}
	}

	@Override
	public void eliminarUsuario() {
		String idString = vista.getId().getText();
		int id = 0;
		try {
			id = Integer.parseInt(idString);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(vista, "Introduce un Id Valido");
			return;
		}
		int success = dao.eliminarUsuario(id);
		if(success == 1) {
			JOptionPane.showMessageDialog(vista, "El usuario se elimino correctamente");
			ArrayList<Usuario> distribuidoras = dao.conseguirUsuarios();
			vista.llenarTabla(distribuidoras);
			vista.llenarCampos(null);
			vista.getAgregar().setEnabled(true);
			vista.getEditar().setEnabled(false);
			vista.getEliminar().setEnabled(false);
		}else {
			JOptionPane.showMessageDialog(vista, "El usuario no se pudo eliminar");
		}
	}

	@Override
	public void nuevoUsuario() {
		vista.llenarCampos(null);
		vista.getAgregar().setEnabled(true);
		vista.getEditar().setEnabled(false);
		vista.getEliminar().setEnabled(false);
		vista.getTabla().clearSelection();
	}

	@Override
	public void filaSeleccionada() {
		Usuario usu = vista.getUsuarioSeleccionado();
		vista.llenarCampos(usu);
		vista.getAgregar().setEnabled(false);
		vista.getEditar().setEnabled(true);
		vista.getEliminar().setEnabled(true);
	}

}
