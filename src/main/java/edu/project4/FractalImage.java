package edu.project4;

public record FractalImage(Pixel[][] data, int width, int height) {

    public static FractalImage create(int width, int height) {
        Pixel[][] pixels = new Pixel[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = new Pixel(0, 0, 0);
            }
        }
        return new FractalImage(pixels, width, height);
    }

    boolean contains(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < width;
    }

    Pixel pixel(int x, int y) {
        if (contains(x, y)) {
            return data[x][y];
        } else {
            return null;
        }
    }
}
