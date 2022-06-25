import org.rocksdb.*;

public class ParseRocksdbPolicy {
  private static RocksDB rocksDB;

  static {
    RocksDB.loadLibrary();
  }

  public static void main(String[] args) throws Exception {
    Options options = new Options();
    options.setCreateIfMissing(true);
    rocksDB = RocksDB.open(options, args[0]);
    RocksIterator rocksIterator = rocksDB.newIterator();
    for (rocksIterator.seekToFirst(); rocksIterator.isValid(); rocksIterator.next()) {
      System.out.println("key:" + ObjectAndByteArray.deserialize(rocksIterator.key()) + ",value:" + ObjectAndByteArray.deserialize(rocksIterator.value()));
    }
  }
}

