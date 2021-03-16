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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import proyecto.controlador.DistribuidoraControlador;
import proyecto.modelo.entidades.Distribuidora;
import proyecto.utilidades.Colores;
import proyecto.utilidades.Fonts;

public class AdministrarDistribuidoraPanel extends JPanel implements ActionListener, MouseListener{

	private class AdminTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		private String[] columnas = { "Id", "Nombre", "Telefono", "Direccion", "Cuenta" };
		private ArrayList<Distribuidora> datos = new ArrayList<Distribuidora>();

		@Override
		public int getRowCount() {
			return datos.size();
		}

		@Override
		public int getColumnCount() {
			return columnas.length;
		}

		@Override
		public String getColumnName(int rowIndex) {
			return columnas[rowIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Distribuidora dis = datos.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return dis.getId();
			case 1:
				return dis.getNombre();
			case 2:
				return dis.getTelefono();
			case 3:
				return dis.getDireccion();
			case 4:
				return dis.getCuentaBancaria();
			default:
				return 0;
			}
		}

	}
	
	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JTextField nombre;
	private JTextField telefono;
	private JTextField cuenta;
	private JTextField direccion;
	private JButton nuevo;
	private JButton agregar;
	private JButton editar;
	private JButton eliminar;
	private JTable tabla;
	private DistribuidoraControlador controlador;

	public AdministrarDistribuidoraPanel() {
		crearGUI();
	}

	public void crearGUI() {
		id = new JTextField("0",20);
		nombre = new JTextField(20);
		telefono = new JTextField(20);
		cuenta = new JTextField(20);
		direccion = new JTextField(20);
		
		nuevo = new JButton("Nuevo");
		nuevo.addActionListener(this);
		agregar = new JButton("Agregar");
		agregar.addActionListener(this);
		editar = new JButton("Editar");
		editar.addActionListener(this);
		eliminar = new JButton("Eliminar");
		eliminar.addActionListener(this);
			
		tabla = new JTable(new AdminTableModel());
		tabla.addMouseListener(this);
		
		JLabel titulo = new JLabel("Administrar distribuidores");
		JPanel panel = new JPanel();
		JPanel botonesPanel = new JPanel();
		JLabel idL = new JLabel("Id: ");
		JLabel nombreL = new JLabel("Nombre: ");
		JLabel telefonoL = new JLabel("Telefono: ");
		JLabel cuentaL = new JLabel("Cuenta: ");
		JLabel direccionL = new JLabel("Direccion: ");
		JScrollPane scrollPanel = new JScrollPane(tabla);

		int center = GridBagConstraints.CENTER;
		int both = GridBagConstraints.BOTH;
		Insets insets5 = new Insets(5, 5, 5, 5);
		Insets insets20 = new Insets(20, 20, 20, 20);

		GridBagConstraints tituloC = new GridBagConstraints(0, 0, 1, 1, 1, 0, center, both, insets5, 0, 0);
		GridBagConstraints panelC = new GridBagConstraints(0, 1, 1, 1, 1, 0, center, both, insets20, 0, 0);
		GridBagConstraints botonesPanelC = new GridBagConstraints(0, 2, 1, 1, 1, 0, center, both, insets20, 0, 0);
		GridBagConstraints scrollPanelC = new GridBagConstraints(0, 3, 1, 1, 1, 1, center, both, insets20, 0, 0);
		
		GridBagConstraints idLC = new GridBagConstraints(0, 0, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints idC = new GridBagConstraints(1, 0, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints telefonoLC = new GridBagConstraints(0, 1, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints telefonoC = new GridBagConstraints(1, 1, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints cuentaLC = new GridBagConstraints(0, 2, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints cuentaC = new GridBagConstraints(1, 2, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints nombreLC = new GridBagConstraints(2, 0, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints nombreC = new GridBagConstraints(3, 0, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints direccionLC = new GridBagConstraints(2, 1, 1, 1, .1, 1, center, both, insets5, 0, 0);
		GridBagConstraints direccionC = new GridBagConstraints(3, 1, 1, 1, .9, 1, center, both, insets5, 0, 0);
		GridBagConstraints nuevoC = new GridBagConstraints(0, 0, 1, 1, 1, 1, center, both, insets5, 10, 10);
		GridBagConstraints agregarC = new GridBagConstraints(1,0, 1, 1, 1, 1, center, both, insets5, 10, 10);
		GridBagConstraints editarC = new GridBagConstraints(2, 0, 1, 1, 1, 1, center, both, insets5, 10, 10);
		GridBagConstraints eliminarC = new GridBagConstraints(3, 0, 1, 1, 1, 1, center, both, insets5, 10, 10);

		// color y diseño//
		setPreferredSize(new Dimension(800, 500));
		panel.setOpaque(false);
		botonesPanel.setOpaque(false);

		titulo.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		titulo.setForeground(Color.WHITE);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);

		idL.setFont(Fonts.ARIAL_PLAIN_15);
		idL.setForeground(Color.WHITE);

		id.setOpaque(false);
		id.setFont(Fonts.ARIAL_PLAIN_15);
		id.setForeground(Color.WHITE);
		id.setCaretColor(Color.WHITE);
		id.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

		telefonoL.setFont(Fonts.ARIAL_PLAIN_15);
		telefonoL.setForeground(Color.WHITE);

		telefono.setOpaque(false);
		telefono.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		telefono.setForeground(Color.WHITE);
		telefono.setCaretColor(Color.WHITE);
		telefono.setFont(Fonts.ARIAL_PLAIN_15);

		cuentaL.setFont(Fonts.ARIAL_PLAIN_15);
		cuentaL.setForeground(Color.WHITE);

		cuenta.setOpaque(false);
		cuenta.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		cuenta.setForeground(Color.WHITE);
		cuenta.setCaretColor(Color.WHITE);
		cuenta.setFont(Fonts.ARIAL_PLAIN_15);

		nombreL.setFont(Fonts.ARIAL_PLAIN_15);
		nombreL.setForeground(Color.WHITE);

		nombre.setOpaque(false);
		nombre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		nombre.setForeground(Color.WHITE);
		nombre.setCaretColor(Color.WHITE);
		nombre.setFont(Fonts.ARIAL_PLAIN_15);

		direccionL.setFont(Fonts.ARIAL_PLAIN_15);
		direccionL.setForeground(Color.WHITE);

		direccion.setOpaque(false);
		direccion.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		direccion.setForeground(Color.WHITE);
		direccion.setCaretColor(Color.WHITE);
		direccion.setFont(Fonts.ARIAL_PLAIN_15);

		nuevo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		nuevo.setBackground(Colores.DARK_BLUE);
		nuevo.setForeground(Color.WHITE);
		nuevo.setFont(Fonts.ARIAL_PLAIN_15);
		nuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nuevo.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Colores.STEAM_BLUE));

		agregar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		agregar.setBackground(Colores.DARK_BLUE);
		agregar.setForeground(Color.WHITE);
		agregar.setFont(Fonts.ARIAL_PLAIN_15);
		agregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		agregar.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Colores.STEAM_BLUE));

		editar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		editar.setBackground(Colores.DARK_BLUE);
		editar.setForeground(Color.WHITE);
		editar.setFont(Fonts.ARIAL_PLAIN_15);
		editar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editar.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Colores.STEAM_BLUE));

		eliminar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		eliminar.setBackground(Colores.DARK_BLUE);
		eliminar.setForeground(Color.WHITE);
		eliminar.setFont(Fonts.ARIAL_PLAIN_15);
		eliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eliminar.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Colores.STEAM_BLUE));
		
		agregar.setForeground(Color.GREEN);
        eliminar.setForeground(Color.RED);
        editar.setForeground(Color.CYAN);
		
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		scrollPanel.setBackground(Colores.DARK_BLUE);
        scrollPanel.getViewport().setBackground(Colores.DARK_BLUE);
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tabla.setForeground(Color.WHITE);
        tabla.setSelectionForeground(Color.CYAN);
        tabla.setOpaque(false);
        ((DefaultTableCellRenderer)tabla.getDefaultRenderer(Object.class)).setOpaque(false);
        ((DefaultTableCellRenderer)tabla.getDefaultRenderer(Integer.class)).setOpaque(false);
        ((DefaultTableCellRenderer)tabla.getDefaultRenderer(Float.class)).setOpaque(false);

		setLayout(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		botonesPanel.setLayout(new GridBagLayout());

		panel.add(idL, idLC);
		panel.add(id, idC);
		panel.add(nombreL, nombreLC);
		panel.add(nombre, nombreC);
		panel.add(telefonoL, telefonoLC);
		panel.add(telefono, telefonoC);
		panel.add(cuentaL, cuentaLC);
		panel.add(cuenta, cuentaC);
		panel.add(direccionL, direccionLC);
		panel.add(direccion, direccionC);
		botonesPanel.add(nuevo, nuevoC);
		botonesPanel.add(agregar, agregarC);
		botonesPanel.add(editar, editarC);
		botonesPanel.add(eliminar, eliminarC);
		
		add(titulo, tituloC);
		add(panel, panelC);
		add(botonesPanel, botonesPanelC);
		add(scrollPanel, scrollPanelC);
	}
	
	public Distribuidora getDistribuidoraSeleccionada() {
		try {
			int index = tabla.getSelectedRow();
			Distribuidora dis = ((AdminTableModel)tabla.getModel()).datos.get(index);
			return dis;
		}catch(Exception e) {
			return null;
		}
	}
	
	public void llenarCampos(Distribuidora dis){
		if(dis == null) {
			id.setText("0");
			nombre.setText("");
			telefono.setText("");
			direccion.setText("");
			cuenta.setText("");
		}else {
			id.setText(""+dis.getId());
			nombre.setText(dis.getNombre());
			telefono.setText(dis.getTelefono());
			direccion.setText(dis.getDireccion());
			cuenta.setText(dis.getCuentaBancaria());
		}
	}
	
	public void llenarTabla(ArrayList<Distribuidora> distribuidoras) {
		AdminTableModel modelo = ((AdminTableModel)tabla.getModel());
		modelo.datos = distribuidoras;
		modelo.fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(controlador != null) {
			String accion = e.getActionCommand();
			switch(accion) {
			case "Nuevo":
				controlador.nuevaDistribuidora();
				break;
			case "Agregar":
				controlador.agregarDistribuidora();
				break;
			case "Editar":
				controlador.editarDistribuidora();
				break;
			case "Eliminar":
				controlador.eliminarDistribuidora();
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

	public JTextField getId() {
		return id;
	}

	public JTextField getNombre() {
		return nombre;
	}

	public JTextField getTelefono() {
		return telefono;
	}

	public JTextField getCuenta() {
		return cuenta;
	}

	public JTextField getDireccion() {
		return direccion;
	}

	public JButton getNuevo() {
		return nuevo;
	}

	public JButton getAgregar() {
		return agregar;
	}

	public JButton getEditar() {
		return editar;
	}

	public JButton getEliminar() {
		return eliminar;
	}

	public JTable getTabla() {
		return tabla;
	}

	public DistribuidoraControlador getControlador() {
		return controlador;
	}

	public void setControlador(DistribuidoraControlador cont) {
		this.controlador = cont;
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
