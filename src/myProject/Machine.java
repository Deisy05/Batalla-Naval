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

    public Machine()
    {

        //We declare the fleet of the machine
        flota = new String[]
                {"Portaaviones","Submarino","Submarino","Destructor","Destructor","Destructor","Fragata","Fragata","Fragata","Fragata"};
        estaFlota = 0;
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

    public void prepararDisparo(String[][] matrix){
    }
    public int getDisparoX(){
        Random random = new Random();
        return random.nextInt(0,10);
    }
    public int getDisparoY(){
        Random random = new Random();
        return random.nextInt(0,10);
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
