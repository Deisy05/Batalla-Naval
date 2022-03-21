package myProject;

import java.util.Random;

/**
 * This class is designed in order to apply the random game rules of machine player
 * @author Carlos Andrés Borja - borja.carlos@correounivalle.edu.co
 *         Deisy Catalina Melo - deisy.melo@correounivalle.edu.co
 * @version v.1.0.2 date: 16/03/2022
 */
public class Machine
{
    private String[] flota;
    private int estaFlota, disparoX, disparoY, espaciosMachine;
    private String[][] tableroInformacionMachine;

    public Machine()
    {
/*
        //We declare the fleet of the machine
        flota = new String[]
                {"Portaaviones","Submarino","Submarino","Destructor","Destructor","Destructor","Fragata","Fragata","Fragata","Fragata"};
        estaFlota = 0;
 */

    //We declare the fleet of the machine
        flota = new String[]{"portaaviones","submarino","submarino","destructor","destructor","destructor","fragata","fragata","fragata","fragata"};
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
    public String getBarco()
    {
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
    public String getOrientacion()
    {
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

    public int getDisparoX(){
        Random random = new Random();
        return random.nextInt(0,10);
    }
    public int getDisparoY(){
        Random random = new Random();
        return random.nextInt(0,10);
    }

    public void prepararSiguienteDisparo(String[][] tableroInfPrincipalM) {
        tableroInformacionMachine=tableroInfPrincipalM;
        if(!hayObjetivo()){
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
     * finds the headpiece and learns the next target to be fired at
     * @return if a new target was selected
     */
    private boolean hayObjetivo() {
        boolean objetivo=false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tableroInformacionMachine[i][j].equals("tocado")&&!objetivo){
                    if(i>0&&tableroInformacionMachine[i-1][j].equals("")){
                        disparoX=i-1;
                        disparoY=j;
                        objetivo=true;
                    }
                    else if(i>1&&tableroInformacionMachine[i-2][j].equals("")&&tableroInformacionMachine[i-1][j].equals("tocado")){
                        disparoX=i-2;
                        disparoY=j;
                        objetivo=true;
                    }
                    else if(i<9&&tableroInformacionMachine[i+1][j].equals("")){
                        disparoX=i+1;
                        disparoY=j;
                        objetivo=true;
                    }
                    else if(i<8&&tableroInformacionMachine[i+2][j].equals("")&&tableroInformacionMachine[i+1][j].equals("tocado")){
                        disparoX=i+2;
                        disparoY=j;
                        objetivo=true;
                    }

                    if(j>0&&tableroInformacionMachine[i][j-1].equals("")){
                        disparoX=i;
                        disparoY=j-1;
                        objetivo=true;
                    }
                    else if(j>1&&tableroInformacionMachine[i][j-2].equals("")&&tableroInformacionMachine[i][j-1].equals("tocado")){
                        disparoX=i;
                        disparoY=j-2;
                        objetivo=true;
                    }
                    else if(j<9&&tableroInformacionMachine[i][j+1].equals("")){
                        disparoX=i;
                        disparoY=j+1;
                        objetivo=true;
                    }
                    else if(j<8&&tableroInformacionMachine[i][j+2].equals("")&&tableroInformacionMachine[i][j+1].equals("tocado")){
                        disparoX=i;
                        disparoY=j+2;
                        objetivo=true;
                    }
                }
            }
        }
        return objetivo;
    }

    /**
     * Method that determines the spaces that a ship occupies according to its name
     * @return espaciosMachine int
     */
    public int getEspacioQueOcupa(String barco) {
        switch (barco) {
            case "Portaaviones" -> espaciosMachine = 4;
            case "Submarino" -> espaciosMachine = 3;
            case "Destructor" -> espaciosMachine = 2;
            case "Fragata" -> espaciosMachine = 1;
        }
        return espaciosMachine;
    }
}
