package zardoni.matteo.SerializzazioneTimeline.lightTimelines;

/**
 * Class modeling a timeline with clock.
 * 
 * @author Software Architecture Laboratory - D.I.S.Co. - University of Milano-Bicocca
 * 
 */
public class PlainTimeLine extends TimeLine {

	/**
	 * ID for serialization
	 */
	private static final long serialVersionUID = 8444844067372396133L;

//	public PlainTimeLine() {
//		super(null, null);
//	}
	
	public PlainTimeLine(String name, PlainManager manager) {
		super(name, manager);
	}
}
