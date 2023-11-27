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
        List<byte[]> encodedDataList = new LinkedList<>();
        int bytesCounter = 0;
        for (String key : data.keySet()) {
            encodedDataList.add(stringEncoder.encode(key));
            bytesCounter += stringEncoder.encode(key).length;
            encodedDataList.add(stringEncoder.encode(data.get(key)));
            bytesCounter += stringEncoder.encode(key).length;
        }

        byte[] encodedData = new byte[bytesCounter];
        splitList(encodedData, encodedDataList);
        return encodedData;
    }

    private void splitList(byte[] result, List<byte[]> bytesList) {
        int pos = 0;
        for (byte[] bytes : bytesList) {
            System.arraycopy(bytes, 0, result, pos, bytes.length);
            pos += bytes.length;
        }
    }
}
