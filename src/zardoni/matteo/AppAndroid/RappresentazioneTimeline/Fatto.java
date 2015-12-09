package zardoni.matteo.AppAndroid.RappresentazioneTimeline;

import zardoni.matteo.SerializzazioneTimeline.timedValue.TimedValueImpl;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class Fatto 
{
	TimedValueImpl<? extends AbstractValue> fatto;
	private String timelineName;
	
	public Fatto(String timeline, TimedValueImpl<? extends AbstractValue> fatto)
	{
		this.fatto = fatto;
		this.timelineName = timeline;
	}
	
	public long getInizio()
	{
		return fatto.getTimeInterval().getBegin();
	}
	
	public long getFine()
	{
		return fatto.getTimeInterval().getEnd();
	}

	public String toString()
	{
		String tmp = "";		
		tmp = "* Nome timeline: '"+arrivoDaTimeline() + "'\n"
				+ "* Nome Fatto: "+fatto.toString() + 
			  "\n* Valore Fatto: "+fatto.getValue().toString() + 
			  "\n(Inizio, Fine) = ("+getInizio()+","+getFine() + 
			  ")\nDurata: "+getDurata();
		return tmp;
	}
	
	public String arrivoDaTimeline()
	{
		return timelineName;
	}
	
	public long getDurata()
	{
		return getFine() - getInizio();
	}
}
