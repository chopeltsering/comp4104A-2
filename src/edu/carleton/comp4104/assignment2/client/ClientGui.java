package edu.carleton.comp4104.assignment2.client;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ClientGui extends JPanel{

	private static final long serialVersionUID = 1L;

	Client model;
	JList<String> aListOfClients;
	JLabel userName;
	JTextArea conversationFromOtherclients;
	JTextField whereUserTypes;
	JButton SendButton;
	String clientName;
	
	public ClientGui(Client model){
		setLayout(null);
		this.model = model;
		this.model.setGuiForUpdate(this);
		
		userName = new JLabel("User: "+model.userName); 
		userName.setLocation(10, 10);
		userName.setSize(100, 50);
		add(userName);
		
		aListOfClients = new JList<String>();
		JScrollPane scrollPaneForListOfclient = new JScrollPane(aListOfClients,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneForListOfclient.setLocation(10,50);
		scrollPaneForListOfclient.setSize(300,100);
		add(scrollPaneForListOfclient);
		
		conversationFromOtherclients = new JTextArea();
		JScrollPane scrollPaneForConversation = new JScrollPane(conversationFromOtherclients,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneForConversation.setLocation(10,170);
		scrollPaneForConversation.setSize(300,100);
		add(scrollPaneForConversation);
		
		whereUserTypes = new JTextField();
		whereUserTypes.setEnabled(false);
		whereUserTypes.setLocation(10,300);
		whereUserTypes.setSize(300,25);
		add(whereUserTypes);
		
		SendButton = new JButton("Send");
		SendButton.setEnabled(false); 			//sendbutton is disable at the beginning
		SendButton.setLocation(350, 300);
		SendButton.setSize(100,25);
		add(SendButton);

		setSize(1000, 1000);
	}
	
	public void update(String cmd){
		System.out.println("update has been called with cmd : "+cmd);
		
		if(cmd.equals("Broadcast")){
			String[] exactList = new String[model.otherClients.size()];
			for(int i = 0; i< model.otherClients.size(); i++){
				if(!model.otherClients.get(i).equals(model.userName)){
					exactList[i] = model.otherClients.get(i);
				}
			}
			aListOfClients.setListData(exactList); // updates the list in Jlist
			
		}else if(cmd.equals("send")){
			whereUserTypes.setText(null);          //means a message has been typed and send, so need to make text area empty.
			
		}else if(cmd.equals("Conversation")){
			String temp = "";
			for(String s: model.conversation){
				temp += s + "\n";
			}
			System.out.println(temp);
			conversationFromOtherclients.setText(temp);
			
		}
		
	}
	
	public JList<String> getList(){
		return aListOfClients;
	}
	
}
