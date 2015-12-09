package zardoni.matteo.SerializzazioneTimeline.value.valueLibrary;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class PressureValue extends AbstractValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5307186374728998403L;
	private double _pressure;

	public PressureValue(){

	}
	
	public PressureValue(double _pressure){
		this._pressure = _pressure;
	}
	
	public double get_pressure() {
		return _pressure;
	}

	public void set_pressure(double _pressure) {
		this._pressure = _pressure;
	}

}