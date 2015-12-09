package zardoni.matteo.SerializzazioneTimeline.value.valueLibrary;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class Cartesian2DValue extends AbstractValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = -951239611837007808L;
	private double _x;
	private double _y;

	public Cartesian2DValue(){

	}
	public Cartesian2DValue(double _x, double _y){
		this._x = _x;
		this._y = _y;
	}

	public double get_y() {
		return _y;
	}

	public void set_y(double _y) {
		this._y = _y;
	}

	public double get_x() {
		return _x;
	}

	public void set_x(double _x) {
		this._x = _x;
	}

}