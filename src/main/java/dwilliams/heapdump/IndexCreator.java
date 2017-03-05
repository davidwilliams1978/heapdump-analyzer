package dwilliams.heapdump;

import dwilliams.heapdump.model.HeapDumpHeader;
import dwilliams.heapdump.io.ByteStreamProvider;
import dwilliams.heapdump.reader.DumpReader;
import dwilliams.heapdump.io.MappedByteStreamProvider;

import java.util.concurrent.TimeUnit;

public class IndexCreator {
    private static final long BUFFER_SIZE = 8092;

    private final String hprofFilePath;

    public IndexCreator(String hprofFilePath) {
        this.hprofFilePath = hprofFilePath;
    }

    public void extractIndexes() throws Exception {
        long startTime = System.nanoTime();

        ByteStreamProvider streamProvider = new MappedByteStreamProvider(BUFFER_SIZE, hprofFilePath);
        DumpReader reader = new DumpReader(streamProvider);
        HeapDumpHeader header = reader.initializeAndReadHeader();

        long endTime = System.nanoTime();

        long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
        System.out.println("Total elapsed time: " + elapsedTimeInMillis + " ms");

    }

    public static final void main(String[] args) {
        String hprofFilePath = args[0];

        IndexCreator extractor = new IndexCreator(hprofFilePath);

        try {
            extractor.extractIndexes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
