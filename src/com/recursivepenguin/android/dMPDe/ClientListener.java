package com.recursivepenguin.android.dMPDe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class ClientListener extends Thread{

	private ServerSocket mServerSocket;
	private int mPort;
	
	private MPDService mParent;
	
	public ClientListener (MPDService parent) {
		this(parent, 6600);
	}
	
	public ClientListener (MPDService parent, int port) {
		mPort = port;
		mParent = parent;
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
		    Log.d("Listener", clientSocket.toString());
		    mParent.onClientConnect(clientSocket);
		} catch (IOException e) {
		    System.out.println("Accept failed: " + mPort);
		    System.exit(-1);
		}
	}
}
