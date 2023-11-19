package edu.project4.gamma;

import edu.project4.FractalImage;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class GammaCorrection implements ImageProcessor {
    private static final double GAMMA = 2.2;

    @Override
    public void process(FractalImage image) {
        int xRes = image.height();
        int yRes = image.width();
        double max = 0.0;

        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                if (image.data()[row][col].getHitCount() != 0) {
                    image.data()[row][col].setNormal(log10(image.data()[row][col].getHitCount()));
                    if (image.data()[row][col].getNormal() > max) {
                        max = image.data()[row][col].getNormal();
                    }
                }
            }
        }
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                image.data()[row][col].setNormal(image.data()[row][col].getNormal() / max);
                image.data()[row][col].setR((int) Math.round(
                    image.data()[row][col].getR() * pow(image.data()[row][col].getNormal(), (1.0 / GAMMA))));
                image.data()[row][col].setG((int) Math.round(
                    image.data()[row][col].getG() * pow(image.data()[row][col].getNormal(), (1.0 / GAMMA))));
                image.data()[row][col].setB((int) Math.round(
                    image.data()[row][col].getB() * pow(image.data()[row][col].getNormal(), (1.0 / GAMMA))));
            }
        }
    }
}
