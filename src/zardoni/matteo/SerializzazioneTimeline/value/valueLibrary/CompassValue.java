package zardoni.matteo.SerializzazioneTimeline.value.valueLibrary;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class CompassValue extends AbstractValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = -27920812841834028L;
	private double _degree;

	public CompassValue(){

	}
	
	public CompassValue(double _degree){
		this._degree = _degree;
	}
	
	public double get_degree() {
		return _degree;
	}

	public void set_degree(double _degree) {
		this._degree = _degree;
	}

}