package com.recursivepenguin.android.dMPDe;

import java.net.Socket;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MPDService extends Service{

	private NotificationManager mNM;
	private ClientListener mClientListener;
	private ClientEventListener mClientEventListener;
	
	@Override
	public void onCreate() {
		mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
        // Display ongoing notification about mpd daemon service
        showNotification();
        
        mClientEventListener = new ClientEventListener() {

			@Override
			boolean doCommand(String command) {
				
				Log.d("EventListener",command);
				return false;
			}
        	
        };
	}
	
	// This is the old onStart method that will be called on the pre-2.0
    // platform.  On 2.0 or later we override onStartCommand() so this
    // method will not be called.
    @Override
    public void onStart(Intent intent, int startId) {
        handleStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleStart(intent, startId);
        return START_NOT_STICKY;
    }
	
    void handleStart(Intent intent, int startId) {
    	mClientListener = new ClientListener(this);
    	mClientListener.start();
    }
    
    void onClientConnect(Socket socket) {
    	Client newClient = new Client(socket);
    	newClient.setEventListener(mClientEventListener);
    	newClient.start();
    }
    
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        CharSequence text = getText(R.string.local_service_started);

        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.icon, text,
                System.currentTimeMillis());

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, dMPDe.class), 0);

        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(this, getText(R.string.local_service_label),
                       text, contentIntent);

        // Send the notification.
        // We use a layout id because it is a unique number.  We use it later to cancel.
        mNM.notify(R.string.local_service_started, notification);
    }
}
