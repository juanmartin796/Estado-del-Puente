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
	private static int cont;

	  @Override
	  public void onReceive(Context context, Intent intent) {
	    
	    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);

	    try {
	    	JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
	    	String estado= json.getString("est");
	    	String descripcion= json.getString("desc");
	    	String hora= json.getString("hora");
	    	
	    	Intent launchActivity=  new Intent(context, MainActivity.class);
	    	launchActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			launchActivity.putExtra("estado", estado);
			launchActivity.putExtra("descripcion", descripcion);
			launchActivity.putExtra("hora", hora);
			PendingIntent pi= PendingIntent.getActivity(context, cont++, launchActivity, 0);
			MainActivity.actualizar(estado, descripcion, hora);
			
			long[] vibracion= {0, 500,100, 500};
			Notification noti= new NotificationCompat.Builder(context)
			.setContentTitle("Estado del Puente: "+estado)
			.setContentText(descripcion)
			.setSmallIcon(R.drawable.ic_launcher)
			.setLargeIcon(icon)
			.setContentIntent(pi)
			.setAutoCancel(true)
			.setVibrate(vibracion)
			.build();
			
			NotificationManager nm= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			nm.notify(0, noti);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}