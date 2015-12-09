package zardoni.matteo.SerializzazioneTimeline.value.valueLibrary;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class GPSValue extends AbstractValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1281319334231969445L;
	private double _altitude;
	private double _latitude;
	private double _longitude;

	public GPSValue(){

	}
	public GPSValue(double _latitude, double _longitude, double _altitude){
		this._latitude = _latitude;
		this._longitude = _longitude;
		this._altitude = _altitude;
	}

	public double get_altitude() {
		return _altitude;
	}

	public void set_altitude(double _altitude) {
		this._altitude = _altitude;
	}

	public double get_latitude() {
		return _latitude;
	}

	public void set_latitude(double _latitude) {
		this._latitude = _latitude;
	}

	public double get_longitude() {
		return _longitude;
	}

	public void set_longitude(double _longitude) {
		this._longitude = _longitude;
	}

}