import java.util.Date;
import java.util.Random;

public class CapteurUtils {

    private static Random random = new Random();
    private static int currentTime = (int) (new Date().getTime()/1000);

    private static float getRandomValue(){
        return random.nextInt(80)+10;
    }


    public static String capteur1Json(){
        String type = "temperature";
        float value = getRandomValue();
        int id = 1;
        String gps = "47.283035, -1.521899";
        String ville = "NTE + Nantes";
        String pays = "FR + France";
        return "{\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"capteurid\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

    public static String capteur2Json(){
        String type = "temperature";
        float value = getRandomValue();
        int id = 2;
        String gps = "47.281505, -1.521851";
        String ville = "NTE + Nantes";
        String pays = "FR + France";
        return "{\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"capteurid\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

    public static String capteur3Json(){
        String type = "pression";
        float value = getRandomValue();
        int id = 3;
        String gps = "47.281694, -1.520865";
        String ville = "NTE + Nantes";
        String pays = "FR + France";
        return "{\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"capteurid\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

    public static String capteur4Json(){
        String type = "humidite";
        float value = getRandomValue();
        int id = 4;
        String gps = "47.282411, -1.520854";
        String ville = "NTE + Nantes";
        String pays = "FR + France";
        return "{\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"capteurid\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

    public static String capteur5Json(){
        String type = "humidite";
        float value = getRandomValue();
        int id = 5;
        String gps = "47.282484, -1.520173";
        String ville = "NTE + Nantes";
        String pays = "FR + France";
        return "{\"date\":"+currentTime+",\"type\":\""+type+"\",\"valeur\":"+value+",\"capteurid\":"+id+",\"gps\":\""+gps+"\",\"ville\":\""+ville+"\",\"pays\":\""+pays+"\"}";
    }

}
