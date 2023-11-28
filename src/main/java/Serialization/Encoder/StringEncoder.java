package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import org.apache.commons.lang3.ArrayUtils;

import Serialization.Encoder.Core.IEncoder;

import java.nio.charset.StandardCharsets;

public class StringEncoder implements IEncoder<String> {

    IEncoder<Integer> intEncoder = new IntEncoder();

    @Override
    public byte[] encode(String data) {
        byte[] value = data.getBytes(StandardCharsets.UTF_8);
        byte[] length = intEncoder.encode(value.length);
        return ArrayUtils.addAll(length, value);
    }

    @Override
    public DecoderResult<String> decode(byte[] encodedData, int fromByte) {
        DecoderResult<Integer> result = intEncoder.decode(encodedData, fromByte);
        String str = new String(encodedData, fromByte + result.getLength(), result.getDecoderResult(), StandardCharsets.UTF_8);
        return new DecoderResult<>(str, result.getLength() + result.getDecoderResult());
    }

    @Override
    public DecoderResult<String> decode(byte[] encodedData){
        return decode(encodedData, 0);
    }
}
