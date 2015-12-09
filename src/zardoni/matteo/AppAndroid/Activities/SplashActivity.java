package zardoni.matteo.AppAndroid.Activities;

import zardoni.matteo.timeline.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity 
{
	public static String descrizione = "";
	
	
	private void setDescrizione()
	{
		descrizione = ""
				+ "* Timeline Visualizer" + "\n"
				+ "* Stage di Matteo Zardoni" + "\n"
				+ "* Università degli Studi di Milano-Bicocca, Anno 2014" + "\n"
				+ "* Versione 1.0.0710";
	}
	
	/*
		Questo metodo invoca la Splash Screen all'apertura dell'app
		Dopo 2 secondi apre la MainActivity che presenta la lista
		delle timeline che sono visualizzabili
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setDescrizione();
		setContentView(R.layout.splash);
	    new Handler().postDelayed(new Runnable() 
	    {	 
            @Override	
		    public void run() 
		    {
            	Intent i = new Intent(SplashActivity.this,
            						MainActivity.class);
		   		startActivity(i);
		        finish();
		    }
        }, 2000);
	}
}
