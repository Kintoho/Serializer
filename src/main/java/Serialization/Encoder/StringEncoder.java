package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import org.apache.commons.lang3.ArrayUtils;

import Serialization.Encoder.Core.IEncoder;

import java.nio.charset.StandardCharsets;

public class StringEncoder implements IEncoder<String> {
    @Override
    public byte[] encode(String data) {
        byte[] value = data.getBytes(StandardCharsets.UTF_8);
        byte[] length = UIntEncoder.coder.encode((long) value.length);

        return ArrayUtils.addAll(length, value);
    }

    @Override
    public DecoderResult<String> decode(byte[] encodedData, int fromByte) {
        DecoderResult<Long> result = UIntEncoder.coder.decode(encodedData, fromByte);
        String str = new String(encodedData, fromByte + result.getLength(), result.getDecoderResult().intValue(),
                StandardCharsets.UTF_8);

        return new DecoderResult<>(str, result.getLength() + result.getDecoderResult().intValue());
    }

    @Override
    public DecoderResult<String> decode(byte[] encodedData) {
        return decode(encodedData, 0);
    }
}
