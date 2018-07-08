import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface DataPipe extends Closeable {

    @NotNull
    DataPipe flush() throws IOException;

    @NotNull
    InputStream getInputStream();

    @NotNull
    OutputStream getOutputStream();

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

    int skipBytes(int n) throws IOException;

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
    DataPipe writeChars(@NotNull String s) throws IOException;

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

    @Override
    void close() throws IOException;

    default byte read() throws IOException {
        return readByte();
    }

    @NotNull
    default DataPipe write(int v) throws IOException {
        return writeByte(v);
    }
}
