package myProject;

/**
 * This class is designed in order to applies the game rules
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 *         Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 * @version v.1.0.1 date: 16/03/2022
 */
public class ModelClass
{
    private String[][] tableroPosUsuario, tableroPosMaquina, tableroInfPrincipalU, tableroInfPrincipalM ;
    private Machine machine;
    private String error, barcoMaquina, orientacion, indicador;
    private int coordenadaX,coordenadaY,espaciosQueOcupa;

    public ModelClass()
    {
        //initializes the matrix to "" to paint the representing button as water
        tableroPosUsuario= new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroPosUsuario[i][j]="";
            }
        }

        tableroPosMaquina= new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroPosMaquina[i][j]="";
            }
        }

        machine = new Machine();

    }

    /**
     * Checks if the ship can be added to the positions
     * @param posicionHorizontal the horizontal position where you want to start painting the boat
     * @param posicionVertical the vertical position where you want to start painting the boat
     * @param alineacion "horizontal" or "vertical"
     * @param barco vessel name to add
     * @param espacioQueOcupa número de casillas por tipo de flota
     * @return whether it is possible or not
     */

    public boolean crearTerritorioDelUsuario(int posicionHorizontal,int posicionVertical,String alineacion, String barco,int espacioQueOcupa){
        return ubicarBarco(tableroPosUsuario,posicionHorizontal,posicionVertical,alineacion,barco, espacioQueOcupa);

    }

    private boolean ubicarBarco(String [][] tableroPlayer, int posicionHorizontal, int posicionVertical, String alineacion, String barco,
                                int espacioQueOcupa)
    {
        boolean answer = true;

        //checks if the initial position (marked by the user) is empty
        if (!tableroPlayer[posicionHorizontal][posicionVertical].equals("")){
            answer = false;
            error="esta posicion ya está en uso";
        }
        else if(alineacion.equals("horizontal"))
        {
            //check that the initial position marked by the user part of the fleet is not left out.
            if(posicionHorizontal+espacioQueOcupa>10){
                answer=false;
                error="el "+barco+" ocupa "+espacioQueOcupa+" espacios. Trata con al menos "+(posicionHorizontal+espacioQueOcupa-10)+" posiciones hacia la izquierda.";
            }else
            {
                for (int i = posicionHorizontal; i < posicionHorizontal + espacioQueOcupa; i++) {
                    //check that all positions where the boat would be are empty.
                    if(!tableroPlayer[i][posicionVertical].equals("")){
                        answer=false;
                        error="una de las posiciones que ocuparía tu "+barco+" ya está en uso.";
                    }
                }
            }
        }
        else if(alineacion.equals("vertical"))
        {
            //check that the initial position marked by the user part of the fleet is not left out.
            if(posicionVertical+espacioQueOcupa>10){
                answer=false;
                error="el "+barco+" ocupa "+espacioQueOcupa+" espacios. Trata con al menos "+(posicionVertical+espacioQueOcupa-10)+" posiciones hacia arriba.";
            }else {
                for (int i = posicionVertical; i < posicionVertical + espacioQueOcupa; i++) {
                    //check that all positions where the boat would be are empty.
                    if(!tableroPlayer[posicionHorizontal][i].equals("")){
                        answer=false;
                        error="una de las posiciones que ocuparía tu "+barco+" ya está en uso.";
                    }
                }
            }
        }
        if(answer)
        {
            setTableroPosicion(tableroPlayer,posicionHorizontal, posicionVertical, alineacion, barco, espacioQueOcupa);
        }
        return answer;
    }

    /**
     * start adding the boat in the indicated position and alignment
     * @param _tableroPlayer matrix of a player
     * @param posicionHorizontal the horizontal position where you want to start painting the boat
     * @param posicionVertical the vertical position where you want to start painting the boat
     * @param alineacion "horizontal" or "vertical"
     * @param barco vessel name to add
     * @param espacio space occupied by the ship
     */
    private void setTableroPosicion(String[][] _tableroPlayer, int posicionHorizontal, int posicionVertical,
                                    String alineacion, String barco, int espacio)
    {
        int contador=1;
        if (alineacion.equals("horizontal")){
            //System.out.println("linea 126 modelclass");
            for (int i = posicionHorizontal; i < posicionHorizontal + espacio; i++) {
                _tableroPlayer[i][posicionVertical]=barco+".H."+contador;
                contador++;
            }
        }
        else{
            //System.out.println("linea 133 model class");
            for (int i = posicionVertical; i < posicionVertical + espacio; i++) {
                _tableroPlayer[posicionHorizontal][i]=barco+".V."+contador;
                contador++;
            }
        }

    }


    /**
     * @return the matrix with the information of where the machine's fleet is positioned.
     */
    public String[][] getTableroPosMaquina()
    {
        return tableroPosMaquina;
    }

    /**
     * @return the reason why the boat could not be added
     */
    public String getError(){
        return error;
    }

    public void ingresarBarcosMaquina()
    {
        barcoMaquina = machine.getBarco();
        espaciosQueOcupa= machine.getEspacioQueOcupa(barcoMaquina);
        orientacion = machine.getOrientacion();
        coordenadaX = machine.getCoordenadaX();
        coordenadaY= machine.getCoordenadaY();

        while(!ubicarBarco(tableroPosMaquina,coordenadaX, coordenadaY, orientacion, barcoMaquina, espaciosQueOcupa)){
            orientacion = machine.getOrientacion();
            coordenadaX = machine.getCoordenadaX();
            coordenadaY= machine.getCoordenadaY();
        }

    }

    public String getBarcoMaquina(){
        return barcoMaquina;
    }

    public  int getCoordenadaXMaquina(){
        return coordenadaX;
    }

    public  int getCoordenadaYMaquina(){
        return coordenadaY;
    }

    public int getEspaciosQueOcupaMaquina(){
        return espaciosQueOcupa;
    }

    public String getOrientacionMaquina(){
        return orientacion;
    }


    /**
     public void setTableroInfPrincipalM(String[][] tableroInfPrincipalM) {
     this.tableroInfPrincipalM = tableroInfPrincipalM;
     }
     **/
    public String[][] getTableroInfPrincipalU() {
        return tableroInfPrincipalU;
    }

    private boolean hundimiento(String[][] tablero,int disparoX, int disparoY)
    {
        boolean hundido;
        String informacion = tablero[disparoX][disparoY];
        String tipoBarco = informacion.substring(0, informacion.indexOf("."));
        String tipoAlineacion = informacion.substring(informacion.indexOf(".") + 3, informacion.indexOf(".") + 4);
        int parteBarco = Integer.parseInt(informacion.substring(informacion.lastIndexOf(".") + 1));
        int espacio = getEspacio(tipoBarco);

        if (espacio == 1) {
            tablero[disparoX][disparoY] = "hundido";
            hundido = true;
        } else {
            int ultimaPos;
            hundido = true;
            String esteDato;

            if (tipoAlineacion.equals("H")) {
                if (parteBarco == espacio) {
                    ultimaPos = disparoX;
                } else {
                    ultimaPos = disparoX + espacio - parteBarco;
                }
                for (int i = 0; i < espacio; i++) {
                    esteDato = tablero[ultimaPos - i][disparoY];
                    //probando por consola
                    System.out.println("Info de la casilla: "+ esteDato);
                    if (esteDato.charAt(esteDato.indexOf(".") + 1) != 'T') {
                        hundido = false;
                    }
                }
            } else {
                if (parteBarco == espacio) {
                    ultimaPos = disparoY;
                } else {
                    ultimaPos = disparoY + espacio - parteBarco;
                }
                for (int i = 0; i < espacio; i++) {
                    esteDato = tablero[disparoX][ultimaPos - i];
                    if (esteDato.charAt(esteDato.indexOf(".") + 1) != 'T') {
                        hundido = false;
                    }
                }
            }
        }
        return hundido;
    }

    private int getEspacio(String tipoBarco) {
        int espacio = 0;
        switch (tipoBarco) {
            case "Portaaviones" -> espacio = 4;
            case "Submarino" -> espacio = 3;
            case "Destructor" -> espacio = 2;
            case "Fragata" -> espacio = 1;
        }
        return espacio;
    }

    public void setTableroInfPrincipalU(int queTableroPlayer,int disparoX, int disparoY)
    {
        String [][] tablero;
        if(queTableroPlayer==1){
            tablero = tableroPosMaquina;
        }else{
            tablero =tableroPosUsuario;
        }
        if (tablero[disparoX][disparoY].equals(""))
        {
            tablero[disparoX][disparoY] = "agua";
            indicador= "agua";
        }
        else
        {
            String miPrimerClicked = tablero[disparoX][disparoY];
            if (miPrimerClicked.charAt(miPrimerClicked.indexOf(".") + 1) != 'T')
            {
                tablero[disparoX][disparoY] = miPrimerClicked.substring(0, miPrimerClicked.indexOf(".")) + ".T"
                        + miPrimerClicked.substring(miPrimerClicked.indexOf("."));
                miPrimerClicked = tablero[disparoX][disparoY];
                if (hundimiento(tablero,disparoX, disparoY))
                {
                    String tipoBarco = miPrimerClicked.substring(0, miPrimerClicked.indexOf("."));
                    int espacio = getEspacio(tipoBarco);
                    int ultimaPos;
                    int parteBarco = Integer.parseInt(miPrimerClicked.substring(miPrimerClicked.lastIndexOf(".") + 1));

                    if (miPrimerClicked.charAt(miPrimerClicked.indexOf(".") + 3) == 'H') {
                        if (parteBarco == espacio)
                        {
                            ultimaPos = disparoX;
                        } else
                        {
                            ultimaPos = disparoX + espacio - parteBarco;
                        }
                        for (int i = 0; i < espacio; i++)
                        {
                            tablero[ultimaPos - i][disparoY] = "hundido";
                            indicador= "hundido";
                        }
                    } else
                    {
                        if (parteBarco == espacio)
                        {
                            ultimaPos = disparoY;
                        } else {
                            ultimaPos = disparoY + espacio - parteBarco;
                        }
                        for (int i = 0; i < espacio; i++) {
                            tablero[disparoX][ultimaPos - i] = "hundido";
                            indicador= "hundido";
                        }
                    }
                } else
                {
                    indicador= "tocado";
                }
            }
        }
    }

    public void setTableroInfPosicionU()
    {
        coordenadaX= machine.getCoordenadaX();
        coordenadaY=machine.getCoordenadaY();
        String contenidoCasilla =tableroPosUsuario[coordenadaX][coordenadaY];
        boolean estaTocado;
        try{
            if(contenidoCasilla.charAt(contenidoCasilla.indexOf(".") + 1) == 'T'){
                estaTocado=true;
            }else{
                estaTocado=false;
            }
        }catch (Exception e){
            estaTocado=false;
        }


        while(contenidoCasilla.equals("agua")||contenidoCasilla.equals("hundido")||estaTocado)
        {
            coordenadaX=machine.getCoordenadaX();
            coordenadaY=machine.getCoordenadaY();
        }
        setTableroInfPrincipalU(2,coordenadaX, coordenadaY);
    }

    public String getIndicador() {
        return indicador;
    }
}