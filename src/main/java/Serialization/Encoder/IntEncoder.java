package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

public class IntEncoder implements IEncoder<Integer> {
    private final int MAX_VALUE = Integer.MAX_VALUE / 2;
    private final int MIN_VALUE = Integer.MIN_VALUE / 2;

    private final IEncoder<Long> longEncoder = new LongEncoder();

    @Override
    public byte[] encode(Integer signed) {
        checkDiapason(signed);

        return longEncoder.encode((long) signed);
    }

    @Override
    public DecoderResult<Integer> decode(byte[] encodedData, int fromByte) {
        DecoderResult<Long> longResult = longEncoder.decode(encodedData, fromByte);
        
        return new DecoderResult<>(longResult.getDecoderResult().intValue(), longResult.getLength());
    }

    @Override
    public DecoderResult<Integer> decode(byte[] encodedData) {
        return decode(encodedData, 0);
    }

    private void checkDiapason(int value) {
        if (value > MAX_VALUE || value < MIN_VALUE) {
            throw new RuntimeException("Incorrect diapason");
        }
    }
}
