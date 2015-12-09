package zardoni.matteo.AppAndroid.Activities;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.IOException;
import java.util.List;
import zardoni.matteo.AppAndroid.ListaScrollableTimeline.ArrayAdapterLivelli;
import zardoni.matteo.AppAndroid.ListaScrollableTimeline.TwoStringFields;
import zardoni.matteo.timeline.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class MainActivity extends Activity
	implements View.OnClickListener, OnItemClickListener, OnCheckedChangeListener,
			   Runnable
{
	private ListView lista;
	private Button OK;
	private boolean[] checkedItems;
	private List<TwoStringFields> list;
	private CheckBox[] checkBoxes;
	private CheckBox dipendenze;
	
	public static ArrayList<String> righeSelezionate;
	public static boolean timelineDip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		MainActivity.righeSelezionate = null;
		MainActivity.timelineDip = false;
	//	overridePendingTransition(R.anim.abc_slide_in_bottom, R..abc_slide_out_top);
		setContentView(R.layout.activity_main);
		createListView();	
		createButton();
	}
	
	
	private void createListView()
	{
		lista = (ListView)findViewById(R.id.listView1);
        list = new LinkedList<TwoStringFields>();
		String[] s;
		try 
		{
			s = this.getApplicationContext()
				.getAssets().list("Timeline");
			for(String ss : s)
			{
				list.add(new TwoStringFields(ss,""));
			}
				
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
        checkedItems = new boolean[list.size()];
        checkBoxes = new CheckBox[list.size()];
        for(int i=0; i<checkedItems.length; i++)
        {
        	checkedItems[i] = false;
        	checkBoxes[i] = (CheckBox)findViewById(R.id.titoloRiga);
        }
        lista.setOnItemClickListener(this);
        ArrayAdapterLivelli adapter =
                new ArrayAdapterLivelli(this, R.layout.rigalista,list,checkedItems);
        lista.setAdapter(adapter);
        lista.setFastScrollEnabled(true);
        
    	/*	ArrayList<String> parole = this.leggiRigheTestoDaFile("parole.txt");
		Iterator<String> it = parole.iterator();    
        list = new LinkedList<TwoStringFields>();
        int contatore = 1;
        while(it.hasNext())
        {
        	String tmp = it.next();
        	list.add(new TwoStringFields(
        			tmp,"Questa è la parola numero "+contatore));
        	contatore++;
        }*/
	}
	
	private void createButton()
	{
		OK = (Button)findViewById(R.id.OK);
		OK.setOnClickListener(this);
		dipendenze = (CheckBox)findViewById(R.id.checkBoxN);
		dipendenze.setOnCheckedChangeListener(this);
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
	
	@Override
	public void onClick(View v) 
	{
		if(v.equals(OK))
		{
			//String tmp = "Hai selezionato:\n";
			boolean checkedAlmenoUno = false;
			righeSelezionate = new ArrayList<String>();
			for(int i=0; i<checkedItems.length; i++)
				if(checkedItems[i])
				{
					checkedAlmenoUno = true;
					//tmp = tmp + list.get(i).getTitle() + "\n";
					righeSelezionate.add(list.get(i).getTitle());
				}
			if(checkedAlmenoUno)
				loadingDialog();
			else
				Toast.makeText(this, "Errore!\n"
						+ "Devi selezionare almeno un elemento!", Toast.LENGTH_SHORT).show();
		}		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		int posizioneVoluta = position;
		int primaPosizioneDisegnata = lista.getFirstVisiblePosition()
								- lista.getHeaderViewsCount(); 
		int posizione = posizioneVoluta - primaPosizioneDisegnata;
		checkBoxes[position] = (CheckBox)parent
								.getChildAt(posizione)
								.findViewById(R.id.titoloRiga);
		if(checkedItems[position])
		{
			checkedItems[position] = false;
			checkBoxes[position].setChecked(false);
		}
		else 
		{
			checkedItems[position] = true;
			checkBoxes[position].setChecked(true);		
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
	{	
		if(dipendenze.isChecked())
			MainActivity.timelineDip = true;
		else
			MainActivity.timelineDip = false;
	
	}
	
	private void loadingDialog()
	{
    	ProgressDialog progress = new ProgressDialog(this);
    	progress.setTitle("Disegno timeline in corso");
    	progress.setMessage("Attendere!\n"
    			+ "Sto disegnando le timeline...");
    	new MyTask(progress).execute();
	}
	
	public void onBackPressed()
	{
		AlertDialog.Builder vuoiChiudere=new AlertDialog.Builder(this);
		vuoiChiudere.setTitle("Conferma chiusura applicazione");
		vuoiChiudere.setMessage("Sei sicuro di voler chiudere l'applicazione?");
		vuoiChiudere.setPositiveButton("Conferma", new DialogInterface.OnClickListener()
													{
														@Override
														public void onClick(
																DialogInterface arg0,
																int arg1) 
														{
															Intent i = new Intent(MainActivity.this, 
																				FinishActivity.class);
															i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
															startActivity(i);
															finish();
														}
													});
		vuoiChiudere.setNegativeButton("Annulla", new DialogInterface.OnClickListener()
													{
														@Override
														public void onClick(
																DialogInterface arg0,
																int arg1) 
														{
														}
													});
		vuoiChiudere.setCancelable(false);
		vuoiChiudere.show();
	}

	@Override
	public void run() 
	{
        try
        {
        }
        catch (Exception e) 
        {
        }
        finally
        {
        }
	}
	
	private class MyTask extends AsyncTask<Void, Void, Void> 
	{
		ProgressDialog progress;
		  public MyTask(ProgressDialog progress) 
		  {
		    this.progress = progress;
		  }

		  public void onPreExecute() 
		  {
		    progress.show();
		  }

		  public void onPostExecute(Void unused) 
		  {
		    progress.dismiss();
            finish();
		  }

		@Override
		protected Void doInBackground(Void... arg0) 
		{
            if(!MainActivity.timelineDip)
            	startActivity(new Intent(MainActivity.this, TimelineActivity.class));
            else
            	startActivity(new Intent(MainActivity.this, TimelineDipendentiActivity.class));
			return null;
		}
	}
	
	/*	private ArrayList<String> leggiRigheTestoDaFile(String nomeFile)
	{
		try
		{
			ArrayList<String> tmp = new ArrayList<String>();
			InputStream is = 
					this.getApplicationContext()
						.getAssets()
						.open(nomeFile);
			BufferedReader reader = 
					new BufferedReader(new InputStreamReader(is, "US-ASCII"));
			String s;
			do
			{
				s = reader.readLine();
				if(s != null)
					tmp.add(s);
			}
			while(s != null);
			reader.close();		
			return tmp;
		}
		catch(Exception e)
		{
			Toast.makeText(this, "Error! "+
					e.getMessage(), Toast.LENGTH_LONG).show();
			return null;
		}
	}	*/	
}
