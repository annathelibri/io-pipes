package pw.aru.utils.io;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface DataPipe extends Closeable {
    //Raw IO
    @NotNull
    OutputStream getOutputStream();

    @NotNull
    InputStream getInputStream();

    //Content Control
    @Override
    void close() throws IOException;

    @NotNull
    DataPipe flush() throws IOException;

    int skipBytes(int n) throws IOException;

    //Default Reads
    boolean readBoolean() throws IOException;

    byte readByte() throws IOException;

    char readChar() throws IOException;

    double readDouble() throws IOException;

    float readFloat() throws IOException;

    @NotNull
    DataPipe readFully(@NotNull byte b[]) throws IOException;

    @NotNull
    DataPipe readFully(@NotNull byte b[], int off, int len) throws IOException;

    int readInt() throws IOException;

    long readLong() throws IOException;

    short readShort() throws IOException;

    @NotNull
    String readString() throws IOException;

    int readUnsignedByte() throws IOException;

    int readUnsignedShort() throws IOException;

    //Default Writes
    @NotNull
    DataPipe writeBoolean(boolean v) throws IOException;

    @NotNull
    DataPipe writeByte(int b) throws IOException;

    @NotNull
    DataPipe writeBytes(@NotNull byte b[]) throws IOException;

    @NotNull
    DataPipe writeBytes(@NotNull byte b[], int off, int len) throws IOException;

    @NotNull
    DataPipe writeBytes(@NotNull String s) throws IOException;

    @NotNull
    DataPipe writeChar(int v) throws IOException;

    @NotNull
    DataPipe writeChars(@NotNull CharSequence s) throws IOException;

    @NotNull
    DataPipe writeDouble(double v) throws IOException;

    @NotNull
    DataPipe writeFloat(float v) throws IOException;

    @NotNull
    DataPipe writeInt(int v) throws IOException;

    @NotNull
    DataPipe writeLong(long v) throws IOException;

    @NotNull
    DataPipe writeShort(int v) throws IOException;

    @NotNull
    DataPipe writeString(@NotNull String s) throws IOException;

    //Byte shortcuts
    default byte read() throws IOException {
        return readByte();
    }

    @NotNull
    default DataPipe write(int v) throws IOException {
        return writeByte(v);
    }

    //Read Arrays
    @NotNull
    default boolean[] readBooleanArray(int length) throws IOException {
        boolean[] array = new boolean[length];
        for (int i = 0; i < array.length; i++) array[i] = readBoolean();
        return array;
    }

    @NotNull
    default byte[] readByteArray(int length) throws IOException {
        byte[] array = new byte[length];
        for (int i = 0; i < array.length; i++) array[i] = readByte();
        return array;
    }

    @NotNull
    default char[] readCharArray(int length) throws IOException {
        char[] array = new char[length];
        for (int i = 0; i < array.length; i++) array[i] = readChar();
        return array;
    }

    @NotNull
    default double[] readDoubleArray(int length) throws IOException {
        double[] array = new double[length];
        for (int i = 0; i < array.length; i++) array[i] = readDouble();
        return array;
    }

    @NotNull
    default float[] readFloatArray(int length) throws IOException {
        float[] array = new float[length];
        for (int i = 0; i < array.length; i++) array[i] = readFloat();
        return array;
    }

    @NotNull
    default int[] readIntArray(int length) throws IOException {
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) array[i] = readInt();
        return array;
    }

    @NotNull
    default long[] readLongArray(int length) throws IOException {
        long[] array = new long[length];
        for (int i = 0; i < array.length; i++) array[i] = readLong();
        return array;
    }

    @NotNull
    default short[] readShortArray(int length) throws IOException {
        short[] array = new short[length];
        for (int i = 0; i < array.length; i++) array[i] = readShort();
        return array;
    }

    @NotNull
    default String[] readStringArray(int length) throws IOException {
        String[] array = new String[length];
        for (int i = 0; i < array.length; i++) array[i] = readString();
        return array;
    }

    //Read Sized Arrays
    @NotNull
    default boolean[] readSizedBooleanArray() throws IOException {
        return readBooleanArray(readInt());
    }

    @NotNull
    default byte[] readSizedByteArray() throws IOException {
        return readByteArray(readInt());
    }

    @NotNull
    default char[] readSizedCharArray() throws IOException {
        return readCharArray(readInt());
    }

    @NotNull
    default double[] readSizedDoubleArray() throws IOException {
        return readDoubleArray(readInt());
    }

    @NotNull
    default float[] readSizedFloatArray() throws IOException {
        return readFloatArray(readInt());
    }

    @NotNull
    default int[] readSizedIntArray() throws IOException {
        return readIntArray(readInt());
    }

    @NotNull
    default long[] readSizedLongArray() throws IOException {
        return readLongArray(readInt());
    }

    @NotNull
    default short[] readSizedShortArray() throws IOException {
        return readShortArray(readInt());
    }

    @NotNull
    default String[] readSizedStringArray() throws IOException {
        return readStringArray(readInt());
    }

    //Write Arrays
    @NotNull
    default DataPipe writeBooleanArray(@NotNull boolean[] array) throws IOException {
        for (boolean v : array) writeBoolean(v);
        return this;
    }

    @NotNull
    default DataPipe writeByteArray(@NotNull byte[] array) throws IOException {
        for (byte v : array) writeByte(v);
        return this;
    }

    @NotNull
    default DataPipe writeCharArray(@NotNull char[] array) throws IOException {
        for (char v : array) writeChar(v);
        return this;
    }

    @NotNull
    default DataPipe writeDoubleArray(@NotNull double[] array) throws IOException {
        for (double v : array) writeDouble(v);
        return this;
    }

    @NotNull
    default DataPipe writeFloatArray(@NotNull float[] array) throws IOException {
        for (float v : array) writeFloat(v);
        return this;
    }

    @NotNull
    default DataPipe writeIntArray(@NotNull int[] array) throws IOException {
        for (int v : array) writeInt(v);
        return this;
    }

    @NotNull
    default DataPipe writeLongArray(@NotNull long[] array) throws IOException {
        for (long v : array) writeLong(v);
        return this;
    }

    @NotNull
    default DataPipe writeShortArray(@NotNull short[] array) throws IOException {
        for (short v : array) writeShort(v);
        return this;
    }

    @NotNull
    default DataPipe writeStringArray(@NotNull String[] array) throws IOException {
        for (String v : array) writeString(v);
        return this;
    }

    //Write Sized Arrays
    @NotNull
    default DataPipe writeSizedBooleanArray(@NotNull boolean[] array) throws IOException {
        return writeInt(array.length).writeBooleanArray(array);
    }

    @NotNull
    default DataPipe writeSizedByteArray(@NotNull byte[] array) throws IOException {
        return writeInt(array.length).writeByteArray(array);
    }

    @NotNull
    default DataPipe writeSizedCharArray(@NotNull char[] array) throws IOException {
        return writeInt(array.length).writeCharArray(array);
    }

    @NotNull
    default DataPipe writeSizedDoubleArray(@NotNull double[] array) throws IOException {
        return writeInt(array.length).writeDoubleArray(array);
    }

    @NotNull
    default DataPipe writeSizedFloatArray(@NotNull float[] array) throws IOException {
        return writeInt(array.length).writeFloatArray(array);
    }

    @NotNull
    default DataPipe writeSizedIntArray(@NotNull int[] array) throws IOException {
        return writeInt(array.length).writeIntArray(array);
    }

    @NotNull
    default DataPipe writeSizedLongArray(@NotNull long[] array) throws IOException {
        return writeInt(array.length).writeLongArray(array);
    }

    @NotNull
    default DataPipe writeSizedShortArray(@NotNull short[] array) throws IOException {
        return writeInt(array.length).writeShortArray(array);
    }

    @NotNull
    default DataPipe writeSizedStringArray(@NotNull String[] array) throws IOException {
        return writeInt(array.length).writeStringArray(array);
    }
}
