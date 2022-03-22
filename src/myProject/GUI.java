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
 * @version v.1.0.3 date: 19/03/2022
 */

public class GUI extends JFrame
{

    private JPanel panelInicio, panelIzquierdo, panelDerecho, panelDerecho2,
            tableroEnemigo;
    private ModelClass modelClass;
    private Escucha escucha;
    private ImageIcon img;
    private JLabel logo,labelAux,labelInicioBatalla, labelCreaFlota, labelPortaavion, labelSubmarino,labelDestructor,
            labelFragata,
            labelAgua,labelTocado,labelHundido;
    private JButton horizontal, vertical, iniciar, instrucciones, salir, ayuda, verTerritorioEnemigo, elegirPortaavion,
            elegirSubmarino,elegirDestructor, elegirFragata;
    private  JButton []vehiculo;
    private int interfaz, casillasFlota,vacio;
    private JButton[][] tableroPosicionU, tableroPrincipalU, tableroPosicionEnemigo;
    private boolean turnoEnemigo;
    private JTextArea cantidadFlotas;
    private int[] cantidadFlota;
    private String[] nombreFlota;
    private String orientacion, tipoFlota;
    private GridBagConstraints constrains;

    /**
     * Constructor of GUI class
     */
    public GUI()
    {
        interfaz = 0;
        tableroPosicionU = new JButton[10][10];
        tableroPrincipalU = new JButton[10][10];
        tableroPosicionEnemigo = new JButton[10][10];
        casillasFlota = 0;
        nombreFlota = new String[]{"Portaaviones", "Submarino", "Destructor", "Fragata"};
        cantidadFlota = new int[]{1, 1, 1, 1};
        turnoEnemigo=false;

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
    private void initGUI()
    {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        constrains = new GridBagConstraints();
        modelClass = new ModelClass();
        escucha = new Escucha();
        vacio=0;

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
    private void pintarEntrada()
    {

        ayuda = new JButton();
        ayuda.addActionListener(escucha);
        ayuda.setPreferredSize(new Dimension(100, 80));
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones/help.png")));
        ayuda.setIcon(new ImageIcon(img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        ayuda.setBorderPainted(false);
        ayuda.setFocusPainted(false);
        ayuda.setContentAreaFilled(false);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda, constrains);

        img= new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/creaFlota.png")));
        img= new ImageIcon(img.getImage().getScaledInstance(500,60,Image.SCALE_SMOOTH));
        labelCreaFlota= new JLabel(img);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(labelCreaFlota, constrains);

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
        pintarTableroPosicion();
        pintarTableroEnemigo();

        panelDerecho = new JPanel(new GridBagLayout());
        panelDerecho.setPreferredSize(new Dimension(480, 220));
        //panelDerecho.setBackground(new Color(0,0,0,150));
        panelDerecho.setOpaque(false);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.gridheight= 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelDerecho, constrains);

        revalidate();
        repaint();
        opcionesFlota();

    }

    /**
     * This method adds 100 buttons to tableroPosicion for the first time, when the array is created
     */
    private void pintarTableroPosicion()
    {
        if (interfaz == 0)
        {
            GridBagConstraints constrainsPosicion = new GridBagConstraints();

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    tableroPosicionU[i][j] = new JButton();
                    tableroPosicionU[i][j].setBackground(new Color(5, 182, 198));
                    tableroPosicionU[i][j].setPreferredSize(new Dimension(46, 46));
                    constrainsPosicion.gridx = i;
                    constrainsPosicion.gridy = j;
                    constrainsPosicion.gridwidth = 1;
                    constrainsPosicion.fill = GridBagConstraints.NONE;
                    constrainsPosicion.anchor = GridBagConstraints.CENTER;
                    panelIzquierdo.add(tableroPosicionU[i][j], constrainsPosicion);
                }
            }

        }
    }

