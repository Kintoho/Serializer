package Serialization.Encoder;

import Serialization.Encoder.Core.ZigZag;
import Serialization.Encoder.Core.IEncoder;

public class LongEncoder implements IEncoder<Long> {
    private final long MAX_VALUE = Long.MAX_VALUE / 2;
    private final long MIN_VALUE = Long.MIN_VALUE / 2;

    @Override
    public byte[] encode(Long signed) {
        checkDiapason(signed);

        IEncoder<Long> serializer = new UIntEncoder();
        long unsigned = ZigZag.wrap(signed);
        
        return serializer.encode(unsigned);
    }

    @Override
    public DecoderResult<Long> decode(byte[] bytes) {
        IEncoder<Long> serializer = new UIntEncoder();
        DecoderResult<Long> unsignedResult = serializer.decode(bytes);
        
        return new DecoderResult<Long>(ZigZag.unwrap(unsignedResult.getDecoderResult()), unsignedResult.getLength());
    }

    private void checkDiapason(Long value) {
        if (value > MAX_VALUE || value < MIN_VALUE) {
            throw new RuntimeException("Incorrect diapason");
        }
    }
}


