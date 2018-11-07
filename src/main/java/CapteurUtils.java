import model.Measure;

import java.util.Date;
import java.util.Random;

public class CapteurUtils {

    private static Random random = new Random();

    private static double getRandomValue(){
        return random.nextInt(80)+10;
    }


    public static Measure capteur1Json(){
        String nom = "Capteur 1";
        String type = "temperature";
        double valeur = getRandomValue();
        int id = 1;
        String gps = "47.283035, -1.521899";
        String ville = "NTE + Nantes";
        String pays = "FR + France";

        

        //String nom, long date, String type, double valeur, int id, String ville, String pays, String gps
        return new Measure(nom, (new Date().getTime()), type, valeur, id, ville, pays, gps);


        //return "{\"nom\":\""+nom+"\",\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"id\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

    public static Measure capteur2Json(){
        String nom = "Capteur 2";
        String type = "temperature";
        double valeur = getRandomValue();
        int id = 2;
        String gps = "47.281505, -1.521851";
        String ville = "NTE + Nantes";
        String pays = "FR + France";

        return new Measure(nom, (new Date().getTime()), type, valeur, id, ville, pays, gps);
        //return "{\"nom\":\""+nom+"\",\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"id\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

    public static Measure capteur3Json(){
        String nom = "Capteur 3";
        String type = "pression";
        double valeur = getRandomValue();
        int id = 3;
        String gps = "47.281694, -1.520865";
        String ville = "NTE + Nantes";
        String pays = "FR + France";

        return new Measure(nom, (new Date().getTime()), type, valeur, id, ville, pays, gps);
        //return "{\"nom\":\""+nom+"\",\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"id\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

    public static Measure capteur4Json(){
        String nom = "Capteur 4";
        String type = "humidite";
        double valeur = getRandomValue();
        int id = 4;
        String gps = "47.282411, -1.520854";
        String ville = "NTE + Nantes";
        String pays = "FR + France";

        return new Measure(nom, (new Date().getTime()), type, valeur, id, ville, pays, gps);
        //return "{\"nom\":\""+nom+"\",\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"id\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

    public static Measure capteur5Json(){
        String nom = "Capteur 5";
        String type = "humidite";
        double valeur = getRandomValue();
        int id = 5;
        String gps = "47.282484, -1.520173";
        String ville = "NTE + Nantes";
        String pays = "FR + France";

        return new Measure(nom, (new Date().getTime()), type, valeur, id, ville, pays, gps);
        //return "{\"nom\":\""+nom+"\",\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"id\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

}
