package zardoni.matteo.SerializzazioneTimeline.lightTimelines;

import java.io.Serializable;

/**
 * Class modeling a time interval on a <code>TimeLine</code>
 * 
 * @author Software Architecture Laboratory - D.I.S.Co. - University of Milano-Bicocca
 * 
 */
public class TimeInterval implements Serializable {

	/**
	 * ID for serialization
	 */
	private static final long serialVersionUID = -904666897651283354L;

	/**
	 * First time grain of the interval
	 */
	private long begin;
	
	/**
	 * Last time grain of the interval
	 */	
	private long end;
	
	private String referenceTimelineName;
	
	/**
	 * Creates a new instance of <code>TimeInterval</code> with the specified endpoints.
	 * @param begin
	 * 		first time grain
	 * @param end
	 * 		last time grain
	 */
	public TimeInterval(long begin, long end) {
		if (begin < 0 || end < 0 || end < begin) {
			throw new TimeIntervalException(this + ": invalid time interval");
		}
		
		this.begin = begin;
		this.end = end;
	}
	
	/**
	 * Creates a new instance of <code>TimeInterval</code> corresponding to a single time grain
	 * @param grain
	 * 		time grain to describe
	 * @throws TimeIntervalException
	 * 		if the specified time grain is <code>null</code>
	 */
	public TimeInterval(long grain) {
		if (grain < 0) {
			throw new TimeIntervalException(this + ": invalid time interval");
		}
		
		this.begin = grain;
		this.end = grain;
	}	
	
	public TimeInterval() {
		this.begin = 0;
		this.end = 0;
	}
	
	/**
	 * Creates a new instance of <code>TimeInterval</code> by copying the specified one.
	 * @param interval
	 * 		the interval to copy
	 * @throws TimeIntervalException
	 * 		if the specified interval is invalid
	 */
	public TimeInterval(TimeInterval interval) {
		this(interval.getBegin(), interval.getEnd());
	}	

	/**
	 * Returns the first time grain of the interval
	 * @return
	 *		<code>long</code> describing the lower endpoint of the interval
	 */
	public synchronized long getBegin() {
		return begin;
	}
	
	/**
	 * Returns the last time grain of the interval
	 * @return
	 *		<code>long</code> describing the upper endpoint of the interval
	 */
	public synchronized long getEnd() {
		return end;
	}	
	
	/**
	 * Sets the lower endpoint of the interval
	 * @param newBegin
	 * 			the new starting time grain 
	 * @throws TimeIntervalException
	 * 			if the new lower endpoint is invalid
	 */
	public synchronized void setBegin(long newBegin) {
		if (newBegin < 0 || newBegin > this.end) {
			throw new TimeIntervalException(this + ": invalid time interval");
		} else {
			this.begin = newBegin;			
		}
	}
	
	/**
	 * Sets the upper endpoint of the interval
	 * @param newBegin
	 * 			the new ending time grain 
	 * @throws TimeIntervalException
	 * 			if the new upper endpoint is invalid
	 */
	public synchronized void setEnd(long newEnd) {
		if (newEnd < 0 || newEnd < this.begin) {
			throw new TimeIntervalException(this + ": invalid time interval");
		} else {
			this.end = newEnd;
		}
	}
	
	/**
	 * Sets both bounds of the time interval
	 * @param newBegin
	 * 			the begin grain
	 * @param newEnd
	 * 			the end grain
	 */
	public synchronized void setBounds(long newBegin, long newEnd) {
		if (newBegin < 0 || newEnd < 0 || newBegin > newEnd) {
			throw new TimeIntervalException(this + ": invalid time interval");
		} else {
			this.begin = newBegin;
			this.end = newEnd;
		}
	}
	
	/**
	 * Checks if the specified time grain is inside the interval 
	 * @param grain
	 * 			the time grain of interest
	 * @return
	 * 			<code>true</code> if the time grain is inside the interval,
	 * 			<code>false</code> otherwise 
	 */
	public synchronized boolean contains(long grain) {
		return grain >= begin && grain <= end;
	}
	
	/**
	 * Checks if the time interval has a non-empty intersection with a specified one
	 * @param interval
	 * 		the time interval to check
	 * @return
	 * 		<code>true</code> if the two intervals overlap,
	 * 		<code>false</code> otherwise 
	 */
	public synchronized boolean intersects(TimeInterval interval) {
		return interval.end >= this.begin && interval.begin <= this.end;
	}

	/**
	 * @return the referenceTimelineName
	 */
	public String getReferenceTimelineName() {
		return referenceTimelineName;
	}

	/**
	 * @param referenceTimelineName the referenceTimelineName to set
	 */
	public void setReferenceTimelineName(String referenceTimelineName) {
		this.referenceTimelineName = referenceTimelineName;
	}
}