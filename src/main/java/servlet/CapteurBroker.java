package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/capteurs")
public class CapteurBroker extends HttpServlet {

    private String tcpAddress = "tcp://test.mosquitto.org";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST Request received");

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }

        //System.out.println("Sb "+sb);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(sb.toString());


        String capteur_id = jsonObject.get("id").getAsString();

        //Gson gson = new Gson();
        //Measure measure = gson.fromJson(sb.toString(), Measure.class);

        try {
            byte[] data = sb.toString().getBytes();
            response.setStatus(HttpServletResponse.SC_OK);

            try {
                sendDataToBroker(tcpAddress, capteur_id, data);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (MqttException e) {
                e.printStackTrace();

                response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Impossible d'envoyer les données au broker");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET Request received");
    }


    private void sendDataToBroker(String brokerUrl, String capteur_id, byte[] data) throws MqttException {
        MqttClient capteur = null;
        try {
            capteur = new MqttClient(
                    brokerUrl,
                    capteur_id,
                    new MemoryPersistence());
            capteur.connect();
            capteur.publish("AAI_filA2", data, 2, false);
            capteur.disconnect();
        } finally {
            assert capteur != null;
            capteur.close();
        }
    }
}
