package emn.MQTTBroker;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Measure;

public class App {
	
	public static void main (String[] args) throws IOException {
		
		PropertiesManager initer = new PropertiesManager();
		initer.initProps();
		
		ClientManager manager = new ClientManager();
		manager.all();
	
	}
}
