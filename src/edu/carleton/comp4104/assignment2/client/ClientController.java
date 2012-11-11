package edu.carleton.comp4104.assignment2.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;

import edu.carleton.comp4104.assignment2.common.JSONMessage;

public class ClientController extends JFrame{


	private static final long serialVersionUID = 1L;
	
	Client clientModel;
	ClientGui clientView;
	
	public ClientController(String title, String userName, String localHost, int port) throws IOException{
		super(title);
		
		// model and view
		clientModel = new Client(userName, localHost, port);
		Thread clientThread = new Thread(clientModel);
		clientThread.start();  // client will login first and then start listening for broadcast
		clientView = new ClientGui(clientModel);
		
		//add the view
		getContentPane().add(clientView);
		
		//add the event handler for send button 
		clientView.SendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					handleSendButtonPress();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//add the event handler for jlist
		clientView.aListOfClients.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				try {
					handleJlist();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
			
		//add the event handler for where user types
		clientView.whereUserTypes.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				try {
					handleWhereUserTypes();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});	
		
		//compute size of the frame
		setSize(500,500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void handleSendButtonPress() throws IOException{ //when send button is pressed
		System.out.println(clientModel.userName+"'s sendButton has been pressed");
		int index = clientView.getList().getSelectedIndex();
		System.out.println("from list, index number "+index+" has been pressed");
		if(index >=0){ 																// if one of the client is selected
			System.out.println("about to send to: "+ clientModel.getSelectedClient(index)+ " text message: "+ clientView.whereUserTypes.getText());
			JSONMessage message = new JSONMessage(clientModel.userName, clientModel.getSelectedClient(index), clientView.whereUserTypes.getText());
			try {
				clientModel.connector.send(message);
				clientView.update("send");                  
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 

		}else{
			System.out.println("please select someone to chat with");
		}
	}
	
	private void handleJlist() throws IOException{
		clientView.whereUserTypes.setEnabled(true);
		//clientView.update(); // update has sendbutton enabling condition
	}
	
	private void handleWhereUserTypes() throws IOException{
		if(clientView.whereUserTypes.isEnabled())
			clientView.SendButton.setEnabled(true);
	}

}
