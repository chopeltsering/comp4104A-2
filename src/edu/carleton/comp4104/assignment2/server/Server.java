package edu.carleton.comp4104.assignment2.server;

import java.io.File;
import java.io.IOException;
import edu.carleton.comp4104.assignment2.common.Acceptor;
import edu.carleton.comp4104.assignment2.common.Reactor;

public class Server {
	Reactor reactor;
	Acceptor acceptor;
	int port;
	String fileName;	
	String path1, path2; //they both go to same path... i was just experimenting
	userVault vault;
	
	public Server(){
		port = 69;
		reactor = Reactor.getInstance();;
		acceptor = Acceptor.getInstance();
		vault = userVault.getInstance();
		fileName = "loadEventHandler.txt";  // file where i have save object name with 
		path1 = new java.io.File("").getAbsolutePath()+ File.separator + fileName;
		path2 = System.getProperty("user.dir");
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException{
		reactor.init();
		reactor.registerHandler(path2, fileName);	
		//reactor.printObject();
		acceptor.init(reactor, port);
		vault.init();
		
		try {	
			while(true){
				 acceptor.accept();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
