package myProject;

/**
 * This class is designed in order to applies the game rules
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 *         Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 * @version v.1.0.1 date: 16/03/2022
 */
public class ModelClass
{
    private String[][] tableroPosUsuario;
    private String[][] tableroPosMaquina;
    private String[][] tableroInfPrincipalU;
    private String[][] tableroInfPrincipalM;
    private Machine machine;
    private String error;

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
        boolean answer = false;

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
                    //after having carried out all the revisions, it is verified that the boat can be added.
                    else if(i== posicionHorizontal + espacioQueOcupa-1){
                        answer=true;
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
                    //after having carried out all the revisions, it is verified that the boat can be added.
                    else if(i== posicionVertical + espacioQueOcupa-1){
                        answer=true;
                    }
                }
            }
        }
        if(answer)
        {
            setTableroPosUsuario(tableroPlayer,posicionHorizontal, posicionVertical, alineacion, barco, espacioQueOcupa);
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
    private void setTableroPosUsuario(String[][] _tableroPlayer, int posicionHorizontal, int posicionVertical, String alineacion, String barco, int espacio) {
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
     * @return the matrix with the information of where the user's fleet is positioned.
     */
    public String[][] getTableroPosUsuario() {
        return tableroPosUsuario;
    }

    /**
     * @return the reason why the boat could not be added
     */
    public String getError(){
        return error;
    }

    public void ingresarBarcosMaquina()
    {
        for (int i = 0; i <10 ; i++) {
            String barcoMaquina = machine.getBarco();
            while(!ubicarBarco(tableroPosMaquina,machine.getCoordenadaX(), machine.getCoordenadaY(), machine.getOrientacion(), barcoMaquina,machine.getEspacioQueOcupa())){
            }
        }
    }

    public String[][] getTableroPosMaquina() {
        return tableroPosMaquina;
    }

    /**
     public void setTableroInfPrincipalM(String[][] tableroInfPrincipalM) {
     this.tableroInfPrincipalM = tableroInfPrincipalM;
     }
     **/
    public String[][] getTableroInfPrincipalU() {
        return tableroInfPrincipalU;
    }

    private boolean hundimiento(int disparoX, int disparoY) {
        boolean hundido = false;
        String informacion = tableroPosMaquina[disparoX][disparoY];
        String tipoBarco = informacion.substring(0, informacion.indexOf("."));
        String tipoAlineacion = informacion.substring(informacion.indexOf(".") + 3, informacion.indexOf(".") + 4);
        int parteBarco = Integer.valueOf(informacion.substring(informacion.lastIndexOf(".") + 1));
        int espacio = getEspacio(tipoBarco);

        if (espacio == 1) {
            tableroPosMaquina[disparoX][disparoY] = "hundido";
            tableroInfPrincipalU[disparoX][disparoY] = "hundido";
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
                for (int i = 1; i <= espacio; i++) {
                    esteDato = tableroPosMaquina[ultimaPos - espacio + i][disparoY];
                    if (!esteDato.substring(esteDato.indexOf(".") + 1, esteDato.indexOf(".") + 2).equals("T")) {
                        hundido = false;
                    }
                }
            } else {
                if (parteBarco == espacio) {
                    ultimaPos = disparoY;
                } else {
                    ultimaPos = disparoY + espacio - parteBarco;
                }
                for (int i = 1; i <= espacio; i++) {
                    esteDato = tableroPosMaquina[disparoX][ultimaPos - espacio + i];
                    if (!esteDato.substring(esteDato.indexOf(".") + 1, esteDato.indexOf(".") + 2).equals("T")) {
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
            case "portaaviones" -> espacio = 4;
            case "submarino" -> espacio = 3;
            case "destructor" -> espacio = 2;
            case "fragata" -> espacio = 1;
        }
        return espacio;
    }

    public void setTableroInfPrincipalU(int disparoX, int disparoY)
    {
        if (tableroPosMaquina[disparoX][disparoY].equals("")) {
            tableroInfPrincipalU[disparoX][disparoY] = "agua";
            setTableroPosicion(tableroPosMaquina, "agua", disparoX, disparoY);
        } else {
            String miPrimerClicked = tableroPosMaquina[disparoX][disparoY];
            if (!miPrimerClicked.substring(miPrimerClicked.indexOf(".") + 1, miPrimerClicked.indexOf(".") + 2).equals("T")) {
                tableroPosMaquina[disparoX][disparoY] = miPrimerClicked.substring(0, miPrimerClicked.indexOf(".")) + ".T"
                        + miPrimerClicked.substring(miPrimerClicked.indexOf("."));
                miPrimerClicked = tableroPosMaquina[disparoX][disparoY];
                if (hundimiento(disparoX, disparoY)) {
                    String tipoBarco = miPrimerClicked.substring(0, miPrimerClicked.indexOf("."));
                    int espacio = getEspacio(tipoBarco);
                    int ultimaPos;
                    int parteBarco = Integer.valueOf(miPrimerClicked.substring(miPrimerClicked.lastIndexOf(".") + 1));

                    if (miPrimerClicked.substring(miPrimerClicked.indexOf(".") + 3, miPrimerClicked.indexOf(".") + 4).equals("H")) {
                        if (parteBarco == espacio) {
                            ultimaPos = disparoX;
                        } else {
                            ultimaPos = disparoX + espacio - parteBarco;
                        }
                        for (int i = 1; i <= espacio; i++) {
                            tableroPosMaquina[ultimaPos - espacio + i][disparoY] = "hundido";
                            tableroInfPrincipalU[ultimaPos - espacio + i][disparoY] = "hundido";
                        }
                    } else {
                        if (parteBarco == espacio) {
                            ultimaPos = disparoY;
                        } else {
                            ultimaPos = disparoY + espacio - parteBarco;
                        }
                        for (int i = 1; i <= espacio; i++) {
                            tableroPosMaquina[disparoX][ultimaPos - espacio + i] = "hundido";
                            tableroInfPrincipalU[disparoX][ultimaPos - espacio + i] = "hundido";
                        }
                    }
                } else {
                    tableroInfPrincipalU[disparoX][disparoY] = "tocado";
                }
            }

        }

    }

}


