package zardoni.matteo.SerializzazioneTimeline.lightTimelines;

/**
 * This exception is thrown on the attemp to create a <code>TimeInterval</code>
 * with invalid endpoints.
 * 
 * @author Software Architecture Laboratory - D.I.S.Co. - University of Milano-Bicocca
 */
public class TimeIntervalException extends RuntimeException {

	/**
	 * ID for serialization
	 */
	private static final long serialVersionUID = 2399685458730117138L;

	/**
	 * Creates a new instance of <code>TimerException</code>.
	 * 
	 * @param message
	 *                the error message.
	 */
    public TimeIntervalException(String message) {
    	super(message);
    }
}
