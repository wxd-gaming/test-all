package luajava;

import java.io.Closeable;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;

public interface ILuaContext extends Closeable, AutoCloseable {
    String load(Path filePath, byte[] bytes);

    String load(String fileName, byte[] bytes);

    boolean has(String name);

    Object call(boolean xpcall, String key, Object... args);

    void memory(AtomicLong memory);

    void gc();

    @Override void close();

    boolean isClosed();

    String getName();
}
