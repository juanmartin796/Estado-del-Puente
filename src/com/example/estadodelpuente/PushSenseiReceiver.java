package com.example.estadodelpuente;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by hkadejo on 12-03-13.
 */
public class PushSenseiReceiver extends BroadcastReceiver {
	//private static final String TAG = "MyCustomReceiver";
	private static int cont;

	  @Override
	  public void onReceive(Context context, Intent intent) {
	    Log.e("Martin", "anda la notificacion 1");
	    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
	    Log.e("Martin", "anda la notificacion 2");

	    try {
	    	JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
		    Log.e("Martin", "anda la notificacion 3");

	    	String estado= json.getString("est");
	    	String descripcion= json.getString("desc");
	    	String hora= json.getString("hora");
		    Log.e("Martin", "anda la notificacion 4");

	    	
	    	Intent launchActivity=  new Intent(context, MainActivity.class);
	    	Log.e("Martin", "anda la notificacion 5");
	    	launchActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			launchActivity.putExtra("estado", estado);
			launchActivity.putExtra("descripcion", descripcion);
			launchActivity.putExtra("hora", hora);
			Log.e("Martin", "anda la notificacion 6");
			PendingIntent pi= PendingIntent.getActivity(context, cont++, launchActivity, 0);
			Log.e("Martin", "anda la notificacion 7");
			
			Log.e("Martin", "anda la notificacion 8");
			
			long[] vibracion= {0, 300,50, 300};
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
			Log.e("Martin", "anda la notificacion 9");
			try {
				MainActivity.actualizar(estado, descripcion, hora);	//Se captura el error, para no forzar al cierre de la aplicacion,
			} catch (Exception e){									// ya que cuando se enciende el celular, no hay todavia ninguna instancia de MainActivity.class
				Log.e("Martin", "Entro al catch");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}