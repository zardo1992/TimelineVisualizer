package zardoni.matteo.AppAndroid.Activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import zardoni.matteo.AppAndroid.RappresentazioneTimeline.TimelineGrafica;
import zardoni.matteo.AppAndroid.RappresentazioneTimeline.TimelineView;
import zardoni.matteo.SerializzazioneTimeline.lightTimelines.PlainTimeLine;
import zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimeLine;
import zardoni.matteo.SerializzazioneTimeline.test.RuntimeTypeAdapterFactory;
import zardoni.matteo.SerializzazioneTimeline.test.TimedValueSerializer;
import zardoni.matteo.SerializzazioneTimeline.timedValue.TimedValueImpl;
import zardoni.matteo.timeline.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TimelineActivity extends Activity 
{
	private TimelineView disegnoTimeline;
	private ArrayList<TimelineGrafica> tl;
	private ArrayList<PlainTimeLine> ptl;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	//	overridePendingTransition(R.anim.abc_slide_in_top,R.anim.abc_slide_out_bottom);
		this.setContentView(R.layout.timeline_view);
		if(MainActivity.righeSelezionate != null)
		{
			ArrayList<String> leggi = MainActivity.righeSelezionate;
			ptl = new ArrayList<PlainTimeLine>();
			tl = new ArrayList<TimelineGrafica>();
			
			for(String tmp : leggi)
				ptl.add(ottieniTimeline(tmp));
	
			for(PlainTimeLine tmp : ptl)
			    	tl.add(new TimelineGrafica(tmp));
			
			disegnoTimeline = new TimelineView(this);	
			disegnoTimeline.setPosizione(20, 20);
			disegnoTimeline.inserisciTimelines(tl);	
		}
	}
	
	
	private PlainTimeLine ottieniTimeline(String filename)
	{
		PlainTimeLine timeline = deserializeJson("Timeline/"+filename, PlainTimeLine.class);	
		return timeline;
	}
	
	@SuppressWarnings("rawtypes")
	public <E> E deserializeJson(String filename, Class<E> class1)
	{
		try 
		{
			GsonBuilder gBuilder = new GsonBuilder();
			
			RuntimeTypeAdapterFactory timelineTypeAdapter = RuntimeTypeAdapterFactory.of(TimeLine.class, "timeline").registerSubtype(PlainTimeLine.class, "plainTimeline");
			gBuilder.registerTypeAdapterFactory(timelineTypeAdapter);
			RuntimeTypeAdapterFactory managerAdapterFactory = RuntimeTypeAdapterFactory
														.of(zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimedValueManager.class, "timedValueManger")
														.registerSubtype(zardoni.matteo.SerializzazioneTimeline.lightTimelines.PlainManager.class, "plainManager")
														.registerSubtype(zardoni.matteo.SerializzazioneTimeline.lightTimelines.TreeMapBasedManager.class, "treeMapBasedManager");
			gBuilder.registerTypeAdapterFactory(managerAdapterFactory);
						
			gBuilder.registerTypeAdapter(TimedValueImpl.class, new TimedValueSerializer());
			
			Gson gson = gBuilder.create();
			return leggiInAndroid(getApplicationContext(),gson,filename,class1);
			
		//	return gson.fromJson((String) Files.readAllLines(Paths.get(filename),Charset.forName("ASCII")).toArray()[0], class1);
		} 
		catch (JsonSyntaxException e) 
		{
			Toast.makeText(this.getApplicationContext(),
					"Errore nel caricamento della timeline!", 
						Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		catch( IOException e)
		{
			Toast.makeText(this.getApplicationContext(),
					"Errore nel caricamento della timeline!", 
						Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		return null;
	}
	
	private <E> E leggiInAndroid(Context c, Gson g, String filename, Class<E> class1) throws IOException
	{
		List<String> json;
		InputStream is = c.getAssets()
							 .open(filename);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "US-ASCII"));
		json = new ArrayList<String>();
		for (;;) 
		{
		  String line = reader.readLine();
		  if (line == null)
		          break;
		   json.add(line);
		}
		reader.close();
		return g.fromJson((String)json.toArray()[0], class1);
	}
	
	public void onBackPressed()
	{
	    new Handler().postDelayed(new Runnable() 
	    {	 
            @Override	
		    public void run() 
		    {
            	Intent i = new Intent(TimelineActivity.this,
            						MainActivity.class);
		   		startActivity(i);
		        finish();
		    }
        }, 1);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_info) 
		{
			AlertDialog.Builder informazioni=new AlertDialog.Builder(this);
			informazioni.setTitle("Informazioni su...");
			informazioni.setMessage(SplashActivity.descrizione);
			informazioni.setIcon(R.drawable.ic_launcher);
			informazioni.setNegativeButton("Chiudi", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(
						DialogInterface arg0,
						int arg1) 
				{
				}
			});
			informazioni.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}

