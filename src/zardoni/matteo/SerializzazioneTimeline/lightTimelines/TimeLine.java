package zardoni.matteo.SerializzazioneTimeline.lightTimelines;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import zardoni.matteo.SerializzazioneTimeline.timedValue.TimedValueImpl;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;


/**
 * Class modeling a timeline.
 * 
 * @author Software Architecture Laboratory - D.I.S.Co. - University of Milano-Bicocca
 * 
 */
public abstract class TimeLine implements Serializable {

	/**
	 * ID for serialization
	 */
	private static final long serialVersionUID = 2051961490465202394L;

	/**
	 * Reference timeline
	 */
	protected TimeLine referenceTimeLine;

	
	/**
	 * Map between the grains of the timeline and the grains of the reference timeline
	 */
	private TreeMap<Long, Long> grainMap;
	
	
	/**
	 * The list of timed facts to be added to the timeline.
	 * This is required for timelines with clock, where all the
	 * modifications to timed facts must be actually performed at the end of a grain.
	 */
	private ArrayList<TimedValueImpl<? extends AbstractValue>> TimedsToBeAdded;
	
	/**
	 * The list of timed facts to be removed from the timeline.
	 * This is required for timelines with clock, where all the
	 * modifications to timed facts must be actually performed at the end of a grain.
	 */
	private ArrayList<TimedValueImpl<? extends AbstractValue>> TimedsToBeRemoved;
	
	/**
	 * The set of timed facts on this timeline.
	 */
	protected ArrayList<TimedValueImpl<? extends AbstractValue>> Timeds;
	
	
	/**
	 * The Manager that handles actual storage and management of timedvalues.
	 */
	private TimedValueManager manager;
	
	/**
	 * This timeline's name.
	 */
	private String name = "";
	
	/**
	 * Creates a new instance of <code>TimeLine</code>
	 */
	public TimeLine() {
		Timeds = new ArrayList<TimedValueImpl<? extends AbstractValue>>();
		
		TimedsToBeAdded = new ArrayList<TimedValueImpl<? extends AbstractValue>>();
		TimedsToBeRemoved = new ArrayList<TimedValueImpl<? extends AbstractValue>>();
		
		grainMap = new TreeMap<Long, Long>();
	}
	
	
	/**
	 * Creates a new instance of <code>TimeLine</code> with the given name.
	 * @param name
	 * 		the name for the new timeline
	 */
	public TimeLine(String name, TimedValueManager manager) {
		this();
		this.manager = manager;
		this.name = name;
	}
	
	/**
	 * Adds a timed fact on the timeline
	 * @param fact
	 * 		the <code>Timed</code> to add
	 */
	public synchronized void addTimed(TimedValueImpl<? extends AbstractValue> fact) {
		TimedsToBeAdded.add(fact);
	}
	
	/**
	 * Removes a timed fact from the timeline
	 * @param fact
	 * 		the <code>Timed</code> to remove
	 */
	public synchronized void removeTimed(TimedValueImpl<? extends AbstractValue> fact) {
		fact.getTimeInterval().setReferenceTimelineName(this.name);
		TimedsToBeRemoved.add(fact);
	}
	
	/**
	 * Returns the timeline's name.
	 * @return
	 * 		the name of this timeline.
	 */
	public synchronized String getName() {
		return name;
	}

	/**
	 * This method is called by the execution engine at the end of a grain
	 */
	public synchronized void grainExpired() {
		Timeds.removeAll(TimedsToBeRemoved);
		Timeds.addAll(TimedsToBeAdded);
		
		TimedsToBeRemoved.clear();
		TimedsToBeAdded.clear();
	}
	
	/**
	 * Returns the timeline's reference
	 * @return
	 * 			this timeline's reference timeline
	 */
	public TimeLine getReferenceTimeLine() {
		return referenceTimeLine;
	}
	
	/**
	 * Returns the interval in the reference timeline corresponding to the specified interval in this timeline
	 * @param interval
	 * 			the interval whose corresponding interval in the reference timeline is to be found
	 * @return
	 * 			the interval in the reference timeline corresponding to the specified interval
	 */
	public synchronized TimeInterval getReferenceInterval(TimeInterval interval) {
		long referenceBegin = 0;
		long referenceEnd = 0;
		
		referenceBegin = grainMap.get(interval.getBegin());
		referenceEnd = grainMap.get(interval.getEnd());
		
		return new TimeInterval(referenceBegin, referenceEnd);
	}
	
	/**
	 * Returns the set of timed facts whose validity time interval has a non-empty overlapping
	 * with a specified time interval
	 * @param interval
	 * 		the time interval of interest
	 * @return
	 * 		the set of facts valid during a part of the specified interval
	 */
	public synchronized ArrayList<TimedValueImpl<? extends AbstractValue>> getTimeds(TimeInterval interval) {
		int i;

		ArrayList<TimedValueImpl<? extends AbstractValue>> res = new ArrayList<TimedValueImpl<? extends AbstractValue>>();

		for (i = 0; i < Timeds.size(); i++) {
			if (Timeds.get(i).getTimeInterval().intersects(interval)) {
				res.add(Timeds.get(i));
			}
		}

		return res;
	}
	
	/**
	 * Returns the set of timed facts whose validity time interval contains a given time grain
	 * @param timeGrain
	 * 		the time grain to consider
	 * @return
	 * 		oggetto <code>Set<Timed></code> contenente i fatti richiesti
	 * @throws TimeIntervalException
	 * 		the set of facts valid during this time grain
	 */
	public synchronized ArrayList<TimedValueImpl<? extends AbstractValue>> getTimeds(long timeGrain) {
		return getTimeds(new TimeInterval(timeGrain, timeGrain));
	}

	/**
	 * Returns a copy of the full set of timed facts.
	 * @return
	 * 		the copy of the full set of timed facts
	 */	
	public synchronized ArrayList<TimedValueImpl<? extends AbstractValue>> getAllTimeds(){
		return new ArrayList<TimedValueImpl<? extends AbstractValue>>(Timeds);
	}
	
	/**
	 * Returns the interval in the ground timeline corresponding to the specified interval in this timeline
	 * @param interval
	 * 			the interval whose corresponding interval in the ground timeline is to be found
	 * @return
	 * 			the interval in the ground timeline corresponding to the specified interval
	 */

	public void setReferenceTimeLine(TimeLine referenceTimeLine) {
		this.referenceTimeLine = referenceTimeLine;
	}

	public TreeMap<Long, Long> getGrainMap() {
		return grainMap;
	}

	public void setGrainMap(TreeMap<Long, Long> grainMap) {
		this.grainMap = grainMap;
	}

	/**
	 * @return the manager
	 */
	public TimedValueManager getManager() {
		return manager;
	}
}