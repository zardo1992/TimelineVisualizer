package zardoni.matteo.SerializzazioneTimeline.timedValue.interfaces;

import zardoni.matteo.SerializzazioneTimeline.lightTimelines.timed.Timed;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;
import zardoni.matteo.SerializzazioneTimeline.value.interfaces.Value;

public interface TimedValue<T extends AbstractValue> extends Value<T>, Timed {
	
}
