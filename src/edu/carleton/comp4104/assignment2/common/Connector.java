package edu.carleton.comp4104.assignment2.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector {

	Socket sock;
	String host;
	int port;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	public Connector(){
	}
	
	public void connect() throws UnknownHostException, IOException{
		sock = new Socket(host, port);
		ois = new ObjectInputStream(sock.getInputStream());
		oos = new ObjectOutputStream(sock.getOutputStream());
	}
	
	public void init(String host, int port){
		this.host = host;
		this.port = port;
	}

	public JSONMessage receive() throws IOException, ClassNotFoundException{
		return (JSONMessage) ois.readObject();
	}
	
	public void send(JSONMessage message) throws IOException, ClassNotFoundException{
		oos.writeObject(message); 
	}
	
	
	public void closeConnection() throws IOException{
		ois.close();
		oos.close();
		sock.close();
	}

}
 