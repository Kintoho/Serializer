package Serialization.Encoder;

import Serialization.Encoder.Core.DecoderResult;
import Serialization.Encoder.Core.IEncoder;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LongSerializerTest {

    @Test
    public void decodingTest() {
        List<Long> testData = List.of(Long.MAX_VALUE / 2, Long.MIN_VALUE / 2, -1231231L, 123L, 0L, 20L, -56L, 123456890L, -123456890L);
        IEncoder<Long> coder = new LongEncoder();

        for (long x : testData) {
            byte[] encodedBytes = coder.encode(x);
            DecoderResult<Long> decoded = coder.decode(encodedBytes);
            
            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(x, decoded.getDecoderResult().longValue());
        }
    }

    @Test
    public void testMinMaxValues() {
        List<Long> wrongData = List.of(Long.MAX_VALUE, Long.MAX_VALUE - 500, Long.MIN_VALUE, Long.MIN_VALUE + 500);
        IEncoder<Long> coder = new LongEncoder();
        for (long x : wrongData) {
            assertThrows(RuntimeException.class, () -> coder.encode(x));
        }
    }
}