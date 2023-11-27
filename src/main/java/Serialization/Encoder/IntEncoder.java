package Serialization.Encoder;

import Serialization.Encoder.Core.ZigZag;
import Serialization.Encoder.Core.IEncoder;

public class IntEncoder implements IEncoder<Long> {
    private static final long MAX_VALUE = Long.MAX_VALUE / 2;
    private static final long MIN_VALUE = Long.MIN_VALUE / 2;

    @Override
    public byte[] encode(Long signed) {
        checkDiapason(signed);

        IEncoder<Long> serializer = new UIntEncoder();
        long unsigned = ZigZag.wrap(signed);
        
        return serializer.encode(unsigned);
    }

    @Override
    public Long decode(byte[] bytes) {
        IEncoder<Long> serializer = new UIntEncoder();
        long unsignedResult = serializer.decode(bytes);

        System.out.println(unsignedResult);
        
        return ZigZag.unwrap(unsignedResult);
    }

    private void checkDiapason(Long value) {
        if (value > MAX_VALUE || value < MIN_VALUE) {
            throw new RuntimeException("Incorrect diapason");
        }
    }
}


