package dwilliams.heapdump.reader;

import dwilliams.heapdump.io.ByteStreamProvider;
import dwilliams.heapdump.model.HeapDumpHeader;
import dwilliams.heapdump.model.ID;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class DumpReader {

    private final ByteStreamProvider provider;
    private int identifierSize;
    private long baseMillisecondsSinceEpoch;

    public DumpReader(ByteStreamProvider provider) {
        this.provider = provider;
    }

    public HeapDumpHeader initializeAndReadHeader() throws Exception {
        String format = readNullTerminatedString();
        int identifierSize = readInt();
        long baseMillisecondsSinceEpoch = readLong();

        HeapDumpHeader header = new HeapDumpHeader(format, identifierSize, baseMillisecondsSinceEpoch);
        this.identifierSize = header.getIdentifierSize();
        this.baseMillisecondsSinceEpoch = header.getBaseMillisecondsSinceEpoch();

        return header;
    }

    public byte readByte() throws Exception {
        return provider.getByte();
    }

    public int readInt() throws Exception {
        byte[] bytes = provider.getBytes(4);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        return buffer.getInt();
    }

    public long readLong() throws Exception {
        byte[] bytes = provider.getBytes(8);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        return buffer.getLong();
    }

    public ID readID() {
        return null;
    }

    public String readNullTerminatedString() throws Exception {
        byte[] bytes = provider.getBytesUntil((byte)0);

        return new String(bytes, 0, bytes.length - 1, Charset.forName("UTF-8"));
    }
}
