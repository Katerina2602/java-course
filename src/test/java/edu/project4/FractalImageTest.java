package edu.project4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class FractalImageTest {
    @Test
    void willCorrectCreateInitialFractalImage() {
        int width = 3;
        int height = 2;

        FractalImage image = FractalImage.create(width, height);

        assertEquals(width, image.width());
        assertEquals(height, image.height());

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Pixel pixel = image.data()[i][j];
                assertEquals(0, pixel.getR());
                assertEquals(0, pixel.getG());
                assertEquals(0, pixel.getB());
                assertEquals(0, pixel.getNormal());
                assertEquals(0, pixel.getHitCount());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("provideContainsPixels")
    void willImageContainsPixel(int x, int y) {
        FractalImage image = FractalImage.create(3, 2);

        assertTrue(image.contains(x, y));
    }

    @ParameterizedTest
    @MethodSource("provideNotContainsPixels")
    void willImageNotContainsPixel(int x, int y) {
        FractalImage image = FractalImage.create(3, 2);

        assertFalse(image.contains(x, y));
    }

    private static Stream<Arguments> provideContainsPixels() {
        return Stream.of(
            Arguments.of(0, 0),
            Arguments.of(1, 2),
            Arguments.of(1, 1)
        );
    }

    private static Stream<Arguments> provideNotContainsPixels() {
        return Stream.of(
            Arguments.of(2, 1),
            Arguments.of(-1, 0),
            Arguments.of(0, -1),
            Arguments.of(2, 2)
        );
    }
}
