package zardoni.matteo.SerializzazioneTimeline.value.abstractValue;

import java.io.Serializable;

public abstract class AbstractValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1678391001509956642L;
	
	private Confidence _confidence;
	private UnitaDiMisura _unitaDiMisura;

	public AbstractValue(){

	}

	public Confidence get_confidence() {
		return _confidence;
	}

	public void set_confidence(Confidence _confidence) {
		this._confidence = _confidence;
	}

	public UnitaDiMisura get_unitaDiMisura() {
		return _unitaDiMisura;
	}

	public void set_unitaDiMisura(UnitaDiMisura _unitaDiMisura) {
		this._unitaDiMisura = _unitaDiMisura;
	}


}