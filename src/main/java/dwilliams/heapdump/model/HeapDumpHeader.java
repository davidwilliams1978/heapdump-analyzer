package dwilliams.heapdump.model;


public class HeapDumpHeader {
    private final String format;
    private final int identifierSize;
    private final long baseMillisecondsSinceEpoch;

    public HeapDumpHeader(String format, int identifierSize, long baseMillisecondsSinceEpoch) {
        this.format = format;
        this.identifierSize = identifierSize;
        this.baseMillisecondsSinceEpoch = baseMillisecondsSinceEpoch;
    }

    public String getFormat() {
        return format;
    }

    public int getIdentifierSize() {
        return identifierSize;
    }

    public long getBaseMillisecondsSinceEpoch() {
        return baseMillisecondsSinceEpoch;
    }
}
