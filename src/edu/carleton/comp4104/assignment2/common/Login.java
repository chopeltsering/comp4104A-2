package edu.carleton.comp4104.assignment2.common;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import edu.carleton.comp4104.assignment2.server.userVault;

public class Login implements EventHandler{
	
	userVault vault;
	
	public void handleEvent(JSONMessage message, ObjectOutputStream oos) throws IOException {
		System.out.println("this is login"); 
		
		vault = userVault.getInstance();     						// init() has already been called from server
		vault.put(message.getYourName(), oos); 					// save the new user in the vault
	
		//send the updated vault to every user
		for (Map.Entry<String, ObjectOutputStream> pair : vault.getUsers().entrySet()){
			pair.getValue().writeObject(new JSONMessage(vault.getUsers().keySet()));// it seems sockets inside the hashmap is unable to be converted to json , besides why send list of sockets to client
		}
		oos.writeObject(new JSONMessage());
	}	
}


