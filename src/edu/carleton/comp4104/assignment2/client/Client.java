package edu.carleton.comp4104.assignment2.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Scanner;

import edu.carleton.comp4104.assignment2.common.Acceptor;
import edu.carleton.comp4104.assignment2.common.Connector;
import edu.carleton.comp4104.assignment2.common.Marshaller;
import edu.carleton.comp4104.assignment2.common.Reactor;

public class Client {
	Reactor reactor;
	Acceptor acceptor;
	Connector connector;
	String host;
	int port;
	String clientName;
	ArrayList<String> otherClients;
	Thread listener;
	String cmd1 ;
	String cmd2;
	String cmd3;
	String cmd4;

	
	public Client() throws IOException{
		//reactor = new Reactor();
		//connector = new Connector();
		//acceptor = new Acceptor(5000); // will accept connection at port 5000 < to be used in peer to peer connection>
		host = "127.0.0.1";
		port = 69;                   // server is listening at port 69 
		otherClients = new ArrayList<String>();
		
		cmd1 = "Login";
		cmd2 = "Conversation";
		cmd3 = "Logout"; 
		cmd4 = "unknown";
		Scanner scanner = new Scanner(System.in); // scanner object to read in
		System.out.println("what is thy name? ");
		clientName = scanner.nextLine();
		listener = start(scanner); // start the client and load in other clients into this clients gui.
		scanner.close();
		
	}
	
	public String getSelectedClient(int i){
		return otherClients.get(i);
	}
	
	public Thread start(Scanner scanner) throws IOException{ 
		Thread t = null;

		t = new Thread( new Runnable(){
            public void run(){
            	try {
            		while(true){
            			System.out.println("client is listening on seperate thread for updates"); 
            			
	            		MulticastSocket sock = new MulticastSocket(444);
	            		InetAddress IP_ADDRESS = InetAddress.getByName("224.0.0.1");
						sock.joinGroup(IP_ADDRESS);
						
						byte[] buf = new byte[256]; 
						DatagramPacket packet = new DatagramPacket(buf, buf.length);
						sock.receive(packet);
						
						String newClient = packet.getData().toString();
						otherClients.add(newClient);
						sock.close();
            		}								
					
				} catch (IOException e) {
					System.out.println("multicast not working");
				}
            	      
            }
        });

		t.start();
		
		
		//connector.connect(host, port); // connect to server at given host and port number
		
		//connector.sendMessage(cmd1); // Login event/request has been passed.
		
		// probably need to receive confirmation of connection.
		
		return t;
			
		//otherClients = (ArrayList<String>) Marshaller.deserializeObject(scanner.nextLine().getBytes());  // need thorough testing first
	}
	
}
