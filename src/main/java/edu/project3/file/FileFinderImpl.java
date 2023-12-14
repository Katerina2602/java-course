package edu.project3.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.net.http.HttpResponse.BodyHandlers.ofInputStream;

public class FileFinderImpl implements FileFinder {
    private static final String HTTP_PATH_PREFIX = "http";
    private static final String TEMP_FILE_PREFIX = "nginx-downloaded-log-";
    private static final int ONE_HUNDRED = 100;
    private final HttpClient httpClient;

    public FileFinderImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override public List<Path> find(String path) {
        if (path.startsWith(HTTP_PATH_PREFIX)) {
            return downloadFile(path);
        } else {
            return findFiles(path);
        }
    }

    private List<Path> downloadFile(String uri) {
        HttpResponse<InputStream> response = doGetRequest(uri);
        validateResponse(response);
        Path downloadFile = saveToTempFile(response.body());

        return Collections.singletonList(downloadFile);
    }

    private List<Path> findFiles(String pattern) {
        ArrayList<Path> foundFiles = new ArrayList<>();

        FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                FileSystem fs = FileSystems.getDefault();
                PathMatcher matcher = fs.getPathMatcher("glob:" + pattern);

                if (matcher.matches(file.toAbsolutePath())) {
                    foundFiles.add(file);
                }

                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(getBaseDir(pattern), matcherVisitor);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to find files for path - " + pattern, ex);
        }

        return foundFiles;
    }

    private static Path getBaseDir(String path) {
        int firstWildcardIndex = path.indexOf("*");

        if (firstWildcardIndex == -1) {
            return Path.of(path);
        }

        int lastSlashIndex = path.lastIndexOf("/", firstWildcardIndex);

        return Path.of(path.substring(0, lastSlashIndex));
    }

    private HttpResponse<InputStream> doGetRequest(String uri) {
        try {
            return httpClient.send(
                buildGetRequest(uri),
                ofInputStream()
            );
        } catch (Exception ex) {
            throw new RuntimeException("Failed to invoke uri - " + uri, ex);
        }
    }

    private static HttpRequest buildGetRequest(String uri) {
        return HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(uri))
            .build();
    }

    private void validateResponse(HttpResponse<InputStream> response) {
        if (response.statusCode() / ONE_HUNDRED != 2) {
            throw new IllegalArgumentException(
                "Failed to parse response from uri - %s, response - %s"
                    .formatted(response.uri(), readInputStreamAsString(response.body()))
            );
        }
    }

    private String readInputStreamAsString(InputStream inputStream) {
        try {
            return new String(inputStream.readAllBytes());
        } catch (IOException ex) {
            throw new RuntimeException("Failed convert InputStream body to String", ex);
        }
    }

    private Path saveToTempFile(InputStream inputStream) {
        try {
            Path tempFile = Files.createTempFile(TEMP_FILE_PREFIX, ".txt");

            try (FileOutputStream fos = new FileOutputStream(tempFile.toFile())) {
                try (InputStream is = inputStream) {
                    fos.write(is.readAllBytes());
                }
            }

            return tempFile;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save file", ex);
        }
    }
}
