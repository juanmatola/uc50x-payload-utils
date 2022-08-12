package decoder;

import java.util.Base64;

public class Main {

	public static void main(String[] args) {

		// hex payload: 0x01755A03000103010003C87805000004000104010104C878050000
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
		System.out.println("PC 04: " + (data.getPulseCounters().get(4) & 0xFFFFFFFF));
		
		System.out.println("\nAI: (Channel 5)");	
		System.out.println("AI 05: " + data.getAnalogInputs().get(5));
		System.out.println("\nAI: (Channel 6)");	
		System.out.println("AI 06: " + data.getAnalogInputs().get(6));
		
		

	}

}
