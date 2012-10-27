package edu.carleton.comp4104.assignment2.client;

import java.io.IOException;

import edu.carleton.comp4104.assignment2.common.Acceptor;
import edu.carleton.comp4104.assignment2.common.Connector;
import edu.carleton.comp4104.assignment2.common.Reactor;

public class Client {
	Reactor reactor;
	Acceptor acceptor;
	Connector connector;
	String host;
	int port;
	String clientName;
	String cmd1 ;
	String cmd2;
	String cmd3;

	
	public Client(String name){
		reactor = new Reactor();
		connector = new Connector();
		acceptor = new Acceptor(5000); // will accept connection at port 5000 < to be used in peer to peer connection>
		host = "127.0.0.1";
		port = 69;                   // server is listening at port 69 
		clientName = name;
		cmd1 = "Register";
		cmd2 = "Deregister";
		cmd3 = "C";
	}
	
	public static void main(String[] args) {
		
		Client client = new Client("Bob");
		try {
			
			client.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void start() throws IOException{ 
		connector.connect(host, port); // connect to server at given host and port number

		connector.sendMessage(cmd); // sent message to server

		System.out.println("done messaging");
	}
	
}
