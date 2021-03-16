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

import proyecto.controlador.CorteControlador;
import proyecto.controlador.CorteControladorImpl;
import proyecto.controlador.VentaControlador;
import proyecto.controlador.VentaControladorImpl;
import proyecto.modelo.entidades.Usuario;
import proyecto.utilidades.Colores;
import proyecto.vista.CortePanel;
import proyecto.vista.VentaPanel;

public class CajeroNivel implements ActionListener{
	
	private JFrame marco;
	private JButton corte;
	private JButton venta;
	private JButton cerrarSesion;
	private CardLayout layout;
	private JPanel panelPadre;
	private VentaPanel ventaPanel;
	private CortePanel cortePanel;
	private VentaControlador ventaControlador;
	private CorteControlador corteControlador;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void iniciar() {
		marco = new JFrame();
		corte = new JButton("Corte");
		venta = new JButton("Venta");
		cerrarSesion = new JButton("Cerrar Sesion");
		layout = new CardLayout();
		panelPadre = new JPanel();
		ventaPanel = new VentaPanel();
		cortePanel = new CortePanel();
		ventaControlador = new VentaControladorImpl(ventaPanel);
		corteControlador = new CorteControladorImpl(cortePanel);
		
		ventaPanel.setControlador(ventaControlador);
		cortePanel.setControlador(corteControlador);
		
		corte.addActionListener(this);
		venta.addActionListener(this);
		cerrarSesion.addActionListener(this);
		
		JMenuBar barra = new JMenuBar();
		
		marco.setTitle("Tienda de Videojuegos - Cajero");
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		barra.setBackground(Colores.DARK_BLUE);
		JButton[] botones = { venta, corte , cerrarSesion};
		for (JButton button : botones) {
			button.setForeground(Color.WHITE);
			button.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			button.setBackground(Colores.DARK_BLUE);
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		barra.add(corte);
		barra.add(venta);
		barra.add(cerrarSesion);
		
		panelPadre.setLayout(layout);
		panelPadre.add("venta",ventaPanel);
		panelPadre.add("corte",cortePanel);
		
		marco.setJMenuBar(barra);
		marco.add(panelPadre, BorderLayout.CENTER);
		
		marco.setSize(1000,700);
		marco.setLocationRelativeTo(null);
		
		ventaControlador.setUsuario(usuario);
		corteControlador.setUsuario(usuario);
		ventaControlador.inicializar();
		layout.show(panelPadre, "venta");
		marco.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String accion = e.getActionCommand();
		switch(accion) {
		case "Venta":
			layout.show(panelPadre, "venta");
			break;
		case "Corte":
			layout.show(panelPadre, "corte");
			break;
		case "Cerrar Sesion":
			Login login = new Login();
			login.iniciar();
			marco.dispose();
		}
	}
}
