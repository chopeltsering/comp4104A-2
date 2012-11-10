package edu.carleton.comp4104.assignment2.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import edu.carleton.comp4104.assignment2.common.Connector;
import edu.carleton.comp4104.assignment2.common.JSONMessage;

public class Client implements Runnable{
	Connector connector;
	JSONMessage message;
	String host;
	int port;
	String userName;
	ArrayList<String> otherClients;
	ArrayList<String> conversation;
	Thread listener;
	String cmd1, cmd2;
	ClientGui gui;

	public Client(String userName, String localHost, int port) throws IOException{
		connector = new Connector();
		this.userName = userName;
		host = localHost;
		this.port = port;
		otherClients = new ArrayList<String>();
		cmd1 = "Login";
		cmd2 = "Logout";
	}
	
	public String getSelectedClient(int i){
		return otherClients.get(i);
	}

	public void run() {
		connector.init(host, port);
		try {
			connector.connect();
			message = new JSONMessage(cmd1, userName);  // cmd1 is for logging in
			connector.send(message);
			while(true){
				JSONMessage reply = connector.receive();
				System.out.println(reply.getCmd());
				if(reply.getCmd().equals("Broadcast")){
					System.out.println("received BroadCast");
					@SuppressWarnings("unchecked")
					Set<String> set = (Set<String>) reply.getObject();
					otherClients.removeAll(otherClients);
					otherClients.addAll(set); // add other clients in the list
					gui.update();
				}else if(reply.getCmd().equals("Conversation")){
					System.out.println("received Conversation");
					String message = (String)reply.getMessage();
					String otherName = (String)reply.getOtherName();
					conversation.add(otherName + " : " + message); // add other clients in the list
					gui.update();
				}else if(reply.getCmd().equals("OK")){
					System.out.println("received confirmation");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setGuiForUpdate(ClientGui clientGui) {
		gui = clientGui;	// i dont know if having each class compose each other is such a good idea
	}
	
}
