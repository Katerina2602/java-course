package edu.hw2.task2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ExtensionTest {
    @Test
    void calculateArea() {
        Rectangle square = new Square(10);
        Rectangle rectangle = new Rectangle(10, 20);

        assertThat(square.area()).isEqualTo(100);
        assertThat(rectangle.area()).isEqualTo(200);
    }
}
