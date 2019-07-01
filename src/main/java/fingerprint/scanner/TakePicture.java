package fingerprint.scanner;
//import com.digitalpersona.uareu.Fid;
//import com.digitalpersona.uareu.Reader;
//import com.digitalpersona.uareu.UareUException;

import com.digitalpersona.uareu.Fid;
import com.github.sarxos.webcam.Webcam;
import javafx.scene.control.Alert;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TakePicture  extends JPanel
        implements ActionListener
{
    private static final long serialVersionUID = 2;
    private static final String ACT_BACK = "back";
    private static final String ACT_PHOTO = "Take Photo";;

    private JDialog       m_dlgParent;
    private CaptureThread m_capture;
    private ImagePanel    m_image;
    private boolean       m_bStreaming;

    JTextField img_title = new JTextField();
    JLabel lbl_status = new JLabel(" ");

    private TakePicture(){

        final int vgap = 5;
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        JButton btnBack = new JButton("Capture Photo");
        btnBack.setActionCommand(ACT_PHOTO);
        btnBack.addActionListener(this);
        add(btnBack);
        add(Box.createVerticalStrut(vgap));

        add(lbl_status);
        add(Box.createVerticalStrut(vgap));


        m_image = new ImagePanel();
        Dimension dm = new Dimension(380, 380);
        m_image.setPreferredSize(dm);
        add(m_image);
        add(Box.createVerticalStrut(vgap));

        JLabel lbl_title = new JLabel("Enter farmer BVN before Taking Photo");
        add(lbl_title);
        add(Box.createVerticalStrut(vgap));

//		JTextField img_title = new JTextField();
        add(img_title);
        add(Box.createVerticalStrut(vgap));

        JButton btnBack2 = new JButton("Back");
        btnBack2.setActionCommand(ACT_BACK);
        btnBack2.addActionListener(this);
        add(btnBack);
        add(Box.createVerticalStrut(vgap));
    }



    public void actionPerformed(ActionEvent e){

       if(e.getActionCommand().equals(CaptureThread.ACT_PHOTO)){
            Webcam webcam = Webcam.getDefault();
            if (webcam != null) {
                System.out.println("webcam: " + webcam.getName());
                webcam.open();
// get image
                BufferedImage image = webcam.getImage();
                m_image.showImage((Fid) image, this.img_title.getText());
// save image to PNG file
                try{
                    ImageIO.write(image, "PNG", new File(System.getProperty("user.dir") + "//photos//" + img_title.getText() + ".png"));
                }catch (IOException ex){

                }

            } else {
                //System.out.println("No webcam detected");
                this.lbl_status.setText("No webcam detected");
            }

        }
    }

    private void doModal(JDialog dlgParent){

        boolean bOk = true;

        if(bOk){
            m_dlgParent = dlgParent;
            m_dlgParent.setContentPane(this);
            m_dlgParent.pack();
            m_dlgParent.setLocationRelativeTo(null);
            m_dlgParent.toFront();
            m_dlgParent.setVisible(true);
            m_dlgParent.dispose();

        }
    }

    public static void Run(){
        JDialog dlg = new JDialog((JDialog)null, "Take Piture", true);
        TakePicture capture = new TakePicture();
       capture.doModal(dlg);
    }
}
