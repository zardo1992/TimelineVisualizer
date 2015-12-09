package zardoni.matteo.SerializzazioneTimeline.value.valueLibrary;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class TemperatureValue extends AbstractValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 40742925087097844L;
	private double _temperature;

	public TemperatureValue(){

	}
	
	public TemperatureValue(double _temperature) {
		this._temperature = _temperature;
	}

	public double get_temperature() {
		return _temperature;
	}

	public void set_temperature(double _temperature) {
		this._temperature = _temperature;
	}

}