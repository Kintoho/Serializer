package Serialization.Encoder;

import Serialization.Encoder.Core.IEncoder;

import java.util.ArrayList;
import java.util.List;

public class UIntEncoder implements IEncoder<Long> {
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
    public DecoderResult<Long> decode(byte[] bytes) {
        long unsigned = 0;
        byte shift = 0;

        int bytesCount;
        for (bytesCount = 0; bytesCount < bytes.length; bytesCount++) {
            unsigned |= (long) (bytes[bytesCount] & 0x7f) << shift;
            shift += 7;

            if ((bytes[bytesCount] & 0x80) == 0) {
                break;
            }

        }

        if (bytesCount > Long.BYTES + 1) {
            throw new RuntimeException("Too many bytes in Long");
        }
        
        return new DecoderResult<>(unsigned, bytes.length);
    }

}
