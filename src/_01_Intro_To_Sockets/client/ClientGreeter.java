package _01_Intro_To_Sockets.client;

import java.net.*;
import java.io.*;

public class ClientGreeter {

	public static void main(String [] args) {
	  //1. Create a String for the ip address of the server. 
	  // If you don't know how to find a computer's ip address, ask about ifconfig on linux/mac and ipconfig on windows.
      String ipAddress = "localHost";
      //2. Create an integer for the server's port number
      int portNum = 1;
      //3. Surround steps 4-9 in a try-catch block that catches any IOExceptions.
      		try {
    	 //4. Create an object of the Socket class. When initializing the object, pass in the ip address and the port number
      			Socket sock = new Socket(ipAddress, portNum);
         //5. Create a DataOutputStream object. When initializing it, use the Socket object you created in step 4 to call the getOutputStream() method.
      			DataOutputStream dataOutput = new DataOutputStream(sock.getOutputStream());
         //6. Use the DataOutputStream object to send a message to the server using the writeUTF(String message) method
      			dataOutput.writeUTF("Deez");
         //7. Create a DataInputStream object. When initializing it, use the Server object you created in step 4 to call the getInputStream() method.
      			DataInputStream dataInput = new DataInputStream(sock.getInputStream());
         //8. Use the DataInputStream object to print a message from the server using the readUTF() method.
      			System.out.println("Message from the internet " + dataInput.readUTF());
         //9. Close the client's server object
      			sock.close();
      		}
      		catch (IOException e) {
      			e.printStackTrace();
      		}

   }
}
