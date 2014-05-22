package com.example.estadodelpuente;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by hkadejo on 12-03-13.
 */
public class PushSenseiReceiver extends BroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";
	 
	  @Override
	  public void onReceive(Context context, Intent intent) {
	    try {
	      String action = intent.getAction();
	      String channel = intent.getExtras().getString("com.parse.Channel");
	      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
	 
	      Log.d(TAG, "got action " + action + " on channel " + channel + " with:");
	      Iterator itr = json.keys();
	      while (itr.hasNext()) {
	        String key = (String) itr.next();
	        Log.d(TAG, "..." + key + " => " + json.getString(key));
	      }
	    } catch (JSONException e) {
	      Log.d(TAG, "JSONException: " + e.getMessage());
	    }
	    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
	    
	    
	    Intent launchActivity=  new Intent(context, MainActivity.class);
	    PendingIntent pi= PendingIntent.getActivity(context, 0, launchActivity, 0);
	    
	    long[] vibracion= {0, 500,100, 500};
	    Notification noti= new NotificationCompat.Builder(context)
	    .setContentTitle("Estado del Puente")
	    .setContentText("como estas")
	    .setSmallIcon(R.drawable.ic_launcher)
	    .setLargeIcon(icon)
	    .setContentIntent(pi)
	    .setAutoCancel(true)
	    .setVibrate(vibracion)
	    .build();
	    
	    NotificationManager nm= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	    nm.notify(0, noti);
	    //sysou;
	  }
}