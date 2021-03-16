package proyecto.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import proyecto.controlador.CompraControlador;
import proyecto.controlador.CompraControladorImpl;
import proyecto.controlador.DistribuidoraControlador;
import proyecto.controlador.DistribuidoraControladorImpl;
import proyecto.controlador.MenuControlador;
import proyecto.controlador.UsuarioControlador;
import proyecto.controlador.UsuarioControladorImpl;
import proyecto.controlador.VideojuegoControlador;
import proyecto.controlador.VideojuegoControladorImpl;
import proyecto.modelo.entidades.Usuario;
import proyecto.utilidades.Colores;
import proyecto.vista.AdministrarDistribuidoraPanel;
import proyecto.vista.AdministrarUsuarioPanel;
import proyecto.vista.AdministrarVideojuegoPanel;
import proyecto.vista.CompraPanel;
import proyecto.vista.MenuPanel;

public class AdministradorNivel implements ActionListener, MenuControlador{
	
	private JFrame marco;
	private JButton regresar;
	private JButton cerrarSesion;
	private CardLayout layout;
	private JPanel panelPadre;
	private MenuPanel menuPanel;
	private CompraPanel compraPanel;
	private AdministrarVideojuegoPanel videojuegoPanel;
	private AdministrarUsuarioPanel usuarioPanel;
	private AdministrarDistribuidoraPanel distribuidoraPanel;
	private CompraControlador compraControlador;
	private VideojuegoControlador videojuegoControlador;
	private UsuarioControlador usuarioControlador;
	private DistribuidoraControlador distribuidoraControlador;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void iniciar() {
		marco = new JFrame();
		regresar = new JButton("Regresar");
		cerrarSesion = new JButton("Cerrar Sesion");
		layout = new CardLayout();
		panelPadre = new JPanel();
		menuPanel = new MenuPanel();
		compraPanel = new CompraPanel();
		videojuegoPanel = new AdministrarVideojuegoPanel();
		usuarioPanel = new AdministrarUsuarioPanel();
		distribuidoraPanel = new AdministrarDistribuidoraPanel();
		compraControlador = new CompraControladorImpl(compraPanel);
		videojuegoControlador = new VideojuegoControladorImpl(videojuegoPanel);
		usuarioControlador = new UsuarioControladorImpl(usuarioPanel);
		distribuidoraControlador = new DistribuidoraControladorImpl(distribuidoraPanel);
		
		regresar.addActionListener(this);
		cerrarSesion.addActionListener(this);
		menuPanel.setControlador(this);
		compraPanel.setControlador(compraControlador);
		videojuegoPanel.setControlador(videojuegoControlador);
		usuarioPanel.setControlador(usuarioControlador);
		distribuidoraPanel.setControlador(distribuidoraControlador);
		
		regresar.setEnabled(false);
		
		JMenuBar barra = new JMenuBar();
		
		barra.setBackground(Colores.DARK_BLUE);
		JButton[] botones = { regresar, cerrarSesion};
		for (JButton button : botones) {
			button.setForeground(Color.WHITE);
			button.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			button.setBackground(Colores.DARK_BLUE);
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		marco.setTitle("Tienda de Videojuegos - Administracion");
		marco.setSize(1000, 700);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelPadre.setLayout(layout);
		panelPadre.add("menu",menuPanel);
		panelPadre.add("compra", compraPanel);
		panelPadre.add("videojuego", videojuegoPanel);
		panelPadre.add("usuario", usuarioPanel);
		panelPadre.add("distribuidora",distribuidoraPanel);
		
		barra.add(regresar);
		barra.add(cerrarSesion);
	
		marco.setJMenuBar(barra);
		marco.add(panelPadre, BorderLayout.CENTER);
		marco.setLocationRelativeTo(null);
		compraControlador.setUsuario(usuario);
		layout.show(panelPadre, "menu");
		marco.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String accion = e.getActionCommand();
		switch(accion) {
		case "Regresar":
			layout.show(panelPadre,"menu");
			regresar.setEnabled(false);
			break;
		case "Cerrar Sesion":
			Login login = new Login();
			login.iniciar();
			marco.dispose();
			break;
		}
	}

	@Override
	public void mostrarCompraPanel() {
		int exito = compraControlador.inicializar();
		if(exito == 1) {
			regresar.setEnabled(true);
			layout.show(panelPadre, "compra");
		}
	}

	@Override
	public void mostrarVideojuegoAdminPanel() {
		if(videojuegoControlador.inicializar() == 1) {
			regresar.setEnabled(true);
			layout.show(panelPadre, "videojuego");
		}
	}

	@Override
	public void mostrarUsuarioAdminPanel() {
		if(usuarioControlador.inicializar() == 1) {
			regresar.setEnabled(true);
			layout.show(panelPadre, "usuario");
		}
	}

	@Override
	public void mostrarDistribuidoraAdminPanel() {
		if(distribuidoraControlador.inicializar() == 1) {
			regresar.setEnabled(true);
			layout.show(panelPadre, "distribuidora");
		}
	}

}
