package com.example.estadodelpuente;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	static TextView tv_desc;
	static TextView tv_estado;
	static TextView tv_hora;
	static ImageView img_puente;
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*Parse.initialize(this, "StygvK9vKwwa3QMIbEDwCJxR0isB3f8P4Kw55Wkn", 
		        "bZpy2jdTpfm29guyn1uj4iDKe0vU0LCWSUgyTP4S");
		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();*/
		tv_desc= (TextView) findViewById(R.id.tv_descEstPuente);
		tv_estado= (TextView) findViewById(R.id.tv_estado);
		tv_hora= (TextView) findViewById(R.id.tv_hora);
		img_puente= (ImageView) findViewById(R.id.img_puente);
		
		
		Bundle extra= getIntent().getExtras();
		if (extra!=null){
			String estado= extra.getString("estado");
			String desc= extra.getString("descripcion");
			String hora= extra.getString("hora");
			actualizar(estado, desc, hora);		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static void actualizar(String estado, String descripcion, String hora){

		tv_estado.setText(estado);
		tv_desc.setText(descripcion);
		tv_hora.setText(hora);
		if (estado.equals("Normal")|| estado.equals("normal") ){
			img_puente.setImageResource(R.drawable.normal);
		} else if (estado.equals("Cortado")|| estado.equals("cortado")){
			img_puente.setImageResource(R.drawable.manifestacion);
		} else if (estado.equals("Demoras")|| estado.equals("demoras")||estado.equals("Demora")|| estado.equals("demora")){
			img_puente.setImageResource(R.drawable.demora);
		} else if(estado.equals("Trabajos")|| estado.equals("trabajos")||estado.equals("Trabajo")|| estado.equals("trabajo")){
			img_puente.setImageResource(R.drawable.trabajando);
		} else if (estado.equals("Accidente")|| estado.equals("accidente")||estado.equals("Accidentes")|| estado.equals("accidentes")){
			img_puente.setImageResource(R.drawable.accidente);
		}
	}

}