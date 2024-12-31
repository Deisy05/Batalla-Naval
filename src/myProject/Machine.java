package myProject;

import java.util.Random;

/**
 * This class is designed in order to generate the information for player "machine"
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 *         Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 * @version v.1.0.3 date: 26/01/2023
 */
public class Machine {

    private String[] flota;
    private int estaFlota, disparoX, disparoY, espaciosMachine;
    private String[][] tableroInformacionMachine;

    /**
     * constructor of Machine class
     */
    public Machine() {

        flota = new String[]{"portaaviones","submarino","submarino","destructor","destructor","destructor","fragata",
                "fragata","fragata","fragata"};
        estaFlota = 0;

        Random random = new Random();
        disparoX= random.nextInt(0,10);
        disparoY= random.nextInt(0,10);

        tableroInformacionMachine = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroInformacionMachine[i][j] = "";
            }
        }
    }

    /**
     * Method that chooses a ship randomly from the fleet "flota"
     * @return barco the name of the fleet
     */
    public String getBarco(){
        String barco="";
        if(estaFlota < 10) {
            barco = flota[estaFlota];
            estaFlota++;
        }
        return barco;
    }

    /**
     * Method that randomizes the location of a ship
     * @return horizontal or vertical String
     */
    public String getOrientacion(){
        Random random = new Random();
        String[] orientacionMaquina = {"horizontal","vertical"};
        return (orientacionMaquina[random.nextInt(0,2)]);
    }

    /**
     * Method that randomly determines the location of the ship at the X coordinate
     * between 0 - 9
     * @return X coordinate
     */
    public int getCoordenadaX(){
        Random random = new Random();
        return random.nextInt(0,10);
    }


    /**
     * Method that randomly determines the location of the ship at the Y coordinate
     * between 0 - 9
     * @return Y coordinate
     */
    public int getCoordenadaY(){
        Random random = new Random();
        return random.nextInt(0,10);
    }

    /**
     * Method that obtains the X coordinate of ship's shot
     * @return coordinate of the shot on the x-axis
     */
    public int getDisparoX(){
        Random random = new Random();
        return random.nextInt(0,10);
    }

    /**
     * Method that obtains the Y coordinate of ship's shot
     * @return coordinate of the shot on the y-axis
     * */
    public int getDisparoY(){
        Random random = new Random();
        return random.nextInt(0,10);
    }
    /**
     * Method that generate randomly shots
     */
    public void generarDisparos(String[][] tableroInfPrincipalM) {
        tableroInformacionMachine=tableroInfPrincipalM;
        if(!objetivoEncontrado()){
            int disparoAnteriorX=disparoX,disparoAnteriorY=disparoY;

            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                int x= random.nextInt(0,10);
                int y= random.nextInt(0,10);
                if (tableroInformacionMachine[x][y].equals("")){
                    disparoX=x;
                    disparoY=y;
                    break;
                }
            }
            if(disparoAnteriorX==disparoX&&disparoAnteriorY==disparoY) {
                for (int i = 0; i < 10 && disparoAnteriorX==disparoX&&disparoAnteriorY==disparoY; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tableroInformacionMachine[j][i].equals("")){
                            disparoX=j;
                            disparoY=i;
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Method that searches for a target close to the touched part of the ship
     * @return objetivo boolean - if a new target was selected
     */
    private boolean objetivoEncontrado() {
        boolean goal=false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tableroInformacionMachine[i][j].equals("tocado")&&!goal){
                    if(i>0&&tableroInformacionMachine[i-1][j].equals("")){
                        disparoX=i-1;
                        disparoY=j;
                        goal=true;
                    }
                    else if(i>1&&tableroInformacionMachine[i-2][j].equals("")&&tableroInformacionMachine[i-1][j].equals("tocado")){
                        disparoX=i-2;
                        disparoY=j;
                        goal=true;
                    }
                    else if(i<9&&tableroInformacionMachine[i+1][j].equals("")){
                        disparoX=i+1;
                        disparoY=j;
                        goal=true;
                    }
                    else if(i<8&&tableroInformacionMachine[i+2][j].equals("")&&tableroInformacionMachine[i+1][j].equals("tocado")){
                        disparoX=i+2;
                        disparoY=j;
                        goal=true;
                    }

                    if(j>0&&tableroInformacionMachine[i][j-1].equals("")){
                        disparoX=i;
                        disparoY=j-1;
                        goal=true;
                    }
                    else if(j>1&&tableroInformacionMachine[i][j-2].equals("")&&tableroInformacionMachine[i][j-1].equals("tocado")){
                        disparoX=i;
                        disparoY=j-2;
                        goal=true;
                    }
                    else if(j<9&&tableroInformacionMachine[i][j+1].equals("")){
                        disparoX=i;
                        disparoY=j+1;
                        goal=true;
                    }
                    else if(j<8&&tableroInformacionMachine[i][j+2].equals("")&&tableroInformacionMachine[i][j+1].equals("tocado")){
                        disparoX=i;
                        disparoY=j+2;
                        goal=true;
                    }
                }
            }
        }
        return goal;
    }

}