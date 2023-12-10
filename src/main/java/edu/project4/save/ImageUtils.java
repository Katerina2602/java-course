package edu.project4.save;

import edu.project4.FractalImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Color color = new Color(
                    image.data()[y][x].getR(),
                    image.data()[y][x].getG(),
                    image.data()[y][x].getB()
                );
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }

        try {
            ImageIO.write(bufferedImage, "jpeg", filename.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }
}
