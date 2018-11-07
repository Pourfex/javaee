import com.google.gson.Gson;
import model.Measure;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import properties.PropertiesManager;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;



public class CapteurSimilator extends TimerTask {

    private String hostAddress;

    public CapteurSimilator(String hostAddress){
        this.hostAddress = hostAddress;
    }


    public void run() {
        System.out.println("Sending data");


        Measure capteur1 = CapteurUtils.capteur1Json();
        Measure capteur2 = CapteurUtils.capteur2Json();
        Measure capteur3 = CapteurUtils.capteur3Json();
        Measure capteur4 = CapteurUtils.capteur4Json();
        Measure capteur5 = CapteurUtils.capteur5Json();

        Gson gson = new Gson();

        try {
            sendPostRequest(gson.toJson(capteur1));
            TimeUnit.SECONDS.sleep(1);
            sendPostRequest(gson.toJson(capteur2));
            TimeUnit.SECONDS.sleep(1);
            sendPostRequest(gson.toJson(capteur3));
            sendPostRequest(gson.toJson(capteur4));
            TimeUnit.SECONDS.sleep(1);
            sendPostRequest(gson.toJson(capteur5));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendPostRequest(String body) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        try {

            HttpPost request = new HttpPost(hostAddress);
            StringEntity params = new StringEntity( body);
            request.addHeader("content-type", "text/plain");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            System.out.println("Response : "+response.getStatusLine().getStatusCode());

        }catch (Exception ex) {
            //System.out.println("Error sending");
            //handle exception here

        }

    }


}
