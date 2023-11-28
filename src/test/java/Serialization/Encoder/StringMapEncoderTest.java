package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class StringMapEncoderTest extends TestCase {
    @Test
    public void testCoding() {
        var test = List.of(
                Map.of("1", "Alexander", "2", "Dmitry")
        );
        StringMapEncoder coder = new StringMapEncoder();

        for (Map<String, String> x : test) {
            byte[] encodedBytes = coder.encode(x);
            DecoderResult<Map<String, String>> decoded = coder.decode(encodedBytes, 0);

            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(x, decoded.getDecoderResult());
        }
    }

}