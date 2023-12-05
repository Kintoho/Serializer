package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapListEncoderTest {
    @Test
    public void testCoding() {
        Map<String, List<String>> dataMap = new HashMap<>();
        List<String> list = new ArrayList<>(100_000);
        for (int i = 1; i <= 100_000; i++) {
            list.add("Value: " + i);
        }
        for (int i = 1; i <= 100; i++) {
            dataMap.put(String.valueOf(i), list);
        }

        IEncoder<Map<String, List<String>>> coder = new MapListEncoder<>();

        long startEncode = System.currentTimeMillis();
        byte[] encodedBytes = coder.encode(dataMap);
        long endEncode = System.currentTimeMillis();
        System.out.println("Time to encode " + (endEncode - startEncode));
        System.out.println("Memory: " + encodedBytes.length);

        long startDecode = System.currentTimeMillis();
        DecoderResult<Map<String, List<String>>> decoded = coder.decode(encodedBytes, 0);
        long endDecode = System.currentTimeMillis();
        System.out.println("Time to decode " + (endDecode - startDecode));

        assertEquals(encodedBytes.length, decoded.getLength());
        assertEquals(dataMap, decoded.getDecoderResult());
    }
}