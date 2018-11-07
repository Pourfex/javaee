package emn.MQTTBroker;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Measure;

public class FileWriteCallback implements MqttCallback {

	private String directory;
	private HashMap<Integer, ArrayList<Measure>> measures;

	public FileWriteCallback() {
		super();

		directory = "resources/logs/" + new Date().getTime();
		new File(directory).mkdirs();
		
		measures = new HashMap<Integer, ArrayList<Measure>>();
	}

	public void connectionLost(Throwable cause) {
		System.out.println("<FileWriterCallback> Connection lost..");
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// May fail if the content of the payload is not interpretable as String
		String payload = message.toString();

		ObjectMapper mapper = new ObjectMapper();
		try {
			Measure measure = mapper.readValue(payload, Measure.class);
			if (!measures.containsKey(measure.getId())) {
				measures.put(measure.getId(), new ArrayList<Measure>());
			}

			measures.get(measure.getId()).add(measure);

			String jsonResult = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(measures.get(measure.getId()));

			FileWriter file = new FileWriter(directory + "/" + measure.getId() + ".json");

			file.write(jsonResult);
			System.out.println("<FileWriterCallback> Successfully Copied JSON Object to File...");

			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("<FileWriterCallback> Delivery complete..");
	}
}
