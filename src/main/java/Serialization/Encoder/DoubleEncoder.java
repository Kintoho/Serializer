package Serialization.Encoder;

public class DoubleEncoder implements IEncoder<Double> {

    @Override
    public byte[] encode(Double data) {
        return new byte[0];
    }

    @Override
    public Double decode(byte[] encodedData) {
        return null;
    }
}
