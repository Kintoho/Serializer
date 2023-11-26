package Serialization.Encoder;

import org.apache.commons.lang3.ArrayUtils;

import java.nio.charset.StandardCharsets;

public class StringEncoder implements IEncoder<String> {

    VarIntEncoder varIntEncoder = new VarIntEncoder();

    @Override
    public byte[] encode(String data) {
        byte[] value = data.getBytes(StandardCharsets.UTF_8);
        byte[] length = varIntEncoder.encode(value.length);

        return ArrayUtils.addAll(length, value);
    }

    @Override
    public String decode(byte[] data) {
        return "Переделай меня";
    }
}
