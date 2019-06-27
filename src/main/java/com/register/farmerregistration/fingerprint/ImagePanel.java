package com.register.farmerregistration.fingerprint;

import com.digitalpersona.uareu.*;
import com.digitalpersona.uareu.Fid.Fiv;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImagePanel
        extends JPanel {
    private static final long serialVersionUID = 5;
    private BufferedImage m_image;

    public void showImage(Fid image) {
        Fiv view = image.getViews()[0];
        m_image = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        m_image.getRaster().setDataElements(0, 0, view.getWidth(), view.getHeight(), view.getImageData());
        repaint();


        String x = "ims";
        //saving file
        File png = new File("C://Users//HP//Documents//.Fingerprints Iseng//" + x + ".png");
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
