package Serialization.Encoder;

import java.nio.ByteBuffer;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

public class DoubleEncoder implements IEncoder<Double> {
    private final double MAX_VALUE = Double.MAX_VALUE / 2;
    private final double MIN_VALUE = - Double.MAX_VALUE / 2;

    @Override
    public byte[] encode(Double data) {
        checkDiapason(data);
        long longBits = Double.doubleToLongBits(data);
        
        return new byte[] { 
            (byte) ((longBits >> 56) & 0xFF), 
            (byte) ((longBits >> 48) & 0xFF), 
            (byte) ((longBits >> 40) & 0xFF), 
            (byte) ((longBits >> 32) & 0xFF), 
            (byte) ((longBits >> 24) & 0xFF), 
            (byte) ((longBits >> 16) & 0xFF), 
            (byte) ((longBits >> 8) & 0xFF), 
            (byte) ((longBits) & 0xFF) 
        };
    }

    @Override
    public DecoderResult<Double> decode(byte[] encodedData, int fromByte) {
        return new DecoderResult<>(ByteBuffer.wrap(encodedData).getDouble(fromByte), encodedData.length);
    }

    @Override
    public DecoderResult<Double> decode(byte[] encodedData) {
        return decode(encodedData, 0);
    }

    private void checkDiapason(Double value) {
        if (value > MAX_VALUE || value < MIN_VALUE) {
            throw new RuntimeException("Incorrect diapason");
        }
    }
}
