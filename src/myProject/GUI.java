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

public class GUI extends JFrame {
    private static final String BIENVENIDA="¡BIENVENIDO/A A BATALLA NAVAL!\n Distribuye tu flota para iniciar partida";
    private Header headerProject;
    private JPanel panelInicio, panelIzquierdo, panelDerecho, panelDerecho2, tableroPosicion, tableroPrincipal;
    private ModelClass modelClass;
    private Escucha escucha;
    private ImageIcon img;
    private JLabel logo,labelAux;
    private JButton horizontal, vertical, iniciar, instrucciones,
    salir, territorioEnemigo, elegirPortaavion,elegirSubmarino,
            elegirDestructor, elegirFragata;
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
    public GUI() {
        interfaz = 0;
        tableroPosicionU = new JButton[10][10];
        tableroPosicionM = new JButton[10][10];
        posicionFlota = 0;
        nombreFlota = new String[]{"Portaaviones", "Submarinos", "Destructores", "Fragatas"};
        //cantidadFlota = new int[]{1, 2, 3, 4};

        this.setContentPane(new Canvas()); // to Paint the background image of the Frame
        initGUI();
        // Default JFrame configuration
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
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


        salir = new JButton();
        salir.addActionListener(escucha);
        salir.setPreferredSize(new Dimension(100, 80));
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones/exit.png")));
        salir.setIcon(new ImageIcon(img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        salir.setBorderPainted(false);
        salir.setFocusPainted(false);
        salir.setContentAreaFilled(false);
        constrains.gridx = 1;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.LINE_END;
        this.add(salir, constrains);

        panelInicio = new JPanel(new GridBagLayout());
        panelInicio.setPreferredSize(new Dimension(960, 500));
        panelInicio.setOpaque(false);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        this.add(panelInicio, constrains);

        img= new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/logoGame.png")));
        img= new ImageIcon(img.getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH));
        logo= new JLabel(img);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.PAGE_START;
        panelInicio.add(logo, constrains);

        iniciar= new JButton();
        iniciar.addActionListener(escucha);
        iniciar.setPreferredSize(new Dimension(300, 80));
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones/botonIniciar" +
                ".png")));
        iniciar.setIcon(new ImageIcon(img.getImage().getScaledInstance(300, 60, Image.SCALE_SMOOTH)));
        iniciar.setBorderPainted(false);
        iniciar.setContentAreaFilled(false);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelInicio.add(iniciar, constrains);

        instrucciones= new JButton();
        instrucciones.addActionListener(escucha);
        instrucciones.setPreferredSize(new Dimension(300, 70));
        img =
            new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones" +
                    "/botonInstrucciones.png")));
        instrucciones.setIcon(new ImageIcon(img.getImage().getScaledInstance(300, 60, Image.SCALE_SMOOTH)));
        instrucciones.setBorderPainted(false);
        instrucciones.setContentAreaFilled(false);
        constrains.gridx = 0;
        constrains.gridy = 2;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelInicio.add(instrucciones, constrains);

        revalidate();
        repaint();


    }

    /**
     *Este método crea los paneles necesarios para los tableros del juego
     */

    private void pintarEntrada(){

        img= new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/creaFlota.png")));
        img= new ImageIcon(img.getImage().getScaledInstance(500,60,Image.SCALE_SMOOTH));
        labelAux= new JLabel(img);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.PAGE_END;
        add(labelAux, constrains);

        panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setPreferredSize(new Dimension(480, 500));
        panelIzquierdo.setOpaque(false);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.gridheight= 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelIzquierdo, constrains);

        tableroPosicion = new JPanel(new GridBagLayout());
        tableroPosicion.setPreferredSize(new Dimension(460, 460));
        tableroPosicion.setBackground(Color.GREEN);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelIzquierdo.add(tableroPosicion, constrains);
        pintarTableroPosicion();

        panelDerecho = new JPanel(new GridBagLayout());
        panelDerecho.setPreferredSize(new Dimension(480, 220));
        //panelDerecho.setBackground(Color.gray);
        panelDerecho.setOpaque(false);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.gridheight= 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelDerecho, constrains);

        opcionesFlota();

    }

    /**
     * This method adds 100 buttons to tableroPosicion for the first time, when the array is created
     */
    private void pintarTableroPosicion() {
        if (interfaz == 0) {
            GridBagConstraints constrainsPosicion = new GridBagConstraints();

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    tableroPosicionU[i][j] = new JButton();
                    tableroPosicionU[i][j].setBackground(new Color(91, 164, 252));
                    tableroPosicionU[i][j].setPreferredSize(new Dimension(46, 46));
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
     * Este método agrega los componentes necesarios para elegir la flota
     */
    private void opcionesFlota(){

        elegirFragata= new JButton();
        elegirFragata.addActionListener(escucha);
        elegirFragata.setPreferredSize(new Dimension(110, 80));
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/fragata/fragata.png")));
        elegirFragata.setIcon(new ImageIcon(img.getImage().getScaledInstance(110, 80, Image.SCALE_SMOOTH)));
        elegirFragata.setBorderPainted(false);
        elegirFragata.setFocusPainted(false);
        elegirFragata.setContentAreaFilled(false);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(elegirFragata, constrains);

        labelAux= new JLabel("Fragata");
        labelAux.setFont(new Font(Font.SERIF,Font.BOLD,15));
        labelAux.setForeground(Color.WHITE);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(labelAux, constrains);

        elegirDestructor= new JButton();
        elegirDestructor.addActionListener(escucha);
        elegirDestructor.setPreferredSize(new Dimension(110, 80));
        img =
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/destructor" +
                        "/destructor.png")));
        elegirDestructor.setIcon(new ImageIcon(img.getImage().getScaledInstance(110, 80, Image.SCALE_SMOOTH)));
        elegirDestructor.setBorderPainted(false);
        elegirDestructor.setFocusPainted(false);
        elegirDestructor.setContentAreaFilled(false);
        constrains.gridx = 1;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(elegirDestructor, constrains);

        labelAux= new JLabel("Destructor");
        labelAux.setFont(new Font(Font.SERIF,Font.BOLD,15));
        labelAux.setForeground(Color.WHITE);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(labelAux, constrains);

        elegirPortaavion= new JButton();
        elegirPortaavion.addActionListener(escucha);
        elegirPortaavion.setPreferredSize(new Dimension(110, 80));
        img =
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/portaaviones" +
                        "/portaaviones.png")));
        elegirPortaavion.setIcon(new ImageIcon(img.getImage().getScaledInstance(110, 80, Image.SCALE_SMOOTH)));
        elegirPortaavion.setBorderPainted(false);
        elegirPortaavion.setFocusPainted(false);
        elegirPortaavion.setContentAreaFilled(false);
        constrains.gridx = 2;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(elegirPortaavion, constrains);

        labelAux= new JLabel("Portaaviones");
        labelAux.setFont(new Font(Font.SERIF,Font.BOLD,15));
        labelAux.setForeground(Color.WHITE);
        constrains.gridx = 2;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(labelAux, constrains);

        elegirSubmarino= new JButton();
        elegirSubmarino.addActionListener(escucha);
        elegirSubmarino.setPreferredSize(new Dimension(110, 80));
        img =
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/submarino/submarino" +
                        ".png")));
        elegirSubmarino.setIcon(new ImageIcon(img.getImage().getScaledInstance(110, 80, Image.SCALE_SMOOTH)));
        elegirSubmarino.setBorderPainted(false);
        elegirSubmarino.setFocusPainted(false);
        elegirSubmarino.setContentAreaFilled(false);
        constrains.gridx = 3;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(elegirSubmarino, constrains);

        labelAux= new JLabel("Submarino");
        labelAux.setFont(new Font(Font.SERIF,Font.BOLD,15));
        labelAux.setForeground(Color.WHITE);
        constrains.gridx = 3;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(labelAux, constrains);

    }

    /**
     * Este método agrega los componentes para determinar la alineación de cada elemento de la flota
     */
    private void alineacionFlota(){

        panelDerecho2 = new JPanel(new GridBagLayout());
        panelDerecho2.setPreferredSize(new Dimension(470, 120));
        panelDerecho2.setOpaque(true);
        panelDerecho2.setBackground(new Color(0,0,0, 150));
        constrains.gridx = 1;
        constrains.gridy = 2;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.FIRST_LINE_START;
        add(panelDerecho2, constrains);

        img= new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/orientacion.png")));
        img= new ImageIcon(img.getImage().getScaledInstance(280,60,Image.SCALE_SMOOTH));
        labelAux= new JLabel(img);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.PAGE_START;
        panelDerecho2.add(labelAux, constrains);

        horizontal = new JButton();
        horizontal.addActionListener(escucha);
        horizontal.setPreferredSize(new Dimension(200, 30));
        img =
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones/horizontal" +
                        ".png")));
        horizontal.setIcon(new ImageIcon(img.getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH)));
        horizontal.setBorderPainted(false);
        horizontal.setContentAreaFilled(false);

        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.LINE_START;
        panelDerecho2.add(horizontal, constrains);

        vertical = new JButton();
        vertical.addActionListener(escucha);
        vertical.setPreferredSize(new Dimension(200, 30));
        img =
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones/vertical" +
                        ".png")));
        vertical.setIcon(new ImageIcon(img.getImage().getScaledInstance(200, 30, Image.SCALE_SMOOTH)));
        vertical.setBorderPainted(false);
        vertical.setFocusPainted(false);
        vertical.setContentAreaFilled(false);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.LINE_END;
        panelDerecho2.add(vertical, constrains);
    }

    /**
     * Este método crea la flota de acuerdo a la selección del usuario
     */
    private void crearFlota(){

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
            //constrainsPosicionDerecha.weightx = 40;
            //constrainsPosicionDerecha.weighty = 40;
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
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class, in charge of monitoring the user's actions with the program and updating the interface and the game accordingly.
     */

    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==salir){
                System.exit(0);
            }

            if(e.getSource()==iniciar){
                System.out.println("funciona el boton");
                remove(panelInicio);
                pintarEntrada();
                revalidate();
                repaint();
            }
            if(e.getSource()==elegirFragata||e.getSource()==elegirDestructor||e.getSource()==elegirPortaavion||e.getSource()==elegirSubmarino){
                alineacionFlota();
                revalidate();
                repaint();
            }
            if(e.getSource()==horizontal){
                orientacion="horizontal";
            }
            if(e.getSource()==vertical){
                orientacion="vertical";
            }

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

                    }
                }
            }
        }
    }
}