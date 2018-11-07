package emn.MQTTBroker;

import java.io.IOException;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ClientManager {

	public void all() throws IOException {
		String topic = PropertiesManager.prop.getProperty("topic");
		String broker = "tcp://" + PropertiesManager.prop.getProperty("broker");
		MemoryPersistence persistence = new MemoryPersistence();

		try {
			MqttClient dataBaseClient = new MqttClient(broker, UUID.randomUUID().toString(), persistence);
			dataBaseClient.setCallback(new DataBaseCallback());

			MqttClient fileWriteClient = new MqttClient(broker, UUID.randomUUID().toString(), persistence);
			fileWriteClient.setCallback(new FileWriteCallback());

			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(false);

			System.out.println("Connecting clients to " + broker + "...");
			dataBaseClient.connect(connOpts);
			dataBaseClient.subscribe(topic);

			fileWriteClient.connect(connOpts);
			fileWriteClient.subscribe(topic);
			
			System.out.println("Done");

		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}

}
