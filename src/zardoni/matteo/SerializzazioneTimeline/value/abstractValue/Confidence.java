package zardoni.matteo.SerializzazioneTimeline.value.abstractValue;
public class Confidence {

	private double _confidence;
	
	public Confidence(){

	}
	public Confidence(double _confidence) {
		this._confidence = _confidence;
	}

	public double get_confidence() {
		return _confidence;
	}

	public void set_confidence(double _confidence) {
		this._confidence = _confidence;
	}
}