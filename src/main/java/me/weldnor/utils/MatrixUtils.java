package me.weldnor.utils;

import lombok.extern.java.Log;
import me.weldnor.domain.Matrix;

@Log
public final class MatrixUtils {
    private MatrixUtils() {

    }

    public static Matrix multiply(Matrix first, Matrix second, int threadCount) {

        if (first.getWidth() != second.getHeight()) {
            throw new IllegalArgumentException();
        }

        Matrix result = new Matrix(first.getHeight(), second.getWidth());

        TaskExecutor executor = new TaskExecutor(threadCount);

        for (int i = 0; i < first.getHeight(); i++) {
            for (int j = 0; j < second.getWidth(); j++) {
                executor.add(createMultiplyTask(first, second, result, i, j));
            }
        }
        executor.stop();
        return result;
    }

    public static Matrix multiply(Matrix first, Matrix second) {
        return multiply(first, second, 2);
    }

    private static Runnable createMultiplyTask(Matrix first, Matrix second, Matrix result, int x, int y) {
        return () -> {
            int sum = 0;
            for (int i = 0; i < first.getWidth(); i++) {
                sum += first.get(x, i) * second.get(i, y);
            }

            result.set(x, y, sum);
            log.info("task " + x + " " + y + " calculated");
        };
    }
}
