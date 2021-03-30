package me.weldnor;

import me.weldnor.domain.Matrix;
import me.weldnor.utils.MatrixUtils;

public class App {
    public static void main(String[] args) {
        Matrix first = new Matrix(new long[][]{{1, 2}, {3, 4}});
        Matrix second = new Matrix(new long[][]{{5, 6}, {7, 8}});
        System.out.println(first);
        System.out.println(second);
        System.out.println(MatrixUtils.multiply(first, second));
    }
}
