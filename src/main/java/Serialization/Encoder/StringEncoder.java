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
        return "Переделай меня";
    }
}
