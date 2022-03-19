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
    private String error, barcoMaquina, orientacion;
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


        tableroInfPrincipalU= new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroInfPrincipalU[i][j]="";
            }
        }

        tableroInfPrincipalM= new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroInfPrincipalM[i][j]="";
            }
        }

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
                                int espacioQueOcupa){
        boolean answer = true;

        //checks if the initial position (marked by the user) is empty
        if (!tableroPlayer[posicionHorizontal][posicionVertical].equals("")){
            answer = false;
            error="esta posicion ya está en uso";
        }
        else if(alineacion.equals("horizontal")){
            //check that the initial position marked by the user part of the fleet is not left out.
            if(posicionHorizontal+espacioQueOcupa>10){
                answer=false;
                error="el "+barco+" ocupa "+espacioQueOcupa+" espacios. Trata con al menos "+(posicionHorizontal+espacioQueOcupa-10)+" posiciones hacia la izquierda.";
            }else {
                for (int i = posicionHorizontal; i < posicionHorizontal + espacioQueOcupa; i++) {
                    //check that all positions where the boat would be are empty.
                    if(!tableroPlayer[i][posicionVertical].equals("")){
                        answer=false;
                        error="una de las posiciones que ocuparía tu "+barco+" ya está en uso.";
                    }
                }
            }
        }
        else if(alineacion.equals("vertical")){
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
                               String alineacion, String barco, int espacio) {
        int contador=1;
        if (alineacion.equals("horizontal")){
            for (int i = posicionHorizontal; i < posicionHorizontal + espacio; i++) {
                _tableroPlayer[i][posicionVertical]=barco+".H."+contador;
                contador++;
            }
        }
        else{
            for (int i = posicionVertical; i < posicionVertical + espacio; i++) {
                _tableroPlayer[posicionHorizontal][i]=barco+".V."+contador;
                contador++;
            }
        }
    }

    /**
     *
     * @param disparoX
     * @param disparoY
     */
    public void setTableroInfPrincipalU(int disparoX, int disparoY){
        if(tableroPosMaquina[disparoX][disparoY].equals("")){
            tableroInfPrincipalU[disparoX][disparoY]="agua";
            setTableroPosicion(tableroPosMaquina,"agua",disparoX,disparoY);
        }
        else {
            //falta implementar
        }

    }

    /**
     *
     * @param matrix
     * @param opcionAPintar
     * @param disparoX
     * @param disparoY
     */
    private void setTableroPosicion(String[][] matrix,String opcionAPintar,int disparoX,int disparoY){
        /**
         *  String informacionBarco =matrix[posicionH][posicionV];
         *         String barcoTocado=informacionBarco.substring(0,informacionBarco.indexOf("."))+".T"+informacionBarco.substring(informacionBarco.indexOf("."));informacionBarco=barcoTocado;
         *
         */
        if(opcionAPintar.equals("agua")){
            matrix[disparoX][disparoY]="agua";
        }
    }

    /**
     * @return the reason why the boat could not be added
     */
    public String getError(){
        return error;
    }

    public void ingresarBarcosMaquina()
    {
        barcoMaquina= machine.getBarco();
        espaciosQueOcupa= machine.getEspacioQueOcupa(barcoMaquina);
        orientacion = machine.getOrientacion();
        coordenadaX = machine.getCoordenadaX();
        coordenadaY= machine.getCoordenadaY();

        while(!ubicarBarco(tableroPosMaquina,coordenadaX, coordenadaY, orientacion,
        barcoMaquina, espaciosQueOcupa)){
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

}
