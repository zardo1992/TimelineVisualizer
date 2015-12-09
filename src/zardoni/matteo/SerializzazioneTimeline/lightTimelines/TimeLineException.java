package zardoni.matteo.SerializzazioneTimeline.lightTimelines;

/**
 * Exception thrown as a consequence of an incorrect use of a <code>TimeLine</code>
 * 
 * @author Software Architecture Laboratory - D.I.S.Co. - University of Milano-Bicocca
 */
public class TimeLineException extends RuntimeException {
	/**
	 * ID for serialization
	 */
	private static final long serialVersionUID = -2857495287233464393L;

	/**
	 * Creates a new instance of <code>TimeLineException</code>.
	 * 
	 * @param message
	 *                the error message.
	 */
    public TimeLineException(String message) {
    	super(message);
    }
}
