package Serialization.Encoder;

import Serialization.Encoder.Core.ZigZag;
import Serialization.Encoder.Core.IEncoder;

public class IntEncoder implements IEncoder<Long> {
    @Override
    public byte[] encode(Long signed) {
        IEncoder<Long> serializer = new UIntEncoder();
        Long unsigned = ZigZag.wrap(signed);
        
        return serializer.encode(unsigned);
    
    }

    @Override
    public Long decode(byte[] bytes) {
        IEncoder<Long> serializer = new UIntEncoder();
        Long unsignedResult = serializer.decode(bytes);
        System.out.println("Testing: " + unsignedResult);
        return ZigZag.unwrap(unsignedResult);
    }

}
