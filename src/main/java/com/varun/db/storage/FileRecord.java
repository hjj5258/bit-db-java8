package com.varun.db.storage;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.varun.db.util.FileRecordConfig.*;

public class FileRecord {
    private long timestamp;
    private int keySize;
    private int valueSize;
    private String key;
    private String value;

    public FileRecord(long timestamp, int keySize, int valueSize, String key, String value) {
        this.timestamp = timestamp;
        this.keySize = keySize;
        this.valueSize = valueSize;
        this.key = key;
        this.value = value;
    }

    public static FileRecord buildFileRecord(byte[] bytes) {
        long timestamp = parseTimestamp(bytes);
        int keySize = parseKeySize(bytes);
        int valueSize = parseValueSize(bytes);
        String key = parseKey(bytes, keySize);
        String value = parseValue(bytes,/* valuePosition= */ KEY_OFFSET + keySize, valueSize);

        return new FileRecord(timestamp, keySize, valueSize, key, value);
    }

    private static long parseTimestamp(byte[] bytes) {
        byte[] longBytes = Arrays.copyOfRange(bytes, TIMESTAMP_OFFSET, TIMESTAMP_OFFSET + TIMESTAMP_LENGTH);
        return Longs.fromByteArray(longBytes);
    }

    private static int parseKeySize(byte[] bytes) {
        byte[] intBytes = Arrays.copyOfRange(bytes, KEY_SIZE_OFFSET, KEY_SIZE_OFFSET + KEY_SIZE_LENGTH);
        return Ints.fromByteArray(intBytes);
    }

    private static int parseValueSize(byte[] bytes) {
        byte[] intBytes = Arrays.copyOfRange(bytes, VALUE_SIZE_OFFSET, VALUE_SIZE_OFFSET + VALUE_SIZE_LENGTH);
        return Ints.fromByteArray(intBytes);
    }

    private static String parseKey(byte[] bytes, int keySize) {
        byte[] keyBytes = Arrays.copyOfRange(bytes, KEY_OFFSET, KEY_OFFSET + keySize);
        return new String(keyBytes, StandardCharsets.UTF_8);
    }

    private static String parseValue(byte[] bytes, int valuePosition, int valueSize) {
        byte[] valueBytes = Arrays.copyOfRange(bytes, valuePosition, valuePosition + valueSize);
        return new String(valueBytes, StandardCharsets.UTF_8);
    }

    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(Longs.toByteArray(getTimestamp()));
        outputStream.write(Ints.toByteArray(getKeySize()));
        outputStream.write(Ints.toByteArray(getValueSize()));
        outputStream.write(key.getBytes());
        outputStream.write(value.getBytes());
        return outputStream.toByteArray();
    }





    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public int getValueSize() {
        return valueSize;
    }

    public void setValueSize(int valueSize) {
        this.valueSize = valueSize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}