package Serialization.Encoder.Comparison;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import com.google.protobuf.ListValue;

import Serialization.Encoder.MapListEncoder;
import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;
import Serialization.Encoder.Protobuf.ProtoColumnEncoder;

public class TestComparison {
    private static final int rows = 100_000;
    private static final int cols = 100;

    @Test
    public void testInteger() {
        Pair<Map<String, List<Integer>>, Map<String, ListValue>> mapsPair = Generate.generateInteger(rows, cols);

        IEncoder<Map<String, List<Integer>>> myCoder = new MapListEncoder<>();
        ProtoColumnEncoder protoCoder = new ProtoColumnEncoder();

        System.out.println("TESTING INTEGER LIST");

        //!! 1
        long startEncode = System.currentTimeMillis();

        byte[] myBytes = myCoder.encode(mapsPair.getLeft());

        long endEncode = System.currentTimeMillis();
        System.out.println("Time to encode with MY method - " + (endEncode - startEncode));
        System.out.println("Memory with MY method: - " + myBytes.length);
        System.out.println();

        //!! 2
        startEncode = System.currentTimeMillis();

        byte[] protoBytes = protoCoder.encode(mapsPair.getRight());

        endEncode = System.currentTimeMillis();
        System.out.println("Time to encode with PROTO method - " + (endEncode - startEncode));
        System.out.println("Memory with PROTO method: - " + protoBytes.length);
        System.out.println();

        //!! 3
        startEncode = System.currentTimeMillis();

        DecoderResult<Map<String, List<Integer>>> myResult = myCoder.decode(myBytes);

        endEncode = System.currentTimeMillis();
        System.out.println("Time to decode with MY method - " + (endEncode - startEncode));

        //!! 4
        startEncode = System.currentTimeMillis();

        DecoderResult<Map<String, ListValue>> protoResult = protoCoder.decode(protoBytes);

        endEncode = System.currentTimeMillis();
        System.out.println("Time to decode with PROTO method - " + (endEncode - startEncode));
        System.out.println();

        // assertEquals(myResult.getDecoderResult(), protoResult.getDecoderResult());
    }

    @Test
    public void testLong() {
        Pair<Map<String, List<Long>>, Map<String, ListValue>> mapsPair = Generate.generateLong(rows, cols);

        IEncoder<Map<String, List<Long>>> myCoder = new MapListEncoder<>();
        ProtoColumnEncoder protoCoder = new ProtoColumnEncoder();

        System.out.println("TESTING LONG LIST");

        //!! 1
        long startEncode = System.currentTimeMillis();

        byte[] myBytes = myCoder.encode(mapsPair.getLeft());

        long endEncode = System.currentTimeMillis();
        System.out.println("Time to encode with MY method - " + (endEncode - startEncode));
        System.out.println("Memory with MY method: - " + myBytes.length);
        System.out.println();

        //!! 2
        startEncode = System.currentTimeMillis();

        byte[] protoBytes = protoCoder.encode(mapsPair.getRight());

        endEncode = System.currentTimeMillis();
        System.out.println("Time to encode with PROTO method - " + (endEncode - startEncode));
        System.out.println("Memory with PROTO method: - " + protoBytes.length);
        System.out.println();

        //!! 3
        startEncode = System.currentTimeMillis();

        DecoderResult<Map<String, List<Long>>> myResult = myCoder.decode(myBytes);

        endEncode = System.currentTimeMillis();
        System.out.println("Time to decode with MY method - " + (endEncode - startEncode));

        //!! 4
        startEncode = System.currentTimeMillis();

        DecoderResult<Map<String, ListValue>> protoResult = protoCoder.decode(protoBytes);

        endEncode = System.currentTimeMillis();
        System.out.println("Time to decode with PROTO method - " + (endEncode - startEncode));
        System.out.println();

        // assertEquals(myResult.getDecoderResult(), protoResult.getDecoderResult());
    }

    @Test
    public void testDouble() {
        Pair<Map<String, List<Double>>, Map<String, ListValue>> mapsPair = Generate.generateDouble(rows, cols);

        IEncoder<Map<String, List<Double>>> myCoder = new MapListEncoder<>();
        ProtoColumnEncoder protoCoder = new ProtoColumnEncoder();

        System.out.println("TESTING DOUBLE LIST");

        //!! 1
        long startEncode = System.currentTimeMillis();

        byte[] myBytes = myCoder.encode(mapsPair.getLeft());

        long endEncode = System.currentTimeMillis();
        System.out.println("Time to encode with MY method - " + (endEncode - startEncode));
        System.out.println("Memory with MY method: - " + myBytes.length);
        System.out.println();

        //!! 2
        startEncode = System.currentTimeMillis();

        byte[] protoBytes = protoCoder.encode(mapsPair.getRight());

        endEncode = System.currentTimeMillis();
        System.out.println("Time to encode with PROTO method - " + (endEncode - startEncode));
        System.out.println("Memory with PROTO method: - " + protoBytes.length);
        System.out.println();

        //!! 3
        startEncode = System.currentTimeMillis();

        DecoderResult<Map<String, List<Double>>> myResult = myCoder.decode(myBytes);

        endEncode = System.currentTimeMillis();
        System.out.println("Time to decode with MY method - " + (endEncode - startEncode));

        //!! 4
        startEncode = System.currentTimeMillis();

        DecoderResult<Map<String, ListValue>> protoResult = protoCoder.decode(protoBytes);

        endEncode = System.currentTimeMillis();
        System.out.println("Time to decode with PROTO method - " + (endEncode - startEncode));
        System.out.println();

        // assertEquals(myResult.getDecoderResult(), protoResult.getDecoderResult());
    }

    @Test
    public void testString() {
        Pair<Map<String, List<String>>, Map<String, ListValue>> mapsPair = Generate.generateString(rows, cols);

        IEncoder<Map<String, List<String>>> myCoder = new MapListEncoder<>();
        ProtoColumnEncoder protoCoder = new ProtoColumnEncoder();

        System.out.println("TESTING STRING LIST");

        //!! 1
        long startEncode = System.currentTimeMillis();

        byte[] myBytes = myCoder.encode(mapsPair.getLeft());

        long endEncode = System.currentTimeMillis();
        System.out.println("Time to encode with MY method - " + (endEncode - startEncode));
        System.out.println("Memory with MY method: - " + myBytes.length);
        System.out.println();

        //!! 2
        startEncode = System.currentTimeMillis();

        byte[] protoBytes = protoCoder.encode(mapsPair.getRight());

        endEncode = System.currentTimeMillis();
        System.out.println("Time to encode with PROTO method - " + (endEncode - startEncode));
        System.out.println("Memory with PROTO method: - " + protoBytes.length);
        System.out.println();

        //!! 3
        startEncode = System.currentTimeMillis();

        DecoderResult<Map<String, List<String>>> myResult = myCoder.decode(myBytes);

        endEncode = System.currentTimeMillis();
        System.out.println("Time to decode with MY method - " + (endEncode - startEncode));

        //!! 4
        startEncode = System.currentTimeMillis();

        DecoderResult<Map<String, ListValue>> protoResult = protoCoder.decode(protoBytes);

        endEncode = System.currentTimeMillis();
        System.out.println("Time to decode with PROTO method - " + (endEncode - startEncode));
        System.out.println();

        // assertEquals(myResult.getDecoderResult(), protoResult.getDecoderResult());
    }
}
