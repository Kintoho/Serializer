package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class StringEncoderTest {
    @Test
    public void testCoding() {
        List<String> expList = List.of("12ufshgvadbfn;l", "qwertyuioasdfghjkzxcv", "", "123.4567", "/n/n/n/t/t/n");
        IEncoder<String> coder = new StringEncoder();

        for (String x : expList) {
            byte[] encodedBytes = coder.encode(x);
            DecoderResult<String> decoded = coder.decode(encodedBytes);

            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(x, decoded.getDecoderResult());
        }
    }
}
