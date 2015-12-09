package zardoni.matteo.SerializzazioneTimeline.value;

import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;
import zardoni.matteo.SerializzazioneTimeline.value.interfaces.Value;

public class ValueImpl<T extends AbstractValue> implements Value<T> {

	private T _value;
	
	public ValueImpl(){
		
	}
	
	public ValueImpl(T value){
		this._value = value;
	}
	
	@Override
	public void setValue(T value) {
		this._value = value;
	}

	@Override
	public int compareValue(T value) {
		return 0;
	}

	@Override
	public T getValue() {
		return this._value;
	}

}
