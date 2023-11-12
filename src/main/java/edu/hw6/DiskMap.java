package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final Path path;

    public DiskMap(String path) {
        this.path = Path.of(path);
    }

    @Override
    public int size() {
        try {
            return Files.readAllLines(path).size();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to get size", ex);
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        if (Files.notExists(path)) {
            return null;
        }

        try {
            return Files.readAllLines(path)
                .stream()
                .map(entry -> entry.split(":"))
                .filter(entry -> entry[0].equals(key))
                .findFirst()
                .map(entry -> entry[1])
                .orElse(null);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to get value", ex);
        }
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        try {
            checkFileExistsAndCreateIfNeed();

            String oldValue = get(key);

            Files.writeString(path, "%s:%s".formatted(key, value));

            return oldValue;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to put entry", ex);
        }
    }

    @Override
    public String remove(Object key) {
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {

    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return null;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return null;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return null;
    }

    private void checkFileExistsAndCreateIfNeed() throws IOException {
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
    }
}
