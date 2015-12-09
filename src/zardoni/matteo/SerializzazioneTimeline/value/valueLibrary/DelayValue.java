package zardoni.matteo.SerializzazioneTimeline.value.valueLibrary;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class DelayValue extends AbstractValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5268424925397649697L;
	private double _delay;

	public DelayValue(){

	}
	
	public DelayValue(double _delay){
		this._delay = _delay;
	}
	
	public double get_delay() {
		return _delay;
	}

	public void set_delay(double _delay) {
		this._delay = _delay;
	}

}