package proyecto.vista;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import proyecto.utilidades.Colores;
import proyecto.utilidades.Fonts;


public class EligirDistribuidora extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	private JComboBox<String> dis;
	private JButton bot;
	
	public EligirDistribuidora() {
		CrearGUI();
	}
	
	private void CrearGUI(){
		JLabel l1 = new JLabel("Elige una distribuidora:");
		
	
		dis = new JComboBox<String>();
		
		bot = new JButton("Continuar");
		bot.addActionListener(this);
		
		//panel.setBackground(Colores.STEAM_BLUE);
		
		l1.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		l1.setForeground(Color.WHITE);
		l1.setOpaque(false);
		l1.setBackground(Colores.DARK_BLUE);
		
		
		
		dis.setOpaque(false);
		dis.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		dis.setForeground(Color.WHITE);
		dis.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
	
		bot.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bot.setBackground(Colores.DARK_BLUE);
		bot.setForeground(Color.WHITE);
		bot.setFont(Fonts.CENTURYGOTHIC_BOLD_20);
		bot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setAlignmentY(CENTER_ALIGNMENT);
		
		add(l1);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(Box.createVerticalGlue());
		add(dis);
		add(Box.createRigidArea(new Dimension(10,10)));
		add(Box.createVerticalGlue());
		add(bot);
	}
	
	public JComboBox<String> getDis() {
        return dis;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			
	}
	
	
	public void paintComponent(Graphics g){
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

}

