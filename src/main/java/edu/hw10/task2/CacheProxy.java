package edu.hw10.task2;

import edu.hw10.task2.annotation.Cache;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CacheProxy {
    private CacheProxy() {
    }

    public static <T> T create(T instance, Class<T> type) {
        return (T) Proxy.newProxyInstance(
            type.getClassLoader(),
            new Class<?>[] {type},
            new CacheHandler(instance)
        );
    }

    private static class CacheHandler implements InvocationHandler {
        private final Map<String, Object> inMemoryCache = new HashMap<>();
        private final Path cachePath;
        private final Object target;

        CacheHandler(Object target) {
            this.target = target;
            try {
                this.cachePath = Files.createTempFile("cache-", String.valueOf(Instant.now().getEpochSecond()));
            } catch (IOException ex) {
                throw new RuntimeException("Failed create cache file");
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Optional<Boolean> cacheAnnotation = findCacheAnnotation(method);

            if (cacheAnnotation.isPresent()) {
                String key = getKey(method, args);

                if (cacheAnnotation.get()) {
                    return findValueInFileOrCreate(key, method, args);
                } else {
                    return findValueInMemoryOrCreate(key, method, args);
                }
            }

            return method.invoke(target, args);
        }

        private Optional<Boolean> findCacheAnnotation(Method method) {
            return Arrays.stream(method.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType() == Cache.class)
                .findFirst()
                .map(Cache.class::cast)
                .map(Cache::persist);
        }

        private String getKey(Method method, Object[] args) {
            return "%s|%s|%s".formatted(
                method.getName(),
                Arrays.toString(method.getParameters()),
                Arrays.toString(args)
            );
        }

        private Object findValueInFileOrCreate(String key, Method method, Object[] args) throws Exception {
            String value = getFromFile(key);

            if (value != null) {
                Class<?> returnType = method.getReturnType();

                if (returnType == String.class) {
                    return value;
                } else if (returnType == Integer.class || returnType == int.class) {
                    return Integer.parseInt(value);
                } else if (returnType == Long.class || returnType == long.class) {
                    return Long.parseLong(value);
                } else {
                    throw new UnsupportedOperationException("Can not deserialize value from file");
                }
            } else {
                Object result = method.invoke(target, args);
                putToFile(key, result);
                return result;
            }
        }

        private Object findValueInMemoryOrCreate(String key, Method method, Object[] args) throws Exception {
            Object value = inMemoryCache.get(key);

            if (value != null) {
                return value;
            } else {
                Object result = method.invoke(target, args);
                inMemoryCache.put(key, result);
                return result;
            }
        }

        private String getFromFile(String key) {
            try {
                return Files.readAllLines(cachePath)
                    .stream()
                    .map(entry -> entry.split(":"))
                    .filter(entry -> entry[0].equals(key))
                    .findFirst()
                    .map(entry -> entry[1])
                    .orElse(null);
            } catch (IOException ex) {
                throw new RuntimeException("Failed get value from file");
            }
        }

        private void putToFile(String key, Object value) {
            try {
                Files.writeString(cachePath, "%s:%s".formatted(key, value));
            } catch (IOException ex) {
                throw new RuntimeException("Failed put value to file");
            }
        }
    }
}
