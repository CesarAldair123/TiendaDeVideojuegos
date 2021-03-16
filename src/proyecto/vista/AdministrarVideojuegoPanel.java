package proyecto.vista;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import proyecto.controlador.VideojuegoControlador;
import proyecto.modelo.entidades.Distribuidora;
import proyecto.modelo.entidades.Videojuego;
import proyecto.utilidades.Colores;
import proyecto.utilidades.Constantes;
import proyecto.utilidades.Fonts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AdministrarVideojuegoPanel extends JPanel implements ActionListener, MouseListener {

	private class VideojuegoTabla extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		private String[] columnNames = { "ID", "Distribuidora", "Nombre", "Desarrolladora", "Consola", "Clasificacion",
				"Generos", "Precio", "Stock" };
		private ArrayList<Videojuego> datos = new ArrayList<Videojuego>();

		@Override
		public int getRowCount() {
			return datos.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getColumnName(int rowIndex) {
			return columnNames[rowIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Videojuego videojuego = datos.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return videojuego.getId();
			case 1:
				Distribuidora dis = videojuego.getDistribuidora();
				String disString = "" + dis.getId() + " - " + dis.getNombre();
				return disString;
			case 2:
				return videojuego.getNombre();
			case 3:
				return videojuego.getDesarrolladora();
			case 4:
				return videojuego.getConsola();
			case 5:
				return videojuego.getClasificacion();
			case 6:
				return videojuego.getGeneros();
			case 7:
				return videojuego.getPrecio();
			case 8:
				return videojuego.getStock();
			default:
				return 0;
			}
		}
	}
	
	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JComboBox<String> distribuidora;
	private JTextField nombre;
	private JTextField desarrolladora;
	private JComboBox<String> consola;
	private JComboBox<String> clasificacion;
	private JTextField precio;
	private JSpinner stock;
	private JList<String> generos;
	private JTable tabla;

	private JButton agregar;
	private JButton eliminar;
	private JButton editar;
	private JButton nuevo;

	private VideojuegoControlador controlador;

	public AdministrarVideojuegoPanel() {
		createGUI();
	}

	public void createGUI() {
		id = new JTextField("0",20);
		distribuidora = new JComboBox<String>();
		nombre = new JTextField(20);
		desarrolladora = new JTextField(20);
		consola = new JComboBox<String>(Constantes.consolas);
		clasificacion = new JComboBox<String>(Constantes.clasificaciones);
		precio = new JTextField("0", 20);
		stock = new JSpinner();
		generos = new JList<String>(Constantes.generos);

		TableModel tableModel = new VideojuegoTabla();
		tabla = new JTable(tableModel);

		agregar = new JButton("Agregar");
		eliminar = new JButton("Eliminar");
		editar = new JButton("Editar");
		nuevo = new JButton("Nuevo");

		agregar.addActionListener(this);
		eliminar.addActionListener(this);
		editar.addActionListener(this);
		nuevo.addActionListener(this);

		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.addMouseListener(this);

		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(900, 700));

		JLabel title = new JLabel("Administrador de Videojuegos");
		JPanel fieldsPanel = createFieldsPanel();
		JPanel buttonsPanel = createButtonsPanel();
		JScrollPane tablePanel = new JScrollPane(tabla);

		int center = GridBagConstraints.CENTER;
		int both = GridBagConstraints.BOTH;
		Insets insets5 = new Insets(5, 5, 5, 5);

		GridBagConstraints titleC = new GridBagConstraints(0, 0, 1, 1, 1, 0, center, both, insets5, 0, 0);
		GridBagConstraints fieldsPanelC = new GridBagConstraints(0, 1, 1, 1, 1, 0, center, both, insets5, 0, 0);
		GridBagConstraints buttonsPanelC = new GridBagConstraints(0, 2, 1, 1, 1, 0, center, both, insets5, 0, 0);
		GridBagConstraints tablePanelC = new GridBagConstraints(0, 3, 1, 1, 1, 1, center, both, insets5, 0, 0);

		title.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBackground(Colores.DARK_BLUE);
		title.setOpaque(true);

		tablePanel.setBackground(Colores.DARK_BLUE);
		tablePanel.getViewport().setBackground(Colores.DARK_BLUE);
		tablePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabla.setForeground(Color.WHITE);
		tabla.setSelectionForeground(Color.CYAN);
		tabla.setOpaque(false);
		((DefaultTableCellRenderer) tabla.getDefaultRenderer(Object.class)).setOpaque(false);
		((DefaultTableCellRenderer) tabla.getDefaultRenderer(Integer.class)).setOpaque(false);
		((DefaultTableCellRenderer) tabla.getDefaultRenderer(Float.class)).setOpaque(false);

		add(title, titleC);
		add(fieldsPanel, fieldsPanelC);
		add(buttonsPanel, buttonsPanelC);
		add(tablePanel, tablePanelC);
	}

	private JPanel createFieldsPanel() {
		JPanel fieldsPanel = new JPanel();
		JLabel idL = new JLabel("ID: ");
		JLabel publisherL = new JLabel("Distribuidora");
		JLabel nameL = new JLabel("Nombre: ");
		JLabel developerL = new JLabel("Desarrolladora: ");
		JLabel consoleL = new JLabel("Consola: ");
		JLabel classificationL = new JLabel("Clasificacion");
		JLabel genresL = new JLabel("Generos: ");
		JLabel priceL = new JLabel("Precio: ");
		JLabel stockL = new JLabel("Stock: ");
		JScrollPane genresPanel = new JScrollPane(generos);

		int center = GridBagConstraints.CENTER;
		int both = GridBagConstraints.BOTH;
		int none = GridBagConstraints.NONE;
		Insets insets5 = new Insets(5, 5, 5, 5);

		GridBagConstraints idLC = new GridBagConstraints(0, 0, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints publisherLC = new GridBagConstraints(0, 1, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints nameLC = new GridBagConstraints(0, 2, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints developerLC = new GridBagConstraints(0, 3, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints consoleLC = new GridBagConstraints(2, 0, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints classificationLC = new GridBagConstraints(2, 1, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints priceLC = new GridBagConstraints(2, 2, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints stockLC = new GridBagConstraints(2, 3, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints genresLC = new GridBagConstraints(4, 0, 1, 4, .1, 1, center, none, insets5, 0, 0);

		GridBagConstraints idSC = new GridBagConstraints(1, 0, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints publisherCBC = new GridBagConstraints(1, 1, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints nameFC = new GridBagConstraints(1, 2, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints developerFC = new GridBagConstraints(1, 3, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints consoleCBC = new GridBagConstraints(3, 0, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints classificationCBC = new GridBagConstraints(3, 1, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints priceFC = new GridBagConstraints(3, 2, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints stockSC = new GridBagConstraints(3, 3, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints genresJLC = new GridBagConstraints(5, 0, 1, 4, .9, 1, center, both, insets5, 0, 0);

		fieldsPanel.setOpaque(false);
		fieldsPanel.setLayout(new GridBagLayout());

		genresPanel.setOpaque(false);

		JLabel[] labels = { idL, publisherL, nameL, developerL, consoleL, classificationL, genresL, priceL, stockL };
		for (JLabel label : labels) {
			label.setForeground(Color.WHITE);
		}

		generos.setForeground(Color.WHITE);
		generos.setBackground(Colores.DARK_BLUE);
		generos.setBorder(BorderFactory.createEmptyBorder());
		generos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JTextField[] fields = { id,nombre, desarrolladora, precio };
		for (JTextField field : fields) {
			field.setForeground(Color.WHITE);
			field.setOpaque(false);
			field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
			field.setCaretColor(Color.WHITE);
		}

		JComboBox<?>[] comboBoxes = { distribuidora, consola, clasificacion };
		for (JComboBox<?> comboBox : comboBoxes) {
			comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			comboBox.setForeground(Color.WHITE);
			comboBox.setBackground(Colores.DARK_BLUE);
			for (Component component1 : comboBox.getComponents()) {
				component1.setBackground(Color.WHITE);
			}
		}

		JSpinner[] spinners = { stock };
		for (JSpinner spinner : spinners) {
			spinner.setForeground(Color.WHITE);
			spinner.setOpaque(false);
			spinner.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
			spinner.getEditor().setOpaque(false);
			((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setOpaque(false);
			((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setCaretColor(Color.WHITE);
			((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setForeground(Color.WHITE);
		}

		fieldsPanel.add(idL, idLC);
		fieldsPanel.add(publisherL, publisherLC);
		fieldsPanel.add(nameL, nameLC);
		fieldsPanel.add(developerL, developerLC);
		fieldsPanel.add(consoleL, consoleLC);
		fieldsPanel.add(classificationL, classificationLC);
		fieldsPanel.add(genresL, genresLC);
		fieldsPanel.add(priceL, priceLC);
		fieldsPanel.add(stockL, stockLC);

		fieldsPanel.add(id, idSC);
		fieldsPanel.add(distribuidora, publisherCBC);
		fieldsPanel.add(nombre, nameFC);
		fieldsPanel.add(desarrolladora, developerFC);
		fieldsPanel.add(consola, consoleCBC);
		fieldsPanel.add(clasificacion, classificationCBC);
		fieldsPanel.add(genresPanel, genresJLC);
		fieldsPanel.add(precio, priceFC);
		fieldsPanel.add(stock, stockSC);

		return fieldsPanel;
	}

	private JPanel createButtonsPanel() {
		JPanel buttonsPanel = new JPanel();

		int center = GridBagConstraints.CENTER;
		int both = GridBagConstraints.BOTH;
		Insets insets10 = new Insets(10, 10, 10, 10);

		GridBagConstraints nuevoC = new GridBagConstraints(0, 0, 1, 1, 1, 1, center, both, insets10, 10, 10);
		GridBagConstraints agregarC = new GridBagConstraints(1, 0, 1, 1, 1, 1, center, both, insets10, 10, 10);
		GridBagConstraints editarC = new GridBagConstraints(2, 0, 1, 1, 1, 1, center, both, insets10, 10, 10);
		GridBagConstraints eliminarC = new GridBagConstraints(3, 0, 1, 1, 1, 1, center, both, insets10, 10, 10);

		buttonsPanel.setOpaque(false);
		buttonsPanel.setLayout(new GridBagLayout());

		JButton[] buttons = { agregar, eliminar, editar, nuevo };
		for (JButton button : buttons) {
			button.setForeground(Color.WHITE);
			button.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Colores.STEAM_BLUE));
			button.setBackground(Colores.DARK_BLUE);
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		agregar.setForeground(Color.GREEN);
		eliminar.setForeground(Color.RED);
		editar.setForeground(Color.CYAN);

		buttonsPanel.add(agregar, agregarC);
		buttonsPanel.add(eliminar, eliminarC);
		buttonsPanel.add(editar, editarC);
		buttonsPanel.add(nuevo, nuevoC);

		return buttonsPanel;
	}

	public void setDistribuidorasEnElCombo(ArrayList<Distribuidora> distribuidoras) {
		ArrayList<String> disStrings = new ArrayList<String>();
		for (Distribuidora dis : distribuidoras) {
			String disString = "" + dis.getId() + " - " + dis.getNombre();
			disStrings.add(disString);
		}
		distribuidora.removeAllItems();
		for (String dis : disStrings) {
			distribuidora.addItem(dis);
		}
	}

	public void llenarCampos(Videojuego videojuego) {
		if (videojuego == null) {
			id.setText("0");
			nombre.setText("");
			desarrolladora.setText("");
			precio.setText("0.0");
			generos.setSelectedIndices(new int[] {});
			stock.setValue(0);
		} else {
			id.setText(""+videojuego.getId());
			int disId = videojuego.getDistribuidoraId();
			for (int i = 0; i < distribuidora.getModel().getSize(); i++) {
				String disString = distribuidora.getItemAt(i).split("-")[0].trim();
				if (disString.equals("" + disId)) {
					distribuidora.setSelectedIndex(i);
					break;
				}
			}
			nombre.setText(videojuego.getNombre());
			desarrolladora.setText(videojuego.getDesarrolladora());
			String con = videojuego.getConsola();
			for (int i = 0; i < consola.getModel().getSize(); i++) {
				if (consola.getItemAt(i).equals(con)) {
					consola.setSelectedIndex(i);
				}
			}
			String clas = videojuego.getClasificacion();
			for (int i = 0; i < clasificacion.getModel().getSize(); i++) {
				if (clasificacion.getItemAt(i).equals(clas)) {
					clasificacion.setSelectedIndex(i);
					break;
				}
			}
			String[] gen = videojuego.getGeneros().split(",");
			int[] indices = new int[gen.length];
			for (int i = 0; i < Constantes.generos.length; i++) {
				for(int j = 0; j < gen.length; j++) {
					if(gen[j].equals(Constantes.generos[i])) {
						indices[j] = i;
					}
				}
			}
			generos.setSelectedIndices(indices);
			precio.setText(""+videojuego.getPrecio());
			stock.setValue(videojuego.getStock());
		}
	}
	
	public void llenarTabla(ArrayList<Videojuego> videojuegos) {
		VideojuegoTabla modelo = ((VideojuegoTabla)tabla.getModel());
		modelo.datos = videojuegos;
		modelo.fireTableDataChanged();
	}
	
	public Videojuego getVideojuegoSeleccionado() {
		try {
			int index = tabla.getSelectedRow();
			Videojuego vid = ((VideojuegoTabla)tabla.getModel()).datos.get(index);
			return vid;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(controlador != null) {
			String accion = e.getActionCommand();
			switch(accion) {
			case "Nuevo":
				controlador.nuevoVideojuego();
				break;
			case "Agregar":
				controlador.agregarVideojuego();
				break;
			case "Editar":
				controlador.editarVideojuego();
				break;
			case "Eliminar":
				controlador.eliminarVideojuego();
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(controlador != null) {
			controlador.filaSeleccionada();
		}
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
	
	public JTextField getId() {
		return id;
	}

	public JComboBox<String> getDistribuidora() {
		return distribuidora;
	}

	public JTextField getNombre() {
		return nombre;
	}

	public JTextField getDesarrolladora() {
		return desarrolladora;
	}

	public JComboBox<String> getConsola() {
		return consola;
	}

	public JComboBox<String> getClasificacion() {
		return clasificacion;
	}

	public JTextField getPrecio() {
		return precio;
	}

	public JSpinner getStock() {
		return stock;
	}

	public JList<String> getGeneros() {
		return generos;
	}

	public JTable getTabla() {
		return tabla;
	}

	public JButton getAgregar() {
		return agregar;
	}

	public JButton getEliminar() {
		return eliminar;
	}

	public JButton getEditar() {
		return editar;
	}

	public JButton getNuevo() {
		return nuevo;
	}

	public VideojuegoControlador getControlador() {
		return controlador;
	}

	public void setControlador(VideojuegoControlador controlador) {
		this.controlador = controlador;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
