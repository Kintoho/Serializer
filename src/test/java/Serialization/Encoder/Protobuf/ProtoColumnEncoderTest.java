package Serialization.Encoder.Protobuf;

import Serialization.Encoder.Core.DecoderResult;
import com.google.protobuf.ListValue;
import com.google.protobuf.Value;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ProtoColumnEncoderTest extends TestCase {

    @Test
    public void testEncode() {
        ProtoColumnEncoder coder = new ProtoColumnEncoder();

        Value value = Value.newBuilder().setNumberValue(132.231D).build();
        Value valueMax = Value.newBuilder().setNumberValue(Double.MAX_VALUE / 2).build();
        Value valueMin = Value.newBuilder().setNumberValue(Double.MIN_VALUE / 2).build();

        ListValue listValue = ListValue.newBuilder().addValues(value).addValues(valueMax).addValues(valueMin).build();
        Map<String, ListValue> map = new HashMap<>();
        map.put("First", listValue);

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