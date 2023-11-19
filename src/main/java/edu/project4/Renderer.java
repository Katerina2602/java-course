package edu.project4;

import edu.project4.transformation.affine.AffineTransformation;
import edu.project4.transformation.nonlinear.NonLinearTransformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rectangle world,
        List<AffineTransformation> affineVariations,
        List<NonLinearTransformation> nonlinearVariations,
        int samples,
        short iterPerSample
    );
}
