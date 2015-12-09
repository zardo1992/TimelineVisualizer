package zardoni.matteo.AppAndroid.RappresentazioneTimeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Random;

import zardoni.matteo.AppAndroid.Altro.FattiLivelliColori;
import zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimeInterval;
import zardoni.matteo.timeline.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TimelineViewDipendenza extends View
{
	public static final int SPAZIO_PER_FATTI_SOPRA = 120;
	public static final int SPAZIO_PER_FATTI_SOTTO = SPAZIO_PER_FATTI_SOPRA /2;
	public static final int DISTANZA_FRA_TIMELINE = 30;
	public static final int SPESSORE_FATTI = 10;
	public static final int DISTANZA_FRA_FATTI = 15;
	
	public static ArrayList<TimelineGrafica> listaTl;
	public static ArrayList<TimelineGrafica> store;
	public static int x;
	public static int y;
	public static int ySalvata;
	public static int widthMax;
	public static int heightMax;
	public static boolean giaDisegnato;
	public static Map<Fatto,Integer> fattiLivelli;
	public static Map<Fatto,Integer> fattiColori;
	
	private int xAngoloDestroGiu;
	private int yAngoloDestroGiu;
	private boolean inseritaLaPrima;
	private Paint p;
	private int r;
	private int g;
	private int b;	
	private Map<Fatto,Coordinate> fattiCoord;
	private boolean checkDipendenza;

	
	public TimelineViewDipendenza(Context context)
	{
		super(context);
		init();		
	}
		
	public TimelineViewDipendenza(Context context, AttributeSet attrs)
	{
		super(context,attrs);
		init(); 
	}
	
	public TimelineViewDipendenza(Context context, AttributeSet attrs, int defStyle)
	{
        super(context, attrs, defStyle);
        init();
    }
	
	private void init()
	{
		this.setDrawingCacheEnabled(true);
		this.p = new Paint();
		this.xAngoloDestroGiu = 0;
		this.yAngoloDestroGiu = 0;
		this.fattiCoord = new LinkedHashMap<Fatto,Coordinate>();
		this.checkDipendenza = true;
		TimelineViewDipendenza.x  = 0;
		TimelineViewDipendenza.y = 0;
		TimelineViewDipendenza.ySalvata = 0;
		TimelineViewDipendenza.widthMax = 0;
		TimelineViewDipendenza.heightMax = 0;
		TimelineViewDipendenza.giaDisegnato = false;
	}
	
	public void setPosizione(int x, int y)
	{
		TimelineViewDipendenza.x = x;
		TimelineViewDipendenza.y = y;
		TimelineViewDipendenza.ySalvata = y;
	}
	
	public void inserisciTimelines(ArrayList<TimelineGrafica> tls)
	{
		store = new ArrayList<TimelineGrafica>(tls);
		inseritaLaPrima = false;
	}
	
	private void disegnaPunta(Canvas c, Paint p, TimelineGrafica t)
	{		
		int[] x = new int[3];
		int[] y = new int[3];
		
		x[0] = t.getUltimoGrain() * t.getDistanzaIntervalli() 
			   + TimelineViewDipendenza.x + 2 * t.getDistanzaIntervalli();
		y[0] = TimelineViewDipendenza.y + SPAZIO_PER_FATTI_SOTTO;
		
		//Punta del triangolo
		x[1] =  t.getUltimoGrain() * t.getDistanzaIntervalli() + 40 + TimelineViewDipendenza.x + 2 * t.getDistanzaIntervalli();
		y[1] = TimelineViewDipendenza.y + (t.getSpessoreTimeline()/2) + SPAZIO_PER_FATTI_SOPRA;
		
		x[2] = t.getUltimoGrain() * t.getDistanzaIntervalli() + TimelineViewDipendenza.x+ 2 * t.getDistanzaIntervalli();
		y[2] = TimelineViewDipendenza.y + t.getSpessoreTimeline() + SPAZIO_PER_FATTI_SOTTO  + SPAZIO_PER_FATTI_SOPRA;
					
		c.drawLine(x[0], y[0], x[1], y[1], p);
		c.drawLine(x[1], y[1], x[2], y[2], p);
		c.drawLine(x[0], y[0], x[2], y[2], p);
		
		this.xAngoloDestroGiu = x[1];
		this.yAngoloDestroGiu = y[2];
		
		if(this.xAngoloDestroGiu > TimelineViewDipendenza.widthMax)
			TimelineViewDipendenza.widthMax = this.xAngoloDestroGiu;
		if(this.yAngoloDestroGiu > TimelineViewDipendenza.heightMax)
			TimelineViewDipendenza.heightMax = this.yAngoloDestroGiu;
	}
	
	private void disegnaRettangolo(Canvas c, Paint p, TimelineGrafica t)
	{
		//Ricavo la lunghezza del rettangolo che devo disegnare
		int durataMassimaTimeline = t.getDistanzaIntervalli()*t.getUltimoGrain();
		//drawRect() supporta massimo disegni 2048/2048: suddivido in più rettangoli
		int quantiRettangolini = durataMassimaTimeline / 300;
		//e disegno lo scarto che rimane
		int scarto = durataMassimaTimeline - (quantiRettangolini * 300);
		//aggiungendo i due grain "invisibili" per distanziare la punta
		scarto += 2 * t.getDistanzaIntervalli();
		int incremento = 300;
		int X = x;
		for(int j=0; j<t.getSpessoreTimeline();j++)
		{
			for(int i=0;i<quantiRettangolini; i++)
			{
				c.drawLine(	X,
							y+j+SPAZIO_PER_FATTI_SOPRA,
							incremento,
							y+j+SPAZIO_PER_FATTI_SOPRA, 
							p);
				X = incremento;
				incremento += 300; 
			}
			if(scarto > 0)
				c.drawLine(X,
							y+j+SPAZIO_PER_FATTI_SOPRA,
							scarto+X+x, 
							y+j+SPAZIO_PER_FATTI_SOPRA,
							p);
			incremento = 300;
			X = x;
		}
	}
	
	private void disegnaGrain(Canvas c, Paint p, TimelineGrafica t)
	{
		for(int i=0, cX = x; i<=t.getUltimoGrain(); 
				i++, cX += t.getDistanzaIntervalli())
		{
			c.drawLine(cX,y, cX,
						y+ SPAZIO_PER_FATTI_SOPRA
						+ t.getSpessoreTimeline() + SPAZIO_PER_FATTI_SOTTO,
						p);
			if(i%2==0)
				c.drawText(""+i, (int)cX-4,(int)y + SPAZIO_PER_FATTI_SOPRA
								  + t.getSpessoreTimeline() + 
								  SPAZIO_PER_FATTI_SOTTO + 10, p);
		}
	}	
	
	private void disegnaFatti(Canvas c, Paint p, TimelineGrafica t)
	{
		fattiLivelli = associaFattiLivelliColori(t).getFattiLivelli();
		fattiColori = associaFattiLivelliColori(t).getFattiColori();
		Iterator<?> i1 = fattiLivelli.entrySet().iterator();
		Iterator<?> i2 = fattiLivelli.entrySet().iterator();
		int yFatto = y;
		while(i1.hasNext() && i2.hasNext())
		{
			@SuppressWarnings("unchecked")
			Entry<Fatto, Integer> elemento = (Entry<Fatto, Integer>) i1.next();
			Fatto f = (Fatto) elemento.getKey();
			int livello = (Integer)elemento.getValue();
			p.setColor(fattiColori.get(f));
			for(int conta=0; conta<SPESSORE_FATTI; conta++)
		 	{
				if(livello == 0)
				{
				 	c.drawLine((int)f.getInizio()*t.getDistanzaIntervalli()+x-t.getDistanzaIntervalli()/2,
				 				   yFatto+conta,
				 				   (int)f.getFine()*t.getDistanzaIntervalli()+x+t.getDistanzaIntervalli()/2,
				 				   yFatto+conta, p);
				 	if(conta == SPESSORE_FATTI - 1)
				 	{
				 		Coordinate punto = new Coordinate(
				 				(int)f.getInizio()*t.getDistanzaIntervalli()+x-t.getDistanzaIntervalli()/2,
				 				yFatto,
				 				(int)f.getFine()*t.getDistanzaIntervalli()+x+t.getDistanzaIntervalli()/2,
				 				 yFatto+conta);
				 		fattiCoord.put(f, punto);
				 	}
				}
				else
				{
			 		c.drawLine((int)f.getInizio()*t.getDistanzaIntervalli()+x,
			 				   yFatto+conta+(DISTANZA_FRA_FATTI * livello),
			 				   (int)f.getFine()*t.getDistanzaIntervalli()+x,
			 				   yFatto+conta+(DISTANZA_FRA_FATTI * livello), p);
			 		if(conta == SPESSORE_FATTI - 1)
			 		{
			 			Coordinate punto = new Coordinate(
			 					(int)f.getInizio()*t.getDistanzaIntervalli()+x,
			 					 yFatto+(DISTANZA_FRA_FATTI * livello),
			 					(int)f.getFine()*t.getDistanzaIntervalli()+x,
			 					 yFatto+conta+(DISTANZA_FRA_FATTI * livello));
			 			fattiCoord.put(f, punto);
			 		}
				}
		 	}				
		}	
	}

	private FattiLivelliColori associaFattiLivelliColori(TimelineGrafica t)
	{
		int numeroLivelli = 0;	
		ArrayList<Fatto> fatti = t.getFatti();
		//Associo a ogni fatto il livello secondo il quale viene disegnato
		Map<Fatto,Integer> fattiLivelli = new LinkedHashMap<Fatto, Integer>();
		//Associo un colore a ciascun fatto
		Map<Fatto,Integer> fattiColori = new LinkedHashMap<Fatto, Integer>();
		Fatto fPrecedente = fatti.get(0);
		fattiLivelli.put(fPrecedente, 0);
		boolean primoFatto = true;
		int max = 0;
		for(Fatto f: fatti)
		{
			Random rand = new Random();
			do
			{
				r = rand.nextInt();
				g = rand.nextInt();
				b = rand.nextInt();
			}
			while(g < 200 && r > 150);
			if(singolo(f))
			{
				fattiLivelli.put(f, 0);
				fattiColori.put(f, Color.rgb(r, g, b));
			}
			else
			{
				if(!primoFatto && inMezzo(f,(int) fPrecedente.getFine()))
				{
					numeroLivelli++;
					//Stesso livello, ma si sovrascrive
					//Cerco se sullo stesso livello c'è qualcosa che può essere sovrascritto
					Object[] facts = getKeysByValue(fattiLivelli,numeroLivelli).toArray();
					int conta = 0;
					while(conta < facts.length)
					{
						Fatto tm = (Fatto)facts[conta];
						if(f.getInizio() < tm.getFine())
						{
							numeroLivelli++;
							facts = getKeysByValue(fattiLivelli,numeroLivelli).toArray();
							conta = 0;
						}
						else
							conta++;
					}
				}
				else
				{
					if (max < numeroLivelli)
						max = numeroLivelli;
					fPrecedente = f;
					numeroLivelli = 1;
					Object[] facts = getKeysByValue(fattiLivelli,numeroLivelli).toArray();
					int conta = 0;
					while(conta < facts.length)
					{
						Fatto tm = (Fatto)facts[conta];
						if(f.getInizio() < tm.getFine())
						{
							numeroLivelli++;
							facts = getKeysByValue(fattiLivelli,numeroLivelli).toArray();
							conta = 0;
						}
						else
							conta++;
					}

				}
				fattiLivelli.put(f, numeroLivelli);
				fattiColori.put(f, Color.rgb(r, g, b));					
			}
			primoFatto = false;
		}
		return new FattiLivelliColori(fattiLivelli,fattiColori);
	}
	
	private <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) 
	{
	    Set<T> keys = new HashSet<T>();
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
	    return keys;
	}
	
	private boolean singolo(Fatto f)
	{
		if(f.getInizio() == f.getFine())
			return true;
		else 
			return false;
	}
	
	private boolean inMezzo(Fatto f, int finePrec)
	{
		if(f.getInizio() < finePrec)
			return true;
		else
			return false;
	}	

	private ArrayList<TimelineGrafica> setDipendenze(TimelineGrafica t)
	{
		ArrayList<TimelineGrafica> daDisegnare = new ArrayList<TimelineGrafica>();
		if(isDipendente(t))
		{
			TimelineGrafica tmp = t;
			while(isDipendente(tmp))
			{
				TimelineGrafica tmpRef = new TimelineGrafica(tmp.getTimelineOriginale().getReferenceTimeLine());
				if(checkDipendenza)
				{
					int moltiplicatore = (int)tmpRef.getTimelineOriginale().getReferenceInterval(new TimeInterval(1,2)).getBegin();
					tmp.setDistanzaIntervalli(tmp.getDistanzaIntervalli() * moltiplicatore);
					checkDipendenza = false;
				}
				if(listaTl.contains(tmp))
					listaTl.remove(tmp);
				for(int i=0; i<listaTl.size(); i++)
					if(listaTl.get(i).getNome().equals(tmpRef.getNome()))
						listaTl.remove(listaTl.get(i));
				daDisegnare.add(tmp);
				daDisegnare.add(tmpRef);
				tmp = tmpRef;
			}
		}
		else
			if(!daDisegnare.contains(t))
			{
				daDisegnare.add(t);
				listaTl.remove(t);
			}
		Collections.reverse(daDisegnare);
		return daDisegnare;
	}
	
	//Devo capire qual è la timeline con più dipendenze, così riesco ad ottenere una migliore gerarchia
	private TimelineGrafica timelineMaxDipendenze()
	{
		int max = 0;
		int vecchioMax = 0;
		boolean primoCiclo = true;
		TimelineGrafica tlMax =  null;
		TimelineGrafica thisTl = null;
		for(TimelineGrafica t : listaTl)
		{
			thisTl = t;
			if(max == 0)
				tlMax = t;
			while(isDipendente(t))
			{
				max++;
				t = new TimelineGrafica(t.getTimelineOriginale().getReferenceTimeLine());
			}
			if(primoCiclo)
			{
				vecchioMax = max;
				primoCiclo = false;
			}
			else
				if(max > vecchioMax)
				{
					vecchioMax = max;
					tlMax = thisTl;
				}
		}
		return tlMax;
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas)
	{
		canvas.setDensity(Bitmap.DENSITY_NONE);
		super.onDraw(canvas);
		listaTl = new ArrayList<TimelineGrafica>(store);
		if(!listaTl.isEmpty())
		{
			canvas.drawColor(Color.WHITE);		
			p.setStyle(Paint.Style.FILL_AND_STROKE); 
			p.setAntiAlias(true);	
			p.setStrokeWidth(1);
			while(!listaTl.isEmpty())
			{
				ArrayList<TimelineGrafica> tls = setDipendenze(timelineMaxDipendenze());
				for(TimelineGrafica t : tls)
				{
					if(inseritaLaPrima)
					{
						 y = ySalvata + this.yAngoloDestroGiu + DISTANZA_FRA_TIMELINE;
						 Paint pTmp = new Paint();
						 pTmp.setStrokeWidth(2);
						 pTmp.setColor(Color.LTGRAY);
						 for(int i=0, cX = x; i<=t.getUltimoGrain(); 
									i++, cX += t.getDistanzaIntervalli())
								canvas.drawLine(cX,this.yAngoloDestroGiu, cX,
										y + DISTANZA_FRA_TIMELINE,
										pTmp);
					}
					p.setColor(t.getColor());
					disegnaPunta(canvas,p,t);
					disegnaRettangolo(canvas,p,t);
					disegnaGrain(canvas,p,t);
					disegnaFatti(canvas,p,t);
					inseritaLaPrima = true;
				}
				y = ySalvata;
				inseritaLaPrima = false;
			}
		}
		requestLayout();
		giaDisegnato = true;
	}
	
	private boolean isDipendente(TimelineGrafica t)
	{
		if(t.getTimelineOriginale().getReferenceTimeLine().getName().equals("groundTimeline"))
			return false;
		return true;
	}	
		
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
    {
    	int pezzettinoWidth = 0; 
    	int pezzettinoHeight = 0;
    	
    	super.onMeasure(widthMeasureSpec+pezzettinoWidth+20,
    					heightMeasureSpec+pezzettinoHeight+20);
    	
    	//Se la dimensione in ampiezza della View supera quella di default
    	if(widthMax > widthMeasureSpec)
    		//Calcolo il pezzettino da aggiungere per settare la nuova dimensione
    		pezzettinoWidth = widthMax - widthMeasureSpec;

    	//Se la dimensione in altezza della View supera quella di default
    	if(heightMax > heightMeasureSpec)
    		//Calcolo il pezzettino da aggiungere per settare la nuova dimensione
    		pezzettinoHeight = heightMax - heightMeasureSpec;
    	
    	if(widthMax > 0 && heightMax > 0)
    	{
    		setMeasuredDimension(widthMeasureSpec+pezzettinoWidth+20,
    							 heightMeasureSpec+pezzettinoHeight+20);
    	}
		//Setto la nuova dimensione, impostando quella di default, in aggiunta a...
    	//i nuovi pezzettini che "sforano" la view (pezzettinoWidth, pezzettinoHeight)
    	//20, per settare la distanza dal margine		
    }
		
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@SuppressWarnings("unchecked")
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		  MediaPlayer mp = MediaPlayer.create(getContext(),R.raw.misc224);
	      switch (event.getAction())
	      {
	        case MotionEvent.ACTION_DOWN:
	        {
	    		Iterator<?> i1 = fattiCoord.entrySet().iterator();
				boolean trovato = false;
				while(i1.hasNext() && !trovato)
				{
					Entry<Fatto, Coordinate> elemento = (Entry<Fatto, Coordinate>) i1.next();
					Fatto f = (Fatto) elemento.getKey();
					Coordinate punto = (Coordinate)elemento.getValue();
					int toccoX = (int)event.getX();
					int toccoY = (int)event.getY();						
					if(toccoX >= punto.getX1() && toccoX <= punto.getX2())
						if(toccoY >= punto.getY1() && toccoY <= punto.getY2())
						{
							mp.setVolume(1,1);
							mp.start();
							Toast.makeText(getContext(), f.toString()
					        		, Toast.LENGTH_LONG).show();
							trovato = true;
						}
				}
	        }
	        break;
	        case MotionEvent.ACTION_UP:
	        {
	        	mp.stop();
	        }
	        break;
	      }
	      return true;
	}
	
	
	private class Coordinate
	{
		private int x1;
		private int y1;
		private int x2;
		private int y2;
			
		public Coordinate(int x1, int y1, int x2, int y2)
		{
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		
		public int getX1()
		{
			return x1;
		}
		
		public int getY1()
		{
			return y1;
		}
		public int getX2()
		{
			return x2;
		}
		
		public int getY2()
		{
			return y2;
		}
	}
}

