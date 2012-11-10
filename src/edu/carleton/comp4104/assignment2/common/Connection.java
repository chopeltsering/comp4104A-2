package edu.carleton.comp4104.assignment2.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable{
	
	Socket sock;
	Reactor reactor;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public Connection(Reactor reactor, Socket sock){
		this.reactor = reactor;
		this.sock = sock;
	}
	
	public void run() {  
		try {
			oos = new ObjectOutputStream(sock.getOutputStream()); 
			ois = new ObjectInputStream(sock.getInputStream());
			while(true){
				JSONMessage message = (JSONMessage)ois.readObject();
				System.out.println("after reading inside runnable");
				reactor.dispatch(message, oos); // is it okay to pass in socket too? isnt reactor only suppose to receive events?
				System.out.println("after dispatch inside the runnable");
			} 		
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("exception received");
			try {
				reactor.dispatch(new JSONMessage("Logout", ""), oos);
			} catch (IOException e1) {
				System.out.println("socket is being reset");
			}
			
		}
	}

}
