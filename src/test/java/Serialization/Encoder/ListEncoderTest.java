package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class ListEncoderTest extends TestCase {
    @Test
    public void testCoding() {
        List<Double> test_1 = List.of(0.001D, 345.0987654321D, 678.123D, 12763.0979876D);
        IEncoder<List<Double>> coder = new ListEncoder<>();

        byte[] encodedBytes = coder.encode(test_1);
        DecoderResult<List<Double>> decoded = coder.decode(encodedBytes, 0);

        assertEquals(encodedBytes.length, decoded.getLength());
        assertEquals(test_1, decoded.getDecoderResult());
    }
}