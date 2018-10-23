package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/capteurs")
public class CapteurBroker extends HttpServlet {

    private String tcpAddress = "tcp://localhost:1883";

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

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(sb.toString());

        String capteur_id = jsonObject.get("capteurid").getAsString();
        String type = jsonObject.get("type").getAsString();

        /*
        String date = jsonObject.get("date").getAsString();
        String valeur = jsonObject.get("valeur").getAsString();
        String gps = jsonObject.get("gps").getAsString();
        String ville = jsonObject.get("ville").getAsString();
        String pays = jsonObject.get("pays").getAsString();
        */

        response.setStatus(HttpServletResponse.SC_OK);

        try {
            sendDataToBroker(tcpAddress, capteur_id, type, jsonObject.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (MqttException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Impossible d'envoyer les données au broker");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET Request received");
    }


    private void sendDataToBroker(String brokerUrl, String capteur_id, String topic, String data) throws MqttException {

        MqttClient capteur = null;
        try{
            capteur = new MqttClient(
                    brokerUrl,
                    capteur_id,
                    new MemoryPersistence());
            capteur.connect();
            capteur.publish(topic, data.getBytes(), 2, false);
            capteur.disconnect();
        } finally {
            assert capteur != null;
            capteur.close();
        }


    }
}
