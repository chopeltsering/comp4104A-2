package edu.carleton.comp4104.assignment2.client;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ClientGui extends JPanel{

	private static final long serialVersionUID = 1L;

	Client model;
	JList<String> aListOfClients;
	JLabel userName;
	JTextField conversationFromOtherclients;
	JTextField whereUserTypes;
	JButton SendButton;
	
	public ClientGui(Client client){
		setLayout(null);
		model = client;
		
		userName = new JLabel(model.clientName); 
		userName.setLocation(10, 10);
		userName.setSize(100, 10);
		add(userName);
		
		aListOfClients = new JList<String>();
		JScrollPane scrollPaneForListOfclient = new JScrollPane(aListOfClients,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneForListOfclient.setLocation(10,50);
		scrollPaneForListOfclient.setSize(300,100);
		add(scrollPaneForListOfclient);
		
		conversationFromOtherclients = new JTextField();
		conversationFromOtherclients.setEditable(false);
		JScrollPane scrollPaneForConversation = new JScrollPane(conversationFromOtherclients,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneForConversation.setLocation(10,170);
		scrollPaneForConversation.setSize(300,100);
		add(scrollPaneForConversation);
		
		whereUserTypes = new JTextField();
		whereUserTypes.setLocation(10,300);
		whereUserTypes.setSize(300,25);
		add(whereUserTypes);
		
		SendButton = new JButton("Send");
		SendButton.setLocation(350, 300);
		SendButton.setSize(100,25);
		add(SendButton);

		setSize(1000, 1000);
	}
	
	public void update(){
		aListOfClients.setListData((String [])model.otherClients.toArray()); // updates the list in Jlist
		SendButton.setEnabled((aListOfClients.getSelectedIndex()) >= 0 && (!whereUserTypes.getText().isEmpty())); // enable send button as focused when theres text in textfield and when one of the user is selected
		 
	}
	
	public JList<String> getList(){
		return aListOfClients;
	}
	
}
