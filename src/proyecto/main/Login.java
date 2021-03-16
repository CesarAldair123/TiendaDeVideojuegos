package proyecto.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import proyecto.controlador.LoginControlador;
import proyecto.controlador.LoginControladorImpl;
import proyecto.vista.LoginPanel;

public class Login{
	
	private JFrame marco;
	private LoginPanel loginPanel;
	private LoginControlador loginControlador;
	
	public void iniciar() {
		marco = new JFrame();
		loginPanel = new LoginPanel();
		loginControlador = new LoginControladorImpl(loginPanel,marco);
		
		marco.setTitle("Inciar Sesion");
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setSize(800, 500);
		
		loginPanel.setControlador(loginControlador);
		loginControlador.inicializar();
		
		marco.add(loginPanel, BorderLayout.CENTER);
		marco.setLocationRelativeTo(null);
		marco.setVisible(true);
	}

}
