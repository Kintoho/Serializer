package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

import java.util.ArrayList;
import java.util.List;

public class UIntEncoder implements IEncoder<Long> {
    public static final IEncoder<Long> encoder = new UIntEncoder();

    @Override
    public byte[] encode(Long unsigned) {
        List<Byte> encodedBytesList = new ArrayList<>();

        while (unsigned > 0) {
            byte varByte = (byte) (unsigned & 0x7f);
            unsigned = unsigned >> 7;
            varByte |= (unsigned > 0) ? 0x80 : 0x0;
            encodedBytesList.add(varByte);
        }

        if (encodedBytesList.isEmpty()) {
            encodedBytesList.add((byte) 0);
        }

        byte[] encodedBytes = new byte[encodedBytesList.size()];
        for (int i = 0; i < encodedBytesList.size(); i++) {
            encodedBytes[i] = encodedBytesList.get(i).byteValue();
        }

        return encodedBytes;
    }

    @Override
    public DecoderResult<Long> decode(byte[] encodedData, int fromByte) {
        long unsigned = 0;
        byte shift = 0;

        int bytesCount;
        for (bytesCount = 0; bytesCount < encodedData.length; bytesCount++) {
            unsigned |= (long) (encodedData[bytesCount + fromByte] & 0x7f) << shift;
            shift += 7;

            if ((encodedData[bytesCount + fromByte] & 0x80) == 0) {
                break;
            }
        }
        bytesCount++;

        if (bytesCount > Long.BYTES + 1) {
            throw new RuntimeException("Too many encodedData in Long");
        }

        return new DecoderResult<>(unsigned, bytesCount);
    }

    @Override
    public DecoderResult<Long> decode(byte[] encodedData) {
        return decode(encodedData, 0);
    }
}
