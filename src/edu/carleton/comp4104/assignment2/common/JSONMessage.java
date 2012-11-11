package edu.carleton.comp4104.assignment2.common;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JSONMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String cmd;
	private final String object;
	private  String type;
	private final String sender;
	private final String receiver;
	private final String message;

	
	// Use this for a Login and Logout
	public JSONMessage(String cmd, String sender) {
		this.cmd = cmd;
		this.object = null;
		this.type = null;
		this.sender = sender;
		this.receiver = null;
		this.message = null;
	}
	
	// for conversation request
	public JSONMessage(String sender, String receiver, String message) {
		this.cmd = "Conversation";
		this.type = null;
		this.object = null;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}
	
	//for broadcast
	public JSONMessage(Object obj) {
		this.cmd = "Broadcast";
		this.type = obj.getClass().getName();
		if(type.equals("java.util.HashMap$KeySet")){ // when sending hashapkeyset over network, it gets converteed to java.util.LinkedHashSet
			type = "java.util.LinkedHashSet";   // thats why i need to convert type to java.util.LinkedHashSet, so that i get no exception when casting back
		}
		Gson gson = new Gson();
		this.object = gson.toJson(obj);
		this.sender = null;
		this.receiver = null;
		this.message = null;
	}
	
	public JSONMessage() {
		this.cmd = "OK";
		this.object = null;
		this.type = null;
		this.sender = null;
		this.receiver = null;
		this.message = null;
	}
	
	public String getCmd() {
		return cmd;
	}
	
	public String getsender(){
		return sender;
	}
	
	public String getreceiver(){
		return receiver;
	}
	public String getMessage() {
		return message;
	}

	public Object getObject() throws JsonSyntaxException, ClassNotFoundException {
		// The type is used to find the class associated with the object.
		// We store a String for type in order to make a JSONMessage serializable using Gson.
		Gson gson = new Gson();
		return gson.fromJson(object, Class.forName(type));
	}
	
}
