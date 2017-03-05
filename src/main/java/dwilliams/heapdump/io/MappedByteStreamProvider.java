package dwilliams.heapdump.io;

import com.google.common.primitives.Bytes;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class MappedByteStreamProvider implements ByteStreamProvider {
    private final long bufferSize;
    private final long dumpSize;

    private RandomAccessFile hprofFile;
    private FileChannel inChannel;
    private MappedByteBuffer buffer;
    private long positionInDump = 0;
    private long positionInBuffer = -1;

    public MappedByteStreamProvider(long bufferSize, String hprofFilePath) throws Exception {
        this.bufferSize = bufferSize;
        this.hprofFile = new RandomAccessFile(hprofFilePath, "r");
        this.inChannel = hprofFile.getChannel();
        this.dumpSize = inChannel.size();
    }

    @Override
    public byte getByte() throws Exception {
        return getNextByte();
    }

    @Override
    public byte[] getBytes(int count) throws Exception {
        byte[] bytes = new byte[count];

        for(int positionInByteArray = 0; positionInByteArray < count; positionInByteArray++) {
            bytes[positionInByteArray] = getNextByte();
        }

        return bytes;
    }

    @Override
    public byte[] getBytesUntil(byte endByte) throws Exception {
        List<Byte> byteList = new ArrayList<>();
        while(true) {
            byte nextByte = getNextByte();
            byteList.add(nextByte);
            if(nextByte == endByte) {
                break;
            }
        }

        byte[] bytes = Bytes.toArray(byteList);

        return bytes;
    }

    private byte getNextByte() throws Exception {
        if(buffer == null || positionInBuffer == buffer.limit()) {
            loadBuffer();
        }

        byte nextByte = buffer.get();
        positionInBuffer++;

        return nextByte;
    }

    private void loadBuffer() throws Exception {
        long bytesToLoad = Math.min(bufferSize, dumpSize - positionInDump);
        buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, positionInDump, bytesToLoad);
        positionInDump += bytesToLoad;
        positionInBuffer = 0;
    }

    public void close() throws IOException {
        hprofFile.close();
    }
}
