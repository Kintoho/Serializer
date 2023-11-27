package Serialization.Encoder;

import Serialization.Encoder.Core.IEncoder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StringMapEncoder {

    private static final IEncoder<Long> intEncoder = new IntEncoder(); //TODO: Првоерить тип
    private static final IEncoder<String> stringEncoder = new StringEncoder();
    public byte[] encode(Map<String, String> data) {

        List<byte[]> encodedData = new LinkedList<>();
        for (String key : data.keySet()) {
            encodedData.add(stringEncoder.encode(key));
            encodedData.add(stringEncoder.encode(data.get(key)));
            System.out.println("ID = " + key + ", День недели = " + data.get(key));
        }
        System.out.println();

        byte[] result = new byte[1000]; // TODO:
        splitList(result, encodedData);
        return result;
    }

    private void splitList(byte[] result, List<byte[]> bytesList) {
        int pos = 0;
        for (byte[] bytes : bytesList) {
            System.arraycopy(bytes, 0, result, pos, bytes.length);
            pos += bytes.length;
        }
    }
}
