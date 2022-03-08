package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


/**
 * This class is designed in order to view ModelClass
 *
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 *         Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 * @version v.1.0.0 date: 07/03/2022
 */

public class GUIGridBagLayout extends JFrame {
    private Header headerProject;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JPanel tableroPosicion;
    private JPanel tableroPrincipal;
    private ModelClass modelClass;
    private Escucha escucha;
    private JButton horizontal, vertical, iniciar, territorioEnemigo;
    private int interfaz, posicionFlota;
    private JButton[][] tableroPosicionU, tableroPosicionM;
    private JTextArea cantidadFlotas, ayuda;
    private int[] cantidadFlota;
    private String[] nombreFlota;
    private String orientacion, tipoFlota;
    private GridBagConstraints constrains;

    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout() {
        interfaz = 0;
        tableroPosicionU = new JButton[10][10];
        tableroPosicionM = new JButton[10][10];
        posicionFlota = 0;
        nombreFlota = new String[]{"Portaaviones", "Submarinos", "Destructores", "Fragatas"};
        cantidadFlota = new int[]{1, 2, 3, 4};
        initGUI();

        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        constrains = new GridBagConstraints();

        modelClass = new ModelClass();

        escucha = new Escucha();

        cantidadFlotas = new JTextArea(1, 5);
        ayuda = new JTextArea(2, 5);

        horizontal = new JButton();
        vertical = new JButton();

        headerProject = new Header("Procede a organizar los barcos", new Color(64, 162, 27));
        headerProject.setPreferredSize(new Dimension(11, 40));
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constrains);

        panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setPreferredSize(new Dimension(480, 500));
        panelIzquierdo.setBackground(Color.WHITE);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelIzquierdo, constrains);

        tableroPosicion = new JPanel(new GridBagLayout());
        tableroPosicion.setPreferredSize(new Dimension(400, 400));
        tableroPosicion.setBackground(null);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelIzquierdo.add(tableroPosicion, constrains);
        pintarTableroPosicion();

        panelDerecho = new JPanel(new GridBagLayout());
        panelDerecho.setPreferredSize(new Dimension(360, 500));
        panelDerecho.setBackground(Color.white);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelDerecho, constrains);

        pintarOpcionAlineacion();
        horizontal.addActionListener(escucha);
        horizontal.setBackground(null);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;


        ayuda.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        ayuda.setBackground(null);
        ayuda.setEditable(false);
        constrains.gridx = 0;
        constrains.gridy = 2;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;


        vertical.addActionListener(escucha);
        vertical.setBackground(null);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;

    }

    /**
     * This method adds 100 buttons to tableroPosicion for the first time, when the array is created
     */
    private void pintarTableroPosicion() {
        if (interfaz == 0) {
            GridBagConstraints constrainsPosicion = new GridBagConstraints();
            constrainsPosicion.weightx = 40;
            constrainsPosicion.weighty = 40;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    tableroPosicionU[i][j] = new JButton();
                    tableroPosicionU[i][j].setBackground(new Color(91, 164, 252));
                    tableroPosicionU[i][j].setPreferredSize(new Dimension(40, 40));
                    constrainsPosicion.gridx = i;
                    constrainsPosicion.gridy = j;
                    constrainsPosicion.gridwidth = 1;
                    constrainsPosicion.fill = GridBagConstraints.NONE;
                    constrainsPosicion.anchor = GridBagConstraints.CENTER;
                    tableroPosicion.add(tableroPosicionU[i][j], constrainsPosicion);
                }
            }
        }
    }

    /**
     * This method has the purpose of modifying the images displayed by the buttons
     * with respect to what is in the array parameter
     *
     * @param matrix with the changes to be made
     */
    private void pintarTableroPosicion(String[][] matrix) {

    }

    private void pintarTableroPrincipal() {
        if (interfaz == 2) {
            GridBagConstraints constrainsPosicionDerecha = new GridBagConstraints();
            constrainsPosicionDerecha.weightx = 40;
            constrainsPosicionDerecha.weighty = 40;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    tableroPosicionM[i][j] = new JButton();
                    tableroPosicionM[i][j].setBackground(new Color(30, 124, 236));
                    tableroPosicionM[i][j].setPreferredSize(new Dimension(40, 40));
                    constrainsPosicionDerecha.gridx = i;
                    constrainsPosicionDerecha.gridy = j;
                    constrainsPosicionDerecha.gridwidth = 1;
                    constrainsPosicionDerecha.fill = GridBagConstraints.NONE;
                    constrainsPosicionDerecha.anchor = GridBagConstraints.CENTER;
                    tableroPrincipal.add(tableroPosicionM[i][j], constrainsPosicionDerecha);
                }
            }
        }
    }

    private void pintarTableroPrincipal(String[][] matrixTabPrincipal) {

    }

    /**
     * Method in order to load the information that displays in panelChoice
     */
    private void pintarPanelEleccion() {
        if (posicionFlota < 4) {
            switch (nombreFlota[posicionFlota]) {
                case "Portaaviones" -> tipoFlota = "portaaviones";
                case "Submarinos" -> tipoFlota = "submarino";
                case "Destructores" -> tipoFlota = "destructor";
                case "Fragatas" -> tipoFlota = "fragata";
            }
            int espacio = 0;
            switch (tipoFlota) {
                case "portaaviones" -> espacio = 4;
                case "submarino" -> espacio = 3;
                case "destructor" -> espacio = 2;
                case "fragata" -> espacio = 1;
            }

        }
    }

    /**
     * Shows the images of the ships that will be located at the beginning
     */
    private void pintarOpcionAlineacion() {
        String direccion = "";
        switch (nombreFlota[posicionFlota]) {
            case "Portaaviones" -> direccion = "/resources/portaaviones.";
            case "Submarinos" -> direccion = "/resources/submarino.";
            case "Destructores" -> direccion = "/resources/destructor.";
            case "Fragatas" -> direccion = "/resources/fragata.";
        }
        if (interfaz == 0) {
            interfaz = 1;
        }

    }

    /**
     * adds the listener to the 100 buttons
     *
     * @param matrix where the buttons are
     */
    private void addEscucha(JButton[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j].setBackground(new Color(16, 92, 234));
                matrix[i][j].addActionListener(escucha);
            }
        }
    }

    /**
     * remove the listener to the 100 buttons
     *
     * @param matrix where the buttons are
     */
    private void removeEscucha(JButton[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j].setBackground(new Color(30, 124, 236));
                matrix[i][j].removeActionListener(escucha);
            }
        }
    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is executed by console.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class, in charge of monitoring the user's actions with the program and updating the interface and the game accordingly.
     */

    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (interfaz == 1) {
                if (posicionFlota < 4) {

                    if (e.getSource() == horizontal) {
                        orientacion = "horizontal";
                        addEscucha(tableroPosicionU);
                        horizontal.removeActionListener(escucha);
                        vertical.removeActionListener(escucha);

                    } else if (e.getSource() == vertical) {
                        orientacion = "vertical";
                        addEscucha(tableroPosicionU);
                        horizontal.removeActionListener(escucha);
                        vertical.removeActionListener(escucha);

                    } else {
                        //check which of the 100 buttons was clicked
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (tableroPosicionU[i][j] == e.getSource()) {
                                    //once found, it is checked to see if it can be added to the underlying positions
                                    if (modelClass.ubicarBarco(i, j, orientacion, tipoFlota)) {
                                        pintarTableroPosicion(modelClass.getTableroPosUsuario());
                                        if (cantidadFlota[posicionFlota] == 0) {
                                            posicionFlota++;
                                            if (posicionFlota < 4) {
                                                pintarOpcionAlineacion();
                                            }
                                            //when the entire fleet was positioned
                                            else {
                                                iniciar = new JButton("Iniciar");
                                                iniciar.addActionListener(escucha);
                                                constrains.gridx = 0;
                                                constrains.gridy = 1;
                                                constrains.gridwidth = 1;
                                                constrains.fill = GridBagConstraints.NONE;
                                                constrains.anchor = GridBagConstraints.CENTER;
                                                panelIzquierdo.add(iniciar, constrains);

                                                panelDerecho.removeAll();
                                                ayuda.setText("Bienvenid@ a Batalla Naval\n      Presiona 'Iniciar'    ");
                                                ayuda.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
                                                constrains.gridx = 0;
                                                constrains.gridy = 0;
                                                constrains.gridwidth = 2;
                                                constrains.fill = GridBagConstraints.NONE;
                                                constrains.anchor = GridBagConstraints.CENTER;
                                                panelDerecho.add(ayuda, constrains);

                                                interfaz = 2;
                                            }
                                        }

                                        pintarPanelEleccion();
                                        removeEscucha(tableroPosicionU);
                                        horizontal.addActionListener(escucha);
                                        vertical.addActionListener(escucha);
                                    } else {
                                        JOptionPane.showMessageDialog(tableroPosicion, "No se pudo posicionar la flota porque " + modelClass.getError(), "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}