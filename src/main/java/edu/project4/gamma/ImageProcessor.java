package edu.project4.gamma;

import edu.project4.FractalImage;

// пост-обработка in-place, например, гамма-коррекция
@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
