package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.ZigZag;
import Serialization.Encoder.Core.IEncoder;

public class LongEncoder implements IEncoder<Long> {
    private final long MAX_VALUE = Long.MAX_VALUE / 2;
    private final long MIN_VALUE = Long.MIN_VALUE / 2;

    @Override
    public byte[] encode(Long signed) {
        checkDiapason(signed);
        long unsigned = ZigZag.wrap(signed);

        return UIntEncoder.coder.encode(unsigned);
    }

    @Override
    public DecoderResult<Long> decode(byte[] encodedData, int fromByte) {
        DecoderResult<Long> unsignedResult = UIntEncoder.coder.decode(encodedData, fromByte);

        return new DecoderResult<Long>(ZigZag.unwrap(unsignedResult.getDecoderResult()), unsignedResult.getLength());
    }

    @Override
    public DecoderResult<Long> decode(byte[] encodedData) {
        return decode(encodedData, 0);
    }

    private void checkDiapason(Long value) {
        if (value > MAX_VALUE || value < MIN_VALUE) {
            throw new RuntimeException("Incorrect diapason");
        }
    }
}
