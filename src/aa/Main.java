package aa;

import java.util.Base64;
import java.util.HashMap;

public class Main {

	private static boolean salida;
	private static HashMap<Integer, Boolean> entradas;
	private static HashMap<Integer, Boolean> salidas;

	public static void main(String[] args) {

		String trama = "AwEABAEBBQJ2BHYEdgR2BA==";

		byte[] tramaBinaria = Base64.getDecoder().decode(trama);

		decodeDigitalInput(tramaBinaria, 3);

		System.out.println("Salida: " + salida);

	}
	
	public static boolean decodeDigitalInput(byte[] bytes, int canal) {
		
		decodeDigitalChannels(bytes);
		
		if (entradas.containsKey(canal)) {
			salida = entradas.get(canal);
			return true;
		}
				
		return false;
	}
	
	public static boolean decodeDigitalOutPuts(byte[] bytes, int canal) {
		
		decodeDigitalChannels(bytes);
		
		if (salidas.containsKey(canal)) {
			salida = salidas.get(canal);
			return true;
		}
				
		return false;
	}

	public static void decodeDigitalChannels(byte[] bytes) {
		
		entradas = new HashMap<Integer, Boolean>();
		salidas = new HashMap<Integer, Boolean>();
		
		for (int i = 0; i < bytes.length; i++) {
			
			if (i > 0)
				i--;

			int channel_id = bytes[i++];
			int channel_type = bytes[i++];
			
			//Solo recorro hasta el canal 4
			if (channel_id > 0x04)
				break;

			// BATTERY
			if (channel_id == 0x01 && channel_type == 0x75) {
				i += 1;
			}
			// GPIO1 in
			else if (channel_id == 0x03 && channel_type == 0x00) {
				entradas.put(channel_id, bytes[i] == 0x01);
				i += 1;
			}
			// GPIO2 in
			else if (channel_id == 0x04 && channel_type == 0x00) {
				entradas.put(channel_id, bytes[i] == 0x01);
				i += 1;
			}
			// GPIO1 out
			else if (channel_id == 0x03 && channel_type == 0x01) {
				salidas.put(channel_id, bytes[i] == 0x01);
				i += 1;
			}
			// GPIO2 out
			else if (channel_id == 0x04 && channel_type == 0x01) {
				salidas.put(channel_id, bytes[i] == 0x01);
				i += 1;
			}
			// PULSE COUNTER 1
			else if (channel_id == 0x03 && channel_type == 0xc8) {
				i += 4;
			}
			// PULSE COUNTER 2
			else if (channel_id == 0x04 && channel_type == 0xc8) {
				i += 4;
			}

		}

	}

}
