package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DoubleEncoderTest {
    @Test
    public void decodingTest() {
        List<Double> testData = List.of(Double.MAX_VALUE / 2, Double.MIN_VALUE / 2, -1231231.123D, 123.4356D, 0.000001D,
                20.1234689D, -56.98765D, 123456890.123456789987D, -123456890.123456789987D);
        IEncoder<Double> coder = new DoubleEncoder();

        for (Double x : testData) {
            byte[] encodedBytes = coder.encode(x);
            DecoderResult<Double> decoded = coder.decode(encodedBytes);

            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(x, decoded.getDecoderResult());
        }
    }

    @Test
    public void testMinMaxValues() {
        List<Double> wrongData = List.of(Double.MAX_VALUE, Double.MAX_VALUE - 500, -Double.MAX_VALUE,
                -Double.MAX_VALUE + 500);
        IEncoder<Double> coder = new DoubleEncoder();
        
        for (Double x : wrongData) {
            assertThrows(RuntimeException.class, () -> coder.encode(x));
        }
    }
}