package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class StringSerializerTest {
    @Test
    public void testCoding() {
        List<String> expList = List.of("Lala2", "Lallre", "Я могучий", "12345 вышел зайчик");
        IEncoder<String> coder = new StringEncoder();
        
        for (String x : expList) {
            byte[] encodedBytes = coder.encode(x);
            DecoderResult<String> decoded = coder.decode(encodedBytes);
            
            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(x, decoded.getDecoderResult());
        }
    }
}
