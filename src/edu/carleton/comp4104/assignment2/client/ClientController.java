package edu.carleton.comp4104.assignment2.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

public class ClientController extends JFrame{


	private static final long serialVersionUID = 1L;
	
	Client clientModel;
	ClientGui clientView;
	
	public ClientController(String title) throws IOException{
		super(title); 
		
		// model and view
		clientModel = new Client();
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
		
		//compute size of the frame
		setSize(500,500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
	
	
	private void handleSendButtonPress() throws IOException{ //when send button is pressed
		
		int index = clientView.getList().getSelectedIndex();
		
		if(index >=0){ // if one of the client is selected
			
			clientModel.connector.connect(clientModel.host, clientModel.port);
			String header = clientModel.cmd2;
			String body = clientModel.getSelectedClient(index);
			clientModel.connector.sendMessage(header + body); //sending in clientName + request type... here request type/or event type should be only of conversation.
			
		/*when client sends the above message, server will go through its list of clients and connect to appropriate 
		 * clients and send the message to that client since header here is conversation<which is send message>
		 * */
			
			
		}else{
			System.out.println("please select someone to chat with");
		}
	}
	
	public static void main(String[] args) {

		JFrame frame;
		try {
			frame = new ClientController("Client Ui");
			frame.setVisible(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		


	}

}
