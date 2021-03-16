package proyecto.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import proyecto.controlador.MenuControlador;
import proyecto.utilidades.Colores;
import proyecto.utilidades.Fonts;

public class MenuPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JButton realizarCompra;
	private JButton administrarVideojuego;
	private JButton administrarDistribuidora;
	private JButton administrarUsuario;
	private MenuControlador controlador;

	public MenuPanel() {
		crearGUI();
	}

	private void crearGUI() {
		realizarCompra = new JButton("Realizar Compra");
		administrarVideojuego = new JButton("Administrar Videojuego");
		administrarDistribuidora = new JButton("Administrar Distribuidora");
		administrarUsuario = new JButton("Administrar Usuario");
		realizarCompra.addActionListener(this);
		administrarVideojuego.addActionListener(this);
		administrarDistribuidora.addActionListener(this);
		administrarUsuario.addActionListener(this);

		int center = GridBagConstraints.CENTER;
		int both = GridBagConstraints.BOTH;
		Insets insets70 = new Insets(70, 70, 70, 70);

		GridBagConstraints b1 = new GridBagConstraints(0, 0, 1, 1, 1, 1, center, both, insets70, 80, 40);
		GridBagConstraints b2 = new GridBagConstraints(0, 1, 1, 1, 1, 1, center, both, insets70, 80, 40);
		GridBagConstraints b3 = new GridBagConstraints(1, 0, 1, 1, 1, 1, center, both, insets70, 80, 40);
		GridBagConstraints b4 = new GridBagConstraints(1, 1, 1, 1, 1, 1, center, both, insets70, 80, 40);

		JButton[] botones = { realizarCompra, administrarUsuario, administrarVideojuego, administrarDistribuidora };
		for (JButton button : botones) {
			button.setForeground(Color.WHITE);
			button.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
			button.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Colores.STEAM_BLUE));
			button.setBackground(Colores.DARK_BLUE);
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		setLayout(new GridBagLayout());
		add(realizarCompra, b1);
		add(administrarUsuario, b2);
		add(administrarVideojuego, b3);
		add(administrarDistribuidora, b4);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth();
		int h = getHeight();
		Color color1 = new Color(35, 37, 49);
		Color color2 = new Color(26, 37, 97);
		GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Realizar Compra":
			controlador.mostrarCompraPanel();
			break;
		case "Administrar Videojuego":
			controlador.mostrarVideojuegoAdminPanel();
			break;
		case "Administrar Usuario":
			controlador.mostrarUsuarioAdminPanel();
			break;
		case "Administrar Distribuidora":
			controlador.mostrarDistribuidoraAdminPanel();
			break;
		}
	}

	public JButton getRealizarCompra() {
		return realizarCompra;
	}

	public JButton getAdministrarVideojuego() {
		return administrarVideojuego;
	}

	public JButton getAdministrarDistribuidora() {
		return administrarDistribuidora;
	}

	public JButton getAdministrarUsuario() {
		return administrarUsuario;
	}

	public MenuControlador getControlador() {
		return controlador;
	}

	public void setControlador(MenuControlador controlador) {
		this.controlador = controlador;
	}
	
	
}
