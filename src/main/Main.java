package main;

import java.util.Base64;

import main.decoder.UC50XData;
import main.decoder.UC50XDecoder;
import main.encoder.UC50XEncoder;
import main.encoder.UCDigitalOutputs;

public class Main {

	public static void main(String[] args) {

		//DECODER
		
		// hex payload: 0x03000004c8000000000502020102010201020106020000000000000000
		String payload = "AwAABMgAAAAABQICAQIBAgECAQYCAAAAAAAAAAA=";

		// Get binary payload
		byte[] binaryPayload = Base64.getDecoder().decode(payload);

		UC50XData data = UC50XDecoder.decode(binaryPayload);

		System.out.println("Battery Level: " + data.getBatteryLevel());

		System.out.println("\nGPIO 1: (Channel 3)");
		System.out.println("DI 03: " + data.getDigitalInputs().get(3));
		System.out.println("DO 03: " + data.getDigitalOutputs().get(3));
		System.out.println("PC 03: " + data.getPulseCounters().get(3));

		System.out.println("\nGPIO 2: (Channel 4)");
		System.out.println("DI 04: " + data.getDigitalInputs().get(4));
		System.out.println("DO 04: " + data.getDigitalOutputs().get(4));
		System.out.println("PC 04: " + data.getPulseCounters().get(4));

		System.out.println("\nAI: (Channel 5)");
		System.out.println("AI 05: " + data.getAnalogInputs().get(5));
		System.out.println("\nAI: (Channel 6)");
		System.out.println("AI 06: " + data.getAnalogInputs().get(6));

		// ENCODER

		// DO control
		// Ex:
		// do1 low, do2 high
		// hex: 070000ff080100ff
		// base64: BwAA/wgBAP8=

		System.out.println();
		System.out.println("Encode payloads\n----------------");
		System.out.println("-DO control payload");

		UCDigitalOutputs outputsValues = new UCDigitalOutputs();
		outputsValues.setDO01Value(false);
		outputsValues.setDO02Value(true);

		byte[] encodedControlPayload = UC50XEncoder.encodeControlPayload(outputsValues);
		String base64ControlPayload = Base64.getEncoder().encodeToString(encodedControlPayload);

		System.out.println("base64: " + base64ControlPayload);

		// Set reporting interval
		System.out.println();
		System.out.println("-Set reporting interval payload");
		byte[] encodedReportingPayload = UC50XEncoder.encodeReportingIntervalPayload(600);

		String base64ReportingPayload = Base64.getEncoder().encodeToString(encodedReportingPayload);
		System.out.println("base64: " + base64ReportingPayload);

	}

}
