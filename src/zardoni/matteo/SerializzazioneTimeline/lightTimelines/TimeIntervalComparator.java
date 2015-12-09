package zardoni.matteo.SerializzazioneTimeline.lightTimelines;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator of <code>TimeInterval</code> objects
 * 
 * @author Software Architecture Laboratory - D.I.S.Co. - University of Milano-Bicocca
 * 
 */
public class TimeIntervalComparator implements Comparator<TimeInterval>, Serializable {

	/**
	 * ID for serialization
	 */
	private static final long serialVersionUID = 6293333778932260547L;

	/**
	 * Method to compare two <code>TimeInterval</code> objects
	 * @param interval1
	 * 		the first interval to compare
	 * @param interval2
	 * 		the second interval to compare
	 * @return
	 *		- 0 if both the lower and upper endpoints of the two intervals are the same
	 *		- a negative number, if the lower endpoint of the first <code>TimeInterval</code>
	 *		is less than the one of the second interval, or if the two lower endpoints are equal but
	 *		the upper endpoint of the first <code>TimeInterval</code> is less than the one of the second interval
	 *		- a positive number otherwise
	 */
	public int compare(TimeInterval interval1, TimeInterval interval2) {
		int res = 0;
		if (interval1.getBegin() == interval2.getBegin()) {
			if (interval1.getEnd() > interval2.getEnd()) {
				res = 1;
			} else if (interval1.getEnd() == interval2.getEnd()) {
				res = 0;
			} else {
				res = -1;
			}
		} else {
			if (interval1.getBegin() > interval2.getBegin()) {
				res = 1;
			} else {
				res = -1;
			}
		}
			
		return res;
	}
}
