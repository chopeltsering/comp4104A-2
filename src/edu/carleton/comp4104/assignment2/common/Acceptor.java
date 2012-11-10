package edu.carleton.comp4104.assignment2.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class Acceptor {
	
	Reactor reactor;
	ServerSocket listener;
	Socket sock;
	int poolSize = 10;
	Gson gson;
	
	private static Acceptor instance = null;  // for singleton pattern
	
	public Acceptor(){
	}
	public static Acceptor getInstance() {
		if(instance == null) {
	         instance = new Acceptor();
	    }
	    return instance;
	}
	
	public void init(Reactor reactor, int port) throws IOException{
		this.reactor = reactor;
		listener = new ServerSocket(port);
		gson = new Gson();
	} 
	
	public void accept() throws IOException {
		System.out.println("waiting for connection");
		sock = listener.accept();

		Thread connection = new Thread(new Connection(reactor, sock));
	
		ExecutorService pool = Executors.newFixedThreadPool(poolSize);
		pool.execute(connection);
	}
}
