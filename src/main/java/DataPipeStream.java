import org.jetbrains.annotations.NotNull;

import java.io.*;

public class DataPipeStream implements DataPipe {

    private final InputStream in;
    private final OutputStream out;
    private byte bytearr[] = new byte[80];
    private char chararr[] = new char[80];
    private byte readBuffer[] = new byte[8];
    private byte writeBuffer[] = new byte[8];

    public DataPipeStream(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    @NotNull
    @Override
    public DataPipe flush() throws IOException {
        out.flush();
        return this;
    }

    @NotNull
    @Override
    public InputStream getInputStream() {
        return in;
    }

    @NotNull
    @Override
    public OutputStream getOutputStream() {
        return out;
    }

    @Override
    public final boolean readBoolean() throws IOException {
        int ch = in.read();
        if (ch < 0)
            throw new EOFException();
        return (ch != 0);
    }

    @Override
    public final byte readByte() throws IOException {
        int ch = in.read();
        if (ch < 0) throw new EOFException();
        return (byte) (ch);
    }

    @Override
    public final char readChar() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0) throw new EOFException();
        return (char) ((ch1 << 8) + (ch2));
    }

    @Override
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @NotNull
    @Override
    public final DataPipe readFully(@NotNull byte b[]) throws IOException {
        readFully(b, 0, b.length);
        return this;
    }

    @NotNull
    @Override
    public final DataPipe readFully(@NotNull byte b[], int off, int len) throws IOException {
        if (len < 0)
            throw new IndexOutOfBoundsException();
        int n = 0;
        while (n < len) {
            int count = in.read(b, off + n, len - n);
            if (count < 0)
                throw new EOFException();
            n += count;
        }
        return this;
    }

    @Override
    public final int readInt() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        int ch3 = in.read();
        int ch4 = in.read();
        if ((ch1 | ch2 | ch3 | ch4) < 0) throw new EOFException();
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4));
    }

    @Override
    public final long readLong() throws IOException {
        readFully(readBuffer, 0, 8);
        return (((long) readBuffer[0] << 56) +
            ((long) (readBuffer[1] & 255) << 48) +
            ((long) (readBuffer[2] & 255) << 40) +
            ((long) (readBuffer[3] & 255) << 32) +
            ((long) (readBuffer[4] & 255) << 24) +
            ((readBuffer[5] & 255) << 16) +
            ((readBuffer[6] & 255) << 8) +
            ((readBuffer[7] & 255)));
    }

    @Override
    public final short readShort() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0) throw new EOFException();
        return (short) ((ch1 << 8) + (ch2));
    }

    @NotNull
    @Override
    public final String readString() throws IOException {
        return _readString();
    }

    @Override
    public final int readUnsignedByte() throws IOException {
        int ch = in.read();
        if (ch < 0)
            throw new EOFException();
        return ch;
    }

    @Override
    public final int readUnsignedShort() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (ch1 << 8) + (ch2);
    }

    @Override
    public final int skipBytes(int n) throws IOException {
        int total = 0;
        int cur;

        while ((total < n) && ((cur = (int) in.skip(n - total)) > 0)) {
            total += cur;
        }
        return total;
    }

    @NotNull
    @Override
    public final DataPipeStream writeBoolean(boolean v) throws IOException {
        out.write(v ? 1 : 0);
        return this;
    }

    @NotNull
    @Override
    public final DataPipeStream writeByte(int v) throws IOException {
        out.write(v);
        return this;
    }

    @NotNull
    @Override
    public DataPipe writeBytes(@NotNull byte[] b) throws IOException {
        out.write(b);
        return this;
    }

    @NotNull
    @Override
    public synchronized DataPipeStream writeBytes(@NotNull byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        return this;
    }

    @NotNull
    @Override
    public final DataPipeStream writeBytes(String s) throws IOException {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            out.write((byte) s.charAt(i));
        }
        return this;
    }

    @NotNull
    @Override
    public final DataPipeStream writeChar(int v) throws IOException {
        out.write((v >>> 8) & 0xFF);
        out.write((v) & 0xFF);
        return this;
    }

    @NotNull
    @Override
    public final DataPipeStream writeChars(String s) throws IOException {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int v = s.charAt(i);
            out.write((v >>> 8) & 0xFF);
            out.write((v) & 0xFF);
        }
        return this;
    }

    @NotNull
    @Override
    public final DataPipeStream writeDouble(double v) throws IOException {
        return writeLong(Double.doubleToLongBits(v));
    }

    @NotNull
    @Override
    public final DataPipeStream writeFloat(float v) throws IOException {
        return writeInt(Float.floatToIntBits(v));
    }

    @NotNull
    @Override
    public final DataPipeStream writeInt(int v) throws IOException {
        out.write((v >>> 24) & 0xFF);
        out.write((v >>> 16) & 0xFF);
        out.write((v >>> 8) & 0xFF);
        out.write((v) & 0xFF);
        return this;
    }

    @NotNull
    @Override
    public final DataPipeStream writeLong(long v) throws IOException {
        writeBuffer[0] = (byte) (v >>> 56);
        writeBuffer[1] = (byte) (v >>> 48);
        writeBuffer[2] = (byte) (v >>> 40);
        writeBuffer[3] = (byte) (v >>> 32);
        writeBuffer[4] = (byte) (v >>> 24);
        writeBuffer[5] = (byte) (v >>> 16);
        writeBuffer[6] = (byte) (v >>> 8);
        writeBuffer[7] = (byte) (v);
        out.write(writeBuffer, 0, 8);
        return this;
    }

    @NotNull
    @Override
    public final DataPipeStream writeShort(int v) throws IOException {
        out.write((v >>> 8) & 0xFF);
        out.write((v) & 0xFF);
        return this;
    }

    @NotNull
    @Override
    public final DataPipeStream writeString(@NotNull String str) throws IOException {
        _writeString(str);
        return this;
    }

    @Override
    public void close() throws IOException {
        try {
            in.close();
        } finally {
            out.close();
        }
    }

    private String _readString() throws IOException {
        int utflen = readUnsignedShort();
        if (bytearr.length < utflen) {
            bytearr = new byte[utflen * 2];
            chararr = new char[utflen * 2];
        }

        int c, char2, char3;
        int count = 0;
        int chararr_count = 0;

        readFully(bytearr, 0, utflen);

        while (count < utflen) {
            c = (int) bytearr[count] & 0xff;
            if (c > 127) break;
            count++;
            chararr[chararr_count++] = (char) c;
        }

        while (count < utflen) {
            c = (int) bytearr[count] & 0xff;
            switch (c >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:

                    count++;
                    chararr[chararr_count++] = (char) c;
                    break;
                case 12:
                case 13:

                    count += 2;
                    if (count > utflen)
                        throw new UTFDataFormatException(
                            "malformed : partial character at end");
                    char2 = (int) bytearr[count - 1];
                    if ((char2 & 0xC0) != 0x80)
                        throw new UTFDataFormatException(
                            "malformed  around byte " + count);
                    chararr[chararr_count++] = (char) (((c & 0x1F) << 6) |
                        (char2 & 0x3F));
                    break;
                case 14:

                    count += 3;
                    if (count > utflen)
                        throw new UTFDataFormatException(
                            "malformed : partial character at end");
                    char2 = (int) bytearr[count - 2];
                    char3 = (int) bytearr[count - 1];
                    if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
                        throw new UTFDataFormatException(
                            "malformed  around byte " + (count - 1));
                    chararr[chararr_count++] = (char) (((c & 0x0F) << 12) |
                        ((char2 & 0x3F) << 6) |
                        ((char3 & 0x3F)));
                    break;
                default:

                    throw new UTFDataFormatException(
                        "malformed  around byte " + count);
            }
        }
        // The number of chars produced may be less than utflen
        return new String(chararr, 0, chararr_count);
    }

    private void _writeString(String str) throws IOException {
        int strlen = str.length();
        int utflen = 0;
        int c, count = 0;

        for (int i = 0; i < strlen; i++) {
            c = str.charAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                utflen++;
            } else if (c > 0x07FF) {
                utflen += 3;
            } else {
                utflen += 2;
            }
        }

        if (utflen > 65535)
            throw new UTFDataFormatException(
                "encoded string too long: " + utflen + " bytes");

        byte[] bytearr;
        bytearr = new byte[utflen + 2];

        bytearr[count++] = (byte) ((utflen >>> 8) & 0xFF);
        bytearr[count++] = (byte) ((utflen) & 0xFF);

        int i;
        for (i = 0; i < strlen; i++) {
            c = str.charAt(i);
            if (!((c >= 0x0001) && (c <= 0x007F))) break;
            bytearr[count++] = (byte) c;
        }

        for (; i < strlen; i++) {
            c = str.charAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                bytearr[count++] = (byte) c;

            } else if (c > 0x07FF) {
                bytearr[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
                bytearr[count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
                bytearr[count++] = (byte) (0x80 | ((c) & 0x3F));
            } else {
                bytearr[count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
                bytearr[count++] = (byte) (0x80 | ((c) & 0x3F));
            }
        }
        writeBytes(bytearr, 0, utflen + 2);
    }
}
