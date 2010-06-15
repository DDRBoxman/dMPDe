package com.recursivepenguin.android.dMPDe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientListener extends Thread{

	private ServerSocket mServerSocket;
	private int mPort;
	
	public ClientListener () {
		this(6600);
	}
	
	public ClientListener (int port) {
		mPort = port;
		try {
			mServerSocket = new ServerSocket(mPort);
		} catch (IOException e) {
		    System.out.println("Could not listen on port: " + mPort);
		    System.exit(-1);
		}
	}
	
	public void run() {
		Socket clientSocket = null;
		try {
		    clientSocket = mServerSocket.accept();
		} catch (IOException e) {
		    System.out.println("Accept failed: " + mPort);
		    System.exit(-1);
		}
	}
}
