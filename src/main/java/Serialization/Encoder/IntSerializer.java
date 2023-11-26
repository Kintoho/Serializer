package Serialization.Encoder;

import Serialization.Encoder.Core.UIntSerializer;
import Serialization.Encoder.Core.ZigZag;
import Serialization.Encoder.Core.ISerializer;

public class IntSerializer implements ISerializer<Long> {
    @Override
    public byte[] encode(Long signed) {
        ISerializer<Long> serializer = new UIntSerializer();
        Long unsigned = ZigZag.wrap(signed);
        
        return serializer.encode(unsigned);
    
    }

    @Override
    public Long decode(byte[] bytes) {
        ISerializer<Long> serializer = new UIntSerializer();
        Long unsignedResult = serializer.decode(bytes);
        
        return ZigZag.unwrap(unsignedResult);
    }

}
