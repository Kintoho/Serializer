package Serialization.Encoder;

import org.apache.commons.lang3.ArrayUtils;

import Serialization.Encoder.Core.IEncoder;

import java.nio.charset.StandardCharsets;

public class StringEncoder implements IEncoder<String> {

    IntEncoder varIntEncoder = new IntEncoder();

    @Override
    public byte[] encode(String data) {
        byte[] value = data.getBytes(StandardCharsets.UTF_8);
        byte[] length = varIntEncoder.encode((long) value.length);

        return ArrayUtils.addAll(length, value);
    }

    @Override
    public String decode(byte[] data) {
        Long result = varIntEncoder.decode(data);
        String str = new String(data, data.length, Math.toIntExact(result), StandardCharsets.UTF_8); // TODO: type
        return str;
    }
}
