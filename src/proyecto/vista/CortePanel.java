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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import proyecto.controlador.CorteControlador;
import proyecto.utilidades.Colores;
import proyecto.utilidades.Fonts;

public class CortePanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton generarCorte;
	private JTextField totalVentas;
	private JTextField totalCompras;
	private JTextField total;
	private JTextField fecha;
	private CorteControlador controlador;
	
	public CortePanel() {
		crearGUI();
	}
	
	public void crearGUI() {
		generarCorte = new JButton("Generar Corte");
		totalVentas = new JTextField(20);
		totalCompras = new JTextField(20);
		total = new JTextField(20);
		fecha = new JTextField(20);
		
		generarCorte.addActionListener(this);
		
		JLabel titulo = new JLabel("Corte");
		JLabel totalVentasL = new JLabel("Total Ventas: ");
		JLabel totalComprasL = new JLabel("Total Compras: ");
		JLabel totalL = new JLabel("Total: ");
		JLabel fechaL = new JLabel("Fecha: ");
		
		int center = GridBagConstraints.CENTER;
		int both = GridBagConstraints.BOTH;
		Insets insets5 = new Insets(5, 5, 5, 5);
		
		GridBagConstraints tituloC = new GridBagConstraints(0,0,2,1,1,1,center,both,insets5,0,0);
		GridBagConstraints generarCorteC = new GridBagConstraints(0,1,2,1,1,1,center,both,insets5,20,20);
		GridBagConstraints totalVentasC = new GridBagConstraints(1,2,1,1,1,1,center,both,insets5,0,0);
		GridBagConstraints totalComprasC = new GridBagConstraints(1,3,1,1,1,1,center,both,insets5,0,0);
		GridBagConstraints totalC = new GridBagConstraints(1,4,1,1,1,1,center,both,insets5,0,0);
		GridBagConstraints fechaC = new GridBagConstraints(1,5,1,1,1,1,center,both,insets5,0,0);
		
		GridBagConstraints totalVentasLC = new GridBagConstraints(0,2,1,1,1,1,center,both,insets5,0,0);
		GridBagConstraints totalComprasLC = new GridBagConstraints(0,3,1,1,1,1,center,both,insets5,0,0);
		GridBagConstraints totalLC = new GridBagConstraints(0,4,1,1,1,1,center,both,insets5,0,0);
		GridBagConstraints fechaLC = new GridBagConstraints(0,5,1,1,1,1,center,both,insets5,0,0);
		
		setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		titulo.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		titulo.setForeground(Color.WHITE);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel[] etiquetas = {totalVentasL,totalComprasL,totalL,fechaL};
		for(JLabel label : etiquetas) {
			label.setFont(Fonts.ARIAL_PLAIN_15);
			label.setForeground(Color.WHITE);
		}
		
		JTextField[] campos = {totalVentas, totalCompras, total, fecha};
		for(JTextField campo : campos) {
			campo.setOpaque(false);
			campo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
			campo.setForeground(Color.WHITE);
			campo.setCaretColor(Color.WHITE);
			campo.setFont(Fonts.ARIAL_PLAIN_15);
		}
		
		generarCorte.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		generarCorte.setBackground(Colores.DARK_BLUE);
		generarCorte.setForeground(Color.WHITE);
		generarCorte.setFont(Fonts.ARIAL_PLAIN_15);
		generarCorte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		generarCorte.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Colores.STEAM_BLUE));
		
		setLayout(new GridBagLayout());
		
		add(titulo, tituloC);
		add(generarCorte, generarCorteC);
		add(totalVentasL, totalVentasLC);
		add(totalVentas, totalVentasC);
		add(totalComprasL, totalComprasLC);
		add(totalCompras, totalComprasC);
		add(totalL, totalLC);
		add(total, totalC);
		add(fechaL, fechaLC);
		add(fecha, fechaC);
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
			controlador.generarCorte();
		}
	}

	public JButton getGenerarCorte() {
		return generarCorte;
	}

	public JTextField getTotalVentas() {
		return totalVentas;
	}

	public JTextField getTotalCompras() {
		return totalCompras;
	}

	public JTextField getTotal() {
		return total;
	}

	public JTextField getFecha() {
		return fecha;
	}

	public CorteControlador getControlador() {
		return controlador;
	}

	public void setControlador(CorteControlador controlador) {
		this.controlador = controlador;
	}
	
	
}
