package myProject;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 *         Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 * @version v.1.0.1 date: 12/02/2022
 */
public class Canvas  extends JPanel
{
    private ImageIcon imagenFrame;

    /**
     * Constructor of class Canvas
     * */

    public Canvas()
    {
        imagenFrame =
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/inicio.png")));
    }

    /**
     * This method is responsible for painting the background of the JFrame and the panelGame
     * */
    public void paintComponent(Graphics g) {

        //imagenFrame = new ImageIcon(imagenFrame.getImage().getScaledInstance(960, 581, Image.SCALE_SMOOTH));
        g.drawImage(imagenFrame.getImage(), 0, 0, getWidth(), getHeight(), null);
        setOpaque(false);
        super.paintComponent(g);
    }
}
