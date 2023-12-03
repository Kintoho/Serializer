package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class ListEncoderTest {
    @Test
    public void test() {
        var testList = List.of(
                List.of(
                        new ListEncoder<Double>(),
                        List.of(Double.MAX_VALUE / 2, Double.MIN_VALUE / 2, -1231231.123D, 123.4356D, 0.000001D,
                                20.1234689D, -56.98765D, 123456890.123456789987D, -123456890.123456789987D)),
                List.of(
                        new ListEncoder<Integer>(),
                        List.of(Integer.MAX_VALUE / 2, Integer.MIN_VALUE / 2, -1231231, 123, 0, 20, -56, 123456890,
                                -123456890)),
                List.of(
                        new ListEncoder<Long>(),
                        List.of(Long.MAX_VALUE / 2, Long.MIN_VALUE / 2, -1231231L, 123L, 0L, 20L, -56L, 123456890L,
                                -123456890L)),
                List.of(
                        new ListEncoder<String>(),
                        List.of("12ufshgvadbfn;l", "qwertyuioasdfghjkzxcv", "", "123.4567", "/n/n/n/t/t/n")),
                        
                List.of(
                        new ListEncoder<List<Long>>(),
                        List.of(
                                List.of(Long.MAX_VALUE / 2, Long.MIN_VALUE / 2, -1231231L, 123L, 0L, 20L, -56L, 123456890L,
                                -123456890L),
                                List.of(Long.MAX_VALUE / 2, Long.MIN_VALUE / 2, -1231231L, 123L, 0L, 20L, -56L, 123456890L,
                                -123456890L)
                        )),
                List.of(
                        new ListEncoder<List<String>>(),
                        List.of(
                                List.of("12ufshgvadbfn;l", "qwertyuioasdfghjkzxcv", "", "123.4567", "/n/n/n/t/t/n"),
                                List.of("12ufshgvadbfn;l", "qwertyuioasdfghjkzxcv", "", "123.4567", "/n/n/n/t/t/n")
                        )),
                List.of(
                        new ListEncoder<List<Double>>(),
                        List.of(
                                List.of(Double.MAX_VALUE / 2, Double.MIN_VALUE / 2, -1231231.123D, 123.4356D, 0.000001D,
                                20.1234689D, -56.98765D, 123456890.123456789987D, -123456890.123456789987D),
                                List.of(Double.MAX_VALUE / 2, Double.MIN_VALUE / 2, -1231231.123D, 123.4356D, 0.000001D,
                                20.1234689D, -56.98765D, 123456890.123456789987D, -123456890.123456789987D)
                        )),
                List.of(
                        new ListEncoder<List<Integer>>(),
                        List.of(
                                List.of(Integer.MAX_VALUE / 2, Integer.MIN_VALUE / 2, -1231231, 123, 0, 20, -56, 123456890,
                                -123456890),
                                List.of(Integer.MAX_VALUE / 2, Integer.MIN_VALUE / 2, -1231231, 123, 0, 20, -56, 123456890,
                                -123456890)
                        )));

        for (var test : testList) {
            var coder = (IEncoder) test.get(0);
            var array = test.get(1);

            byte[] encodedBytes = coder.encode(array);
            DecoderResult<List<Double>> decoded = coder.decode(encodedBytes, 0);

            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(array, decoded.getDecoderResult());
        }
    }

    @Test
    public void test2() {
        var testList = List.of(
                List.of(Long.MAX_VALUE / 2, Long.MIN_VALUE / 2, -1231231L, 123L, 0L, 20L, -56L, 123456890L,
                                -123456890L),
                List.of(Long.MIN_VALUE / 2, Long.MIN_VALUE / 2, -1231231L, 0L, 20L, -56L, 123456890L),
                List.of(Long.MAX_VALUE / 2, Long.MIN_VALUE / 2, -1231231L, 123L, 0L, 20L)
        );

        IEncoder<List<Long>> coder = new ListEncoder<>();

        byte[] encRes1 = coder.encode(testList.get(0));
        byte[] encRes2 = coder.encode(testList.get(1));
        byte[] encRes3 = coder.encode(testList.get(2));

        DecoderResult<List<Long>> res1 = coder.decode(encRes1);
        DecoderResult<List<Long>> res2 = coder.decode(encRes2);
        DecoderResult<List<Long>> res3 = coder.decode(encRes3);

        assertEquals(encRes1.length, res1.getLength());
        assertEquals(testList.get(0), res1.getDecoderResult());
        
        assertEquals(encRes2.length, res2.getLength());
        assertEquals(testList.get(1), res2.getDecoderResult());

        assertEquals(encRes3.length, res3.getLength());
        assertEquals(testList.get(2), res3.getDecoderResult());
    }
}