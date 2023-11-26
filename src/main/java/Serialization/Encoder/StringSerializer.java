package Serialization.Encoder;

import org.apache.commons.lang3.ArrayUtils;

import Serialization.Encoder.Core.ISerializer;

import java.nio.charset.StandardCharsets;

public class StringSerializer implements ISerializer<String> {

    IntSerializer varIntEncoder = new IntSerializer();

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
