package proyecto.utilidades;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImagenUtilidades {

	public static ImageIcon getImage(String src, int width, int height) {
		ImageIcon icon = new ImageIcon(src);
		Image image = icon.getImage();
		icon = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return icon;
	}
	
}
