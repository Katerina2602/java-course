package edu.project4.save;

import edu.project4.FractalImage;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class ImageUtilsTest {
    @Test
    void willSaveFractalImage() throws IOException {
        FractalImage image = FractalImage.create(2, 3);
        Path directory = Files.createTempDirectory("tmp-images");
        Path path = directory.resolve("test-flame-image.jpg");

        assertFalse(Files.exists(path));

        ImageUtils.save(image, path);

        assertTrue(Files.exists(path));
    }
}
