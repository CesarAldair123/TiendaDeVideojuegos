package proyecto.controlador;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import proyecto.main.AdministradorNivel;
import proyecto.main.CajeroNivel;
import proyecto.modelo.dao.UsuarioDAO;
import proyecto.modelo.dao.UsuarioDAOImpl;
import proyecto.modelo.entidades.Usuario;
import proyecto.vista.LoginPanel;

public class LoginControladorImpl implements LoginControlador {

	private LoginPanel vista;
	private UsuarioDAO dao;
	private JFrame marco;

	public LoginControladorImpl(LoginPanel vista, JFrame marco) {
		this.vista = vista;
		this.marco = marco;
	}

	@Override
	public int inicializar() {
		dao = new UsuarioDAOImpl();
		return 1;
	}

	@Override
	public void iniciarSesion() {
		String idString = vista.getId().getText();
		int id = 0;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vista, "Introduce un Id valido");
			return;
		}
		String nombre = vista.getNombre().getText();
		String password = new String(vista.getPassword().getPassword());
		if (nombre.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(vista, "Llena todos los campos");
			return;
		}
		Usuario usuario = dao.conseguirUsuario(id, nombre, password);
		if (usuario == null) {
			JOptionPane.showMessageDialog(vista, "No hay conexion con la base de datos");
			return;
		}
		if(usuario.getId()==0) {
			JOptionPane.showMessageDialog(vista, "Datos Incorrectos");
			return;
		}
		JOptionPane.showMessageDialog(vista, "Sesion iniciada Correctamente!");
		if (usuario.getNivel().equals("Administrador")) {
			AdministradorNivel nivel = new AdministradorNivel();
			nivel.setUsuario(usuario);
			nivel.iniciar();
		} else {
			CajeroNivel nivel = new CajeroNivel();
			nivel.setUsuario(usuario);
			nivel.iniciar();
		}
		marco.setVisible(false);
	}

}
