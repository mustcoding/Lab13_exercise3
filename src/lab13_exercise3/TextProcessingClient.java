package lab13_exercise3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TextProcessingClient{

    int serverSidePortNo = 4321;
    
    public void startClient() throws IOException {
    	Socket socket=null;
        try {
        	
            //1. Connect to the remote machine
            socket = new Socket(InetAddress.getLocalHost(),serverSidePortNo);
            
        	//2. Send request to the server
        	OutputStream outputStream = socket.getOutputStream();
        	String text = "This this lab13 exercise 5 hoorey";
        	byte[] textBytes = text.getBytes();
        	outputStream.write(textBytes);
        	outputStream.flush();
        	
        	//3. Accept response from the server
        	InputStream inputStream = socket.getInputStream();
        	byte[] buffer = new byte[1024]; // Buffer size for reading data from the server
            int bytesRead;
            StringBuilder responseBuilder = new StringBuilder();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                  String responseChunk = new String(buffer, 0, bytesRead);
                  responseBuilder.append(responseChunk);
              }
              String response = responseBuilder.toString();
        	
              //4. Response the process
        	System.out.println("Response from server: "+response);
        }catch (IOException e){
        	e.printStackTrace();
        }
        	finally
        {
        		 if (socket != null)
        		 {
                     try 
                     {
                         socket.close();
                     } catch (IOException e) 
                     {
                         e.printStackTrace();
                     }
        		 }
        }
    }
    

    public static void main(String[] args) {
    	TextProcessingClient client = new TextProcessingClient();
        try {
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
	
				

