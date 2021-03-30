package me.weldnor.utils;


import me.weldnor.domain.Matrix;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MatrixUtilsTest {

    @Test
    public void multiply() {
        Matrix first = new Matrix(new long[][]{{1, 2}, {3, 4}});
        Matrix second = new Matrix(new long[][]{{5, 6, 7}, {8, 9, 10}});

        Matrix result = MatrixUtils.multiply(first, second);

        assertThat(result.toArray()).isEqualTo(new long[][]{{21, 24, 27}, {47, 54, 61}});
    }

    @Test
    public void multiply_illegalArguments() {
        Matrix first = new Matrix(new long[][]{{1, 2}, {3, 4}});
        Matrix second = new Matrix(new long[][]{{5, 6, 7}, {8, 9, 10}, {11, 12, 13}});

        assertThatThrownBy(() -> MatrixUtils.multiply(first, second))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
