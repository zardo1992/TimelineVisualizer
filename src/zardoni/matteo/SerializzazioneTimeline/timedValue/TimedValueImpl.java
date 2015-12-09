package zardoni.matteo.SerializzazioneTimeline.timedValue;
import zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimeInterval;
import zardoni.matteo.SerializzazioneTimeline.lightTimelines.TimeIntervalComparator;
import zardoni.matteo.SerializzazioneTimeline.lightTimelines.timed.Timed;
import zardoni.matteo.SerializzazioneTimeline.timedValue.interfaces.TimedValue;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.*;

public class TimedValueImpl<T extends AbstractValue> implements TimedValue<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2849519238225914842L;
	private T _value;
	private TimeInterval _timeInterval;
	 
	public TimedValueImpl(){
		
	}
	public TimedValueImpl(T value, TimeInterval timeInterval){
		this._value = value;
		this._timeInterval = timeInterval;
	}
	
	@Override
	public void setValue(T value) {
		this._value = value;
	}

	@Override
	public T getValue() {
		return _value;
	}

	@Override
	public int compareValue(T value) {
		return 0;
	}


	@Override
	public void setTimeInterval(TimeInterval timeInterval) {
		this._timeInterval = timeInterval;
	}

	@Override
	public TimeInterval getTimeInterval() {
		return _timeInterval;
	}
	
	@Override
	public int compareTimeInterval(Timed timed) {
		return (new TimeIntervalComparator()).compare(this.getTimeInterval(), timed.getTimeInterval());
	}
}
