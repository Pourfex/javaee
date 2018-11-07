package emn.MQTTBroker;

import java.io.FileWriter;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import emn.DB.DataBaseManager;
import model.Measure;

public class DataBaseCallback implements MqttCallback{
	
	private DataBaseManager databaseManager = new DataBaseManager();
	
	public DataBaseCallback() {
		super();
	}
	
	public void connectionLost(Throwable cause) {
		System.out.println("<DataBaseCallback> Connection lost..");
		
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("a message has been received : " + message.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Measure measure = mapper.readValue(message.toString(), Measure.class);
			databaseManager.putData(measure);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("<DataBaseCallback> Delivery complete..");
		
	}
}