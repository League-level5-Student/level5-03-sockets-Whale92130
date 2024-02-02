package _02_Chat_Application;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import _01_Intro_To_Sockets.server.ServerGreeter;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */



public class ChatApp {
	JFrame frame = new JFrame("Chat App");
	JPanel panel = new JPanel();
	
	
	void run() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int hoster = JOptionPane.showConfirmDialog(null, "Would you like to host a server?", "Chat App", JOptionPane.YES_NO_OPTION);
		if (hoster == 0) {
			//host server
			System.out.print("you are hosting :)");
			ServerGreeter server = new ServerGreeter(6969);
			frame.setTitle("Server");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			
			
		}
		else if (hoster == 1) {
			//client
			System.out.print("you are a client :)");
		}
	}
}
