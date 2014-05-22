package com.example.estadodelpuente;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.Parse;
import com.parse.ParseBroadcastReceiver;
import com.parse.ParseInstallation;
import com.parse.PushService;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Parse.initialize(this, "StygvK9vKwwa3QMIbEDwCJxR0isB3f8P4Kw55Wkn", 
		        "bZpy2jdTpfm29guyn1uj4iDKe0vU0LCWSUgyTP4S"); 
		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		    		    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
