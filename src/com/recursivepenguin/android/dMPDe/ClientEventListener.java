package com.recursivepenguin.android.dMPDe;

import java.util.EventListener;

public abstract class ClientEventListener implements EventListener {

	abstract String doCommand(String command);
	
}
