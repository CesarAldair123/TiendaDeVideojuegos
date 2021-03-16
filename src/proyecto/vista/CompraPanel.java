package proyecto.vista;

import java.awt.Color;
import java.awt.Component;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import proyecto.controlador.CompraControlador;
import proyecto.modelo.entidades.DetallesCompra;
import proyecto.modelo.entidades.Videojuego;
import proyecto.utilidades.Colores;
import proyecto.utilidades.Constantes;
import proyecto.utilidades.Fonts;

public class CompraPanel extends JPanel implements ActionListener, ItemListener, ListSelectionListener, CaretListener{

	private static final long serialVersionUID = 1L;
	private JButton agregar;
	private JButton finalizar;
	private JButton cancelar;
	private CompraControlador controlador;
	
	private JTextField nombre;
	private JComboBox<String> consola;
	private JList<String> generos;
	private JComboBox<String> videojuego;
	private JSpinner cantidad;
	private JTable tabla;
	private JTextField total;

	private class CompraTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		private String[] columnas = { "Videojuego", "Cantidad", "Total" };
		private ArrayList<DetallesCompra> datos = new ArrayList<DetallesCompra>();

		@Override
		public int getRowCount() {
			return datos.size();
		}

		@Override
		public int getColumnCount() {
			return columnas.length;
		}

