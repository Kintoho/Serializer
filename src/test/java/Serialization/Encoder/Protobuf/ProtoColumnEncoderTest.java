package Serialization.Encoder.Protobuf;

import Serialization.Encoder.Core.DecoderResult;
import com.google.protobuf.ListValue;
import com.google.protobuf.Value;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ProtoColumnEncoderTest {

    @Test
    public void testEncode() {
        ProtoColumnEncoder coder = new ProtoColumnEncoder();

        ListValue.Builder builder = ListValue.newBuilder();
        Value.Builder valueBuilder = Value.newBuilder();
        for (int i = 1; i <= 100_000; i++){
            Value value = valueBuilder.setStringValue("Value: " + i).build();
            builder.addValues(value);
        }

        Map<String, ListValue> map = new HashMap<>();
        for (int i = 1; i <= 100; i++){
            map.put(Integer.toString(i), builder.build());

        }

        long startEncode = System.currentTimeMillis();
        byte[] encodedBytes = coder.encode(map);
        long endEncode = System.currentTimeMillis();
        System.out.println("Time to encode " + (endEncode - startEncode));
        System.out.println("Memory: " + encodedBytes.length);

        long startDecode = System.currentTimeMillis();
        DecoderResult<Map<String, ListValue>> decoded = coder.decode(encodedBytes);
        long endDecode = System.currentTimeMillis();
        System.out.println("Time to decode " + (endDecode - startDecode));

        assertEquals(encodedBytes.length, decoded.getLength());
        assertEquals(map, decoded.getDecoderResult());
    }
}