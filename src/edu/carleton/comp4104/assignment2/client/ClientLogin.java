package edu.carleton.comp4104.assignment2.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientLogin extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel panel;
	String userName = "";
	String localHost;
	int port;
	JLabel loginLabel;
	JTextField login;
	JButton button;
	
	public ClientLogin(){
		
		super("Login");

		panel = new JPanel();
		panel.setLayout(null);
		loginLabel = new JLabel("User Name: "); 
		loginLabel.setLocation(10, 50);
		loginLabel.setSize(100, 30);
		panel.add(loginLabel);
		
		login = new JTextField();
		login.setLocation(100,50);
		login.setSize(200, 30);
		panel.add(login);
		
		button = new JButton("Login");
		button.setLocation(350, 50);
		button.setSize(100,30);
		panel.add(button);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					handleLoginButtonPress();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		getContentPane().add(panel);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 200);
		setVisible(true);

	}
	
	public void handleLoginButtonPress() throws IOException{
		userName = login.getText();
		if(userName.equals("")){
			System.out.println("please enter your user name");
		}else{
			localHost = "localhost";
			port = 69;
			JFrame mainFrame;
			mainFrame = new ClientController("Client Ui", userName, localHost, port);
			mainFrame.setVisible(true);
			this.dispose();
		}
		
	} 
	
	public static void main(String args[]){
		ClientLogin loginFrame;
		loginFrame = new ClientLogin();
		loginFrame.setVisible(true);
	}
	
	
}
