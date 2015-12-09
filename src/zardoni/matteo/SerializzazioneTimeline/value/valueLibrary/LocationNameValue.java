package zardoni.matteo.SerializzazioneTimeline.value.valueLibrary;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class LocationNameValue extends AbstractValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 954953597851607690L;
	private String _location;

	public LocationNameValue(){

	}
	
	public LocationNameValue(String _location){
		this._location = _location;
	}
	
	public String get_location() {
		return _location;
	}

	public void set_location(String _location) {
		this._location = _location;
	}

}