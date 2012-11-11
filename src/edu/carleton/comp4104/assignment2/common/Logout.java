package edu.carleton.comp4104.assignment2.common;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import edu.carleton.comp4104.assignment2.server.userVault;

public class Logout implements EventHandler {

	userVault vault;
	
	public void handleEvent(JSONMessage message, ObjectOutputStream oos) throws IOException {
		System.out.println("this is logout");
		
		vault = userVault.getInstance();     						// init() has already been called from server
		if(message.getsender().equals("")){
			System.out.println("exception logout");
			System.out.println("before removing :"+vault.getUsers().keySet());
			vault.removeUsers(oos);
			System.out.println("after removing :"+vault.getUsers().keySet());
		}else{
			System.out.println("logout command remove");
			vault.removeUsers(message.getsender());
		}
		
		//send the updated vault to every user
		for (Map.Entry<String, ObjectOutputStream> pair : vault.getUsers().entrySet()){
			//System.out.println(vault.getUsers().keySet());
			pair.getValue().writeObject(new JSONMessage(vault.getUsers().keySet()));
		}
		//oos.writeObject(new JSONMessage());
		oos.close();  // how do i close socket associated with this oos variable.
	}
}
