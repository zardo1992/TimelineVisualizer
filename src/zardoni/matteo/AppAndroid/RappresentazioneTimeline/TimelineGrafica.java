package zardoni.matteo.AppAndroid.RappresentazioneTimeline;

import java.util.ArrayList;
import android.graphics.Color;
import zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimeLine;
import zardoni.matteo.SerializzazioneTimeline.timedValue.TimedValueImpl;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class TimelineGrafica 
{
	private int distanzaIntervalli;
	private int spessoreTimeline;
	private int color;
	private int numeroFatti;
	private int durata;
	private ArrayList<Fatto> fatti;
	private int ultimoGrain;
	private TimeLine timelineOriginale;
	
	public TimelineGrafica(TimeLine tl)
	{
		this(tl,30,Color.BLACK);
	}
	
	public TimelineGrafica(TimeLine tl, int distanza)
	{
		this(tl,distanza,Color.BLACK);
	}
	
	public String getNome()
	{
		return getTimelineOriginale().getName();
	}
	
	public TimelineGrafica(TimeLine tl, int distanza, int color)
	{		
		this.timelineOriginale = tl;
		this.color = color;
		this.distanzaIntervalli = distanza;
		this.spessoreTimeline = 20;
		
		durata = 0;
		numeroFatti = 0;
		
		ArrayList<TimedValueImpl<? extends AbstractValue>> tmp = tl.getAllTimeds();
		fatti = new ArrayList<Fatto>();
		Fatto fattoTrovato = null;
		for(TimedValueImpl<? extends AbstractValue> f : tmp)
		{
			fattoTrovato = new Fatto(tl.getName(),f);
			durata = (int)fattoTrovato.getFine();
			fatti.add(fattoTrovato);
			numeroFatti++;
		}
		this.ultimoGrain = (int)fattoTrovato.getFine();
	}
	
	public TimeLine getTimelineOriginale()
	{
		return this.timelineOriginale;
	}
	
	public int getUltimoGrain()
	{
		return this.ultimoGrain;
	}
	
	public ArrayList<Fatto> getFatti()
	{
		return this.fatti;
	}
	
	public int getTotaleDurata()
	{
		return durata;
	}

	public int getDistanzaIntervalli() 
	{
		return distanzaIntervalli;
	}
	
	public void setDistanzaIntervalli(int value) 
	{
		distanzaIntervalli = value;
	}

	public int getSpessoreTimeline() 
	{
		return spessoreTimeline;
	}

	public int getColor() 
	{
		return color;
	}
	
	public void setColor(int color)
	{
		this.color = color;
	}

	public int getNumeroFatti() 
	{
		return numeroFatti;
	}
}
