# UC50X LoRa Decoder and Encoder

This repository provides a Java implementation for encoding and decoding payloads for the communication with the LoRa UC50X series controllers from the manufacturer Milesight.

## Overview

```
|-----decoder
|             |--------UC50XData
|             |--------UC50XDecoder
|-----encoder
              |--------UCDigitalOutputs
              |--------UC50XEncoder
```

The library contains two main packages:

1. `decoder`: Contains the `UC50XDecoder` class for decoding the payloads received from the UC50X series controllers and the `UC50XData` class for storing the decoded payload data.
2. `encoder`: Contains the `UC50XEncoder` class for encoding payloads to be sent to the UC50X series controllers and the `UCDigitalOutputs` class for storing the digital output values.

### UC50XDecoder

The `UC50XDecoder` class provides a method `decode` that accepts a byte array and returns an instance of `decoder.UC50XData`.

### UC50XEncoder

The `UC50XEncoder` class provides methods for encoding the digital outputs and the reporting interval configuration payload:

1. `encodeControlPayload(UCDigitalOutputs outputs)`: Encodes the digital outputs into a byte array to be sent to the UC50X series controllers. The `UCDigitalOutputs` class is used to store the digital output values.
2. `encodeReportingIntervalPayload(Integer seconds)`: Encodes the reporting interval configuration payload. The method accepts an integer representing the reporting interval in seconds, which should be in the range of 10-64800 seconds.

## Usage

To use this library, simply import UC50X LoRa Decoder and Encoder classes.

### Example

```java
// Replace with the actual byte array received from the UC50X series controller
byte[] receivedPayload = new byte[]{...};

UC50XData decodedData = UC50XDecoder.decode(receivedPayload);

// Configure the digital outputs
UCDigitalOutputs digitalOutputs = new UCDigitalOutputs(true, false);
byte[] encodedControlPayload = UC50XEncoder.encodeControlPayload(digitalOutputs);

// Set the reporting interval
byte[] encodedReportingIntervalPayload = UC50XEncoder.encodeReportingIntervalPayload(1800);
```
