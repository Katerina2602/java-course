package edu.project3.file;

import java.io.IOException;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileFinderImplTest {
    private final HttpClient httpClient = mock(HttpClient.class);
    private final FileFinder fileFinder = new FileFinderImpl(httpClient);

    @Test
    void willSuccessDownloadFileWhenPathStartsWithHttp() throws IOException, InterruptedException {
        String path = "https://logs/1";
        String responseContent = "test content";

        when(httpClient.send(any(), any()))
            .thenReturn(new SuccessHttpResponse(responseContent));

        List<Path> files = fileFinder.find(path);

        assertEquals(1, files.size());
        assertEquals(responseContent, Files.readString(files.get(0)));
    }

    @Test
    void willThrowsExceptionWhenDownloadFileWithResponseNot200() throws IOException, InterruptedException {
        String path = "https://logs/1";

        when(httpClient.send(any(), any()))
            .thenReturn(new ErrorHttpResponse());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> fileFinder.find(path));
        assertTrue(exception.getMessage().contains("Failed to parse response from uri"));
    }

    @Test
    void willSuccessFindFileOnFileSystem() throws IOException {
        Path tempDirectory = Files.createTempDirectory("nginx-files");
        Files.createTempFile(tempDirectory, "log-", ".txt");
        Files.createTempFile(tempDirectory, "log-", ".txt");

        String path = tempDirectory.toAbsolutePath() + "/*.txt";

        List<Path> files = fileFinder.find(path);

        assertEquals(2, files.size());
    }

    @Test
    void willReturnEmptyListWhenFilesNotFound() throws IOException {
        Path tempDirectory = Files.createTempDirectory("nginx-files");
        Files.createTempFile(tempDirectory, "log-", ".png");

        String path = tempDirectory.toAbsolutePath() + "/*.txt";

        List<Path> files = fileFinder.find(path);

        assertTrue(files.isEmpty());
    }
}
