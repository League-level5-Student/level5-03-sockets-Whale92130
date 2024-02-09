package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import _01_Intro_To_Sockets.client.ClientGreeter;
import _01_Intro_To_Sockets.server.ServerGreeter;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	JFrame frame = new JFrame("Chat App");
	JPanel panel = new JPanel();
	JButton button = new JButton("Send");
	JTextField text = new JTextField();
	JLabel label = new JLabel();
	void run() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int hoster = JOptionPane.showConfirmDialog(null, "Would you like to host a server?", "Chat App",
				JOptionPane.YES_NO_OPTION);
		if (hoster == 0) {
			// host server
			frame.setTitle("Server");
			JOptionPane.showMessageDialog(null, "Server started at: " + "\nPort: ");
			frame.add(panel);
			panel.add(button);
			text.setSize(100, 20);
			text.setText("Enter Message Here");
			panel.add(text);
			panel.add(label);
			label.setText("Messages Apear Here");
			frame.pack();
			frame.setVisible(true);
			System.out.println("done");
			int port = Integer.parseInt(JOptionPane.showInputDialog("What is the port number"));
			ServerSocket sock;
					
			boolean maybeItsTrueMaybeNot = true;
			while (maybeItsTrueMaybeNot == true) {
				System.out.println("\nDebug: run\n");
				try {
					sock = new ServerSocket(port);
					
					System.out.println("The server is ready for connection");
			
					Socket socket = sock.accept();
				
					System.out.println("User Conected");
					
					DataInputStream inputStream = new DataInputStream(socket.getInputStream());
					
					DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
					button.addActionListener(e -> {
	      				try {
	      					outputStream.writeUTF(text.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	      			});
					
					while(true) {
					
					label.setText(inputStream.readUTF());
				
					}
					
					
				} catch (SocketTimeoutException e) {
					e.printStackTrace();
					System.out.println("SockeTimeoutException caught, server closed");
					maybeItsTrueMaybeNot = false;
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("IOException caught, server closed");
					maybeItsTrueMaybeNot = false;
				}
			}

		} else if (hoster == 1) {
			// client
			String ipAddress = "localHost";
			int port = Integer.parseInt(JOptionPane.showInputDialog("What is the port number"));
			
	      		try {
	    	
	      			
	      			frame.setTitle("Client");
	    			frame.add(panel);
	    			panel.add(label);
	    			label.setSize(100, 100);
	    			text.setSize(100, 20);
	    			text.setText("Enter Message Here");
	    			panel.add(text);
	    			panel.add(button);
	    			label.setText("Messages Apear Here");
	    			frame.pack();
	    			frame.setVisible(true);
	      			
	      			Socket sock = new Socket(ipAddress, port);
	      			DataOutputStream dataOutput = new DataOutputStream(sock.getOutputStream());
	        
	      			button.addActionListener(e -> {
	      				try {
							dataOutput.writeUTF(text.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	      			});
	      			
	      			DataInputStream dataInput = new DataInputStream(sock.getInputStream());
	        
	      			while (true) {
	      			label.setText(dataInput.readUTF());
	      			}
	      		}
	      		catch (IOException e) {
	      			e.printStackTrace();
	      		}
		}
	}
}
