package zardoni.matteo.SerializzazioneTimeline.lightTimelines;

/**
 * Class modeling a timeline with clock.
 * 
 * @author Software Architecture Laboratory - D.I.S.Co. - University of Milano-Bicocca
 * 
 */
public class EnhancedTimeLine extends TimeLine {

	/**
	 * ID for serialization
	 */
	private static final long serialVersionUID = 8444844067372396133L;

	public EnhancedTimeLine(String name, EnhancedManager manager) {
		super(name, manager);
	}
}
