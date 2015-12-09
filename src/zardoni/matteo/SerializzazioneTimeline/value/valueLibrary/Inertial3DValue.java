package zardoni.matteo.SerializzazioneTimeline.value.valueLibrary;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class Inertial3DValue extends AbstractValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6243966471947093740L;
	private double _x;
	private double _y;
	private double _z;

	public Inertial3DValue(){

	}
	
	public Inertial3DValue(double _x, double _y, double _z){
		this._x = _x;
		this._y = _y;
		this._z = _x;
	}
	
	public double get_x() {
		return _x;
	}

	public void set_x(double _x) {
		this._x = _x;
	}

	public double get_y() {
		return _y;
	}

	public void set_y(double _y) {
		this._y = _y;
	}

	public double get_z() {
		return _z;
	}

	public void set_z(double _z) {
		this._z = _z;
	}



}