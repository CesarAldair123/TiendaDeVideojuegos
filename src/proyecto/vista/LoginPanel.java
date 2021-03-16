package proyecto.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import proyecto.controlador.LoginControlador;
import proyecto.utilidades.Colores;
import proyecto.utilidades.Fonts;
import proyecto.utilidades.ImagenUtilidades;

public class LoginPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JTextField nombre;
	private JPasswordField password;
	private JButton iniciarSesion;
	private LoginControlador controlador;

	public LoginPanel() {
		crearGUI();
	}

	public void crearGUI() {
		
		//Inicializacion de variables de clase
		id = new JTextField(20);
		nombre = new JTextField(20);
		password = new JPasswordField(20);
		iniciarSesion = new JButton("Iniciar Sesion");

		iniciarSesion.addActionListener(this);

		//Inicializacion de variables de metodo
		JPanel panel = new JPanel();
		JLabel texto = new JLabel("Tienda de Videojuegos");
		JLabel titulo = new JLabel("Iniciar Sesion");
		JLabel idL = new JLabel("Id: ");
		JLabel nombreL = new JLabel("Nombre: ");
		JLabel passwordL = new JLabel("Password: ");
		ImageIcon imagen = ImagenUtilidades.getImage("src/imagenes/videojuego.jpeg", 350, 245);
		JLabel imagenContenedor = new JLabel(imagen);

		int center = GridBagConstraints.CENTER;
		int west = GridBagConstraints.WEST;
		int both = GridBagConstraints.BOTH;
		Insets insets0 = new Insets(0, 0, 0, 0);
		Insets insets5 = new Insets(5, 5, 5, 5);
		Insets insets20 = new Insets(20, 20, 20, 20);

		GridBagConstraints imagenC = new GridBagConstraints(0, 0, 1, 1, 1, 1, center, both, insets0, 0, 0);
		GridBagConstraints textoC = new GridBagConstraints(0, 1, 1, 1, 1, 1, center, both, insets0, 0, 0);
		GridBagConstraints panelC = new GridBagConstraints(1, 0, 1, 2, 1, 1, center, both, insets20, 0, 0);

		GridBagConstraints tituloC = new GridBagConstraints(0, 0, 1, 1, 1, 1, center, both, insets5, 0, 0);
		GridBagConstraints idLC = new GridBagConstraints(0, 1, 1, 1, 1, 1, west, both, insets5, 0, 0);
		GridBagConstraints idC = new GridBagConstraints(0, 2, 1, 1, 1, 1, center, both, insets5, 0, 0);
		GridBagConstraints nombreLC = new GridBagConstraints(0, 3, 1, 1, 1, 1, west, both, insets5, 0, 0);
		GridBagConstraints nombreC = new GridBagConstraints(0, 4, 1, 1, 1, 1, center, both, insets5, 0, 0);
		GridBagConstraints passwordLC = new GridBagConstraints(0, 5, 1, 1, 1, 1, west, both, insets5, 0, 0);
		GridBagConstraints passwordC = new GridBagConstraints(0, 6, 1, 1, 1, 1, center, both, insets5, 0, 0);
		GridBagConstraints loginC = new GridBagConstraints(0, 7, 1, 1, 1, 1, center, both, insets5, 0, 0);
		
		
		//Agregando el estilo decorativo a los elementos
		setPreferredSize(new Dimension(800,500));
		imagenContenedor.setHorizontalAlignment(SwingConstants.CENTER);
		imagenContenedor.setOpaque(true);
		imagenContenedor.setBackground(Colores.DARK_BLUE);
		
		texto.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		texto.setForeground(Color.WHITE);
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		texto.setOpaque(true);
		texto.setBackground(Colores.DARK_BLUE);
		texto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel.setOpaque(false);
		
		titulo.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		titulo.setForeground(Color.WHITE);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		idL.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		idL.setForeground(Color.WHITE);
		
		id.setOpaque(false);
		id.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		id.setForeground(Color.WHITE);
		id.setCaretColor(Color.WHITE);
		id.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		nombreL.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		nombreL.setForeground(Color.WHITE);
		
		nombre.setOpaque(false);
		nombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		nombre.setForeground(Color.WHITE);
		nombre.setCaretColor(Color.WHITE);
		nombre.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		
		passwordL.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		passwordL.setForeground(Color.WHITE);
		
		password.setOpaque(false);
		password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		password.setForeground(Color.WHITE);
		password.setCaretColor(Color.WHITE);
		password.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		
		iniciarSesion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		iniciarSesion.setBackground(Colores.DARK_BLUE);
		iniciarSesion.setForeground(Color.WHITE);
		iniciarSesion.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		iniciarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		//Agregando el posicionamiento de los elementos
		setLayout(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		
		panel.add(titulo, tituloC);
		panel.add(idL, idLC);
		panel.add(id, idC);
		panel.add(nombreL, nombreLC);
		panel.add(nombre, nombreC);
		panel.add(passwordL, passwordLC);
		panel.add(password, passwordC);
		panel.add(iniciarSesion, loginC);

		add(imagenContenedor, imagenC);
		add(texto, textoC);
		add(panel, panelC);
	}	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth();
		int h = getHeight();
		Color color1 = Colores.DARK_BLUE;
		Color color2 = Colores.STEAM_BLUE;
		GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(controlador != null) {
			controlador.iniciarSesion();
		}
	}

	public JTextField getId() {
		return id;
	}

	public JTextField getNombre() {
		return nombre;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public JButton getIniciarSesion() {
		return iniciarSesion;
	}

	public LoginControlador getControlador() {
		return controlador;
	}
	
	public void setControlador(LoginControlador controlador) {
		this.controlador = controlador;
	}

}
