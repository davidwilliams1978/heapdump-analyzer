package dwilliams.heapdump.handler;

import dwilliams.heapdump.RecordType;
import dwilliams.heapdump.index.Index;
import dwilliams.heapdump.model.Record;
import dwilliams.heapdump.reader.DumpReader;

public interface RecordHandler<R extends Record, I extends Index> extends DataHandler<R, I> {
    RecordType getRecordType();

    default R readData(I index) {
        return null;
    }

    default I createIndex(DumpReader reader) {
        return null;
    }
}
