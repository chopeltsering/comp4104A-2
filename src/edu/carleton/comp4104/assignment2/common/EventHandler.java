package edu.carleton.comp4104.assignment2.common;

import java.io.IOException;
import java.io.ObjectOutputStream;


public interface EventHandler {
	public void handleEvent(JSONMessage message, ObjectOutputStream oos) throws IOException; 
}
   