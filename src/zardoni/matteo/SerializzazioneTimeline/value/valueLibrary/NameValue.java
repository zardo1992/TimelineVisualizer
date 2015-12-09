package zardoni.matteo.SerializzazioneTimeline.value.valueLibrary;
import zardoni.matteo.SerializzazioneTimeline.value.abstractValue.AbstractValue;

public class NameValue extends AbstractValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6807098548742823938L;
	private String _name;

	public NameValue(){

	}
	
	public NameValue(String _name){
		this._name = _name;
	}
	
	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

}