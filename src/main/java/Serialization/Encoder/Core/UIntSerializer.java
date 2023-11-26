package Serialization.Encoder.Core;

import java.util.ArrayList;
import java.util.List;

public class UIntSerializer implements ISerializer<Long> {
    @Override
    public byte[] encode(Long unsigned) {
        List<Byte> encodedBytesList = new ArrayList<>();

        while (unsigned > 0) {
            byte varByte = (byte) (unsigned & 0x7f);
            unsigned = unsigned >> 7;
            varByte |= (unsigned > 0) ? 0x80 : 0x0;
            encodedBytesList.add(varByte);
        }

        byte[] encodedBytes = new byte[encodedBytesList.size()];
        for (int i = 0; i < encodedBytesList.size(); i++) {
            encodedBytes[i] = encodedBytesList.get(i).byteValue();
        }

        return encodedBytes;
    }

    @Override
    public Long decode(byte[] bytes) {
        long unsigned = 0;
        long shift = 0;

        for (int i = 0; i < bytes.length; i++) {
            unsigned |= (bytes[i] & 0x7f) << shift;
            shift += 7;
        }
        
        return unsigned;
    }

}
