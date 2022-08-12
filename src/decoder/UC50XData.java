package decoder;

import java.util.HashMap;

public class UC50XData {

	// Battery report
	private Integer batteryLevel;
	// Digital inputs: the keys of the hash map are the channel identifiers: 03 or
	// 04
	private HashMap<Integer, Boolean> digitalInputs;
	// Digital outputs: the keys of the hash map are the channel identifiers: 03 or
	// 04
	private HashMap<Integer, Boolean> digitalOutputs;
	// Pulse Counters: the keys of the hash map are the channel identifiers: 03 or
	// 04
	private HashMap<Integer, Long> pulseCounters;

	// TODO: decode analog inputs
	private HashMap<Integer, Integer> analogInputs;

	public UC50XData() {
		this.digitalInputs = new HashMap<Integer, Boolean>();
		this.digitalOutputs = new HashMap<Integer, Boolean>();
		this.pulseCounters = new HashMap<Integer, Long>();
		this.analogInputs = new HashMap<Integer, Integer>();
	}

	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public HashMap<Integer, Boolean> getDigitalInputs() {
		return digitalInputs;
	}

	public void setDigitalInputs(HashMap<Integer, Boolean> digitalInputs) {
		this.digitalInputs = digitalInputs;
	}

	public HashMap<Integer, Boolean> getDigitalOutputs() {
		return digitalOutputs;
	}

	public void setDigitalOutputs(HashMap<Integer, Boolean> digitalOutputs) {
		this.digitalOutputs = digitalOutputs;
	}

	public HashMap<Integer, Long> getPulseCounters() {
		return pulseCounters;
	}

	public void setPulseCounters(HashMap<Integer, Long> pulseCounters) {
		this.pulseCounters = pulseCounters;
	}

	public HashMap<Integer, Integer> getAnalogInputs() {
		return analogInputs;
	}

	public void setAnalogInputs(HashMap<Integer, Integer> analogInputs) {
		this.analogInputs = analogInputs;
	}

}
