package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

public class ColumnEncoderTest {
    @Test
    public void testCoding() {
        var test = List.of(
                Pair.of("1", List.of(Double.MAX_VALUE / 2, Double.MIN_VALUE / 2, -1231231.123D, 123.4356D, 0.000001D,
                        20.1234689D, -56.98765D, 123456890.123456789987D, -123456890.123456789987D)));

        IEncoder<Pair<String, List<Double>>> coder = new ColumnEncoder<>();

        for (Pair<String, List<Double>> x : test) {
            byte[] encodedBytes = coder.encode(x);
            DecoderResult<Pair<String, List<Double>>> decoded = coder.decode(encodedBytes, 0);

            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(x, decoded.getDecoderResult());
        }
    }

}