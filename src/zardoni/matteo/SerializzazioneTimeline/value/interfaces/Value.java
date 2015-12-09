package zardoni.matteo.SerializzazioneTimeline.value.interfaces;

import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public interface Value <T extends AbstractValue>{
	
	public void setValue(T value);
	public T getValue();
	public int compareValue(T value);

}
