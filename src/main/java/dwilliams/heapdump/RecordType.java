package dwilliams.heapdump;

public enum RecordType {
    StringInUTF8(0x01),
    LoadClass(0x02),
    UnloadClass(0x03),
    StackFrame(0x04),
    StackTrace(0x05),
    AllocSites(0x06),
    HeapSummary(0x07),
    StartThread(0x0A),
    EndThread(0x0B),
    HeapDump(0x0C),
    HeapDumpSegment(0x1C),
    HeapDumpEnd(0x2C),
    CpuSamples(0x0D),
    ControlSettings(0x0E);

    private final byte tag;

    RecordType(int tag) {
        this.tag = (byte)tag;
    }

    public byte getTag() {
        return tag;
    }
}
