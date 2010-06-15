package com.recursivepenguin.android.dMPDe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{
	
	private Socket mSocket;
	private PrintWriter mSocketOut = null;
    private BufferedReader mSocketIn = null;
    private ClientEventListener mListener;
    
	public Client(Socket socket) {
		
		mSocket = socket;
		try {
			mSocketOut = new PrintWriter(mSocket.getOutputStream(), true);
			mSocketIn = new BufferedReader(new InputStreamReader(
			        mSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		//Display info about this particular thread
		System.out.println(Thread.currentThread().getName());
	}
	
	private String getCommand() {
		try {
			return  mSocketIn.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public void setEventListener(ClientEventListener mClientEventListener) {
		mListener = mClientEventListener;
	}
}
