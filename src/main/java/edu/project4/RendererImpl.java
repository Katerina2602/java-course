package edu.project4;

import edu.project4.transformation.affine.AffineTransformation;
import edu.project4.transformation.nonlinear.NonLinearTransformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class RendererImpl implements Renderer {
    private static final Random RANDOM = new Random();
    private final int threadCount;
    private final ExecutorService executorService;

    public RendererImpl(int threadCount, ExecutorService executorService) {
        this.threadCount = threadCount;
        this.executorService = executorService;
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rectangle world,
        List<AffineTransformation> affineVariations,
        List<NonLinearTransformation> nonlinearVariations,
        int samples,
        short iterPerSample
    ) {
        ArrayList<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(
                CompletableFuture.runAsync(
                    () -> generateFractalFlame(
                        canvas,
                        world,
                        affineVariations,
                        nonlinearVariations,
                        samples,
                        iterPerSample
                    ),
                    executorService
                )
            );
        }

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        return canvas;
    }

    private void generateFractalFlame(
        FractalImage canvas,
        Rectangle world,
        List<AffineTransformation> affineVariations,
        List<NonLinearTransformation> nonlinearVariations,
        int samples,
        short iterPerSample
    ) {
        for (int i = 0; i < samples / threadCount; i++) {
            Point startPoint = world.random();
            AffineTransformation affineTransformation = randomAffine(affineVariations);
            NonLinearTransformation nonLinearTransformation = randomNonlinear(nonlinearVariations);
            Point newPoint = transform(startPoint, affineTransformation, nonLinearTransformation);
            Point pixel;

            for (short step = 0; step < iterPerSample; ++step) {
                newPoint = transform(newPoint, affineTransformation, nonLinearTransformation);
                pixel = pointToPixel(newPoint, world, canvas.width(), canvas.height());

                if (canvas.contains((int) pixel.x(), (int) pixel.y())) {
                    Pixel currentPixel = canvas.pixel((int) pixel.x(), (int) pixel.y());
                    updatePixel(currentPixel, affineTransformation);
                }
            }
        }
    }

    private void updatePixel(Pixel pixel, AffineTransformation affineTransformation) {
        synchronized (pixel) {
            if (pixel.getHitCount() == 0) {
                pixel.setR(affineTransformation.getRed());
                pixel.setG(affineTransformation.getGreen());
                pixel.setB(affineTransformation.getBlue());
            } else {
                pixel.setR((pixel.getR() + affineTransformation.getRed()) / 2);
                pixel.setG((pixel.getG() + affineTransformation.getGreen()) / 2);
                pixel.setB((pixel.getB() + affineTransformation.getBlue()) / 2);
            }
            pixel.setHitCount(pixel.getHitCount() + 1);
        }
    }

    private Point transform(
        Point point,
        AffineTransformation affineTransformation,
        NonLinearTransformation nonlinearTransformation
    ) {
        return affineTransformation.andThen(nonlinearTransformation).apply(point);
    }

    private Point pointToPixel(Point point, Rectangle rectangle, int weight, int height) {
        return new Point(
            height - Math.round(((rectangle.yMax() - point.y()) / (rectangle.yMax() - rectangle.yMin())) * height),
            weight - Math.round(((rectangle.xMax() - point.x()) / (rectangle.xMax() - rectangle.xMin())) * weight)
        );
    }

    private AffineTransformation randomAffine(List<AffineTransformation> transformations) {
        return transformations.get(RANDOM.nextInt(0, transformations.size()));
    }

    private NonLinearTransformation randomNonlinear(List<NonLinearTransformation> transformations) {
        return transformations.get(RANDOM.nextInt(transformations.size()));
    }
}