    /**
     * Este metodo crea el territorio vacio del enemigo
     */
    private void pintarTableroEnemigo()
    {
        tableroEnemigo= new JPanel(new GridBagLayout());
        tableroEnemigo.setPreferredSize(new Dimension(480, 500));
        tableroEnemigo.setOpaque(false);

        if (interfaz == 0) {
            GridBagConstraints constrainsPosicion = new GridBagConstraints();

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    tableroPosicionEnemigo[i][j] = new JButton();
                    tableroPosicionEnemigo[i][j].setBackground(new Color(5, 182, 198));
                    tableroPosicionEnemigo[i][j].setPreferredSize(new Dimension(46, 46));
                    constrainsPosicion.gridx = i;
                    constrainsPosicion.gridy = j;
                    constrainsPosicion.gridwidth = 1;
                    constrainsPosicion.fill = GridBagConstraints.NONE;
                    constrainsPosicion.anchor = GridBagConstraints.CENTER;
                    tableroEnemigo.add(tableroPosicionEnemigo[i][j], constrainsPosicion);
                }
            }

        }
    }

    /**
     * Este método agrega los componentes necesarios para crear la flota
     */
    private void opcionesFlota()
    {

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

        labelFragata= new JLabel("Fragata");
        labelFragata.setFont(new Font(Font.SERIF,Font.BOLD,15));
        labelFragata.setForeground(Color.WHITE);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(labelFragata, constrains);

        elegirDestructor = new JButton();
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

        labelDestructor= new JLabel("Destructor");
        labelDestructor.setFont(new Font(Font.SERIF,Font.BOLD,15));
        labelDestructor.setForeground(Color.WHITE);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(labelDestructor, constrains);

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

        labelPortaavion= new JLabel("Portaavión");
        labelPortaavion.setFont(new Font(Font.SERIF,Font.BOLD,15));
        labelPortaavion.setForeground(Color.WHITE);
        constrains.gridx = 2;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(labelPortaavion, constrains);

        elegirSubmarino= new JButton();
        elegirSubmarino.addActionListener(escucha);
        elegirSubmarino.setPreferredSize(new Dimension(110, 80));
        img =
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/submarino" +
                        "/submarino.png")));
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

        labelSubmarino= new JLabel("Submarino");
        labelSubmarino.setFont(new Font(Font.SERIF,Font.BOLD,15));
        labelSubmarino.setForeground(Color.WHITE);
        constrains.gridx = 3;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(labelSubmarino, constrains);

        vehiculo = new JButton[]{elegirPortaavion,elegirSubmarino,elegirDestructor,elegirFragata};

        revalidate();
        repaint();

    }

    /**
     * Este método agrega los componentes para determinar la alineación de cada elemento de la flota
     */
    private void alineacionFlota()
    {

        elegirFragata.removeActionListener(escucha);
        elegirPortaavion.removeActionListener(escucha);
        elegirDestructor.removeActionListener(escucha);
        elegirSubmarino.removeActionListener(escucha);

        //panel para orientación horizontal o vertical
        panelDerecho2 = new JPanel(new GridBagLayout());
        panelDerecho2.setPreferredSize(new Dimension(470, 150));
        panelDerecho2.setOpaque(true);
        panelDerecho2.setBackground(new Color(66, 66, 66, 150));
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
        horizontal.setPreferredSize(new Dimension(150, 30));
        img =
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones/horizontal" +
                        ".png")));
        horizontal.setIcon(new ImageIcon(img.getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH)));
        horizontal.setBorderPainted(false);
        horizontal.setContentAreaFilled(false);
        constrains.weightx= 1;
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.PAGE_START;
        panelDerecho2.add(horizontal, constrains);

        vertical = new JButton();
        vertical.addActionListener(escucha);
        vertical.setPreferredSize(new Dimension(150, 30));
        img =
                new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones/vertical" +
                        ".png")));
        vertical.setIcon(new ImageIcon(img.getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH)));
        vertical.setBorderPainted(false);
        vertical.setFocusPainted(false);
        vertical.setContentAreaFilled(false);
        constrains.weightx= 1;
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.PAGE_START;
        panelDerecho2.add(vertical, constrains);
        revalidate();
        repaint();
    }

    /**
     * Este método crea el componente para dar una instruccion al usuario
     */
    private void textoSeleccionarCasilla()
    {

        img= new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/seleccionarCasilla" +
                ".png")));
        img= new ImageIcon(img.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH));
        labelAux=new JLabel(img);
        panelDerecho2.add(labelAux);
        addEscucha(tableroPosicionU);
        interfaz= 1; //pasamos a interfaz para selección de flota
        revalidate();
        repaint();

    }

    /**
     * This method has the purpose of modifying the images displayed by the buttons
     * @param posicionPlayer matrix of player
     * @param posicionEnX es la posicion inicial X en la matriz
     * @param posicionEnY es la posicion inicial Y en la matriz
     */
    private void pintarFlotaTableroPosicion(JButton[][] posicionPlayer,int posicionEnX, int posicionEnY)
    {

        String direccion = "";

        for(int p = 1; p <= casillasFlota;  p++){
            switch (tipoFlota) {
                case "Portaaviones" -> direccion = "/myProject/resources/portaaviones/portaaviones";
                case "Submarino" -> direccion = "/myProject/resources/submarino/submarino";
                case "Destructor" -> direccion = "/myProject/resources/destructor/destructor";
                case "Fragata" -> direccion = "/myProject/resources/fragata/fragata";
            }
            switch (orientacion){
                case "horizontal" -> {
                    direccion+= "H "+p+".png";
                    img = new ImageIcon(Objects.requireNonNull(getClass().getResource(direccion)));
                    posicionPlayer[posicionEnX][posicionEnY].setIcon(new ImageIcon(img.getImage().getScaledInstance(46,
                            46,
                            Image.SCALE_SMOOTH)));

                    posicionEnX++;
                }
                case "vertical" -> {
                    direccion += "V " + p + ".png";
                    img = new ImageIcon(Objects.requireNonNull(getClass().getResource(direccion)));
                    posicionPlayer[posicionEnX][posicionEnY].setIcon(new ImageIcon(img.getImage().getScaledInstance(46,
                            46,
                            Image.SCALE_SMOOTH)));
                    posicionEnY++;
                }
            }
            revalidate();
            repaint();
        }

    }
