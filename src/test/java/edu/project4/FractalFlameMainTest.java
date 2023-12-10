package edu.project4;

import edu.project4.gamma.GammaCorrection;
import edu.project4.save.ImageUtils;
import edu.project4.transformation.affine.AffineTransformation;
import edu.project4.transformation.affine.RandomCoefficientAffine;
import edu.project4.transformation.nonlinear.Disk;
import edu.project4.transformation.nonlinear.Heart;
import edu.project4.transformation.nonlinear.Horseshoe;
import edu.project4.transformation.nonlinear.NonLinearTransformation;
import edu.project4.transformation.nonlinear.Polar;
import edu.project4.transformation.nonlinear.Sinusoidal;
import edu.project4.transformation.nonlinear.Spherical;
import edu.project4.transformation.nonlinear.Swirl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.*;

class FractalFlameMainTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int WIDTH_SIZE = 100;
    private static final int HEIGHT_SIZE = 100;
    private static final int AFFINE_TRANSFORMATION_COUNT = 10;
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int SAMPLES = 1_000;
    private static final short ITERATION_PER_SAMPLE = 10;
    private static final double X_MIN = -1.777;
    private static final double X_MAX = 1.777;
    private static final double Y_MIN = -1;
    private static final double Y_MAX = 1;

    @Test
    void willRenderAndSaveFractalFlame() {
        FractalImage fractalImage = FractalImage.create(WIDTH_SIZE, HEIGHT_SIZE);

        List<AffineTransformation> affineTransformations = new ArrayList<>();
        for (int i = 0; i < AFFINE_TRANSFORMATION_COUNT; i++) {
            affineTransformations.add(new RandomCoefficientAffine());
        }

        List<NonLinearTransformation> nonlinearTransformations = List.of(
            new Sinusoidal(),
            new Disk(),
            new Swirl(),
            new Horseshoe(),
            new Heart(),
            new Spherical(),
            new Polar()
        );

        Rectangle rectangle = new Rectangle(X_MIN, X_MAX, Y_MIN, Y_MAX);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        Renderer renderer = new RendererImpl(THREAD_COUNT, executorService);

        LOGGER.info("THREADS COUNT: {}", THREAD_COUNT);

        Instant start = Instant.now();
        FractalImage image = renderer.render(
            fractalImage,
            rectangle,
            affineTransformations,
            nonlinearTransformations,
            SAMPLES,
            ITERATION_PER_SAMPLE
        );
        LOGGER.info("RENDER TIME: {} second(s)", Duration.between(start, Instant.now()).toSeconds());

        executorService.shutdown();

        GammaCorrection gammaCorrection = new GammaCorrection();
        gammaCorrection.process(image);

        ImageUtils.save(
            image,
            Path.of("src/test/resources/project4/flame-%s.jpeg".formatted(Instant.now().toEpochMilli()))
        );
    }
}
