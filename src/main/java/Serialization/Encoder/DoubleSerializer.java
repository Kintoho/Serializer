package Serialization.Encoder;

import Serialization.Encoder.Core.ISerializer;

public class DoubleSerializer implements ISerializer<Double> {

    @Override
    public byte[] encode(Double data) {
        return new byte[0];
    }

    @Override
    public Double decode(byte[] encodedData) {
        return null;
    }
}
