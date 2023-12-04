package Serialization.Encoder.Protobuf;

import Serialization.Encoder.Core.DecoderResult;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ListValue;

import java.util.Map;

public class ProtoColumnEncoder {
    public byte[] encode(Map<String, ListValue> data) {
        ColumnOuterClass.Column columnOuterClass = ColumnOuterClass.Column.newBuilder().putAllData(data).build();
        return columnOuterClass.toByteArray();
    }

    public DecoderResult<Map<String, ListValue>> decode(byte[] bytes) {
        ColumnOuterClass.Column parsedColumn;
        try {
            parsedColumn = ColumnOuterClass.Column.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        return new DecoderResult<>(parsedColumn.getDataMap(), bytes.length);
    }
}
