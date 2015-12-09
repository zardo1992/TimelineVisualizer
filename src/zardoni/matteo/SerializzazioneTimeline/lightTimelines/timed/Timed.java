package zardoni.matteo.SerializzazioneTimeline.lightTimelines.timed;
import java.io.Serializable;

import zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimeInterval;


public interface Timed extends Serializable{

	public void setTimeInterval(TimeInterval interval);
	
	public TimeInterval getTimeInterval();
	
	public int compareTimeInterval(Timed timed);
}