		@Override
		public String getColumnName(int column) {
			return columnas[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			DetallesCompra detalles = datos.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return ""  + detalles.getVideojuego().getId() + " - " + detalles.getVideojuego().getNombre();
			case 1:
				return "" + detalles.getCantidad();
			case 2:
				return "" + detalles.getMonto();
			default:
				return 0;
			}
		}

	}

	public CompraPanel() {
		crearGUI();
	}

	public void crearGUI() {
		
		nombre = new JTextField();
		total = new JTextField(20);
		total.setEditable(false);
		String[] valores = new String[Constantes.consolas.length + 1];
		
		valores[0] = "Todas";
		for(int i = 0; i < Constantes.consolas.length; i++) {
			valores[i + 1] = Constantes.consolas[i];
		}
		consola = new JComboBox<String>(valores);
		generos = new JList<String>(Constantes.generos);
		videojuego = new JComboBox<String>();
		cantidad = new JSpinner();
		
		consola.addItemListener(this);
		generos.addListSelectionListener(this);

		agregar = new JButton("Agregar");
		finalizar = new JButton("Finalizar");
		cancelar = new JButton("Cancelar Compra");

		agregar.addActionListener(this);
		finalizar.addActionListener(this);
		cancelar.addActionListener(this);
		nombre.addCaretListener(this);

		tabla = new JTable(new CompraTableModel());

		JLabel titulo = new JLabel("Realizar Compra");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JLabel nombreL = new JLabel("Nombre: ");
		JLabel consolaL = new JLabel("Consola: ");
		JLabel generosL = new JLabel("Generos:");
		JLabel videojuegoL = new JLabel("Videojuego: ");
		JLabel cantidadL = new JLabel("Cantidad: ");
		JLabel totalL = new JLabel("Total: ");
		JScrollPane scrollPanel = new JScrollPane(tabla);
		JScrollPane scrollGeneros = new JScrollPane(generos);

		int center = GridBagConstraints.CENTER;
		int both = GridBagConstraints.BOTH;
		Insets insets5 = new Insets(5, 5, 5, 5);
		Insets insets20 = new Insets(20, 20, 20, 20);
		Insets insets20top = new Insets(10,20,20,20);
		Insets insets5down = new Insets(5,5,10,5);

		GridBagConstraints tituloC = new GridBagConstraints(0, 0, 1, 1, 1, 0, center, both, insets5, 0, 0);
		GridBagConstraints panel1C = new GridBagConstraints(0, 1, 1, 1, 1, 0, center, both, insets20, 0, 0);
		GridBagConstraints panel2C = new GridBagConstraints(0, 2, 1, 1, 1, 0, center, both, insets20top, 0, 0);
		GridBagConstraints scrollPanelC = new GridBagConstraints(0, 3, 1, 1, 1, 1, center, both, insets20, 0, 0);
		GridBagConstraints panel3C = new GridBagConstraints(0, 4, 1, 1, 1, 0, center, both, insets20, 0, 0);

		GridBagConstraints nombreLC = new GridBagConstraints(0,0,1,1,.05,1,center,both,insets5,0,0);
		GridBagConstraints consolaLC = new GridBagConstraints(0, 1, 1, 1, .05, 1, center, both, insets5, 0, 0);
		GridBagConstraints generosLC = new GridBagConstraints(0, 2, 1, 2, .05, 1, center, both, insets5, 0, 0);
		GridBagConstraints block = new GridBagConstraints(0, 2, 3, 1, .05, 1, center, both, insets5, 0, 0);

		GridBagConstraints nombreC = new GridBagConstraints(1,0,2,1,.95,1,center,both,insets5,0,0);
		GridBagConstraints consolaC = new GridBagConstraints(1, 1, 2, 1, .95, 1, center, both, insets5, 0, 0);
		GridBagConstraints generosC = new GridBagConstraints(1, 2, 2, 2, .95, 1, center, both, insets5down, 0, 0);
		
		GridBagConstraints videojuegoLC = new GridBagConstraints(0, 1, 1, 1, 0, 1, center, both, insets5, 0, 0);
		GridBagConstraints cantidadLC = new GridBagConstraints(0, 2, 1, 1, 0, 1, center, both, insets5, 0, 0);
		
		GridBagConstraints videojuegoC = new GridBagConstraints(1, 1, 2, 1, 1, 1, center, both, insets5, 0, 0);
		GridBagConstraints cantidadC = new GridBagConstraints(1, 2, 1, 1, 1, 1, center, both, insets5, 0, 0);

		GridBagConstraints agregarC = new GridBagConstraints(2, 2, 1, 1, 1, 1, center, both, insets5, 10, 10);

		GridBagConstraints totalLC = new GridBagConstraints(0, 0, 1, 1, 1, 1, center, both, insets5, 0, 0);
		GridBagConstraints totalC = new GridBagConstraints(1, 0, 1, 1, 1, 1, center, both, insets5, 0, 0);
		GridBagConstraints finalizarC = new GridBagConstraints(2, 0, 1, 1, 1, 1, center, both, insets5, 10, 10);
		GridBagConstraints cancelarC = new GridBagConstraints(3, 0, 1, 1, 1, 1, center, both, insets5, 10, 10);

		// color y diseño//
		setPreferredSize(new Dimension(800, 500));
		// panel.setBackground(Colores.STEAM_BLUE);
		panel1.setOpaque(false);
		panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Colores.STEAM_BLUE), "Filtros De Videojuegos",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,Fonts.ARIAL_PLAIN_15,Color.WHITE));
		panel2.setOpaque(false);
		panel3.setOpaque(false);

		titulo.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		titulo.setForeground(Color.WHITE);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);

		videojuegoL.setFont(Fonts.ARIAL_PLAIN_15);
		videojuegoL.setForeground(Color.WHITE);
		
		nombreL.setFont(Fonts.ARIAL_PLAIN_15);
		nombreL.setForeground(Color.WHITE);

		videojuego.setOpaque(false);
		videojuego.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		videojuego.setForeground(Color.WHITE);
		videojuego.setFont(Fonts.ARIAL_PLAIN_15);
		videojuego.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		videojuego.setForeground(Color.WHITE);
		videojuego.setBackground(Colores.DARK_BLUE);
		for (Component component1 : videojuego.getComponents()) {
			component1.setBackground(Color.WHITE);
		}

		consolaL.setFont(Fonts.ARIAL_PLAIN_15);
		consolaL.setForeground(Color.WHITE);

		consola.setOpaque(false);
		consola.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		consola.setForeground(Color.WHITE);
		consola.setFont(Fonts.ARIAL_PLAIN_15);
		consola.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		consola.setForeground(Color.WHITE);
		consola.setBackground(Colores.DARK_BLUE);
		for (Component component1 : consola.getComponents()) {
			component1.setBackground(Color.WHITE);
		}

		generosL.setFont(Fonts.ARIAL_PLAIN_15);
		generosL.setForeground(Color.WHITE);

		generos.setOpaque(false);
		generos.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		generos.setForeground(Color.WHITE);
		generos.setFont(Fonts.ARIAL_PLAIN_15);
		generos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		generos.setForeground(Color.WHITE);
		generos.setBackground(Colores.DARK_BLUE);
		for (Component component1 : generos.getComponents()) {
			component1.setBackground(Color.WHITE);
		}

		cantidadL.setFont(Fonts.ARIAL_PLAIN_15);
		cantidadL.setForeground(Color.WHITE);

		cantidad.setOpaque(false);
		cantidad.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		cantidad.setForeground(Color.WHITE);
		cantidad.setFont(Fonts.ARIAL_PLAIN_15);
		cantidad.setForeground(Color.WHITE);
		cantidad.setOpaque(false);
		cantidad.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		cantidad.getEditor().setOpaque(false);
		((JSpinner.DefaultEditor) cantidad.getEditor()).getTextField().setOpaque(false);
		((JSpinner.DefaultEditor) cantidad.getEditor()).getTextField().setCaretColor(Color.WHITE);
		((JSpinner.DefaultEditor) cantidad.getEditor()).getTextField().setForeground(Color.WHITE);

		totalL.setFont(Fonts.ARIAL_PLAIN_15);
		totalL.setForeground(Color.WHITE);

		total.setOpaque(false);
		total.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		total.setForeground(Color.WHITE);
		total.setCaretColor(Color.WHITE);
		total.setFont(Fonts.ARIAL_PLAIN_15);
		
		nombre.setOpaque(false);
		nombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		nombre.setForeground(Color.WHITE);
		nombre.setCaretColor(Color.WHITE);
		nombre.setFont(Fonts.ARIAL_PLAIN_15);

		agregar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		agregar.setBackground(Colores.DARK_BLUE);
		agregar.setForeground(Color.WHITE);
		agregar.setFont(Fonts.ARIAL_PLAIN_15);
		agregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		agregar.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Colores.STEAM_BLUE));

		finalizar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		finalizar.setBackground(Colores.DARK_BLUE);
		finalizar.setForeground(Color.WHITE);
		finalizar.setFont(Fonts.ARIAL_PLAIN_15);
		finalizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		finalizar.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Colores.STEAM_BLUE));

		cancelar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		cancelar.setBackground(Colores.DARK_BLUE);
		cancelar.setForeground(Color.WHITE);
		cancelar.setFont(Fonts.ARIAL_PLAIN_15);
		cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelar.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Colores.STEAM_BLUE));

		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPanel.setBackground(Colores.DARK_BLUE);
		scrollPanel.getViewport().setBackground(Colores.DARK_BLUE);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabla.setForeground(Color.WHITE);
		tabla.setSelectionForeground(Color.CYAN);
		tabla.setOpaque(false);
		((DefaultTableCellRenderer) tabla.getDefaultRenderer(Object.class)).setOpaque(false);
		((DefaultTableCellRenderer) tabla.getDefaultRenderer(Integer.class)).setOpaque(false);
		((DefaultTableCellRenderer) tabla.getDefaultRenderer(Float.class)).setOpaque(false);

		// Agregando los elementos al panel

		setLayout(new GridBagLayout());
		panel1.setLayout(new GridBagLayout());
		panel2.setLayout(new GridBagLayout());
		panel3.setLayout(new GridBagLayout());
		
		panel1.add(nombreL,nombreLC);
		panel1.add(nombre, nombreC);
		panel1.add(consolaL, consolaLC);
		panel1.add(consola, consolaC);
		panel1.add(generosL, generosLC);
		panel1.add(Box.createRigidArea(new Dimension(60, 60)), block);
		panel1.add(scrollGeneros, generosC);
		panel2.add(videojuegoL, videojuegoLC);
		panel2.add(videojuego, videojuegoC);
		panel2.add(cantidadL, cantidadLC);
		panel2.add(cantidad, cantidadC);
		panel2.add(agregar, agregarC);

		panel3.add(totalL, totalLC);
		panel3.add(total, totalC);
		panel3.add(finalizar, finalizarC);
		panel3.add(cancelar, cancelarC);

		add(titulo, tituloC);
		add(panel1, panel1C);
		add(panel2,panel2C);
		add(scrollPanel, scrollPanelC);
		add(panel3, panel3C);
	}
	
	public void setVideojuegosEnELCombo(ArrayList<Videojuego> videojuegos) {
		ArrayList<String> strings = new ArrayList<String>();
		for(Videojuego vid : videojuegos) {
			String string = "" + vid.getId()+ " - " +vid.getNombre() + " - " + vid.getConsola() + " - " + vid.getClasificacion() + " - " + vid.getPrecio();
			strings.add(string);
		}
		this.videojuego.removeAllItems();
		for(String string : strings) {
			this.videojuego.addItem(string);
		}
	}
	
	public void llenarTabla(ArrayList<DetallesCompra> detalles) {
		CompraTableModel modelo = ((CompraTableModel)tabla.getModel());
		modelo.datos = detalles;
		modelo.fireTableDataChanged();
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
		switch (e.getActionCommand()) {
		case "Agregar":
			controlador.agregar();
			break;
		case "Finalizar":
			controlador.procesarCompra();
			break;
		case "Cancelar Compra":
			controlador.cancelar();
			break;
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(controlador != null) {
			controlador.filtrarPorConsola();
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(controlador != null) {
			controlador.filtrarPorGenero();
		}
	}
	
	@Override
	public void caretUpdate(CaretEvent e) {
		if(controlador != null) {
			controlador.filtrarPorNombre();
		}
	}

	public JButton getAgregar() {
		return agregar;
	}

	public JButton getFinalizar() {
		return finalizar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public JComboBox<String> getConsola() {
		return consola;
	}

	public JList<String> getGeneros() {
		return generos;
	}

	public JSpinner getCantidad() {
		return cantidad;
	}

	public JTable getTabla() {
		return tabla;
	}

	public JTextField getTotal() {
		return total;
	}
	

	public JTextField getNombre() {
		return nombre;
	}

	public CompraControlador getControlador() {
		return controlador;
	}
	

	public JComboBox<String> getVideojuego() {
		return videojuego;
	}


	public void setControlador(CompraControlador cont) {
		this.controlador = cont;
	}

}
