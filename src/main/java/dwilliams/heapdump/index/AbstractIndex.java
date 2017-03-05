package dwilliams.heapdump.index;

public abstract class AbstractIndex implements Index {

    private final long startPosition;
    private final long length;

    AbstractIndex(long startPosition, long length) {
        this.startPosition = startPosition;
        this.length = length;
    }

    @Override
    public long getStartPosition() {
        return startPosition;
    }

    @Override
    public long getLength() {
        return length;
    }
}
