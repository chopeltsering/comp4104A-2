package edu.carleton.comp4104.assignment2.common;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import edu.carleton.comp4104.assignment2.server.userVault;

public class Conversation implements EventHandler{

	userVault vault;
	int num=0;
	public void handleEvent(JSONMessage message, ObjectOutputStream oos) throws IOException {
		System.out.println("this is conversation");
		
		vault = userVault.getInstance(); 
		for (Map.Entry<String, ObjectOutputStream> pair : vault.getUsers().entrySet()){
			if(pair.getKey().equals(message.getreceiver())){
				num++;
				System.out.println(message.getreceiver());
				pair.getValue().writeObject(new JSONMessage(message.getsender(), message.getreceiver(), message.getMessage()));
			}
		}		
		System.out.println(num);
		
		oos.writeObject(new JSONMessage());
	}

}
