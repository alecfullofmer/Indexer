package clientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import batch.BatchState;
import client.communication;
import client_communicator.ValidateUser_Params;
import client_communicator.ValidateUser_Result;

public class LoginFrame extends JFrame {
	


	public JButton blogin;
	private JPanel loginpanel;
	private JTextField userText;
	private JTextField passText;
	public JButton exit;
	private JLabel username;
	private JLabel password;
	ValidateUser_Result result;
	
	public LoginFrame()
	{
		super();

		this.setTitle("Login to Indexer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		blogin = new JButton("Login");
		loginpanel = new JPanel();
		userText = new JTextField(15);
		passText = new JTextField(15);
		exit = new JButton("Exit");
		username = new JLabel("USERNAME:");
		password = new JLabel("PASSWORD:");
		
		this.setSize(300, 200);
		this.setLocation(500, 280);
		this.setResizable(false);
		loginpanel.setLayout(null);
		
		userText.setBounds(95,30,150,20);
		passText.setBounds(95, 65, 150, 20);
		blogin.setBounds(110, 100, 80, 20);
		username.setBounds(20, 28, 80, 20);
		password.setBounds(20,63,80,20);
		exit.setBounds(110, 135, 80, 20);
		
		loginpanel.add(blogin);
		loginpanel.add(exit);
		loginpanel.add(username);
		loginpanel.add(userText);
		loginpanel.add(password);
		loginpanel.add(passText);
		
		getContentPane().add(loginpanel);	
		
	}
	
	public String getUserText() {
		return userText.getText();
	}


	public String getPassText() {
		return passText.getText();
	}
	
}
