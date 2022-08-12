package decoder;

public abstract class UC50XDecoder {

	public static UC50XData decode(byte[] bytes) {

		UC50XData data = new UC50XData();

		int i = 0;

		while (i < bytes.length) {

			byte channel_id = bytes[i++];
			byte channel_type = bytes[i++];

			// BATTERY
			if (channel_id == 0x01 && channel_type == 0x75) {
				data.setBatteryLevel(bytes[i] & 0xFF);
				i += 1;
			}
			// GPIO1 in
			else if (channel_id == 0x03 && channel_type == 0x00) {
				data.getDigitalInputs().put((int) channel_id, bytes[i] == 0x01);
				i += 1;
			}
			// GPIO2 in
			else if (channel_id == 0x04 && channel_type == 0x00) {
				data.getDigitalInputs().put((int) channel_id, bytes[i] == 0x01);
				i += 1;
			}
			// GPIO1 out
			else if (channel_id == 0x03 && channel_type == 0x01) {
				data.getDigitalOutputs().put((int) channel_id, bytes[i] == 0x01);
				i += 1;
			}
			// GPIO2 out
			else if (channel_id == 0x04 && channel_type == 0x01) {
				data.getDigitalOutputs().put((int) channel_id, bytes[i] == 0x01);
				i += 1;
			}
			// PULSE COUNTER 1
			else if (channel_id == 0x03 && channel_type == (byte) 0xC8) {
				long byte3 = unsignedByteToLong(bytes[i + 3]);
				long byte2 = unsignedByteToLong(bytes[i + 2]);
				long byte1 = unsignedByteToLong(bytes[i + 1]);
				long byte0 = unsignedByteToLong(bytes[i]);
				long value = (byte3 << 24) + (byte2 << 16) + (byte1 << 8) + byte0;
				data.getPulseCounters().put((int) channel_id, value);
				i += 4;
			}
			// PULSE COUNTER 2
			else if (channel_id == 0x04 && channel_type == (byte) 0xC8) {
				long byte3 = unsignedByteToLong(bytes[i + 3]);
				long byte2 = unsignedByteToLong(bytes[i + 2]);
				long byte1 = unsignedByteToLong(bytes[i + 1]);
				long byte0 = unsignedByteToLong(bytes[i]);
				long value = (byte3 << 24) + (byte2 << 16) + (byte1 << 8) + byte0;
				data.getPulseCounters().put((int) channel_id, value);
				i += 4;
			} // AI 1
			else if (channel_id == 0x05) {
				int value = (unsignedByteToInt(bytes[i + 1]) << 8) + unsignedByteToInt(bytes[i]);
				data.getAnalogInputs().put((int) channel_id, value);
				i += 8;
			}
			// AI 2
			else if (channel_id == 0x06) {
				int value = (unsignedByteToInt(bytes[i + 1]) << 8) + unsignedByteToInt(bytes[i]);
				data.getAnalogInputs().put((int) channel_id, value);
				i += 8;
			}

		}

		return data;
	}

	private static int unsignedByteToInt(byte b) {

		return ((int) b) & 0xFF;

	}

	private static long unsignedByteToLong(byte b) {

		return ((long) b) & 0xFF;

	}

}
