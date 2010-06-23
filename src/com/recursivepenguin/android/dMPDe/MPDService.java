package com.recursivepenguin.android.dMPDe;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

public class MPDService extends Service{

	private NotificationManager mNM;
	private AudioManager mAudioManager;
	private ClientListener mClientListener;
	private ClientEventListener mClientEventListener;
	private Command mCommand;
	private com.android.music.IMediaPlaybackService mIMediaPlaybackService;
	
	@Override
	public void onCreate() {
		mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		
		mCommand = new Command(mAudioManager);
		
		/*
		 * Bind to music player service
		 *
		 */
		bindService(new Intent().setClassName("com.android.music", "com.android.music.MediaPlaybackService"), new ServiceConnection() {
			public void onServiceConnected(ComponentName comp, IBinder binder) {
				mCommand.mIMediaPlaybackService = com.android.music.IMediaPlaybackService.Stub.asInterface(binder);
			}

			public void onServiceDisconnected(ComponentName comp) {
				mCommand.mIMediaPlaybackService = null;
			}
		}, 0);
		
        // Display ongoing notification about mpd daemon service
        showNotification();
        
        mClientEventListener = new ClientEventListener() {

			@Override
			String doCommand(String command) {
				String result = "";
				
				if (command != null) {
					Log.d("EventListener",command);
					if (command.contains("status")) {
						result += mCommand.status();
					}
					else if (command.contains("commands")) {
						if (!command.contains("notcommands"))
							result += mCommand.commands();
					}
					else if (command.contains("outputs")) {
						result += mCommand.outputs();
					} else if (command.contains("tagtypes")) {
						result += mCommand.tagtypes();
					}
					result += "OK\n";
				}
				else {
					result = "ACK\n";
				}
				
				return result;
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
    	newClient.sendCommand("OK MPD 0.12.2\n");
    	newClient.start();
    }
    
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                    return inetAddress.getHostAddress().toString();
	                }
	            }
	        }
	    } catch (SocketException ex) {
	        Log.e("", ex.toString());
	    }
	    return null;
	}
	
	/**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        CharSequence text = getLocalIpAddress();

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
