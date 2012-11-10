package edu.carleton.comp4104.assignment2.server;

import java.io.ObjectOutputStream;
import java.util.HashMap;


public class userVault {

	HashMap<String, ObjectOutputStream> users;
	private static userVault instance;
	
	public userVault(){
		
	}
	
	public static userVault getInstance(){
		if(instance == null){
			instance = new userVault();
		}
		
		return instance;
	}
	
	public void init(){
		users = new HashMap<String, ObjectOutputStream>();
	}
	
	public HashMap<String, ObjectOutputStream>getUsers(){
		return users;
	}
	
	public void put(String key, ObjectOutputStream oos){
		users.put(key, oos);
	}
	
	public ObjectOutputStream removeUsers(String key){
		return users.remove(key);
	}

	public void removeUsers(ObjectOutputStream oos){ 
		/*String key = "";
		synchronized(users){
			for (Map.Entry<String, ObjectOutputStream> pair : users.entrySet()){
				if(pair.getValue().equals(oos)){
					key = pair.getKey();
				}
			}
		}
		if(!key.equals(""))
			users.remove(key);
		
		//System.out.println("Is oos in the vault: " + users.values().contains(oos));
		
		//boolean remove = users.values().remove(oos);
		//System.out.println("removal of oos was successful: " + remove);
*/		users.values().remove(oos);
	}
}
