package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ListEncoderTest extends TestCase {
    @Test
    public void testCoding() {
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
                        List.of("12ufshgvadbfn;l", "qwertyuioasdfghjkzxcv", "", "123.4567", "/n/n/n/t/t/n")));

        for (var test : testList) {
            var encoder = (IEncoder) test.get(0);
            var array = test.get(1);

            byte[] encodedBytes = encoder.encode(array);
            DecoderResult<List<Double>> decoded = encoder.decode(encodedBytes, 0);

            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(array, decoded.getDecoderResult());
        }

    }
}