package dwilliams.heapdump.io;

public interface ByteStreamProvider {
    byte getByte() throws Exception;
    byte[] getBytes(int count) throws Exception;
    byte[] getBytesUntil(byte endByte) throws Exception;
}