//    private void pintarFlotaTableroPosicion(String[][] matrix) {
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    if (!matrix[i][j].equals("")) {
//                        tableroPosicionU[i][j].setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("src/myProject/resources/misBarcos/bomb.png"/* + "bomb" + ".png"*/))));
//                    }
//                }
//            }
//            repaint();
//            revalidate();
//        }

    /**
     * Este método confirma si aún está disponible determinado tipo de vehículo
     * Cuando no queden vehículos por ubicar, crea la opcion de iniciar partida
     */
    private void eliminarOpcionFlota()
    {

        switch (tipoFlota){
            case "Portaaviones" -> {panelDerecho.remove(elegirPortaavion);
                panelDerecho.remove(labelPortaavion);
                vacio++;
            }
            case "Submarino" -> {cantidadFlota[1]--;
                if(cantidadFlota[1]==0){
                    panelDerecho.remove(elegirSubmarino);
                    panelDerecho.remove(labelSubmarino);
                    vacio++;}
            }
            case "Destructor" -> {cantidadFlota[2]--;
                if(cantidadFlota[2]==0){
                    panelDerecho.remove(elegirDestructor);
                    panelDerecho.remove(labelDestructor);
                    vacio++;}
            }
            case "Fragata" -> {cantidadFlota[3]--;
                if(cantidadFlota[3]==0){
                    panelDerecho.remove(elegirFragata);
                    panelDerecho.remove(labelFragata);
                    vacio++;}
            }
        }

        if(vacio==4){
            this.remove(labelCreaFlota);
            img=
                    new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/iniciaBatalla.png")));
            img= new ImageIcon(img.getImage().getScaledInstance(600,70,Image.SCALE_SMOOTH));
            labelInicioBatalla= new JLabel(img);
            constrains.gridx = 0;
            constrains.gridy = 0;
            constrains.gridwidth = 2;
            constrains.fill = GridBagConstraints.NONE;
            constrains.anchor = GridBagConstraints.CENTER;
            add(labelInicioBatalla, constrains);

            iniciar= new JButton();
            iniciar.addActionListener(escucha);
            iniciar.setPreferredSize(new Dimension(300, 80));
            img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones/botonIniciar" +
                    ".png")));
            iniciar.setIcon(new ImageIcon(img.getImage().getScaledInstance(300, 60, Image.SCALE_SMOOTH)));
            iniciar.setBorderPainted(false);
            iniciar.setContentAreaFilled(false);
            constrains.gridx = 0;
            constrains.gridy = 0;
            constrains.gridwidth = 1;
            constrains.fill = GridBagConstraints.NONE;
            constrains.anchor = GridBagConstraints.CENTER;
            panelDerecho.add(iniciar, constrains);

        }

        revalidate();
        repaint();
    }

    /**
     * This method adds 100 buttons to tableroPrincipal for the first time, when the tableroPosicion is created
     * Enemy zone
     */
    private void pintarTableroPrincipal()
    {
        panelDerecho.setPreferredSize(new Dimension(480,500));
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.gridheight= 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        this.add(panelDerecho,constrains);

        GridBagConstraints constrainsPosicionDerecha = new GridBagConstraints();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroPrincipalU[i][j] = new JButton();
                tableroPrincipalU[i][j].setBackground(new Color(83, 191, 227));
                tableroPrincipalU[i][j].setPreferredSize(new Dimension(46, 46));
                constrainsPosicionDerecha.gridx = i;
                constrainsPosicionDerecha.gridy = j;
                constrainsPosicionDerecha.gridwidth = 1;
                constrainsPosicionDerecha.fill = GridBagConstraints.NONE;
                constrainsPosicionDerecha.anchor = GridBagConstraints.CENTER;
                panelDerecho.add(tableroPrincipalU[i][j], constrainsPosicionDerecha);
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Este método crea el componente JButton para acceder a la vista del territorio enemigo
     */

    private void opcionTerritorioEnemigo()
    {
        remove(labelInicioBatalla); //remove text indications
        revalidate();
        repaint();

        verTerritorioEnemigo = new JButton();
        verTerritorioEnemigo.addActionListener(escucha);
        verTerritorioEnemigo.setPreferredSize(new Dimension(100, 80));
        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/botones/enemy1.png")));
        verTerritorioEnemigo.setIcon(new ImageIcon(img.getImage().getScaledInstance(60, 70, Image.SCALE_SMOOTH)));
        verTerritorioEnemigo.setBorderPainted(false);
        verTerritorioEnemigo.setFocusPainted(false);
        verTerritorioEnemigo.setContentAreaFilled(false);
        constrains.gridx = 1;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(verTerritorioEnemigo, constrains);

    }

    /**
     * Este metodo cambia las casillas del tablero principal por un indicador segun el disparo
     */
    private void pintarDisparo(JButton[][] tablero, int posicionX, int posicionY)
    {

        switch (modelClass.getIndicador()){
            case "agua" -> {
                img = new ImageIcon(getClass().getResource("/myProject/resources/indicadores/agua.png"));
                tablero[posicionX][posicionY].setIcon(new ImageIcon(img.getImage().getScaledInstance(46,
                        46,
                        Image.SCALE_SMOOTH)));
                if(!turnoEnemigo){
                    disparoMaquina();
                }else {
                    turnoEnemigo = false;
                }
            }
            case "tocado"->{
                img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/indicadores/bomb.png")));
                tablero[posicionX][posicionY].setIcon(new ImageIcon(img.getImage().getScaledInstance(46,
                        46,
                        Image.SCALE_SMOOTH)));

            }
            case "hundido"->{
                String [][] matrixDisparos= modelClass.getTableroPosMaquina();
                for (int i = 0; i < 10 ; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(matrixDisparos[i][j]== "hundido"){
                            img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/myProject/resources/indicadores" +
                                    "/fire2.png")));
                            tablero[i][j].setIcon(new ImageIcon(img.getImage().getScaledInstance(46,
                                    46,
                                    Image.SCALE_SMOOTH)));
                        }

                    }
                }


                //validarFinPartida(){}

            }
        }
    }

    public void disparoMaquina() {
        turnoEnemigo=true;
        modelClass.setTableroInfPosicionU();
        pintarDisparo(tableroPosicionU, modelClass.getCoordenadaXMaquina(), modelClass.getCoordenadaYMaquina());
    }


    /**
     * adds the listener to the 100 buttons
     *
     * @param matrix where the buttons are
     */
    private void addEscucha(JButton[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j].setBackground(new Color(30, 124, 120));
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
                matrix[i][j].setBackground(new Color(5, 182, 198));
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
        int opcionIniciar=0;

        @Override
        public void actionPerformed(ActionEvent e)
        {

            if (e.getSource()==salir)
            {
                System.exit(0);
            }
            if(e.getSource()==iniciar&& opcionIniciar==0)
            {
                remove(panelInicio);
                pintarEntrada();
                opcionIniciar=1;
                revalidate();
                repaint();
            }else if(e.getSource()==iniciar)
            {
                panelDerecho.remove(iniciar);
                pintarTableroPrincipal();
                opcionTerritorioEnemigo(); //sale después de ordenar la flota
                addEscucha(tableroPrincipalU);
                interfaz=2;
                revalidate();
                repaint();
            }
            switch (interfaz)
            {
                case 0 ->{
                    //revisar cuál de los vehículos fue seleccionado y aplicarle alineación a esa flota seleccionada
                    for (int m = 0; m < 4; m++)
                    {
                        if (vehiculo[m] == e.getSource())
                        {
                            alineacionFlota(); //elige la orientación
                            tipoFlota = nombreFlota[m];
                            casillasFlota = 5 - (m + 1);
                            break;
                        }
                    }
                    if (e.getSource() == horizontal) {
                        orientacion = "horizontal";
                        panelDerecho2.removeAll();
                        revalidate();
                        repaint();
                        textoSeleccionarCasilla();
                    }
                    if (e.getSource() == vertical) {
                        orientacion = "vertical";
                        panelDerecho2.removeAll();
                        textoSeleccionarCasilla();
                        revalidate();
                        repaint();

                    }
                }
                case 1 ->{
                    //check which of the 100 buttons was clicked
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (tableroPosicionU[i][j] == e.getSource()) {
                                //once found, it is checked to see if it can be added to the underlying positions
                                if (modelClass.crearTerritorioDelUsuario(i, j, orientacion, tipoFlota, casillasFlota)) {
                                    //pintarFlotaTableroPosicion(modelClass.getTableroPosUsuario());
                                    pintarFlotaTableroPosicion(tableroPosicionU,i, j);
                                    eliminarOpcionFlota();
                                    modelClass.ingresarBarcosMaquina();
                                    tipoFlota= modelClass.getBarcoMaquina();
                                    orientacion= modelClass.getOrientacionMaquina();
                                    casillasFlota= modelClass.getEspaciosQueOcupaMaquina();

                                    pintarFlotaTableroPosicion(tableroPosicionEnemigo, modelClass.getCoordenadaXMaquina(),modelClass.getCoordenadaYMaquina());
                                    panelDerecho2.removeAll();
                                    removeEscucha(tableroPosicionU);
                                    remove(panelDerecho2);

                                    interfaz=0;

                                    elegirPortaavion.addActionListener(escucha);
                                    elegirSubmarino.addActionListener(escucha);
                                    elegirDestructor.addActionListener(escucha);
                                    elegirFragata.addActionListener(escucha);

                                    revalidate();
                                    repaint();

                                } else{
                                    JOptionPane.showMessageDialog(panelIzquierdo,
                                            "No se pudo posicionar la flota porque " + modelClass.getError(), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            }
                        }
                    }
                }
                case 2->
                        {
                            for (int i = 0; i < 10 ; i++)
                            {
                                for (int j = 0; j < 10; j++)
                                {
                                    if(e.getSource() == tableroPrincipalU[i][j])
                                    {
                                        modelClass.setTableroInfPrincipalU(1,i,j);
                                        tableroPrincipalU[i][j].removeActionListener(escucha);
                                        pintarDisparo(tableroPrincipalU,i,j);
                                        pintarDisparo(tableroPosicionEnemigo,i,j);
                                    }

                                }
                            }

                        }

            }

            if(e.getSource()== verTerritorioEnemigo)
            {
                JOptionPane.showMessageDialog(null, tableroEnemigo, "TABLERO DE POSICIÓN DEL ENEMIGO",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
}