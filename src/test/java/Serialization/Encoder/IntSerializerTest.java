package Serialization.Encoder;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class IntSerializerTest {

    @Test
    public void decodingTest() {
        List<Integer> testData = List.of(Integer.MAX_VALUE / 2, Integer.MIN_VALUE / 2, -1231231, 123, 0, 20, -56, 123456890, -123456890);
        IntEncoder coder = new IntEncoder();

        for (Integer x : testData) {
            byte[] encodedBytes = coder.encode(x);
            DecoderResult<Integer> decoded = coder.decode(encodedBytes);
            
            assertEquals(encodedBytes.length, decoded.getLength());
            assertEquals(x, decoded.getDecoderResult());
        }
    }

    @Test
    public void testMinMaxValues() {
        List<Integer> wrongData = List.of(Integer.MAX_VALUE, Integer.MAX_VALUE - 500, Integer.MIN_VALUE, Integer.MIN_VALUE + 500);
        IntEncoder coder = new IntEncoder();
        for (Integer x : wrongData) {
            assertThrows(RuntimeException.class, () -> coder.encode(x));
        }
    }
}