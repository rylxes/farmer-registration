package fingerprint.scanner;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.digitalpersona.uareu.*;
import com.digitalpersona.uareu.Fid.Fiv;

public class ImagePanel 
	extends JPanel
{
	private static final long serialVersionUID = 5;
	private BufferedImage m_image;

	public void showImage(Fid image, String title){
		Fiv view = image.getViews()[0];
		m_image = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		m_image.getRaster().setDataElements(0, 0, view.getWidth(), view.getHeight(), view.getImageData());
		repaint();
		String path = System.getProperty("user.dir");
		System.out.println("Finger image path: " + path + "//fingerprints//" + title + ".png");
		File png = new File(path + "//fingerprints//" + title + ".png");
		try {
			ImageIO.write(m_image, "png", png);
		} catch (IOException ex) {
			Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("Done Saving..");
	}

	public void paint(Graphics g) {
		g.drawImage(m_image, 0, 0, null);
	}

}
