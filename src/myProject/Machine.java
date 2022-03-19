package myProject;

import java.util.Random;

public class Machine {
    private String[] flota;
    private int estaFlota, espaciosMachine;

    public Machine() {
        flota = new String[]{"Portaaviones","Submarino","Submarino","Destructor","Destructor","Destructor","Fragata",
                "Fragata","Fragata","Fragata"};
        estaFlota = 0;
    }
    // toma aleatoria de barcos
    public String getBarco(){
        String barco="";
        if(estaFlota < 10) {
            barco = flota[estaFlota];
            estaFlota++;
        }
        return barco;
    }

    public String getOrientacion(){
        Random random = new Random();
        String[] orientacionMaquina = {"horizontal","vertical"};
        return (orientacionMaquina[random.nextInt(0,2)]);
    }

    public int getCoordenadaX(){
        Random random = new Random();
        return random.nextInt(0,10);
    }

    public int getCoordenadaY(){
        Random random = new Random();
        return random.nextInt(0,10);
    }

    public int getEspacioQueOcupa( String barco) {
        switch (barco) {
            case "Portaaviones" -> espaciosMachine = 4;
            case "Submarino" -> espaciosMachine = 3;
            case "Destructor" -> espaciosMachine = 2;
            case "Fragata" -> espaciosMachine = 1;
        }
        return espaciosMachine;
    }
}
