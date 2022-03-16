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
    private String[][] posicionDisparos;
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
}
