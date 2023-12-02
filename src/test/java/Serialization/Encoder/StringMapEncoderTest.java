package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

public class StringMapEncoderTest {
    @Test
    public void testCoding() {
        var test = List.of(
                Map.of("1", "Alexander", "2", "Dmitry"),
                Map.of("jhvkjeagvj", "ertyuio;plkjhnbvm,.", "123456890", "zxcbm,sfghjklwetuio",
                        "help me", "i am dying"));
        IEncoder<Map<String, String>> coder = new StringMapEncoder();

        for (Map<String, String> x : test) {
            byte[] encodedBytes = coder.encode(x);
            DecoderResult<Map<String, String>> decoded = coder.decode(encodedBytes, 0);

            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(x, decoded.getDecoderResult());
        }
    }

}